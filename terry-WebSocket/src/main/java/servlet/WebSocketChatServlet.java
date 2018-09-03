package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import service.ChatWebSocketHolder;
import socket.ChatWebSocket;

@WebServlet(name = "ChatServlet", urlPatterns = "/chat")
public class WebSocketChatServlet extends WebSocketServlet {

    private static final long serialVersionUID = -8985641472958742787L;

    private static final long LOGOUT_TIME = 10 * 60 * 1000;

    private ChatWebSocketHolder connections;

    public WebSocketChatServlet() {
        connections = new ChatWebSocketHolder();
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator((req, resp) -> new ChatWebSocket(connections));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + ":" + password);
        PrintWriter pw = response.getWriter();
        if ("admin".equals(username) && "123".equals(password)) {
            pw.write("login success");
            //要先创建success.jsp文件
            //转发
            //request.getRequestDispatcher("success.jsp").forward(request, response);
            //重定向
            //response.sendRedirect("success.jsp");
        } else {
            //pw.write("login fail");
            request.setAttribute("loginInfo", "用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            //request.getRequestDispatcher("fail.jsp").forward(request, response);
            //response.sendRedirect("fail.jsp");
        }
    }
}
