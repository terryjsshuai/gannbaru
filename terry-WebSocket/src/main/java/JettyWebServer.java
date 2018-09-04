import java.net.InetSocketAddress;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.webapp.WebAppContext;

import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.WebSocketChatServlet;

public class JettyWebServer extends Thread {

    @Override
    public void run() {
        try {
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");

            String resourceBase = "src/main/webapp/websocket";
            ResourceHandler resourceHandler = new ResourceHandler();
            resourceHandler.setResourceBase(resourceBase);
            resourceHandler.setDirectoriesListed(true);
            resourceHandler.setWelcomeFiles(new String[]{"chat_test.html"});

            WebAppContext webAppContext = new WebAppContext("webapp", "/");
            webAppContext.setResourceBase(resourceBase);
            webAppContext.setClassLoader(Thread.currentThread().getContextClassLoader());

            HandlerList handlers = new HandlerList();
            handlers.setHandlers(new Handler[]{resourceHandler, context, webAppContext});

            InetSocketAddress address = new InetSocketAddress("10.20.0.16", 2222);
            Server server = new Server(address);
            server.setHandler(handlers);
            server.start();
            server.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        JettyWebServer server = new JettyWebServer();
        server.start();
    }
}
