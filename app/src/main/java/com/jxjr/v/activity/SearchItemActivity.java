package com.jxjr.v.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
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
import com.jxjr.p.SearchItemPresenter;
import com.jxjr.utility.AZUIPub;
import com.jxjr.utility.Constance;
import com.jxjr.utility.GI;
import com.jxjr.utility.PbF;
import com.jxjr.v.adadper.SjBizAdapter;

public class SearchItemActivity extends Activity 
		implements IfSearchItemView,OnKeyListener,OnItemClickListener{
		
	final int requestCode_selectSupplier=0x00091;
	
	String documentType="";
	
	@BindView(R.id.searchdocument_title)
	TextView title;
	
	@BindView(R.id.search_condition)
	EditText condition;
	
	@BindView(R.id.seachview_listview)
	ListView listview;
	
	@BindView(R.id.searchActivity_layout)
	LinearLayout layout;
	
	List<BaseInfoObjectDTO> queryList=new ArrayList<BaseInfoObjectDTO>();
	List<String[]> displayList= new ArrayList<String[]>();
	SjBizAdapter adapter; 
	
	private SearchItemPresenter presenter;
	
	String selDecType=Constance.selDec_select;
	
	String barcode="";
	
	String parentCondition="";	//表示前置条件，比如取仓位的时候要先取到仓库ID
	
	boolean showCondition=true;
	
	private ProgressDialog mDownloadDialog;
	
	private int requestCode=0;
	
	private String forgid=GI.forgid;
	
    @Override  
    protected void onCreate(Bundle savedInstanceState) { 
    	Intent intent=getIntent();
    	selDecType = intent.getStringExtra("selDecType");
    	if(Constance.selDec_decode.equals(selDecType)){
    		this.setTheme(R.style.ThemeTranslucent);
    	}else{
    		this.setTheme(R.style.NoActionBar);
    	}
    	
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_search_barcode);
    	ButterKnife.bind(this);
    	initPara();
    	initWidget();
    }    
    
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		if(Constance.selDec_decode.equals(selDecType)){
			if(Constance.codeItemClass.get(requestCode).equals("local")){
				presenter.decodeLocationBar(barcode, parentCondition);
			}else{
				presenter.decodeBar(barcode);
			}			

		}else if(Constance.selDec_foreign.equals(selDecType)){
			presenter.decodeByForeignID(barcode);
		}
		if(!showCondition){
//			if(Constance.codeItemClass.get(requestCode).equals("local")){
//				presenter.refreshLocationList(barcode,parentCondition);
//			}else{
//				
//			}
			presenter.refreshListData(barcode);
			
		}
	}
    
  /* (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		presenter.onDestroy();
	}



	//逻辑区域 开始
    void initWidget(){    	
    	int[] entryTvIds = new int[]{};
    	int itemLayoutId=0;
		entryTvIds=new int[]{
    			R.id.dept_0,R.id.dept_A0
    			,R.id.dept_1,R.id.dept_A1
		};
		itemLayoutId=R.layout.baseinfoitem_dept;
		adapter = new SjBizAdapter(this,itemLayoutId,				
				displayList,entryTvIds);
    	adapter.setListRowColored(true);		//设置相邻列不同颜色
    	adapter.setEntryitem_SurfaceId(R.id.baseinfoitem_Surface);
    	listview.setAdapter(adapter);
    	listview.setOnItemClickListener(this);
    	mDownloadDialog=new ProgressDialog(this);
    	mDownloadDialog.setCanceledOnTouchOutside(false);
    	
    	condition.setOnKeyListener(this);
    	
    }
    void initPara(){
    	Intent intent=getIntent();
    	if(Constance.selDec_decode.equals(selDecType)){
    		layout.setVisibility(View.INVISIBLE);
    	}
    	if(intent.hasExtra("forgid")){
    		forgid=intent.getStringExtra("forgid");
    	}
    	documentType = intent.getStringExtra("documentType");
    	barcode=intent.getStringExtra("barcode");
    	if(intent.hasExtra("parentCondition"))
    		parentCondition=intent.getStringExtra("parentCondition");
    	
    	requestCode = intent.getIntExtra("requestCode", 0);
    	showCondition = intent.getBooleanExtra("showCondition", true);
    	if(!showCondition){
    		LinearLayout a=(LinearLayout)findViewById(R.id.layout_condition);
    		a.setVisibility(View.INVISIBLE);
    	}
    	
    	if(Constance.baseinfo_bz.equals(documentType)){
    		title.setText("班组查询");
//    		requestCode=Constance.requestCode_selectSupplier;
    	}else if(Constance.baseinfo_gx.equals(documentType)){
    		title.setText("工序查询");
//    		requestCode=Constance.requestCode_baseinfoDept;
    	}else if(Constance.baseinfo_qxyy.equals(documentType)){
    		title.setText("缺陷原因查询");
//    		requestCode=Constance.requestCode_baseinfoStock;
    	}else if(Constance.baseinfo_dept.equals(documentType)){
    		title.setText("车间查询");
//    		requestCode=Constance.requestCode_baseinfoStock;
    	}else if(Constance.baseinfo_yg.equals(documentType)){
    		title.setText("员工查询");
//    		requestCode=Constance.requestCode_baseinfoStock;
    	}

    	presenter=new SearchItemPresenter(this,documentType);
    }
   
	public List<String[]> entityList2displayList(List<BaseInfoObjectDTO> list){
		List<String[]> displayList=new ArrayList<String[]>();
		for(int i=0;i<list.size();i++){
			BaseInfoObjectDTO tmpEntity=list.get(i);
			String tmp0=tmpEntity.getfNumber();
			String tmp1=tmpEntity.getfName();
			displayList.add(new String[]{tmp0,tmp1});
		}
		return displayList;
	}
    
  //逻辑区域 结束
    
	@OnClick(R.id.search_btn_back)
	public void clickBack(){
		this.finish();
	}
	//搜索
	@OnClick(R.id.search_btn_search)
	public void clickSearch(){
		String conditionStr=PbF.inzStr(condition.getText().toString().trim());
		if(Constance.codeItemClass.get(requestCode).equals("local")){
			presenter.refreshLocationList(conditionStr,parentCondition,this.forgid);
		}else{
			presenter.refreshListData(conditionStr);
		}
		//presenter.refreshListData(conditionStr);
	}
	
  //接口区域 开始
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		
		Intent intent=new Intent();
		String fid=queryList.get(position).getfItemID()+"";
		intent.putExtra("fid", fid);
		String fnumber=queryList.get(position).getfNumber();
		intent.putExtra("fnumber", fnumber);
		String fname=queryList.get(position).getfName();
		intent.putExtra("fname", fname);
		String fparam=queryList.get(position).getfParam();
		intent.putExtra("fparam", fparam);
		this.setResult(requestCode, intent);//返回gxhbygfrg
		AZUIPub.finishHiddenInput(this);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnKeyListener#onKey(android.view.View, int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.search_condition){
			if(event.getAction()==KeyEvent.ACTION_DOWN){
				if(event.getKeyCode()==KeyEvent.KEYCODE_ENTER){
					String conditionStr=PbF.inzStr(condition.getText().toString().trim());
					if(Constance.codeItemClass.get(requestCode).equals("local")){
						presenter.refreshLocationList(conditionStr,parentCondition,this.forgid);
					}else{
						presenter.refreshListData(conditionStr);
					}
				}					
			}
		}		
		return false;
	}

    
	/* (non-Javadoc)
	 * @see com.lf.contract.SearchItemContract.IfSearchItemView#showprocess()
	 */
	@Override
	public void showprocess() {
		// TODO Auto-generated method stub
		mDownloadDialog.setMessage("加载中");
		mDownloadDialog.show();
	}

	/* (non-Javadoc)
	 * @see com.lf.contract.SearchItemContract.IfSearchItemView#hideprocess()
	 */
	@Override
	public void hideprocess() {
		// TODO Auto-generated method stub
		mDownloadDialog.dismiss();
	}


	/* (non-Javadoc)
	 * @see com.lf.contract.SearchItemContract.IfSearchItemView#loadDatafailure(java.lang.String)
	 */
	@Override
	public void loadDatafailure(String error) {
		// TODO Auto-generated method stub
		AZUIPub.showMessage(this, Constance.show_Toast, error);
//		Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
		
	}


	/* (non-Javadoc)
	 * @see com.lf.contract.SearchItemContract.IfSearchItemView#loadDataSucess()
	 */
	@Override
	public void loadDataSucess() {
		// TODO Auto-generated method stub
		
	}


