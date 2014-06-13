
package com.zjd.universal.net;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import object.TaskScheduled;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import send.HeartMessage1000;
import send.LoginMessage1001;
import send.SocketMessageToSend;
import ui.KingLogin;

public class GameClient {
    public static String LOGIN_IP;

    public static String LAN_LOGIN_IP = "192.168.1.67";

    public static String WAN44_LOGIN_IP = "218.76.35.163";//"192.168.1.248"; //"192.168.1.14";//"192.168.1.65";//"192.168.1.192";//"124.232.136.43";//"192.168.1.67";//

    public static String WAN10000KL_LOGIN_IP = "mobile.10000kl.com";

    public static final int LOGIN_PROT = 4322;

   public static String GAME_IP="113.10.242.132";
//    public static String GAME_IP="louislam0714.xicp.net";

    
//    public static String GAME_IP="218.76.35.162";
    
//    public static String GAME_IP="192.168.1.101";
//    public static String GAME_IP="192.168.1.205";
//    public static String GAME_IP="192.168.1.9";
    
    public static int GAME_PORT=4322;


    private static GameClient gameClient = new GameClient();

    public boolean isConnectGameServer;

    ClientBootstrap bootstrap;

    Channel gameChannel;

    ArrayList<Channel> channelList = new ArrayList<Channel>();

    private GameServerMessageFactory gameServerMessageFactory;

    public GameServerMessageFactory getGSFactory() {
        return gameServerMessageFactory;
    }

    public void setGameServerMessageFactory(GameServerMessageFactory gameServerMessageFactory) {
        this.gameServerMessageFactory = gameServerMessageFactory;
    }

    private GameClient() {
    }

   

    public void connectGameServer(final String ip, final int port) {
        isConnectGameServer = true;
        if (ip == null || port == 0) {
            GameClient.getInstance().isConnectGameServer = false;
            return;
        }

        // gameChannel = null;
        for (Channel channel : channelList) {
            if (channel != null && channel.isConnected())
                channel.close();
        }
        boolean t_isconnect = false;
        do {
            for (Channel channel : channelList) {
                if (channel != null && channel.isConnected())
                    t_isconnect = true;
            }
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (t_isconnect);
        if (gameChannel != null) {
        } else {
        }
        channelList.clear();
        System.out.println("服务器连接准备");
        ChannelFuture connectFuture = bootstrap.connect(new InetSocketAddress(ip, port));
        connectFuture.addListener(new ChannelFutureListener() {
            public void operationComplete(final ChannelFuture cf) {
                if (cf.getChannel().isConnected()) {
                	System.out.println("服务器连接成功1");
//                    cf.getChannel().setAttachment(new NetCommun());
                    channelList.add(cf.getChannel());
                    gameChannel = cf.getChannel();
                    sendMessage(cf.getChannel(), new LoginMessage1001(KingLogin.id));
                    System.out.println("服务器连接成功2");
                    TaskScheduled.scheduleAtFixedRateForever(new Runnable() {
						
						@Override
						public void run() {
							   sendMessage(cf.getChannel(), new HeartMessage1000());
							
						}
					}, 0, 30, TimeUnit.SECONDS);
                }
            }
        });
    }

 






    





    public void onCreate() {
        ChannelFactory factory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool());
        ClientPipelineFactory tcpClientPipelineFactory = new ClientPipelineFactory();
        bootstrap = new ClientBootstrap(factory);
        bootstrap.setPipelineFactory(tcpClientPipelineFactory);
        bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);
        bootstrap.setOption("reuseAddress", true);
        System.setProperty("java.net.preferIPv6Addresses", "false");
    }

    public void disConnect() {
        for (Channel channel : channelList) {
            if (channel != null && channel.isConnected())
                channel.close();
        }
        boolean t_isconnect = false;
        do {
            for (Channel channel : channelList) {
                if (channel != null)
                    if (channel.isConnected())
                        t_isconnect = true;
                    else
                        channel.close();

            }
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (t_isconnect);
        if (gameChannel != null) {
        } else {
        }
        channelList.clear();
    }

    public void sendMessage(Channel channel, SocketMessageToSend msg) {

        try {
            if (channel != null && msg != null) {
                channel.write(msg.pack());
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendMessageToGameServer(SocketMessageToSend msg) {

        if (gameChannel != null && msg != null && gameChannel.isConnected()) {
            gameChannel.write(msg.pack());
        } else {

        }
    }

    public static GameClient getInstance() {
        return gameClient;
    }

}
