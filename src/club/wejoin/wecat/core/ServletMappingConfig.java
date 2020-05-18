package club.wejoin.wecat.core;

import java.util.ArrayList;
import java.util.List;

public class ServletMappingConfig {
    public static List<ServletMapping> servletMappings = new ArrayList<>();

    static {
        servletMappings.add(new ServletMapping("myServlet", "/hello", "club.wejoin.wecat.MyServlet"));
        servletMappings.add(new ServletMapping("aiServlet", "/ai", "club.wejoin.wecat.AiServlet"));
    }
}
