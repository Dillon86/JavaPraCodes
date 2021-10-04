package com.home.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dillon
 * date: 2021/9/25 21:47
 * description:第二台网关中的服务器，地址：http://localhost:8802
 */
public class HttpServer02 {
    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*4);
        ServerSocket server=new ServerSocket(8802);
        while(true){
            final Socket socket=server.accept();
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        print(socket);
                        service(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
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
        String body="hello,nio3";
        out.println("Content-Length:"+body.getBytes().length);
        out.println();
        out.println(body);
        out.close();
        socket.close();
    }
}
