package com.fish.appdemo;

import android.content.Context;
import android.content.SharedPreferences.Editor;

public class Config {

	protected static final long TIME_DELAY = 1000;

	private static final String APP_ID = "com.fish.appdemo";
	private static final String KEY_FIRST_OPEN = "fist";
	
	public static final CharSequence[] PAGER_TITLES = {"Page1", "Page2", "Page3"};

	public static final int PAGE_LIMIT = 3;

	public static void cacheAppStatus(Context context, Boolean isFirst) {
		Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
		e.putBoolean(KEY_FIRST_OPEN, isFirst);
		e.commit();
	}

	// 是否首次打开App
	public static Boolean getCachedAppStatus(Context context) {
		return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE)
				.getBoolean(KEY_FIRST_OPEN, true);
	}

	public static int[] getWelcomeImages() {
		return new int[] { R.drawable.welcome_01, R.drawable.welcome_02,
				R.drawable.welcome_03 };
	}
	
}
