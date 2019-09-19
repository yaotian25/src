package com.yc.bean1;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

 class XunLei1 {
    static   long sum=0;
    public static void main(String[] args) throws IOException {
        String path = "https://dldir1.qq.com/qqfile/qq/PCQQ9.1.6/25808/QQ9.1.6.25808.exe";
        int threadSize=5;
        XunLei1 x1 = new XunLei1();

        x1.download(path, threadSize, new OnSizeChangeListener(){
            @Override
            public void notify(long size) {
                sum+=size;
                System.out.println("已经下载了"+sum);
            }
        });
    }


    private void download(String path,int threadSize,OnSizeChangeListener listener) throws IOException {

        String fileName = getFileName(path);
        long totalSize = getFileSize(path);
        long sizePerThread = getDownLoadSizePerThread(totalSize, threadSize);
        System.out.println(fileName+"\t"+ totalSize+"\t"+ sizePerThread);
        //创建一个randomaccessfile类的对象，并设置好它的大小
        RandomAccessFile raf = new RandomAccessFile(fileName, "rwd");
        raf.setLength(totalSize);
        raf.close();
        for (int i = 0; i < threadSize; i++) {
            Thread t = new Thread(new DownLoadTask(path, fileName, threadSize, i, sizePerThread , listener));
            t.start();
        }
    }

    /**
     * 根据路径查到要下载的文件的总大小
     * @param path
     * @return
     */
    private  long getFileSize(String path) throws IOException {

        URL url = new URL(path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout( 10000 );
        return con.getContentLength();
    }

    /**
     * 计算每个线程要下载的数据量
     * @param totalSize
     * @param threadSize
     * @return
     */
    private long getDownLoadSizePerThread(long totalSize, int threadSize) {

        return totalSize%threadSize==0? totalSize/threadSize :totalSize/threadSize+1;
    }

    /**
     * 根据路径名取出要下载的文件名
     * @param path
     * @return
     */
    private String getFileName(String path) {
        int index = path.lastIndexOf("/");
        return path.substring(index + 1);

    }

    private class DownLoadTask implements Runnable {

        //要下载的文件的url地址
        private String path;
        //保存的文件名
        private String fileName;
        //共有多少线程
        private int threadSize;
        //这是第几个线程
        private int threadId;
        //每个线程要下载多少数据
        private long sizePerThread;
        //大小改变的监听器
        private OnSizeChangeListener listener;
        public DownLoadTask(String path, String fileName, int threadSize, int threadId, long sizePerThread,OnSizeChangeListener listener) {
            super();
            this.path = path;
            this.fileName = fileName;
            this.threadSize = threadSize;
            this.threadId = threadId;
            this.sizePerThread = sizePerThread;
            this.listener=listener;
        }

        @Override
        public void run() {

            RandomAccessFile raf=null;
            BufferedInputStream bis=null;
            //1.计算当前这个线程要下载的起始位置
            try {
                long startPosition = threadId * sizePerThread;
                long endPosition=(threadId+1)*sizePerThread-1;
                //2.创建RandomAccessFile文件对象，以备下载
                raf=new RandomAccessFile(fileName,"rwd");
                raf.seek(startPosition);
                //3.使用HttpURLConnection对象开始下载，协议：con.setRequestProperty("Range","bytes=" + startPosition+"-"  +endPosition);
                URL url = new URL(path);
                HttpURLConnection con=(HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setConnectTimeout(10000);
                con.setRequestProperty("Range", "bytes=" + startPosition + "-" + endPosition);
                //4.下载到的数据，以流的方式存到RandomAccessFile对象中
                bis = new BufferedInputStream(con.getInputStream());
                byte[] bs=new byte[1024*1024];
                int length=-1;
                while ( (length = bis.read(bs, 0, bs.length)) != -1) {
                    raf.write(bs,0,length);

                    if (listener != null) {
                        listener.notify(length);
                    }
                }
                System.out.println("第" + threadId + "个线程下载数据完毕，它的任务从" + startPosition + "到" + endPosition);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                //5.下载完了，则关闭流
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



        }
    }
}
