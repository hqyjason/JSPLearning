package android.jsp.com.activity;

import android.jsp.com.fragment.ExamFragment;
import android.jsp.com.fragment.TrainFragment;
import android.jsp.com.jsplearning.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import application.JSPApplication;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private ViewPager mViewPager;

    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        JSPApplication tdApplication = (JSPApplication) getApplication();
        JSPApplication tdApplication = (JSPApplication) JSPApplication.getInstance();
        Log.e("driverLoginName", tdApplication.getDriverLoginName());
        initView();
    }

    private void initView() {
        // TODO Auto-generated method stub
//		tv_exame = (TextView)findViewById(R.id.tv_exame);
//		tv_train = (TextView)findViewById(R.id.tv_train);
        btn = (Button)findViewById(R.id.button_backward);
//		tv_exame.setOnClickListener(HomeActivity.this);
//		tv_train.setOnClickListener(HomeActivity.this);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        btn.setOnClickListener(HomeActivity.this);
        ExamFragment examFragment = new ExamFragment();
        TrainFragment trainFragment = new TrainFragment();
        mFragments.add(examFragment);
        mFragments.add(trainFragment);

        /**
         * 初始化Adapter
         */
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {
            @Override
            public int getCount()
            {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0)
            {
                return mFragments.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);


    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
    }
}
