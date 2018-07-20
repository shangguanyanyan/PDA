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
import android.widget.Toast;

public class SjBizAdapter extends BaseAdapter {


	List<String[]> list;
	int[] tvIds;
	LayoutInflater mInflater;// 得到一个LayoutInfalter对象用来导入布局
	int layout;
	
	int entryitem_SurfaceId;
	
	private Boolean listRowColored=false;		//用来指示相邻两项是否不同颜色区分
	private int listRowColor_one=Color.parseColor("#E5E5E5");
	private int listRowColor_two=Color.TRANSPARENT;	//透明
	

	/** 构造函数 */
	public SjBizAdapter(Context context,int layout,List<String[]> list, int[] tvids) {
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
	
	/**
	 * @return the listRowColored
	 */
	public Boolean getListRowColored() {
		return listRowColored;
	}

	/**
	 * @param listRowColored the listRowColored to set
	 */
	public void setListRowColored(Boolean listRowColored) {
		this.listRowColored = listRowColored;
	}

	/**
	 * @return the listRowColor_one
	 */
	public int getListRowColor_one() {
		return listRowColor_one;
	}

	/**
	 * @param listRowColor_one the listRowColor_one to set
	 */
	public void setListRowColor_one(int listRowColor_one) {
		this.listRowColor_one = listRowColor_one;
	}

	/**
	 * @return the listRowColor_two
	 */
	public int getListRowColor_two() {
		return listRowColor_two;
	}

	/**
	 * @param listRowColor_two the listRowColor_two to set
	 */
	public void setListRowColor_two(int listRowColor_two) {
		this.listRowColor_two = listRowColor_two;
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
		//LinearLayout entryitem_Surface;
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
		
		if(listRowColored){
			item_Surface.setBackgroundColor(position%2==0?listRowColor_one:listRowColor_two);
		}
		int r_Indicate=0;
		for(int i=0;i<tvIds.length;i++){
			if(!"title".equals(holder.textviews[i].getTag())){
				holder.textviews[i].setText(row[r_Indicate]);
				r_Indicate++;
			}	
		}
//		for (int i = 0; i < row.length; i++) {
//			if(!"title".equals(holder.textviews[i].getTag())){
//				holder.textviews[i].setText(row[r_Indicate]);
//				r_Indicate++;
//			}			
//		}
		return convertView;
	}
	private class ViewHolder {
		TextView[] textviews;
	}
	public void remove(int position) {
		// TODO Auto-generated method stub
		if(position<0) {
			//Toast.makeText(this, "没有这个结果", Toast.LENGTH_LONG).show();
			return;
		}
		else {
			list.remove(position);
		}
	}
}
