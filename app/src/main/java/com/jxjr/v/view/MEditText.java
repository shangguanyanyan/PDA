package com.jxjr.v.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Spannable;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("AppCompatCustomView")
public class MEditText extends EditText {
	public MEditText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    public MEditText(Context context, AttributeSet attrs) {
        super(context,attrs);
        initView(context,attrs);
    }

    public MEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context,attrs);
    }
    
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//    	boolean b=super.onTouchEvent(event);
//    	
//    	return b;
//    }
    private void initView(final Context context, AttributeSet attrs) {
//    	this.setOnTouchListener(new OnTouchListener(){
//
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				// TODO Auto-generated method stub
//////				Toast.makeText(context, "11111111111", Toast.LENGTH_SHORT).show();
////				if(event.getAction()==MotionEvent.ACTION_UP){
////					InputMethodManager imm =(InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE); 				 
////
////					boolean isOpen = imm.isActive(); 
////					if (isOpen) {
////						imm.hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
////					}
////				}
//				int inType = ((TextView) v).getInputType(); // back up the input type
//				((TextView) v).setInputType(android.text.InputType.TYPE_NULL); // disable soft input
//				v.onTouchEvent(event);// call native handler
//				((TextView) v).setInputType(inType); // restore input type
//				return false;
//			}
//    		
//    	});
    	
    	this.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus){
					InputMethodManager imm =(InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE); 
					 
					imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//					boolean isOpen = imm.isActive(); 
//					if (isOpen) {
//						imm.hideSoftInputFromWindow(v.getWindowToken(),0);
//					}
					
					
//					imm.hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
//					imm.showSoftInput(v,InputMethodManager.SHOW_FORCED);
//					imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//					Toast.makeText(context, "2222222222", Toast.LENGTH_SHORT).show();
				}
			}
    		
    	});
    }    
}
