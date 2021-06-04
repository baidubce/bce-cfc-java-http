package com.baidubce.cfc.core.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

public class CfcDefaultHandler extends HttpServlet {
    private HttpRequestHandler handler;
    public ClientConfig clientConfig;

    static final String XCfcRequestID = "X-Cfc-RequestId";
    static final String XCfcApiID = "X-Cfc-ApiID";
    static final String XCfcClientIP = "X-Cfc-ClientIP";
    static final String XCfcClientContext = "X-Cfc-ClientContext";
    static final String XCfcPathParameters = "X-Cfc-PathParameters";
    static final String XCfcAccessKeyID = "X-Cfc-AccessKeyID";
    static final String XCfcAccessKeySecret = "X-Cfc-AccessKeySecret";
    static final String XCfcSecurityToken = "X-Cfc-SecurityToken";

    @Override
    public void init() throws ServletException {
        super.init();
        clientConfig = ClientConfig.getInstance();
        try {
            handler = getRequestHandler();
        } catch (Exception ex) {
            throw new ServletException("init failed.", ex);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContextImpl context = new ContextImpl();
        context.setRequestId(req.getHeader(XCfcRequestID));
        context.setFunctionBrn(ClientConfig.getInstance().getFunctionBrn());
        context.setMemoryLimit(ClientConfig.getInstance().getFunctionMemory());
        context.setFunctionName(ClientConfig.getInstance().getFunctionName());
        context.setFunctionVersion(ClientConfig.getInstance().getFunctionVersion());
        context.setApiID(req.getHeader(XCfcApiID));
        context.setClientIP(req.getHeader(XCfcClientIP));

        String header = req.getHeader(XCfcClientContext);
        if (header != null) {
            context.setClientContext(new String(Base64.getDecoder().decode(header)));
        }
        header = req.getHeader(XCfcPathParameters);
        if (header != null) {
            ObjectMapper mapper = new ObjectMapper();
            Object obj = mapper.readValue(Base64.getDecoder().decode(header),
                    new TypeReference<HashMap<String, String>>(){});
            context.setPathParams((HashMap<String, String>)obj);
        }
        header = req.getHeader(XCfcAccessKeyID);
        if (header != null) {
            StsCredential credential = new StsCredential();
            credential.setAccessKeyId(header);
            credential.setSecretAccessKey(req.getHeader(XCfcAccessKeySecret));
            credential.setSessionToken(req.getHeader(XCfcSecurityToken));
            context.setCredential(credential);
        }

        handler.handleRequest(req, resp, context);
        flushStd();
    }

    protected HttpRequestHandler getRequestHandler() throws Exception {
        Class handlerClass = Class.forName(ClientConfig.getInstance().getFunctionHandler());
        if (HttpRequestHandler.class.isAssignableFrom(handlerClass)) {
            return (HttpRequestHandler)handlerClass.newInstance();
        }
        throw new Exception(String.format("Class %s does not implement HttpRequestHandler interface.",
                ClientConfig.getInstance().getFunctionHandler()));
    }

    private void flushStd() {
        System.out.append('\0');
        System.err.append('\0');
        System.out.flush();
        System.err.flush();
    }
}
