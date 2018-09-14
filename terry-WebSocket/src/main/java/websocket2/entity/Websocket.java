package websocket2.entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;

import com.alibaba.fastjson.JSON;
import websocket2.service.WebsocketManager;

/**
 * 服务器websocket连接端点
 */
public class Websocket implements WebSocketListener {
    static BASE64Encoder encoder = new BASE64Encoder();
    static BASE64Decoder decoder = new BASE64Decoder();
    private Session session;//记录连接，websocket session（websocket会话）

    @Override
    public void onWebSocketBinary(byte[] bytes, int i, int i1) {
        //客户端发来二进制消息时调用
    }

    @Override
    public void onWebSocketText(String s) {
        System.out.println("客户端发来消息" + s);
        //WebsocketManager.sendMessageToAll(s);
    }

    @Override
    public void onWebSocketClose(int i, String s) {
        this.session.close();
        this.session = null;
    }

    @Override
    public void onWebSocketConnect(Session session) {
        this.session = session;
        WebsocketManager.webSocketSet.add(this);
        ReCaptcha capt = new ReCaptcha();
        capt.setImgKey("123456789abcdefghijklmnopq");
        capt.setPrompt("请自上而下输入红色框内的三位数字验证码");
        capt.setImgBase64Str("data:image/png;base64," + ImageToBase64ByLocal("H:\\kuang\\88.png"));
        String msg = JSON.toJSONString(capt);
        sendMessage(msg);
    }

    @Override
    public void onWebSocketError(Throwable throwable) {
        throwable.printStackTrace(System.err);
        WebsocketManager.webSocketSet.remove(this);
        System.out.println("发生错误");
    }

    /**
     * 发送消息
     *
     * @param data
     */
    public void sendMessage(String data) {
        try {
            this.session.getRemote().sendString(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String ImageToBase64ByLocal(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;

        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);

            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }
}
