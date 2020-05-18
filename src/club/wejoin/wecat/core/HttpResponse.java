package club.wejoin.wecat.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class HttpResponse {
    private OutputStream outputStream;

    public HttpResponse (OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public void write(String content) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html\n")
                .append("\r\n")
                .append("<html><head><meta charset=\"UTF-8\"></head><meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"><body>")
                .append(content)
                .append("</body></html>");
        outputStream.write(new String(stringBuffer.toString().getBytes(), "UTF-8").getBytes());
        outputStream.close();
    }
}
