package com.zuoan;

import com.zuoan.application.ZuoanApplication;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;


import java.io.IOException;
import java.net.URI;

/**
 * strat tomcat
 * Created by Administrator on 2016/3/15.
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://0.0.0.0:8080/";

    public static HttpServer startServer() {
        // 加载jersey扫描
        ZuoanApplication loanApplication = new ZuoanApplication();

        // 启动服务
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), loanApplication);
    }

    /**
     * Main method.
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));

        System.in.read();
        server.shutdownNow();
    }
}