package com.ray.ping;

import java.io.IOException;

public class NetScan extends Thread {
    private String ipPreFix;//ip地址前三位
    private int start;//IP地址的最后一直未的起始值
    private int end;//最后一位的结束值
    private String hostName;//网址
    private NotifyNetScan notifyNetScan;//回调接口

    //用户IP地址扫描
    public NetScan(String ipPreFix, int start, int end, NotifyNetScan notifyNetScan) {
        this.ipPreFix=ipPreFix;
        this.end=end;
        this.start=start;
        this.notifyNetScan=notifyNetScan;
    }

    //用于网址
    public NetScan(String hostName, NotifyNetScan notifyNetScan) {
        this.notifyNetScan=notifyNetScan;
        this.hostName=hostName;
        this.start=-1;
    }

    @Override
    public void run() {
        if (this.start != -1) {
            for (int i = start; i <= end; i++) {
                String ip=ipPreFix+"."+i;//47.105.185.217
                Ping ping =new Ping();
                try {
                    String result = ping.getPingResult(ip);
                    IpInfo info = ping.isOnLine(ip, result);
                    if (null != this.notifyNetScan && info.isOnline) {
                        this.notifyNetScan.notify(info);
                    }
                } catch (IOException e) {
                    System.out.println("网址中地址为:"+ip+"的机器连接故障");
                    e.printStackTrace();
                }
            }
        }else{
            Ping ping=new Ping();
            try {
                String result = ping.getPingResult(hostName);
                IpInfo info = ping.isOnLine(hostName, result);
                System.out.println(result);
            } catch (IOException e) {
                System.out.println("网址中地址为:"+hostName+"的机器连接故障");
                e.printStackTrace();
            }
        }
    }
}
