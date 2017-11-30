package com.example.administrator.utils.socket_test;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class ServerWithTCPSocket {

    public ServerWithTCPSocket() {
        Socket socket = null;
        try {
            //穿件一个Socket对象,并指定服务端的IP及端口号
            socket = new Socket("192.168.10.164", 2017);
          /*  // 创建一个InputStream用户读取要发送的文件。
            InputStream inputStream = new FileInputStream("e://a.txt");
            // 获取Socket的OutputStream对象用于发送数据。
            OutputStream outputStream = socket.getOutputStream();
            // 创建一个byte类型的buffer字节数组，用于存放读取的本地文件
            byte buffer[] = new byte[4 * 1024];
            int temp = 0;
            //循环读取文件
            while ((temp = inputStream.read(buffer)) != -1){
                // 把数据写入到OuputStream对象中
                outputStream.write(buffer, 0, temp);
            }
            // 发送读取的数据到服务端
            outputStream.flush();
            /
            /** 或创建一个报文，使用BufferedWriter写入,看你的需求 **/
            String socketData = "[2143213;21343fjks;213]";
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream()));
            writer.write(socketData.replace("\n", " ") + "\n");
            writer.flush();
            /************************************************/
            Log.i("ANDROID", "连接成功");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
