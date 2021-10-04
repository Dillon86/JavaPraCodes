package com.home.server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author dillon
 * date: 2021/9/25 21:47
 * description: 第一台网关中的服务器，地址：http://localhost:8801
 */
public class HttpServer01 {
    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(8801);
        while(true){
            Socket socket=server.accept();
            print(socket);
            service(socket);
        }
    }
    /**
     * 打印请求参数
     * @param socket 套接字对象
     * */
    public static void print(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        byte[] buffer=new byte[1024];
        inputStream.read(buffer);
        System.out.println(new String(buffer));
    }

    /**
     * 生成响应体
     * @param socket 套接字对象
     * */
    public static void service(Socket socket) throws IOException {
        PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type:text/html;charset=utf-8");
        String body="hello,nio1";
        out.println("Content-Length:"+body.getBytes().length);
        out.println();
        out.println(body);
        out.close();
        socket.close();
    }
}
