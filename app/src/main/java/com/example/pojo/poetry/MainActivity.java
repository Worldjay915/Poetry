package com.example.pojo.poetry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView img_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_welcome= (ImageView) findViewById(R.id.img_welcome);

        AlphaAnimation animation  = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(3000);
        img_welcome.startAnimation(animation);
        animation.setAnimationListener(new AnimationImpl());

    }

    private class AnimationImpl implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            img_welcome.setBackgroundResource(R.mipmap.weclome);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            skip(); // 动画结束后跳转到别的页面
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

    private void skip() {
        startActivity(new Intent(MainActivity.this,SecondActivity.class));
        finish();
    }
}
}
