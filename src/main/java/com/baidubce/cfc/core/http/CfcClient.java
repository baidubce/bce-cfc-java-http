package com.baidubce.cfc.core.http;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.unixsocket.UnixSocketConnector;

import java.io.File;

public class CfcClient {
    private static final String SOCKET_PATH = "/var/run/faas-runtime/.runtime-http.sock";

    public static void main(String[] args) {
        try {
            ClientConfig config = ClientConfig.getInstance();
            Server server = getServer(config);

            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            context.addServlet(new ServletHolder(new CfcDefaultHandler()), "/*");
            server.setHandler(context);
            server.start();
            server.join();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    private static Server getServer(ClientConfig config) {
        if (!"socket".equals(config.getHttpMode())) {
            return new Server(8080);
        }
        Server server = new Server();
        String socketFile = config.getHttpSocket();
        if ("".equals(socketFile)) {
            socketFile = SOCKET_PATH;
        }
        File file = new File(socketFile);
        if (file.exists()) {
            file.delete();
        }
        file.deleteOnExit();
        UnixSocketConnector connector = new UnixSocketConnector(server);
        connector.setUnixSocket(file.getAbsolutePath());
        server.addConnector(connector);
        return server;
    }
}
