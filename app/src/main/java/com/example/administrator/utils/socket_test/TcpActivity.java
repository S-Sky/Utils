package com.example.administrator.utils.socket_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.utils.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

@ContentView(R.layout.socket_tcp1)
public class TcpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Event(R.id.btn1)
    private void click(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new ServerWithTCPSocket();
            }
        }).start();
    }

    @Event(R.id.btn2)
    private void btn2(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                connectServerWithUDPSocket();
            }
        }).start();
    }

    //UDP协议客户端发送数据
    protected void connectServerWithUDPSocket() {

        DatagramSocket socket;
        try {
            //创建DatagramSocket对象并指定一个端口号，注意，如果客户端需要接收服务器的返回数据,
            //还需要使用这个端口号来receive，所以一定要记住
            socket = new DatagramSocket(1992);
            //使用InetAddress(Inet4Address).getByName把IP地址转换为网络地址
            InetAddress serverAddress = InetAddress.getByName("192.168.10.164");
            //Inet4Address serverAddress = (Inet4Address) Inet4Address.getByName("192.168.1.32");
            String str = "[2143213;21343fjks;213;asdfasdfasdfasdf]";//设置要发送的报文
            byte data[] = str.getBytes();//把字符串str字符串转换为字节数组
            //创建一个DatagramPacket对象，用于发送数据。
            //参数一：要发送的数据  参数二：数据的长度  参数三：服务端的网络地址  参数四：服务器端端口号
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, 10025);
            socket.send(packet);//把数据发送到服务端。
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //客户端接收服务器返回的数据：
    public void ReceiveServerSocketData() {
        DatagramSocket socket;
        try {
            //实例化的端口号要和发送时的socket一致，否则收不到data
            socket = new DatagramSocket(1985);
            byte data[] = new byte[4 * 1024];
            //参数一:要接受的data 参数二：data的长度
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            //把接收到的data转换为String字符串
            String result = new String(packet.getData(), packet.getOffset(),
                    packet.getLength());
            socket.close();//不使用了记得要关闭
            System.out.println("the number of reveived Socket is  :"
                    + "udpData:" + result);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
