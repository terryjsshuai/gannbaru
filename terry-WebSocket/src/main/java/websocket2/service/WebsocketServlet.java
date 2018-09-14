package websocket2.service;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import websocket2.entity.Websocket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WebSocket Servlet", loadOnStartup = 1, urlPatterns = {"/ws"})
public class WebsocketServlet extends WebSocketServlet {
    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(1000L * 60L * 30L);
        factory.getPolicy().setAsyncWriteTimeout(10L * 1000L);
        factory.register(Websocket.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + ":" + password);
        if ("admin".equals(username) && "123".equals(password)) {
            request.setAttribute("userId", username);
            request.getRequestDispatcher("/jsp/captchaInput.jsp").forward(request, response);
        } else {
            request.setAttribute("loginInfo", "用户名或密码错误");
            request.getRequestDispatcher("/jsp/login2.jsp").forward(request, response);
        }
    }
}
