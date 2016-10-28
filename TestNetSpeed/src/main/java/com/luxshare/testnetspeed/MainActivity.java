package com.luxshare.testnetspeed;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.luxshare.testnetspeed.Tools.ReadFile;

public class MainActivity extends AppCompatActivity {

    TextView fileLength = null;
    TextView speed = null;
    TextView hasDown = null;
    TextView percent = null;
    String url = "http://hiphotos.baidu.com/kw_sx/pic/item/51e960a6cc0854af9152ee4d.jpg";

    public byte[] imageData = null;
    NetWorkSpeedInfo netWorkSpeedInfo = null;
    private final int UPDATE_SPEED = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int value = msg.what;
            switch (value) {
                case UPDATE_SPEED:
                    updateView();
                    break;
                default:
                    break;
            }
        }
    };
    private ImageView mIv;
    private Button mBtn;

    private void updateView() {
        speed.setText(netWorkSpeedInfo.speed + "bytes/s");
        hasDown.setText(netWorkSpeedInfo.hadFinishedBytes + "bytes");
        fileLength.setText(netWorkSpeedInfo.totalBytes + "");

        percent.setText(netWorkSpeedInfo.downloadPercent + "%");

        if (imageData != null) {
            Bitmap b = BitmapFactory.decodeByteArray(imageData, 0,
                    imageData.length);
            mIv.setImageBitmap(b);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewById();
    }

    private void initViewById() {

        fileLength = (TextView) findViewById(R.id.filelength);
        hasDown = (TextView) findViewById(R.id.hasdown);
        percent = (TextView) findViewById(R.id.precent);
        speed = (TextView) findViewById(R.id.speed);
        mIv = ((ImageView) findViewById(R.id.iv));
        mBtn = ((Button) findViewById(R.id.btn));

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });
    }


    public void click() {

        new Thread() {
            @Override
            public void run() {
                imageData = ReadFile.getFileFromUrl(url, netWorkSpeedInfo);
            }
        }.start();

        /*new Thread() {

            @Override
            public void run() {
                System.out.println("------2------");
//                while (netWorkSpeedInfo.hadFinishedBytes < netWorkSpeedInfo.totalBytes) {
//
//                    netWorkSpeedInfo.downloadPercent = (int) (((double) netWorkSpeedInfo.hadFinishedBytes /
//                            (double) netWorkSpeedInfo.totalBytes) * 100);
//                    try {
//                        sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    handler.sendEmptyMessage(UPDATE_SPEED);
//                }
            }
        }.start();*/

        new Thread() {
            @Override
            public void run() {
                Log.i("TAG", "**********开始  netWorkSpeedInfo1*******");
                while (netWorkSpeedInfo.hadFinishedBytes < netWorkSpeedInfo.totalBytes) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(UPDATE_SPEED);
                }
                if (netWorkSpeedInfo.hadFinishedBytes == netWorkSpeedInfo.totalBytes) {
                    handler.sendEmptyMessage(UPDATE_SPEED);
                    netWorkSpeedInfo.hadFinishedBytes = 0;
                }
            }
        }.start();
    }
}
