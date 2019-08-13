package com.jullierme.revolut.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import static com.jullierme.revolut.config.PropertiesConfigurationService.configuration;
import static org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS;

public class JettyService {
    private static final Logger logger = LogManager.getLogger(JettyService.class);

    public static void start() {
        logger.info("Starting Jetty Server...");

        int port = configuration.getInt("app.server.port");
        String contextPath = configuration.getString("app.server.context_path");
        String prefixPath = configuration.getString("app.server.prefix_path");
        String jettyPackages = configuration.getString("app.server.jetty_packages");
        String rootPath = configuration.getString("app.server.root_path");

        Server server = new Server(port);

        ServletContextHandler servletContextHandler = new ServletContextHandler(NO_SESSIONS);
        servletContextHandler.setContextPath(contextPath);

        server.setHandler(servletContextHandler);

        ServletHolder servletHolder = servletContextHandler.addServlet(ServletContainer.class, prefixPath);
        servletHolder.setInitOrder(0);
        servletHolder.setInitParameter(jettyPackages, rootPath);

        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            logger.error("Error occurred while starting Jetty", ex);
            System.exit(1);
        } finally {
            server.destroy();
        }
    }
}