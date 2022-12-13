package org.example;

import java.io.File;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("");
        String os = System.getProperty("os.name").toLowerCase();
        String path;
        if(os.contains("mac")){
            path = resource.toString().split(":")[1];
        }else{
            path = resource.toString().split("C:")[1];
        }
        String split[] = path.split("/");
        String realPath = "";
        for(int i = 0 ; i < 3; i++){
            realPath += split[i];
            realPath += "/";
        }
        realPath += "dev";
        realPath += "/";
        realPath += "Wallet_XX69YMGIK72314OL";
        System.out.println(realPath);
        String str = "C:\\[][\\[[]\\";
        System.out.println(str);
        String s[] = str.split("C:\\\\");
        System.out.println("\\");
    }
}
