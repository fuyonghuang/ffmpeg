package com.example.web;

import com.example.utils.FfmpegUtil;

/**
 * Created by  2017/8/11.
 */
public class VideoTest {
    public static void main(String[] args) {
                Boolean ffmpeg = FfmpegUtil.convertVideoStart( "E:\\ffmpegwin64static\\bin\\ffmpeg.exe", "E:\\test\\1928.mp4", "E:\\test\\" );
    }
}
