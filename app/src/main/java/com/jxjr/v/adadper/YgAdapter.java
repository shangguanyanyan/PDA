package com.jxjr.v.adadper;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class YgAdapter extends BaseAdapter {


	List<String[]> list;
	int[] tvIds;
	LayoutInflater mInflater;// 得到一个LayoutInfalter对象用来导入布局
	int layout;
	int entryitem_SurfaceId;
	private int listRowColor_one=Color.parseColor("#E5E5E5");
	private int listRowColor_two=Color.TRANSPARENT;	//透明
	
	private int selectedPosition = 0;// 选中的位置
	/** 构造函数 */
	public YgAdapter(Context context,int layout,List<String[]> list, int[] tvids) {
		this.mInflater = LayoutInflater.from(context);
		this.layout=layout;
		this.list = list;
		this.tvIds = tvids;
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void setList(List<String[]> list){
		this.list=list;
	}
	
	public int getEntryitem_SurfaceId() {
		return entryitem_SurfaceId;
	}

	public void setEntryitem_SurfaceId(int entryitem_SurfaceId) {
		this.entryitem_SurfaceId = entryitem_SurfaceId;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {			
			convertView = mInflater.inflate(layout, null);
			holder = new ViewHolder();
			int len = tvIds.length;
			holder.textviews = new TextView[len];
			for (int i = 0; i < tvIds.length; i++) {
				holder.textviews[i] = (TextView) convertView
						.findViewById(tvIds[i]);
			}
			convertView.setTag(holder);
			
		} else {
			holder = (ViewHolder) convertView.getTag();

		}
		
		String[] row = list.get(position);
		LinearLayout item_Surface=(LinearLayout)convertView
				.findViewById(entryitem_SurfaceId);
		if (selectedPosition == position) {
			item_Surface.setBackgroundColor(Color.parseColor("#87CEFA"));
		} else {
			item_Surface.setBackgroundColor(Color.TRANSPARENT);
		}
		
		int r_Indicate=0;
		for(int i=0;i<tvIds.length;i++){
			if(!"title".equals(holder.textviews[i].getTag())){
				holder.textviews[i].setText(row[r_Indicate]);
				r_Indicate++;
			}	
		}
		return convertView;
	}
	private class ViewHolder {
		TextView[] textviews;
	}
	
	public void setSelectedPosition(int position) {
	    selectedPosition = position;
	}
}
