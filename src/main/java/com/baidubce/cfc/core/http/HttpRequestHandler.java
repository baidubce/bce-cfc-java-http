package com.baidubce.cfc.core.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HttpRequestHandler {
    void handleRequest(HttpServletRequest request,
                       HttpServletResponse response,
                       CfcContext context) throws ServletException, IOException;
}
