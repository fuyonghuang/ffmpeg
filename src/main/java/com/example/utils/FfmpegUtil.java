package com.example.utils;

import com.example.utils.exception.FFmpegException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FfmpegUtil {



public  static Boolean convertVideoStart(String ffmpegPath, String inputPath, String outputPath){
    try {
        String resolution;
        String outpath;
        int completeCount = 0;
        String fileName = FileUtils.getFileName( "E:\\test\\1928.mp4" );

//        for (int i = 3; i > 0; i--) {
          /* if (i == 3) {
                resolution = "1920*1080";
                outpath = outputPath+"\\HD\\";
            } else if (i == 2) {
                resolution = "1280x720";
                outpath = outputPath+"\\SD\\";
            } else {*/
                resolution = "640x480";
                outpath = outputPath+"\\LD\\";

            FileUtils.createDir( outpath );
            Boolean ffmpeg = FfmpegUtil.convertVideo( ffmpegPath, inputPath, outpath + "\\" + fileName, resolution );
            if (ffmpeg) {
                completeCount++;
            }

        //当视频三种
        if(completeCount==3) {
            return FileUtils.delectFile( inputPath );
        }
    } catch (IllegalStateException e) {
        e.printStackTrace();

    } catch (FFmpegException e) {
        e.printStackTrace();
    }
   return true;
}





    /**
     * 视频转码工具
     * @param ffmpegPath
     * @param inputPath
     * @param outputPath
     * @param resolution
     * @return
     * @throws FFmpegException
     */
    public static Boolean convertVideo(String ffmpegPath, String inputPath, String outputPath, String resolution) throws FFmpegException {

        if (!FileUtils.checkfile( inputPath )) {
            throw new FFmpegException( "文件格式不合法" );
        }
        int type = checkContentType( inputPath );
        List command = getFfmpegConvertVideoCommand( type, ffmpegPath, inputPath, outputPath, resolution );
        if (null != command && command.size() > 0) {
            return process( command );
        }
        return false;
    }

    /**
     * 检查视频的格式
     *
     * @param inputPath
     * @return
     */
    private static int checkContentType(String inputPath) {
        String type = FileUtils.getFileSuffix( inputPath );
        // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
        if (type.equals( "avi" )) {
            return 1;
        } else if (type.equals( "mpg" )) {
            return 1;
        } else if (type.equals( "wmv" )) {
            return 1;
        } else if (type.equals( "3gp" )) {
            return 1;
        } else if (type.equals( "mov" )) {
            return 1;
        } else if (type.equals( "mp4" )) {
            return 1;
        } else if (type.equals( "mkv" )) {
            return 1;
        } else if (type.equals( "asf" )) {
            return 0;
        } else if (type.equals( "flv" )) {
            return 0;
        } else if (type.equals( "rm" )) {
            return 0;
        } else if (type.equals( "rmvb" )) {
            return 1;
        }
        return 9;
    }


    /**
     * 执行转码操作
     *
     * @param command
     * @return
     * @throws FFmpegException
     */
    private static boolean process(List command) throws FFmpegException {

        try {

            if (null == command || command.size() == 0)
                return false;
            //执行编码命令行
            Process videoProcess = new ProcessBuilder( command ).redirectErrorStream( true ).start();

            new PrintStream( videoProcess.getErrorStream() ).start();

            new PrintStream( videoProcess.getInputStream() ).start();

            int exitcode = videoProcess.waitFor();

            if (exitcode == 1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new FFmpegException( "file upload failed", e );
        }
    }

    /**
     * 转码命令行拼装
     *
     * @param type
     * @param ffmpegPath
     * @param oldfilepath
     * @param outputPath
     * @param resolution
     * @return
     * @throws FFmpegException
     */
    private static List getFfmpegConvertVideoCommand(int type, String ffmpegPath, String oldfilepath, String outputPath, String resolution) throws FFmpegException {
        List command = new ArrayList();
        File source = new File( oldfilepath );
        if (type == 1) {
            //编码器地址
            command.add( ffmpegPath );
            command.add( "-threads" );
            command.add( "4" );
            command.add( "-i" );
            //需转码的视频文件路径
            command.add( oldfilepath );
            //分辨率设置
            command.add( "-s" );
            command.add( resolution );
            command.add( "-codec:v" );
            command.add( "libx264" );
            command.add( "-codec:a" );
            command.add( "mp3" );
            command.add( "-map" );
            command.add( "0" );
            command.add( "-f" );
            command.add( "ssegment" );
            command.add( "-segment_format" );
            command.add( "mpegts" );
            command.add( "-segment_list" );
            command.add( outputPath + ".m3u8" );
            command.add( "-segment_time" );
            command.add( "10" );
            command.add( outputPath + "%03d.ts" );
        } else {
            throw new FFmpegException( "不支持当前上传的文件格式" );
        }
        return command;
    }
}

