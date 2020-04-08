package com.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by admin on 2020-01-10.
 */
public class PostTest {

    public static void main(String[] args) {
        try{
            String body = "{\"pf\":4,\"wvid\":3030,\"status\":3}";
            JSONObject jo = JSON.parseObject(body);
            String httpurl = "http://10.65.55.23:5011/admin/externalAllot?" +
                    "pf="+jo.getInteger("pf") + "&wvid="+jo.getInteger("wvid");

            URL url = new URL(httpurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();

            System.out.println(connection.getResponseCode());

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
