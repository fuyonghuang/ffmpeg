package com.example.scheduler;

import com.example.utils.FfmpegUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.LinkedList;

/**
 * Created by  2017/8/10.
 */
@Component
public class MonitorFile {
    @Scheduled(cron="0/3 * * * * ?")
    public void monitorFile(){
        int fileNum = 0;
        int folderNum = 0;
        File file = new File("E:\\test\\");
        if (file.exists()) {

            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (!file2.isDirectory()) {
                    String path = file2.getAbsolutePath();
                    Boolean ffmpeg = FfmpegUtil.convertVideoStart( "E:\\ffmpegwin64static\\bin\\ffmpeg.exe", path, "E:\\test\\" );
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum);

    }
}
