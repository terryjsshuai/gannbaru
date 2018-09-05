package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import dto.ReCaptcha;
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
        PrintWriter pw = response.getWriter();

        ReCaptcha reCaptcha = new ReCaptcha();
        reCaptcha.setImgKey("imgKey--111");
        reCaptcha.setImgBase64Str("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHIAAAAhCAYAAAAFx/8kAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAANvSURBVGhD7ZTBcRsxDEXVRA5uIxXklHpcgiYzObmD1JEeckpNCj7CzwGhT5qy1pbk5Zv5412Qu4bxlj6cDofTKIeXF1lnfv39KuvM9x+/ZZ15enqWdebPl5+yzhyPR1ln9tL/f5ED/EUD/EUDfBADfBADfBADfBADdtH/ErlEVqYHcbTfI3KvIr/ZexH0z2sV9J9rkfsSKQQw/iLeC1xk2J/jInHdoRlEehbxQeC6QzOI9CxS+09QylYi8x7mcUWScl9FxrWAD4LrIlUkIqiDCM/ETPU/YEZkXo+5nciEv6iH7W9EZqzWiBR7pMhAIzKtAfkhBhqRaQ1cKjKC+yyS8B79x3rmMUQaF4tMnIlMe6b6j+tpz5YiM6hRpFoHF4nE5rcGL1J1BoNQdQaDUHUGg1B1BoNQ9dl8mv6HJ9K+Tmzqfa3AvwhF2V9PZIezExn32nVzIgV+Iv0iPM+99rPpX1D75560d3QicYriiYywxhPZI55Itc/7H+D9v/qv1f6g6UFkyjNL5D2KFNmrSA6eIjNcfyyRuM6U9SXyXkWScl9FxjVQalVkXi8MRRqNyLQGXGRcj/vs561EAtQpUu1B7eNFKqzeFQmstrnItH4LkXHw3n8HrEeRCOH9x4g0pl4kBuDYfSNSrL9JZMg9iwRZZM6mInHTC16k6rPBIFSdwSBUncEgVJ3BIFSd2Uv/73sijfpFxz0hUydygJ9IvxDvsJ/ef6wlvH/APWlvPpHx9CAzJ3LEpidyxGYiO2wisve8XS+R5Q+qg+iwuciI3fsg1FphKNJoRKY1cNZ/2hP7jwPn0K8RiXdEkYrbiyz3PohYT3xWkdyD/rk3g/r7iwT2RzWDyFitGUSk1K4SaTQi87rdn4lMnIlMe7ofYuG9RHL9NiLjH1ruuyKB1arIznojUnAmMqURKaiDCM/EDPs3KJLDzkPviQTYR5HxGd4jFNljG5GG/KJD6iAUVm9Exn3lfkok4J4UFzmgGYR4fti/cY1IEEWqXNS/oBGJZnvBi1SdwYtUfTYYhKozGISqMxiEqjN76X/uRA7wL2IAfomTTgIz80WP2OSLHoD+8ymK3EX/0/9aB0yL7PAIIkcskYUlcol0lsgl0lkiC0vkEukskRuKRDO94EWqzuBFqs5gEKo+GwxC1RkMQtWZffT/fPoHWfU783IAAVkAAAAASUVORK5CYII=");
        reCaptcha.setPrompt("请输入香蕉行！");
        reCaptcha.setValue("");

        pw.print(JSON.toJSON(reCaptcha));

//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        System.out.println(username + ":" + password);
//        if ("admin".equals(username) && "123".equals(password)) {
//            pw.write("login success");
//            //要先创建success.jsp文件
//            //转发
//            request.getRequestDispatcher("success.jsp").forward(request, response);
//            //重定向
//            response.sendRedirect("success.jsp");
//
//
//        } else {
//            //pw.write("login fail");
//            request.setAttribute("loginInfo", "用户名或密码错误");
//            request.getRequestDispatcher("/login.jsp").forward(request, response);
//            //request.getRequestDispatcher("fail.jsp").forward(request, response);
//            //response.sendRedirect("fail.jsp");
//        }
    }
}
