package com.fish.appdemo.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fish.appdemo.Config;
import com.fish.appdemo.R;
import com.fish.appdemo.adapter.WelcomePagerAdapter;

//第一次运行的引导页代码
public class WelcomeActivity extends Activity {

	private ViewPager viewPager;
	private PagerAdapter pagerAdapter;
	private Button startButton;
	private LinearLayout indicatorLayout;
	private ArrayList<View> views;
	private ImageView[] indicators = null;
	private int[] welcomeImgs; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_welcome);

		initView();
	}

	private void initView() {
		viewPager = (ViewPager) findViewById(R.id.viewpage);
		startButton = (Button) findViewById(R.id.start);
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (v.getId() == R.id.start) {
					Config.cacheAppStatus(WelcomeActivity.this, false);

					startActivity(new Intent(WelcomeActivity.this,
							HomeActivity.class));
					overridePendingTransition(R.anim.in_from_right,
							R.anim.out_to_left);
					
					WelcomeActivity.this.finish();
				}
			}
		});
		indicatorLayout = (LinearLayout) findViewById(R.id.indicator);
		views = new ArrayList<View>();
		welcomeImgs = Config.getWelcomeImages();
		indicators = new ImageView[welcomeImgs.length]; // 定义指示器数组大小

		for (int i = 0; i < welcomeImgs.length; i++) {
			// 循环加入图片
			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(welcomeImgs[i]);
			views.add(imageView);
			// 循环加入指示器
			indicators[i] = new ImageView(this);
			indicators[i].setBackgroundResource(R.drawable.indicators_default);
			if (i == 0) {
				indicators[i].setBackgroundResource(R.drawable.indicators_now);
			}
			indicatorLayout.addView(indicators[i]);
		}
		pagerAdapter = new WelcomePagerAdapter(views);
		viewPager.setAdapter(pagerAdapter); // 设置适配器
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				// 显示最后一个图片时显示按钮
				if (position == indicators.length - 1) {
					startButton.setVisibility(View.VISIBLE);
				} else {
					startButton.setVisibility(View.INVISIBLE);
				}
				// 更改指示器图片
				for (int i = 0; i < indicators.length; i++) {
					indicators[position]
							.setBackgroundResource(R.drawable.indicators_now);
					if (position != i) {
						indicators[i]
								.setBackgroundResource(R.drawable.indicators_default);
					}
				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int state) {
			
			}
		});
	}

}
