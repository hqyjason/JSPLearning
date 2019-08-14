package android.jsp.com.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import application.JSPApplication;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import android.content.Intent;
import android.jsp.com.activity.ToExamActivity;
import android.jsp.com.jsplearning.R;
import android.jsp.com.pojo.JoinExamInfo;
import android.jsp.com.util.Configuration;
import android.jsp.com.util.ManagerUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ExamFragment extends Fragment {
	
	private View messageLayout;
	private ListView lv_join_exam_list;
	private ProgressBar pb_loading;
	private TextView tv_loading;
	private LinearLayout ll_pb;
	private View txView;
	private Button btn_myaccount_txmore;
	private LinearLayout ll_myaccount_loading;
	private ArrayList<JoinExamInfo> jeInfosList;
	private RequestQueue mQueue;
	private String driverLoginName;
	
	private int pageNo = 1;
	
	/**
	 * 单页
	 */
	private List<JoinExamInfo> jeInfos = new ArrayList<JoinExamInfo>();
	
	/**
	 * 当前展示页数
	 */
	private int currentPage ;
	private String strCurrentPage = "";
	/**
	 * 总页数
	 */
	private int totalPage;
	private String strTotalPage;
	private JoinExamAdapter adapter;
	private String driverName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (messageLayout == null) {
			messageLayout = inflater.inflate(R.layout.activity_exam, container, false);
			initView();
			jeInfosList = new ArrayList<JoinExamInfo>();
			mQueue = Volley.newRequestQueue(getActivity());
			
			addFooterTx();
//			TDApplication application = (TDApplication) TDApplication.getInstance();
//			driverLoginName = application.getDriverLoginName();
			driverLoginName =((JSPApplication) getActivity().getApplication()).getDriverLoginName();
			driverName = ((JSPApplication) getActivity().getApplication()).getDriveName();
			//考试
			joinExam(driverLoginName,pageNo);
			
			lv_join_exam_list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position,
						long id) {
					// TODO Auto-generated method stub

					String shortId = jeInfos.get(position).getShortId();//"3YJFJn"

					Log.e("shortId", ""+shortId);
					Intent iT = new Intent(getActivity(), ToExamActivity.class);

					iT.putExtra("driverName", driverName);
					iT.putExtra("driverLoginName", driverLoginName);
					iT.putExtra("shortId", shortId);

					startActivity(iT);



				}
			});
		}
	
		return messageLayout;
	}

	private void initView() {
		// TODO Auto-generated method stub
		lv_join_exam_list = (ListView)messageLayout.findViewById(R.id.lv_join_exam_list);
		pb_loading = (ProgressBar) messageLayout.findViewById(R.id.pb_loading);
		tv_loading = (TextView) messageLayout.findViewById(R.id.tv_loading);
		ll_pb = (LinearLayout) messageLayout.findViewById(R.id.ll_pb);
	}
	
	/**
	 * 提现底部
	 */
	private void addFooterTx() {
		// TODO Auto-generated method stub

		txView=LayoutInflater.from(getActivity()).inflate(R.layout.list_footer_xkz, null);
		btn_myaccount_txmore=(Button) txView.findViewById(R.id.btn_myaccount_xkzmore);

		lv_join_exam_list.addFooterView(txView);


		ll_myaccount_loading=(LinearLayout) txView.findViewById(R.id.ll_myaccount_loading);
		ll_myaccount_loading.setVisibility(View.GONE);
		
		btn_myaccount_txmore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ll_myaccount_loading.setVisibility(View.VISIBLE);
				btn_myaccount_txmore.setVisibility(View.GONE);

				joinExam(driverLoginName,pageNo);


			}
		});

	}
	
	
	private void joinExam(String driverLoginName2, int pageNo2) {
		// TODO Auto-generated method stub
		String url = Configuration.ADDRESS_MAIN;
		StringBuffer  sb = new StringBuffer(url);
		sb.append(Configuration.JOIN_EXAM);
		sb.append("?loginName=").append(driverLoginName);
		sb.append("&driverIMEI=").append(ManagerUtil.getIMEI(getActivity()));
		sb.append("&pageNo=").append(pageNo);
		Log.e("sb", ""+sb.toString());
		
		StringRequest request = new StringRequest(sb.toString(), 
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						Log.e("shuju ", response);
						int length = response.length();
						String date=response.substring(1, length-1);
						Log.e("data", ""+date);
						String date2 = date.replace("\\", "");
						Log.e("data2", ""+date2);
						try {
							JSONObject json = new JSONObject(date2);
							setSuccess(json);
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
				});
		mQueue.add(request);
	}
	
	
	private void setSuccess(JSONObject object) {
		// TODO Auto-generated method stub

		try {
			String result = object.getString("result");
			if (result.equals("true")) {
				JSONObject data = object.optJSONObject("data");

				strCurrentPage = data.optString("currentPage");
				strTotalPage = data.optString("totalPage");

				currentPage = Integer.parseInt(strCurrentPage);
				totalPage = Integer.parseInt(strTotalPage);

				JSONArray resultList = data.optJSONArray("resultList");

				Gson gson = new Gson();



				JoinExamInfo jei ;

				int rLength  = resultList.length();

				if(rLength == 0){

					lv_join_exam_list.setVisibility(View.GONE);
					ll_pb.setVisibility(View.VISIBLE);
					tv_loading.setVisibility(View.VISIBLE);
					pb_loading.setVisibility(View.GONE);
					tv_loading.setText("目前没有考试题目");
					return;
				}


				for (int i = 0; i < rLength; i++) {


					jei = gson.fromJson(resultList.getString(i), JoinExamInfo.class);


					jeInfos.add(jei);

					jeInfosList.add(jei);

				}




				if(adapter == null){



					adapter = new JoinExamAdapter(jeInfosList);
				}

				adapter.notifyDataSetChanged();

				lv_join_exam_list.setAdapter(adapter);

				ll_pb.setVisibility(View.GONE);
				lv_join_exam_list.setVisibility(View.VISIBLE);

				ll_myaccount_loading.setVisibility(View.GONE);


				pageNo = currentPage;

				pageNo++ ;

				if(pageNo > totalPage ){

					btn_myaccount_txmore.setVisibility(View.GONE);
				}else if(pageNo == totalPage){

					btn_myaccount_txmore.setVisibility(View.VISIBLE);
				}else {

					btn_myaccount_txmore.setVisibility(View.VISIBLE);
				}

			}else {
				lv_join_exam_list.setVisibility(View.GONE);
				ll_pb.setVisibility(View.VISIBLE);
				pb_loading.setVisibility(View.GONE);
				tv_loading.setVisibility(View.VISIBLE);


				//				Toast.makeText(JoinExamActivity.this, "+message", Toast.LENGTH_SHORT).show();

				String msg1 = object.getString("msg");

				if(TextUtils.isEmpty(msg1)){

					msg1 = "服务器未连接成功";
				}

				tv_loading.setText(msg1);
			}
			


		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private class JoinExamAdapter extends BaseAdapter{

		private List<JoinExamInfo> jeiLists;
		private LayoutInflater inflater;

		public JoinExamAdapter(List<JoinExamInfo> jeiLists) {
			super();
			this.jeiLists = jeiLists;
			inflater = LayoutInflater.from(getActivity());
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return jeiLists.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return jeiLists.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			ListHolder holder = null;

			if(convertView == null){

				convertView = inflater.inflate(R.layout.list_item_join_exam_layout, parent, false);

				holder = new ListHolder();

				holder.tv_jexam_item_wei = (TextView) convertView.findViewById(R.id.tv_jexam_item_wei);
				holder.tv_jexam_item_type = (TextView) convertView.findViewById(R.id.tv_jexam_item_type);
				holder.tv_jexam_item_title = (TextView) convertView.findViewById(R.id.tv_jexam_item_title);

				convertView.setTag(holder);
			}else{

				holder = (ListHolder) convertView.getTag();
			}

			holder.tv_jexam_item_wei.setText(jeiLists.get(position).getStatus());
			holder.tv_jexam_item_type.setText(jeiLists.get(position).getPtype());
			holder.tv_jexam_item_title.setText(jeiLists.get(position).getTitle());

			return convertView;
		}

		class ListHolder {
			private TextView tv_jexam_item_wei;
			private TextView tv_jexam_item_type;
			private TextView tv_jexam_item_title;
		}

	}
}
