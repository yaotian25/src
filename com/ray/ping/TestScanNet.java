package com.ray.ping;

public class TestScanNet {
    public static void main(String[] args) {
        System.out.println("网络中上线机器如下：");
        System.out.println("地址\t\tIP\t\t是否在线\t\t连接时间\t\t丢包率\t\t网速");

        NetScan netScan=new NetScan("106.14.153", 240, 252, new NotifyNetScan() {
            @Override
            public void notify(IpInfo info) {
                System.out.println(info.hostName+"\t"+info.ip+"\t\t"+info.isOnline);
            }
        });
        netScan.start();
    }
}
