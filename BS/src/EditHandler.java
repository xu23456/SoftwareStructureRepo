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

public class EditHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        String requestedURI = t.getRequestURI().toString();
        String nameToEdit = null;
        String newName = null;
        String newAddress = null;
        String newPhoneNumber = null;

        try {
            Map<String, String> params = getParams(requestedURI);
            nameToEdit = params.get("name");
            newName = params.get("newName");
            newAddress = params.get("newAddress");
            newPhoneNumber = params.get("newPhoneNumber");

            if (nameToEdit == null || newName == null || newAddress == null || newPhoneNumber == null) {
                String response = "缺少必要的参数!";
                t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
                t.sendResponseHeaders(400, response.getBytes(StandardCharsets.UTF_8).length);
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes(StandardCharsets.UTF_8));
                os.close();
                return;
            }

            boolean contactFound = false;
            for (Main.Contact c : Main.contacts) {
                if (c.name.equals(nameToEdit)) {
                    c.name = newName;
                    c.address = newAddress;
                    c.phoneNumber = newPhoneNumber;
                    contactFound = true;
                    break;
                }
            }

            if (!contactFound) {
                String response = "联系人不存在!";
                t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
                t.sendResponseHeaders(404, response.getBytes(StandardCharsets.UTF_8).length);
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes(StandardCharsets.UTF_8));
                os.close();
                return;
            }

            String response = "联系人修改成功!";
            t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            t.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes(StandardCharsets.UTF_8));
            os.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            String response = "无效请求!";
            t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            t.sendResponseHeaders(400, response.getBytes(StandardCharsets.UTF_8).length);
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes(StandardCharsets.UTF_8));
            os.close();
        }
    }

    private Map<String, String> getParams(String requestedURI) throws UnsupportedEncodingException {
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
        return params;
    }
}
