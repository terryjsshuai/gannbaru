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

            ResourceHandler resource_handler = new ResourceHandler();
            resource_handler.setResourceBase("src/com/phoenixlegend/web/webapp");
            resource_handler.setDirectoriesListed(true);
            resource_handler.setWelcomeFiles(new String[]{"index.html"});

            WebAppContext webAppContext = new WebAppContext("webapp", "/");
            webAppContext.setResourceBase("src/com/phoenixlegend/web/webapp");
            webAppContext.setClassLoader(Thread.currentThread().getContextClassLoader());

            HandlerList handlers = new HandlerList();
            handlers.setHandlers(new Handler[]{resource_handler, context, webAppContext});

            InetSocketAddress address = new InetSocketAddress("192.168.1.3", 8080);
            Server server = new Server(address);
            server.setHandler(handlers);
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        JettyWebServer server = new JettyWebServer();
        server.start();
    }
}
