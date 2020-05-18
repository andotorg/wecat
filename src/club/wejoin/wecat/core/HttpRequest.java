package club.wejoin.wecat.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HttpRequest {
    private String url;
    private String method;
    private Map<String, String> parameter;

    public HttpRequest(InputStream inputStream) throws IOException {
        String httpRequest = "";
        byte[] httpRequestByte = new byte[1024];
        int length = 0;
        if((length = inputStream.read(httpRequestByte)) > 0){
            httpRequest = new String(httpRequestByte, 0, length);
        }
        String httpHeader = httpRequest.split("\n")[0];
        url = httpHeader.split("\\s")[1];
        if(url.indexOf("?")>-1){
            parameter = new ConcurrentHashMap<>();
            String param = url.substring(url.indexOf("?")+1);
            String[] params = param.split("&");
            for (int i = 0; i < params.length; i++) {
                String[] subParams = params[i].split("=");
                String key = "", value = "";
                for (int j = 0; j < subParams.length; j++) {
                    if(j == 0){
                        key = subParams[0];
                    }
                    if(j == 0){
                        value = subParams[1];
                    }
                }
                parameter.put(key, URLDecoder.decode(value, "UTF-8"));
            }
        }
        method = httpHeader.split("\\s")[0];
        System.out.println(this);
    }

    public Map<String, String> getParameter() {
        return parameter;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