//	/* (non-Javadoc)
//	 * @see com.lf.contract.SearchItemContract.IfSearchItemView#refreshList(java.util.List)
//	 */
//	@Override
//	public void refreshList(List<String[]> display) {
//		// TODO Auto-generated method stub
//		displayList=display;
//		adapter.setList(display);
//		adapter.notifyDataSetChanged();
//		
//	}

	/* (non-Javadoc)
	 * @see com.lf.contract.SearchItemContract.IfSearchItemView#popQueryList(java.util.List)
	 */
	@Override
	public void popQueryList(List<BaseInfoObjectDTO> queryList) {
		// TODO Auto-generated method stub
		if(queryList==null||queryList.size()<1){
			AZUIPub.showMessage(this, Constance.show_Dialog, Constance.check_zeroList);
//			Toast.makeText(this, Constance.check_zeroList, Toast.LENGTH_SHORT).show();
			return;
		}
		
		this.queryList=queryList;//拿到查询得到的list
		this.displayList=entityList2displayList(queryList);
		adapter.setList(this.displayList);
		adapter.notifyDataSetChanged();
	}
	
	/* (non-Javadoc)
	 * @see com.lf.contract.SearchMatContract.IfSearchMatView#popEntity(com.lf.m.DTO.JrMaterialBaseInfoDTO)
	 */
	@Override
	public void popEntity(BaseInfoObjectDTO entity) {
		
		try{
			Intent intent=new Intent();
			String fid=entity.getfItemID()+"";
			intent.putExtra("fid", fid);
			String fnumber=entity.getfNumber();
			intent.putExtra("fnumber", fnumber);
			String fname=entity.getfName();
			intent.putExtra("fname", fname);
			String fparam=entity.getfParam();
			intent.putExtra("fparam", fparam);
			this.setResult(requestCode, intent);
			AZUIPub.finishHiddenInput(this);
		}catch(Exception e){
			Intent intent=new Intent();
			intent.putExtra("error", e);
			this.setResult(0,intent);
			AZUIPub.finishHiddenInput(this);
		}
	}

	/* (non-Javadoc)
	 * @see com.fb.contract.SearchItemContract.IfSearchItemView#popForeignEntity(com.fb.m.DTO.BaseInfoObjectDTO)
	 */
	@Override
	public void popForeignEntity(BaseInfoObjectDTO entity) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.fb.contract.SearchItemContract.IfSearchItemView#decodeFailure(java.lang.String)
	 */
	@Override
	public void decodeFailure(String error) {
		// TODO Auto-generated method stub
		AZUIPub.showMessage(this, Constance.show_Dialog, error);
//		Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
		Intent intent=new Intent();
		intent.putExtra("error", error);
		this.setResult(Constance.requestCode_error,intent);
		AZUIPub.finishHiddenInput(this);
	}

	/* (non-Javadoc)
	 * @see com.fb.contract.SearchItemContract.IfSearchItemView#decodeSucess()
	 */
	@Override
	public void decodeSucess() {
		// TODO Auto-generated method stub
		
	}

	
	
  //接口区域 结束
}
