package club.wejoin.wecat.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class StartUp {
    private  int port = 8080;
    private Map<String, String> urlServletMap = new HashMap<>();

    private boolean one  = true;

    public StartUp(int port) {
        if(port == 0){
            port = this.port;
        }
        this.initServletMap();

        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            System.err.println("WeCat Starting..... ");
            while (true){
                if(one){
                    System.err.println("Start up success, port: "+port);
                    System.err.println("Waiting accept commend..... ");
                    one = false;
                }
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                HttpRequest request = new HttpRequest(inputStream);
                HttpResponse response = new HttpResponse(outputStream);
                this.dispatch(request, response);
                socket.close();
            }
        }catch (IOException ex){
            ex.printStackTrace();
            System.err.println("WeCat Starting fail !!! ");
        }
    }

    private void initServletMap(){
        for (ServletMapping servletMapping:ServletMappingConfig.servletMappings){
            urlServletMap.put(servletMapping.getUrl(), servletMapping.getClazz());
        }
    }

    private void dispatch(HttpRequest request, HttpResponse response){
        if("/favicon.ico".equalsIgnoreCase(request.getUrl())){
            System.err.println("/favicon.ico");
        }
        String clazz;
        if(request.getUrl().indexOf("?") > -1){
            clazz = urlServletMap.get(request.getUrl().substring(0, request.getUrl().indexOf("?")));
        }else{
            clazz = urlServletMap.get(request.getUrl());
        }
        WeServlet weServlet;
        if(clazz == null){
            weServlet = new NotFoundServlet();
            weServlet.service(request, response);
            return;
        }
        try {
            Class<WeServlet> weServletClass = (Class<WeServlet>)Class.forName(clazz);
            weServlet = weServletClass.newInstance();
            weServlet.service(request, response);
        } catch (ClassNotFoundException e) {
            weServlet = new NotFoundServlet();
            weServlet.service(request, response);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
