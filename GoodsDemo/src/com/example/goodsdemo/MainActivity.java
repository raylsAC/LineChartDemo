package com.example.goodsdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.goodsdemo.adapter.MyListAdapter;
import com.example.goodsdemo.info.DataInfo;
import com.example.goodsdemo.view.HorizontalListView;
import com.example.goodsdemo.view.MyItemView;

public class MainActivity extends Activity {

	public HorizontalListView mChartListView; 
	public MyListAdapter mAdapter;
	public List<DataInfo> mDataInfos = new ArrayList<DataInfo>();  //表示真实数据的集合
	List<DataInfo> mHeightInfos = new ArrayList<DataInfo>();	//表示报表中高度的集合
	public int clickcount = 1;
	
	public Button mBtnSetselection;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initData();
        initUI();
    }
    
	public void initData() {
		for (int i = 0; i < 30; i++) {
			DataInfo dataInfo = new DataInfo();
			Boolean isBoolean = true;
			if (isBoolean) {
				if (i < 13) {
					if (i < 3 || i == 4 || i == 5 || i == 7) {
						dataInfo.count = 0;
					}else {
						dataInfo.count = 600;
					}
				}else {
					dataInfo.count = new Random().nextInt(599)+1;
				}
			}else {
				dataInfo.count = new Random().nextInt(599)+1;
			}
			mDataInfos.add(dataInfo);
		}
		Datatoheight(getDataMax());
	}
	
	public int getDataMax() {
		int max = 0;
		for (DataInfo dataInfo : mDataInfos) {
			if (dataInfo.count > max) {
				max = dataInfo.count;
			}
		}
		return max;
	}
	
	public void Datatoheight(int Max) {
		for (DataInfo dataInfo : mDataInfos) {
			int height = 0;
			if (dataInfo.count == 0) {
				height = 0;
			}else {
				height = (dataInfo.count * MyItemView.VlinedefaultHeight) / Max;
			}
			DataInfo heightInfo = new DataInfo();
			heightInfo.count = height;
			mHeightInfos.add(heightInfo);
		}
	}
	

	private void initUI() {
		mChartListView = (HorizontalListView) findViewById(R.id.lv_horizontal);
		mAdapter = new MyListAdapter(this, mDataInfos, mHeightInfos);
		mChartListView.setAdapter(mAdapter);
		
		mBtnSetselection = (Button)findViewById(R.id.btn_setselect);
		mBtnSetselection.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						mChartListView.setSelection(clickcount);
						mAdapter.setItemSelector(clickcount++);
						if (clickcount == 31) {
							clickcount = 1;
						}
						mAdapter.notifyDataSetChanged();
					}
				}, 500);
			}
		});
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mAdapter.notifyDataSetChanged();
	}
}
