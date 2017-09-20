package com.example.utils;

import java.io.File;

/**
 * 文件操作工具类
 * Created by  2017/8/11.
 */
public class FileUtils {
    /**
     * 创建目录
     *
     * @param destDirName
     * @return
     */
    public static boolean createDir(String destDirName) {
        File dir = new File( destDirName );
        if (dir.exists()) {
            System.out.println( "创建目录" + destDirName + "失败，目标目录已经存在" );
            return false;
        }
        if (!destDirName.endsWith( File.separator )) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            System.out.println( "创建目录" + destDirName + "成功！" );
            return true;
        } else {
            System.out.println( "创建目录" + destDirName + "失败！" );
            return false;
        }
    }

    /**
     * 获取文件名
     *
     * @param path
     * @return
     */
    public static String getFileName(String path) {
        File file = new File( path );

        if (checkfile(path)) {
            return file.getName().substring( 0, file.getName().lastIndexOf( "." ) );
        }
        return "";
    }
    /**
     * 获取文件名类型/后缀
     *
     * @param path
     * @return
     */
    public static String getFileSuffix(String path) {

        return  path.substring( path.lastIndexOf( "." ) + 1, path.length() ).toLowerCase();
    }

    /**
     * 检查文件是否存在
     *
     * @param path
     * @return
     */
    public  static boolean checkfile(String path) {
        File file = new File( path );
        if (!file.isFile()) {
            return false;
        }
        return true;
    }

    /**
     * 文件删除
     * @param path
     * @return
     */
    public static boolean delectFile(String path){
        File file = new File( path );
        if(file.delete()){
            return  true;
        }
        return false;
    }

}
