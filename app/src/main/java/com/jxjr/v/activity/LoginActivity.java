package com.jxjr.v.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.copyofhy.R;
//import com.jxjr.m.DTO.BaseInfoObjectDTO;
import com.jxjr.m.network.BaseDataDownlodad;
import com.jxjr.m.network.Login;
import com.jxjr.m.network.RResult;
import com.jxjr.m.pojo.BaseInfoObjectPOJO;
import com.jxjr.utility.AZUIPub;
import com.jxjr.utility.ConnectFun;
import com.jxjr.utility.Constance;
import com.jxjr.utility.FindFilePath;
import com.jxjr.utility.GI;
import com.jxjr.utility.HttpFun;
import com.jxjr.utility.LoginPreferencesSV;
import com.jxjr.utility.RequestLoad;
import com.jxjr.utility.SelfDefException;
import com.jxjr.utility.StringUtils;
import com.jxjr.utility.TxtReader;

import butterknife.BindView;


public class LoginActivity extends Activity implements OnClickListener,
        OnCheckedChangeListener, OnCreateContextMenuListener, UpdateActivity.UpdateInterface {

    //AppHelper myApp;

    private boolean loginbreak = false;    //如果更新，则不登陆。

    Boolean c_releaseMode = false;        //当前Activity是否发布模式  false:测试模式

    @BindView(R.id.login_username)
    EditText login_username;
    @BindView(R.id.login_password)
    EditText login_password;
    @BindView(R.id.login_button)
    Button login_button;
    @BindView(R.id.login_exit)
    Button login_exit;
    @BindView(R.id.login_host)
    EditText login_host;
    @BindView(R.id.IPLab)
    TextView login_hostLab;
    @BindView(R.id.login_checkbox)
    CheckBox checkBox;
    @BindView(R.id.versionText)
    TextView versionName;
    @BindView(R.id.login_orgname)
    TextView forgname;
    AlertDialog.Builder forgSelecter;
    String forgid = "1";
    @BindView(R.id.login_Server)
    TextView serverName;    //账套
    AlertDialog.Builder serverSelecter;


    String serverMsg;    //用来接收服务器的回传消息

    private LoginPreferencesSV loginPreferencesSV;
    private Handler mHandler;
    final static int loginSuccess = 0x000ff01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //myApp =  (AppHelper) getApplication();
        initWidget();
//		checkConnect();		//检查是否连接
    }

    private void initWidget() {
        setContentView(R.layout.activity_login);

        if (GI.isLOGIN == true) {
            Intent intent = new Intent(this, IndexActivity.class);
            startActivity(intent);
            finish();
        }


        loginPreferencesSV = new LoginPreferencesSV(this);


        login_button.setOnClickListener(this);
        login_exit.setOnClickListener(this);
        checkBox.setOnCheckedChangeListener(this);
        login_host.setVisibility(View.INVISIBLE);
        login_hostLab.setVisibility(View.INVISIBLE);
        forgname.setOnClickListener(this);
        serverName.setOnClickListener(this);

        Map<String, String> params = loginPreferencesSV.getPerferences();
        login_username.setText(params.get("login_username"));
        login_password.setText(params.get("login_password"));

        GI.HOSTAddress = params.get("login_host");
        if (StringUtils.isEmpty(GI.HOSTAddress)) {
            GI.HOSTAddress = "61.153.238.186:8189";
        }
        if (!StringUtils.isEmpty(GI.HOSTAddress)) {
            login_host.setText(GI.HOSTAddress);
        }
        this.forgid = StringUtils.cnulls(params.get("forgid"), "1");
        this.forgname.setText(Constance.MutiOrgCon.forgMap.get(forgid));

        this.serverName.setText(Constance.ServerCon.serverMap
                .get(login_host.getText().toString()));
        versionName.setText("版本号：" + AZUIPub.getVersionName(this));

        GI.HOSThttp = GI.HOSTPRIX + GI.HOSTAddress;
        intiListener();
//		loginHandler = new Handler(){
//			@Override
//			public void handleMessage(Message msg){
//				if(msg.what == loginSuccess){
//					
//				}
//			}
//		};
    }

    //初始化监听
    protected void intiListener() {
        login_username.setImeOptions(EditorInfo.IME_ACTION_SEND);
        login_username.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发  
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发  
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。  
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件  
                    if ("".equals(AZUIPub.text2String(v).trim())) {
                        return false;
                    }
                    String fbarcode = AZUIPub.text2String(v).trim();
                    String[] fcode = fbarcode.split("/");
                    String ftype = fcode[0];
                    String femp = fcode[2];
                    if (ftype.equals("B1")) {
                        login_username.setText(femp);
                    } else {
                        login_username.setText("");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        login_username.requestFocus();
                                    }
                                });
                            }
                        }).start();
                        login_username.setError("请扫描员工！");
                    }
                }
                return false;
            }
        });


        login_username.setOnKeyListener(
                new OnKeyListener() {
                    int lastF12Event = 0;

                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        // TODO Auto-generated method stub
                        if (event.getAction() == KeyEvent.ACTION_DOWN) {
                            if (GI.isScanKeyCode(keyCode) && lastF12Event < 1) {
                                login_username.setText("");
                                lastF12Event++;        //防止多次触发事件
                            }
                        } else {
                            lastF12Event = 0;
                        }

//					System.out.println("keyCode===="+keyCode);
                        return false;
                    }
                }
        );

        forgSelecter = new AlertDialog.Builder(this);
        forgSelecter.setTitle(Constance.MutiOrgCon.title);
        final List<String> tmpKeys = new ArrayList<String>(Constance.MutiOrgCon.forgMap.keySet());
        final List<String> tmpValues = new ArrayList<String>(Constance.MutiOrgCon.forgMap.values());

        forgname.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                forgSelecter.setSingleChoiceItems(tmpValues.toArray(new String[tmpValues.size()]),
                        tmpKeys.indexOf(forgid), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("which==" + tmpKeys.get(which));
                                forgid = tmpKeys.get(which);
                                forgname.setText(Constance.MutiOrgCon.forgMap.get(forgid));
                                dialog.dismiss();
                            }
                        });
                forgSelecter.show();
            }
        });

        serverSelecter = new AlertDialog.Builder(this);
        serverSelecter.setTitle(Constance.ServerCon.title);
        final List<String> ser_tmpKeys = new ArrayList<String>(Constance.ServerCon.serverMap.keySet());
        final List<String> ser_tmpValues = new ArrayList<String>(Constance.ServerCon.serverMap.values());
        serverName.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                serverSelecter.setSingleChoiceItems(ser_tmpValues.toArray(new String[ser_tmpValues.size()]),
                        ser_tmpKeys.indexOf(login_host.getText().toString().trim())
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("login_host==" + ser_tmpKeys.get(which));
                                login_host.setText(ser_tmpKeys.get(which));
                                serverName.setText(Constance.ServerCon
                                        .serverMap.get(login_host.getText().toString().trim()));
                                dialog.dismiss();
                            }
                        });
                serverSelecter.show();
            }
        });

    }

    public void checkConnect() {
//		try{
//			CheckConnectDTO dto=new CheckConnectDTO("1");
//			String response = HttpFun.doPost(dto.getService(), dto.toString());
//			RResult result = new RResult(response);
//			dto.decode(result.getData());
//			String receive=dto.getReceive();
//			if(!"1".equals(receive))
//				Toast.makeText(this, "服务器返回异常信息", Toast.LENGTH_SHORT).show();
//		}catch(Exception e){			
//			Toast.makeText(this, "无法连接服务器，请检查网络连接是否异常或弱信号", Toast.LENGTH_SHORT).show();
//		}
    }

    @Override
    public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
        // TODO Auto-generated method stub
        if ("~".equals(login_password.getText().toString())) {
            login_host.setVisibility(arg1 ? View.VISIBLE : View.INVISIBLE);
            login_hostLab.setVisibility(arg1 ? View.VISIBLE : View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        int id = v.getId();
        if (id == R.id.login_button) {
            GI.HOSTAddress = login_host.getText().toString().trim();
            String username = login_username.getText().toString().trim();
            String pwd = login_password.getText().toString().trim();
            GI.HOSThttp = GI.HOSTPRIX + GI.HOSTAddress;


            loginPreferencesSV.save(username, pwd, GI.HOSTAddress, forgid);
            //Toast.makeText(this,"正在检查更新。", Toast.LENGTH_SHORT).show();
            // 版本校验

            try {

                if (ConnectFun.isNetworkConnected(this) == true && c_releaseMode) {


                    //checkVersion();

                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                //AZUIPub.showMessage(this, Constance.show_Dialog, e.toString());
                AZUIPub.showMessage(this, Constance.show_Dialog, "更新版本地址异常");
            }

            if (loginbreak) {    //版本更新则取消登录  false
//				this.finish();
                return;
            }
            LoginServiceInner loginServiceInner = new LoginServiceInner(this);
            new Thread(loginServiceInner).start();
        } else if (id == R.id.login_exit) {
            System.exit(0);
        }

    }

    private void service() throws SelfDefException, Exception {


//		if (pwd.equals("")) {
//			Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
//			return;
//		}
        String acctid = "";
        String servername = serverName.getText().toString().trim();
        if (servername.equals("金蝶测试账套")) {
            acctid = "5a542800070a46";
        } else if (servername.equals("内网测试账套")) {
            acctid = "5a9fb2571fbac2";
        } else if (servername.equals("内网正式账套")) {
            acctid = "5acd6d1b0fd74f";
        } else if (servername.equals("测试账套")) {
            acctid = "5a542800070a46";
        }

        String username = login_username.getText().toString().trim();
        String pwd = login_password.getText().toString().trim();

        Login login = new Login(username, pwd, acctid);
        login.setForgid(forgid);
        if (c_releaseMode) {
            String requestStr = login.toString();
            String requestUrl = login.iGetLoginURL();
            String response = HttpFun.doPost(requestUrl, requestStr);
            if (null == response) {
                throw new SelfDefException(SelfDefException.noReponse);
            }
            RResult result = new RResult(response);
            if (!result.isFlag()) {
                //防止错误的Message为Null
                result.setMessage((StringUtils.cnulls(result.getMessage())));
            }

            handlerMessage("text", result.getMessage());
//			serverMsg=result.getMessage();
            //返回Flag为false的情况
            if (!result.isFlag()) {
                throw new SelfDefException(result.getMessage());
            }

            //baseDataDownload.decode(result.getData());
            login.decode((String) result.getData());
            GI.SESSION = login.getLoginResult();
            GI.SESSION.setfPassword(pwd);
            GI.SESSION.setfAcctID(acctid);
            GI.SESSION.setForgid(forgid);
            GI.isLOGIN = true;
        } else {
            String requestStr = login.toString();
            String requestUrl = login.iGetLoginURL();
            //String response = HttpFun.doPost(requestUrl, requestStr);


            TxtReader reader = new TxtReader("/LF", "tst_loginresult.txt");    //storage
            reader.readTxt();
            String response = reader.getContent();
            RResult result = new RResult(response);
            handlerMessage("text", result.getMessage().toString());
            login.decode((String) result.getData());
            GI.SESSION = login.getLoginResult();
            GI.SESSION.setfPassword(pwd);
            GI.SESSION.setfAcctID(acctid);
            GI.forgid = GI.SESSION.getForgid();
            GI.isLOGIN = true;
        }

        BaseDataDownlodad baseDataDownload = new BaseDataDownlodad();

        String response2 = "";
        if (c_releaseMode) {
            response2 = HttpFun.doPost(baseDataDownload.iGetAction(), baseDataDownload.toString());
        } else {
            TxtReader reader = new TxtReader("/LF", "tst_baseinfo.txt");    //storage
            reader.readTxt();
            response2 = reader.getContent();
        }
        RResult result = new RResult(response2);
        if (!result.isFlag()) {
            //防止错误的Message为Null
            result.setMessage((StringUtils.cnulls(result.getMessage())));
        }
        if (!result.isFlag()) {
            throw new SelfDefException(result.getMessage());
        }
        handlerMessage("text", result.getMessage());            //获取基础资料成功
        baseDataDownload.decode(result.getData());

//		myApp.getDaoSession().getBaseInfoObjectPOJODao().deleteAll();
//		List<BaseInfoObjectDTO> fBaseInfo=baseDataDownload.getBaseInfoObjectDTO();
//		for (BaseInfoObjectDTO tmp : fBaseInfo) {
//			myApp.getDaoSession().getBaseInfoObjectPOJODao().insertInTx(JSON.parseObject(JSON.toJSONString(tmp), BaseInfoObjectPOJO.class));
//		}

        Intent intent = new Intent(this, IndexActivity.class);
        intent.putExtra("serverName", AZUIPub.text2String(serverName).trim());
        startActivity(intent);
        finish();
    }

    private void checkVersion() throws NameNotFoundException, IOException {
        PackageManager packageManager = getPackageManager();
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),
                0);
        String current_version = packInfo.versionName;

        JSONObject response = JSONObject.parseObject(HttpFun.doPost(GI.HOSThttp + Constance.webprefix + "/get-last-version!get.action?deviceId="
                + GI.deviceId + "&version=" + current_version + "&hostIp=" + GI.HOSTAddress, "{}"));

        String server_version = response.getJSONObject("data").getString("version");//.getFloat("version");
        String url = response.getJSONObject("data").getString("url");
        url = GI.HOSTPRIX + GI.HOSTAddress + url;

//		if(false){		//测试0911
//			server_version="2.2.4";
//			url=GI.HOSThttp+"/apps/XA-2.2.4.apk";
//		}

//		AZUIPub.showMessage(this, Constance.show_Toast, "server_version=="
//				+server_version+" URL=="+url);
        String apkname = "HY-" + server_version + ".apk";
        if (server_version.compareTo(current_version) > 0) {
            UpdateActivity updateManager = new UpdateActivity(LoginActivity.this, apkname, url);
            updateManager.setiFupdate(this);
            updateManager.checkUpdate();
            loginbreak = true;
        } else {
            loginbreak = false;
        }
    }


//subclass area	

    @Override
    public void updatecancel() {
        // TODO Auto-generated method stub
        LoginServiceInner loginServiceInner = new LoginServiceInner(this);
        new Thread(loginServiceInner).start();
    }


    class LoginServiceInner extends RequestLoad {

        public LoginServiceInner(Context context) {
            super(context, "登陆", "登陆中...");
        }

        @Override
        public void doPost() {
            // TODO Auto-generated method stub

            try {
                //Thread.sleep(5000);
//				mHandler=super.mHandler;  //mhander 和下面语句对比
                LoginActivity.this.mHandler = super.mHandler;  //mhander 主线程，子线程都有
                service();
                // handlerMessage("tip",PbF.tip_LoginSuc);
            } catch (SelfDefException e) {

                handlerMessage("text", e.getMessage().toString());
            } catch (Exception e) {
                handlerMessage("text", e.getMessage().toString());

            }
        }
    }

    void handlerMessage(String Key, String messageString) {
        Message msg = mHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putString(Key, messageString);
        msg.setData(bundle);
        mHandler.sendMessage(msg);
    }
}
