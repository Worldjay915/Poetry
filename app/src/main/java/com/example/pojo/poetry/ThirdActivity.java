package com.example.pojo.poetry;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;

import poetry.Poetry;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_title,tv_shiren, tv_shiren1,tv_content,
            tv_desc,tv_back,tv_tangshi,tv_setting;

    private Poetry poetry;
    private AlertDialog.Builder builder;
    private int size1=25,size2=13,size3=13,size4=27,size5=20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_third);

        poetry = (Poetry) getIntent().getSerializableExtra("poetry");
        init();
        initEvent();
        tv_back.setOnClickListener(this);
        tv_setting.setOnClickListener(this);

        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=57996a01");

    }

    private void initEvent() {
        tv_title.setText(poetry.getTitle());
        tv_shiren.setText(poetry.getAuth());
        tv_content.setText(Html.fromHtml(poetry.getContent()));
        tv_desc.setText(Html.fromHtml(poetry.getDesc()));
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_shiren = (TextView) findViewById(R.id.tv_shiren);
        tv_shiren1 = (TextView) findViewById(R.id.tv_shiren1);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_desc = (TextView) findViewById(R.id.tv_desc);

        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_tangshi = (TextView) findViewById(R.id.tv_tangshi);
        tv_setting = (TextView) findViewById(R.id.tv_setting);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_back:{
                finish();
            } break;
            case R.id.tv_setting:{
                showSingleDialog();
            }break;
        }

    }

    private void showSingleDialog() {

        builder = new AlertDialog.Builder(this);

        final String[] function = new String[]{"增大字体","减小字体","语音朗读"};
        builder.setSingleChoiceItems(function, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0){
                    textIncrease();
                }else if (which==1){
                    textDeIncrease();
                }else {
                    //1.创建 SpeechSynthesizer 对象, 第二个参数：本地合成时传 InitListener
                    SpeechSynthesizer mTts= SpeechSynthesizer.createSynthesizer(ThirdActivity.this, null);
                    //2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
                    //设置发音人（更多在线发音人，用户可参见 附录13.2
                    mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan"); //设置发音人
                    mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
                    mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围 0~100
                    mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
                    //设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”
                    //保存在 SD 卡需要在 AndroidManifestn.xml 添加写 SD 卡权限
                    //仅支持保存为 pcm 和 wav 格式，如果不需要保存合成音频，注释该行代码
//                    mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
                    //3.开始合成
                    Log.i("info","---1---"+tv_title.getText().toString());
                    mTts.startSpeaking(tv_title.getText().toString()+tv_shiren1.getText().toString()
                            +tv_shiren.getText().toString()+tv_content.getText().toString()
                            +tv_desc.getText().toString() ,  mSynListener);
                }
            }
        });
        builder.show();
    }

    private SynthesizerListener mSynListener = new SynthesizerListener(){

        @Override
        public void onSpeakBegin() {
            Log.i("info","---2---");
        }

        @Override
        public void onBufferProgress(int i, int i1, int i2, String s) {

        }

        @Override
        public void onSpeakPaused() {

        }

        @Override
        public void onSpeakResumed() {

        }

        @Override
        public void onSpeakProgress(int i, int i1, int i2) {

        }

        @Override
        public void onCompleted(SpeechError speechError) {
            Log.i("info","---3---"+speechError.getErrorCode());
        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };
    private void textIncrease() {

        tv_title.setTextSize(size1);
        tv_shiren1.setTextSize(size2);
        tv_shiren.setTextSize(size3);
        tv_content.setTextSize(size4);
        tv_desc.setTextSize(size5);
        size1*=1.1;
        size2*=1.1;
        size3*=1.1;
        size4*=1.1;
        size5*=1.1;

    }
    private void textDeIncrease() {

        tv_title.setTextSize(size1);
        tv_shiren1.setTextSize(size2);
        tv_shiren.setTextSize(size3);
        tv_content.setTextSize(size4);
        tv_desc.setTextSize(size5);
        size1*=0.95;
        size2*=0.95;
        size3*=0.95;
        size4*=0.95;
        size5*=0.95;

    }
}
