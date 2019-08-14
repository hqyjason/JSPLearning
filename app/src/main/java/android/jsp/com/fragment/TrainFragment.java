package android.jsp.com.fragment;


import android.annotation.SuppressLint;
import android.jsp.com.jsplearning.R;
import android.jsp.com.util.ManagerUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TrainFragment extends Fragment {
	private View messageLayout;
	private WebView ot_webview;
	
	public  static final String TRAIN_ONLINE  = "http://elearning.xiakesong.cn/";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (messageLayout == null) {
			messageLayout = inflater.inflate(R.layout.activity_train, container, false);
			initView();
		}
	
		return messageLayout;
	}

	@SuppressLint("NewApi")
	private void initView() {
		// TODO Auto-generated method stub
		ot_webview=(WebView)messageLayout.findViewById(R.id.ot_webview);
		WebSettings settings = ot_webview.getSettings();
		ot_webview.canGoBack();
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
	    settings.setUseWideViewPort(true);//设置webview推荐使用的窗口
	    settings.setLoadWithOverviewMode(true);//设置webview加载的页面的模式
	    settings.setDisplayZoomControls(false);//隐藏webview缩放按钮
	    settings.setJavaScriptEnabled(true); // 设置支持javascript脚本
	    settings.setAllowFileAccess(true); // 允许访问文件
	    settings.setBuiltInZoomControls(true); // 设置显示缩放按钮
	    settings.setSupportZoom(true); // 支持缩放	AAZ
	    final String onlineUrl = TRAIN_ONLINE;
	    ot_webview.loadUrl(onlineUrl);
	    ot_webview.setWebChromeClient(new WebChromeClient());
		ot_webview.setWebViewClient(new WebViewClient(){
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);
			return true;
		}
		@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				ot_webview.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub					
						String imei = ManagerUtil.getIMEI(getActivity());
						Log.e("imei", imei);
						String loginType = "2";
						String userType = "driverMobileManager";

						ot_webview.loadUrl("javascript:sendAccountLoginRequestForMobile("+imei+","+loginType+",'"+userType+"')");

					}
				});
			}
	});	
	}
	
/*	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	// TODO Auto-generated method stub
	if (keyCode == KeyEvent.KEYCODE_BACK && ot_webview.canGoBack()) {
		ot_webview.goBack();
		return true;
	}
	return super.onKeyDown(keyCode, event);
}*/	
}
