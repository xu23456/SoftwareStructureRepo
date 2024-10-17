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

public class AddHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        String query = t.getRequestURI().getQuery();
        Map<String, String> params = queryToMap(query);

        String name = params.get("name");
        String address = params.get("address");
        String phoneNumber = params.get("phoneNumber");

        // 将联系人信息添加到 contacts 列表中
        Main.contacts.add(new Main.Contact(name, address, phoneNumber));

        String response = "联系人添加成功!";
        t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        t.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }

    private Map<String, String> queryToMap(String query) throws UnsupportedEncodingException {
        Map<String, String> result = new HashMap<>();
        if (query != null) {
            for (String param : query.split("&")) {
                String[] entry = param.split("=");
                if (entry.length > 1) {
                    result.put(entry[0], URLDecoder.decode(entry[1], "UTF-8"));
                } else {
                    result.put(entry[0], "");
                }
            }
        }
        return result;
    }
}
