package com.jxjr.v.activity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

import com.example.copyofhy.R;
import com.jxjr.contract.SearchItemContract.IfSearchItemView;
import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.entity.JrPdaGxhbYgentry;
import com.jxjr.p.SearchItemPresenter;
import com.jxjr.utility.AZUIPub;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;
import com.jxjr.utility.PbF;
import com.jxjr.v.adadper.SjBizAdapter;

public class YgAlterActivity extends Activity {

	String _fname = "";
	String _fid = "";
	String _fnumber = "";
	String _fparam = "";
	BigDecimal _fygxs;

	int position = 0;
	String documentType = "";
	String curygname = "";
	String curygxs;

	GxhbActivity mActivity;
	@BindView(R.id.searchdocument_title)
	TextView title;

	@BindView(R.id.searcher_yg)
	TextView searcher_yg;

	@BindView(R.id.edit_yg)
	EditText yg;

	@BindView(R.id.edit_ygxs)
	EditText ygxs;

	private int requestCode = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.ygentryitem_gxhb_update);
		ButterKnife.bind(this);
		initPara();
		initWidget();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}

	// //逻辑区域 开始
	void initWidget() {
		// mActivity =iGetActivity();

	}

	//
	// protected GxhbActivity iGetActivity() {
	// // TODO Auto-generated method stub
	// return (GxhbActivity) getActivity();
	// }
	void initPara() {
		Intent intent = getIntent();
		documentType = intent.getStringExtra("documentType");
		position = intent.getIntExtra("position", 0);
		requestCode = intent.getIntExtra("requestCode", 0);
		curygname = intent.getStringExtra("ygname");
		curygxs = intent.getStringExtra("ygxs");
		_fid = intent.getStringExtra("ygid");
		_fnumber = intent.getStringExtra("ygno");
		_fname = intent.getStringExtra("ygname");
		_fparam = intent.getStringExtra("ygxs");

		if (Constance.baseinfo_yg.equals(documentType)) {
			title.setText("员工修改");
			yg.setText(curygname);
			ygxs.setText(curygxs);
			// requestCode=Constance.requestCode_baseinfoStock;
		}
		//
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
		if (resultCode == 0) {
			return;
		}
		switch (requestCode) {
		case Constance.requestCode_baseinfoYg: {
			_fname = intent.getStringExtra("fname");
			_fid = intent.getStringExtra("fid");
			_fnumber = intent.getStringExtra("fnumber");
			_fparam = intent.getStringExtra("fparam");
			_fygxs = new BigDecimal(_fparam);
			try {
				yg.setText(_fname);
				ygxs.setText(_fparam);
			} catch (Exception e) {
			}
			break;
		}
		}
	}

	// 逻辑区域 结束

	// @OnClick(R.id.search_btn_back)
	// public void clickBack(){
	// this.finish();
	// }
	@OnClick(R.id.scan_btn_confirm)
	public void clickConfirm() {

		Intent intent = new Intent();
		intent.putExtra("fid", _fid);
		intent.putExtra("fnumber", _fnumber);
		intent.putExtra("fname", yg.getText().toString());
		intent.putExtra("fparam", ygxs.getText().toString());
		this.setResult(requestCode, intent);
		AZUIPub.finishHiddenInput(this);

	}

	@OnClick(R.id.searcher_yg)
	public void stockSelect() {
		yg.requestFocus();
		Intent intent = new Intent();
		intent.setClass(this, SearchItemActivity.class);
		intent.putExtra("documentType", Constance.baseinfo_yg);
		intent.putExtra("requestCode", Constance.requestCode_baseinfoYg);
		this.startActivityForResult(intent, Constance.requestCode_baseinfoYg);
	}

	// 接口区域 开始

	// 接口区域 结束
}
