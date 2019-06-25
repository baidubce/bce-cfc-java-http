package com.baidubce.cfc.core.http;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class CfcClient {
    public static void main(String[] args) {
        try {
            ClientConfig.getInstance();
            Server server = new Server(8080);
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
}
