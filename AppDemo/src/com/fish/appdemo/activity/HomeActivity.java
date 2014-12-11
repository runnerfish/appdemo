package com.fish.appdemo.activity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fish.appdemo.Config;
import com.fish.appdemo.R;
import com.fish.appdemo.adapter.ContentPagerAdapter;

public class HomeActivity extends FragmentActivity {

	private ViewPager mPager;
	private ContentPagerAdapter mContentAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_home);
		
		initViewPager();
		initActionBar();
	}
	
	private void initActionBar() {
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);

		// Create a tab listener that is called when the user changes tabs.
		TabListener tabListener = new TabListener() {

			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				mPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub

			}

		};

		// Add 3 tabs, specifying the tab's text and TabListener
		for (int i = 0; i < Config.PAGER_TITLES.length; i++) {
			actionBar.addTab(actionBar.newTab().setText(Config.PAGER_TITLES[i])
					.setTabListener(tabListener));
		}

	}
	
	long waitTime = 2000;
	long touchTime = 0;
	@Override
	public void onBackPressed() {
		long currentTime = System.currentTimeMillis();
		if ((currentTime - touchTime) >= waitTime) {
			Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
			touchTime = currentTime;
		} else {
			finish();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void initViewPager() {
		mContentAdapter = new ContentPagerAdapter(getSupportFragmentManager());

		mPager = (ViewPager) findViewById(R.id.pager);
		//Set the number of pages that should be retained to either 
		//side of the current page in the view hierarchy in an idle state.
		mPager.setOffscreenPageLimit(Config.PAGE_LIMIT);
		mPager.setAdapter(mContentAdapter);
		mPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				getActionBar().setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}

		});
		
	}
	
	public interface HomeActivityCallback {

		// Wifi密码输入对话框回调
		void onConnDialogConnectClick(String ssid, String password, boolean isShare);
		void onConnDialogCancelClick();
		
		// 网络通信进度对话框回调
		void onProgressDialogRequestCancel();

		//网络详情对话框回调
		void onDetailDialogDisConnectClick();
		void onDetailDialogDeleteNetworkClick();
		
		//列表详情对话框
		void onItemDetailDialogConnectClick(String ssid);
		void onItemDetailDialogDeleteNetworkClick(String ssid);
	}

}
