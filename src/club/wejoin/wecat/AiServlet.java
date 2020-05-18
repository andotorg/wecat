package club.wejoin.wecat;

import club.wejoin.wecat.core.HttpRequest;
import club.wejoin.wecat.core.HttpResponse;
import club.wejoin.wecat.core.WeServlet;

import java.io.IOException;

public class AiServlet extends WeServlet {

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        try {
            response.write(String.format("hello %s!", request.getParameter().get("name")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        try {
            response.write("hello wecat!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
