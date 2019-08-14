package android.jsp.com.jsplearning;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.jsp.com.activity.HomeActivity;
import android.jsp.com.util.ManagerUtil;
import android.jsp.com.util.StringUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import application.JSPApplication;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_do_login;
    private EditText et_uname;
    private EditText et_pwd;
    private TextView tv_to_version;
    private String loginName;
    private String loginPassword;
    private RequestQueue mQueue;
    private String IMEI;
    private String verName;
    private SharedPreferences sp;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE); //
        String saved_username = sp.getString("USERNAME", ""); //获取sp里面存储的数据
        String saved_password = sp.getString("PASSWORD","");
        et_uname.setText(saved_username);//将sp中存储的username写入EditeText
        et_pwd.setText(saved_password);//将sp中存储的password写入EditeText

        mQueue = Volley.newRequestQueue(MainActivity.this);
    }

    private void initView() {
        // TODO Auto-generated method stub
        btn_do_login = (Button)findViewById(R.id.btn_do_login);
        et_uname = (EditText)findViewById(R.id.et_uname);
        et_pwd = (EditText)findViewById(R.id.et_pwd);
        tv_to_version = (TextView)findViewById(R.id.tv_to_version);
        btn_do_login.setOnClickListener(MainActivity.this);
        tv_to_version.setText("版本号:"+ManagerUtil.getVerName(MainActivity.this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_do_login:
                doLogon();
                break;

            default:
                break;
        }
    }

    private void doLogon() {

        loginName = StringUtil.trim(et_uname.getText().toString());
        if (loginName==null || (loginName).length()==0) {
            et_uname.requestFocus();
//			DialogUtil.showToast(MainActivity.this, R.string.alert_login_no_value_login_name);
            return;
        }

        loginPassword = et_pwd.getText().toString();
        if (loginPassword.length()==0) {
            et_pwd.requestFocus();
//			DialogUtil.showToast(MainActivity.this, R.string.alert_login_no_value_login_password);
            return;
        }
        Log.e("loginPassword", loginPassword);
        dialog = new ProgressDialog(this,ProgressDialog.THEME_HOLO_LIGHT);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//转盘

        dialog.setCancelable(true);

        dialog.setCanceledOnTouchOutside(false);

        dialog.setTitle("提示");

        dialog.setMessage("正在加载，请稍后……");

        dialog.show();


        String url = "http://elearning.xiakesong.cn/manage/user.login";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    private String strResult;

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
//						strResult = StringUtil.formatJSON(strResult);
                        String str = StringUtil.formatJSON(response);
                        JSONObject json;
                        try {
                            json = new JSONObject(str);
                            String r = json.getString("r");
                            if (r.equals("1")) {
                                dialog.dismiss();
                                Log.e("登陆成功", ""+str);
//									String driveName = json.getString("driverName");
                                String driveName = json.optString("driverName");
 //                               JSPApplication application = (JSPApplication)getApplication();
                                JSPApplication application = (JSPApplication) JSPApplication.getInstance();
                                application.setDriverLoginName(loginName);
                                application.setDriveName(driveName);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("USERNAME", et_uname.getText().toString());
                                editor.putString("PASSWORD",et_pwd.getText().toString());
                                editor.commit();

                                Intent zu = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(zu);
                                MainActivity.this.finish();
                            }else {
                                showError(r);
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
                        dialog.dismiss();
                        AlertDialog.Builder builder  = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("提示" ) ;
                        builder.setMessage("连接服务器失败" ) ;
                        builder.setPositiveButton("确定" ,  null );
                        builder.show();
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
                params.put("lt", "driverMobile");
                params.put("imei", ManagerUtil.getIMEI(MainActivity.this));
                params.put("n", loginName);
                params.put("p", loginPassword);
                params.put("version", ManagerUtil.getVerName(MainActivity.this));
                params.put("os", "android");
                return params;
            }
        };

        //新加的代码
        stringRequest.setShouldCache(false);
        mQueue.add(stringRequest);
    }

    private void showError(String result) {
        // TODO Auto-generated method stub
        dialog.dismiss();
        AlertDialog.Builder builder  = new AlertDialog.Builder(MainActivity.this);

        if (result.equals("4")) {

            builder.setTitle("提示" ) ;
            builder.setMessage("用户名密码错误" ) ;
            builder.setPositiveButton("确定" ,  null );
            builder.show();
        }else if (result.equals("3")) {

            builder.setTitle("提示" ) ;
            builder.setMessage("连接服务器失败" ) ;
            builder.setPositiveButton("确定" ,  null );
            builder.show();
        }else if (result.equals("33")) {

            builder.setTitle("提示" ) ;
            builder.setMessage("网络连接失败" ) ;
            builder.setPositiveButton("确定" ,  null );
            builder.show();
        }
    }
}
