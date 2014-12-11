package com.fish.appdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

import com.fish.appdemo.activity.HomeActivity;
import com.fish.appdemo.activity.WelcomeActivity;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		View view = View.inflate(this, R.layout.activity_main, null);
		setContentView(view);
		render(view);
	}

	private void render(View view) {
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
		view.startAnimation(anim);
		anim.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						if (Config.getCachedAppStatus(MainActivity.this)) {
							startActivity(new Intent(MainActivity.this,
									WelcomeActivity.class));
						} else {
							startActivity(new Intent(MainActivity.this,
									HomeActivity.class));
						}
//						// 设置Activity的切换效果
						overridePendingTransition(R.anim.in_from_right,
								R.anim.out_to_left);

						MainActivity.this.finish();
					}
				}, Config.TIME_DELAY);
			}
		});
	}
}
