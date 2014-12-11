package com.fish.appdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fish.appdemo.R;

public class ThirdFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_third, container, false);
		return view;
	}

	public static ThirdFragment newInstance() {
		return new ThirdFragment();
	}

}
