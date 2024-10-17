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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class DeleteHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        String requestedURI = t.getRequestURI().toString();
        String nameToDelete = null;

        try {
            nameToDelete = getNameToDelete(requestedURI);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (nameToDelete != null) {
            Main.Contact contactToRemove = null;
            for (Main.Contact c : Main.contacts) {
                if (c.name.equals(nameToDelete)) {
                    contactToRemove = c;
                    break;
                }
            }
            if (contactToRemove != null) {
                Main.contacts.remove(contactToRemove);
                String response = "联系人删除成功!";
                t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
                t.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes(StandardCharsets.UTF_8));
                os.close();
            } else {
                String response = "不能找到联系人!";
                t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
                t.sendResponseHeaders(404, response.getBytes(StandardCharsets.UTF_8).length);
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes(StandardCharsets.UTF_8));
                os.close();
            }
        } else {
            String response = "无效请求!";
            t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            t.sendResponseHeaders(400, response.getBytes(StandardCharsets.UTF_8).length);
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes(StandardCharsets.UTF_8));
            os.close();
        }
    }

    private String getNameToDelete(String requestedURI) throws UnsupportedEncodingException {
        String query = requestedURI.split("\\?")[1];
        String[] pairs = query.split("&");
        Map<String, String> params = new HashMap<>();
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            if (idx > 0) {
                String key = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
                String value = URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
                params.put(key, value);
            }
        }
        return params.get("name");
    }
}
