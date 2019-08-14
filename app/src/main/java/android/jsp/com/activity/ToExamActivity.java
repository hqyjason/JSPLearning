package android.jsp.com.activity;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.jsp.com.jsplearning.R;
import android.jsp.com.util.Configuration;
import android.jsp.com.util.DialogUtil;
import android.jsp.com.util.ManagerUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import application.JSPApplication;


/**
 * 进入考试系统
 * 
 * 更多界面进入Web
 * @author tongxingbida
 *
 */
		
public class ToExamActivity extends Activity{
	
	private ProgressBar pb_loading;
	private TextView tv_loading;
	private LinearLayout ll_pb;
	
	
	private WebView wv_login_exam;
	
//	private TextView tv_progress;

	private final int SUCCESS_0 = 0;
	private final int FAIL_2 = 2;
	
	
	private Handler jHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {

			case SUCCESS_0:
				
				ll_pb.setVisibility(View.GONE);

				String  examUrl=  (String) msg.obj;


				wv_login_exam.loadUrl(examUrl);
				
				break;


			case FAIL_2:

			
				ll_pb.setVisibility(View.VISIBLE);
				ll_pb.setVisibility(View.GONE);
				tv_loading.setVisibility(View.VISIBLE);
				
				String strMsg = (String) msg.obj;
				
				if(TextUtils.isEmpty(strMsg)){
					
					strMsg = "服务器连接失败";
				}

				tv_loading.setText(strMsg);


				break;

			default:
				break;
			}

		}

	};
	private RequestQueue mQueue;
	private String driverLoginName;
	private String driverName;
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_exam);
	

		pb_loading = (ProgressBar) findViewById(R.id.pb_loading);
		tv_loading = (TextView) findViewById(R.id.tv_loading);
		ll_pb = (LinearLayout) findViewById(R.id.ll_pb);
		mQueue = Volley.newRequestQueue(ToExamActivity.this);
		wv_login_exam = (WebView) findViewById(R.id.wv_login_exam);
		
		ll_pb.setVisibility(View.VISIBLE);
		tv_loading.setVisibility(View.VISIBLE);
		pb_loading.setVisibility(View.VISIBLE);
		
		
		WebSettings webSettings = wv_login_exam.getSettings();
		
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setBuiltInZoomControls(true);
		webSettings.setDisplayZoomControls(false);
		webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		webSettings.setDomStorageEnabled(true);
		//设置WebView属性，能够执行Javascript脚本  
		webSettings.setJavaScriptEnabled(true);  
		
		wv_login_exam.setWebViewClient(new WebViewClient(){
			
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
			
				view.loadUrl(url);
				
				return true;
			}
		});
		
		
		
		
//		String driverName = getIntent().getStringExtra("driverName");
//		String driverLoginName = getIntent().getStringExtra("driverLoginName");
		String shortId = getIntent().getStringExtra("shortId");
		
//		JSPApplication tdApplication = (JSPApplication) getApplication();
		JSPApplication tdApplication = (JSPApplication) JSPApplication.getInstance();
		driverLoginName = tdApplication.getDriverLoginName();
		driverName = tdApplication.getDriveName();
		postJoinExam(driverName, driverLoginName, shortId);
		
		
		
//		以前
//		wv_login_exam.loadUrl(examUrl);
		
		
		
		//加载进度
		wv_login_exam.setWebChromeClient(new WebChromeClient(){
			
			@Override
			public void onProgressChanged(WebView view, int progress) {
				// TODO Auto-generated method stub
				super.onProgressChanged(view, progress);
				

				
				tv_loading.setText(progress + "%");


				if (progress == 100) {

					ll_pb.setVisibility(View.GONE);
				}

				
				
			}
		});
		
	}
	
	/**
	 * 
	 * @param driverName 姓名
	 * @param driverLoginName 电话号码
	 * @param shortId  id
	 */

	private void postJoinExam(final String driverName, final String driverLoginName, final String shortId ) {
		
		if (!ManagerUtil.checkNetState(this)) {
			if (ll_pb.getVisibility() == View.VISIBLE) {
				pb_loading.setVisibility(View.GONE);
				tv_loading.setVisibility(View.VISIBLE);
				tv_loading.setText("请检查网络连接是否正常");
			}
			else
				DialogUtil.showToast(this, "请检查网络连接是否正常");
			return;
		}

		ll_pb.setVisibility(View.VISIBLE);
		pb_loading.setVisibility(View.VISIBLE);
		tv_loading.setVisibility(View.GONE);


		String url = Configuration.ADDRESS_MAIN;
		StringBuffer  sb = new StringBuffer(url);
		sb.append(Configuration.JOIN_EXAM);
		Log.e("去考试", ""+sb.toString());
		StringRequest stringRequest = new StringRequest(Method.POST, sb.toString(), 
				new Response.Listener<String>() {


					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
//						strResult = StringUtil.formatJSON(strResult);
						Log.e("考试", ""+response);	
						String date = response.replace("\\", "");
						Log.e("date", date);
						int length = date.length();
						String finalDate = date.substring(1,length-1);
							JSONObject json;
							Message  mMessage = new Message();
							try {
								json = new JSONObject(finalDate);
								String r = json.getString("result");
								String msg = json.getString("msg");
								if (r.equals("true")) {
														

					
										String examUrl = json.optString("examUrl");

										mMessage.what = SUCCESS_0;
										mMessage.obj = examUrl;
										jHandler.sendMessage(mMessage);

								}else {
									mMessage.what = FAIL_2;
									mMessage.obj = msg;
									jHandler.sendMessage(mMessage);
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	
					}
				},
				new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						
					}
				}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<String, String>();
//				try {
//					verName = getApplicationContext().getPackageManager().
//					        getPackageInfo(getApplicationContext().getPackageName(), 0).versionName;
//					
//
//				} catch (NameNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				params.put("driverName", driverName);
				params.put("driverLoginName", driverLoginName);
				params.put("shortId", shortId);
				return params;
			}
		};

	
		mQueue.add(stringRequest);

		
	
	}

	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		if((keyCode == KeyEvent.KEYCODE_BACK) && wv_login_exam.canGoBack()){
			
			wv_login_exam.goBack();
			
			return true;
		}
		
		ToExamActivity.this.finish();
		overridePendingTransition(0, R.anim.top_to_bottom);
		
		return super.onKeyDown(keyCode, event);
	}

}
