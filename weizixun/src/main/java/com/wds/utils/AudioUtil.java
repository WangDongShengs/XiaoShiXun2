package com.wds.utils;

import android.media.MediaRecorder;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public  class AudioUtil {


    private static final String TAG = "RecorderUtil";

    private static String mFileName = null;
    private static MediaRecorder mRecorder = null;
    private static long startTime;
    private static long timeInterval;
    public static boolean isRecording;


    /**
     * 开始录音
     */
    public static void startRecording() {
        if (mFileName == null) return;
        if (isRecording){
            mRecorder.release();
            mRecorder = null;
        }
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        startTime = System.currentTimeMillis();
        try {
            mRecorder.prepare();
            mRecorder.start();
            isRecording = true;
        } catch (Exception e){
            Log.e(TAG, "prepare() failed");
        }

    }


    /**
     * 停止录音
     */
    public static void stopRecording() {
        if (mFileName == null) return;
        timeInterval = System.currentTimeMillis() - startTime;
        try{
            if (timeInterval>1000){
                mRecorder.stop();
            }
            mRecorder.release();
            mRecorder = null;
            isRecording =false;
        }catch (Exception e){
            Log.e(TAG, "release() failed");
        }

    }

    /**
     * 取消语音
     */
    public static synchronized void cancelRecording() {

        if (mRecorder != null) {
            try {
                mRecorder.release();
                mRecorder = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            File file = new File(mFileName);
            file.deleteOnExit();
        }

        isRecording =false;
    }

    /**
     * 获取录音文件
     */
    public static byte[] getDate() {
        if (mFileName == null) return null;
        try{
            return readFile(new File(mFileName));
        }catch (IOException e){
            Log.e(TAG, "read file error" + e);
            return null;
        }
    }

    /**
     * 获取录音文件地址
     */
    public static String getFilePath(){
        return mFileName;
    }


    /**
     * 获取录音时长,单位秒
     */
    public static long getTimeInterval() {
        return timeInterval/1000;
    }


    /**
     * 将文件转化为byte[]
     *
     * @param file 输入文件
     */
    private  static byte[] readFile(File file) throws IOException {
        // Open file
        RandomAccessFile f = new RandomAccessFile(file, "r");
        try {
            // Get and check length
            long longlength = f.length();
            int length = (int) longlength;
            if (length != longlength)
                throw new IOException("File size >= 2 GB");
            // Read file and return data
            byte[] data = new byte[length];
            f.readFully(data);
            return data;
        } finally {
            f.close();
        }
    }

}
