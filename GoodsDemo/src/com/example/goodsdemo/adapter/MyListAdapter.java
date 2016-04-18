package com.example.goodsdemo.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.goodsdemo.R;
import com.example.goodsdemo.info.DataInfo;
import com.example.goodsdemo.view.MyItemView;

public class MyListAdapter extends BaseAdapter{

	public Context mContext;
	public List<DataInfo> mDataInfos = new ArrayList<DataInfo>();
	public List<DataInfo> mHeightInfos = new ArrayList<DataInfo>();
	public boolean isSelect = false;	//Ñ¡ÖÐÄ³¸öitem
	public int selectposition = 0;
	
	public  MyListAdapter(Context mContext, List<DataInfo> mDataInfos, List<DataInfo> mHeightInfos) {
		this.mContext = mContext;
		this.mDataInfos = mDataInfos;
		this.mHeightInfos = mHeightInfos;
	}
	
	@Override
	public int getCount() {
		return mDataInfos.size() ;
	}

	@Override
	public Object getItem(int position) {
		return mDataInfos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		DataInfo dataInfo = (DataInfo) getItem(position);
		DataInfo HeightInfo = mHeightInfos.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.mychart_item, null);
			holder.itemView = (MyItemView) convertView.findViewById(R.id.item_view);
			
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.itemView.setChartItem(HeightInfo.count, dataInfo.count+"", position+1+"");
		
		if (isSelect) {
			if (selectposition == position && selectposition >= 0) {
				holder.itemView.setBottomCircleVisible();
//				holder.itemView.isYear();
			}else {
				holder.itemView.setBottomCircleinVisible();
			}
		}else {
			holder.itemView.setBottomCircleinVisible();
		}
		if ((position+1) < mHeightInfos.size()) {
			if (HeightInfo.count >= mHeightInfos.get(position + 1).count) {
				holder.itemView.setToptvUp();
			}else {
				holder.itemView.setToptvDown();
			}
			holder.itemView.setChartItem(HeightInfo.count, dataInfo.count+"", position+1+"");
			
			int priorheight = -1;
			if (position != 0 && mHeightInfos.get(position - 1) != null) {
				priorheight = mHeightInfos.get(position - 1).count;
				if (HeightInfo.count == 0 && priorheight == 0) {
					holder.itemView.isZero = true;
				}else {
					holder.itemView.isZero = false;
				}
			}else {
				holder.itemView.isZero = false;
			}
			holder.itemView.drawlinkline(HeightInfo.count, 160, mHeightInfos.get(position + 1).count);
		}else {
			holder.itemView.isZero = false;
			holder.itemView.isLink = false;
		}
		
		return convertView;
	}
	
	public  void setItemSelector(int selectposition) {
		this.selectposition = selectposition - 1;
		isSelect = true;
	}
	
	class ViewHolder {
		public TextView tv_item;
		public MyItemView itemView;
	}
}
