/**
 Test_Name:TiXiJieGou3
 Date:2023.10.23
 Number:202131060927
 Name:Tang_Zhizhen
 **/
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ViewHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        StringBuilder response = new StringBuilder();
        response.append("<html><body><h1>通讯录</h1>");
        response.append("<table border='1'><tr><th>姓名</th><th>地址</th><th>电话</th></tr>");
        for (Main.Contact contact : Main.contacts) {
            response.append("<tr><td>").append(contact.name).append("</td><td>").append(contact.address).append("</td><td>").append(contact.phoneNumber).append("</td></tr>");
        }
        response.append("</table></body></html>");
        byte[] bytes = response.toString().getBytes(StandardCharsets.UTF_8); // 设置字符编码为 UTF-8
        t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8"); // 添加字符编码标头
        t.sendResponseHeaders(200, bytes.length);
        OutputStream os = t.getResponseBody();
        os.write(bytes);
        os.close();
    }
}
