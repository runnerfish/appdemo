package com.fish.appdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fish.appdemo.Config;
import com.fish.appdemo.fragment.FirstFragment;
import com.fish.appdemo.fragment.SecondFragment;
import com.fish.appdemo.fragment.ThirdFragment;

public class ContentPagerAdapter extends FragmentPagerAdapter {

	public ContentPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	
	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 0:
			return FirstFragment.newInstance();
		case 1:
			return SecondFragment.newInstance();
		case 2:
			return ThirdFragment.newInstance();
		default:
			return null;
		}
	}

	@Override
	public int getCount() {
		return Config.PAGER_TITLES.length;
	}

}
