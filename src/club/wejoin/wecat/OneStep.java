package club.wejoin.wecat;

import club.wejoin.wecat.core.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

public class OneStep {
    public static void main(String[] args) {
        int port = 0;
        if(args.length != 0){
            port = Integer.parseInt(args[0]);
        }
        new StartUp(port);
    }
}
