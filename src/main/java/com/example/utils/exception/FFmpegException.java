package com.example.utils.exception;

/**
 * Created by  2017/8/10.
 * FFmpeg 自定义异常
 */
public class FFmpegException extends Exception{
    private static final long serialVersionUID = 1L;

    public FFmpegException() {
        super();
    }

    public FFmpegException(String message) {
        super(message);
    }

    public FFmpegException(Throwable cause) {
        super(cause);
    }

    public FFmpegException(String message, Throwable cause) {
        super(message, cause);
    }
}
