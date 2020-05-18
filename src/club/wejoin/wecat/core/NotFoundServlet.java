package club.wejoin.wecat.core;

import java.io.IOException;

public class NotFoundServlet extends WeServlet {
    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        try {
            response.write("404 not found, 请检查您的地址输入是否正确。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        try {
            response.write("404 not found, 请检查您的地址输入是否正确。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
