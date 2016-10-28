package com.luxshare.phoneinfo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTv = ((TextView) findViewById(R.id.tv));

        TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);


        mTv.setText("手机总内存: " + this.getTotalMemory() + ", " + "可用内存: "
                + this.getAvailMemory() + "总ROM：" + getSDTotalSize()
                + ",可用ROM：" + getSDAvailableSize()
                + ",当前应用信息：" + getRunningAppProcessInfo()
                + "手机型号： " + android.os.Build.MODEL
                + "SDK版本：" + android.os.Build.VERSION.SDK
                + "系统版本：" + android.os.Build.VERSION.RELEASE
                + "电话方位：" + tm.getCellLocation()
        );


        System.out.println("手机型号: " + android.os.Build.MODEL + ",\nSDK版本:"
                + android.os.Build.VERSION.SDK + ",\n系统版本:"
                + android.os.Build.VERSION.RELEASE);


    }

    public void click(View view) {
        startActivity(new Intent(this,LocationAct.class));
    }

    // 获取android当前可用内存大小
    private String getAvailMemory() {

        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        // 将获取的内存大小规格化
        return Formatter.formatFileSize(getBaseContext(), mi.availMem);
    }

    private String getTotalMemory() {

        String str1 = "/proc/meminfo"; // 系统内存信息文件
        String str2;

        String[] arrayOfString;
        int initial_memory = 0;

        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            str2 = localBufferedReader.readLine(); // 读取meminfo第一行，系统总内存大小

            arrayOfString = str2.split("\\s+");
            for (String num : arrayOfString) {
                Log.i("TAG", num + "\t");
                
            }

            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024; //  获得系统总内存，单位是KB，乘以1024转换为Byte

            localBufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Formatter.formatFileSize(getBaseContext(), initial_memory); // Byte转换为KB或者MB，内存大小规格化
    }


    /**
     * 获得SD卡总大小
     *
     * @return
     */
    private String getSDTotalSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return Formatter.formatFileSize(MainActivity.this, blockSize * totalBlocks);
    }


    /**
     * 获得sd卡剩余容量，即可用大小
     *
     * @return
     */
    private String getSDAvailableSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return Formatter.formatFileSize(MainActivity.this, blockSize * availableBlocks);
    }


    private String getRunningAppProcessInfo() {
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        //获得系统里正在运行的所有进程
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessesList = mActivityManager.getRunningAppProcesses();

        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcessesList) {
            // 进程ID号
            int pid = runningAppProcessInfo.pid;
            // 用户ID
            int uid = runningAppProcessInfo.uid;
            // 进程名
            String processName = runningAppProcessInfo.processName;

            if (getCurProcessName(this).equalsIgnoreCase(processName)) {
                // 占用的内存
                int[] pids = new int[]{pid};
                Debug.MemoryInfo[] memoryInfo = mActivityManager.getProcessMemoryInfo(pids);
                int memorySize = memoryInfo[0].dalvikPrivateDirty;

                System.out.println("进程名=" + processName + ",PID=" + pid + ",UID=" + uid + ",MemorySize=" + memorySize + "kb");
                return "进程名=" + processName + ",PID=" + pid + ",UID=" + uid + ",MemorySize=" + memorySize + "kb";
            }
        }
        return null;
    }

    private String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }

}
