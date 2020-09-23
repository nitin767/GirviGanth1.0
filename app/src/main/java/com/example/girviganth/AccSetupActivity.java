package com.example.girviganth;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class AccSetupActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mPager;
    private int[] layouts = {R.layout.activity_signup,R.layout.activity_signin,R.layout.activity_add_branch,R.layout.activity_add_more_branch,
    R.layout.activity_add_metal,R.layout.activity_add_more_metal};
    private MpagerAdapter mpagerAdapter;

    private Button BnNext,btCreateAccount;//,BnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(new PreferenceManager(this).checkPreference())
        {
            loadHome();
        }

        if(Build.VERSION.SDK_INT >= 19)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_accountsetup);
        //setContentView((View) mpagerAdapter.instantiateItem(mPager,0));
        mPager = (CustomViewPager)findViewById(R.id.viewPager);
        ((CustomViewPager) mPager).setPagingEnabled(false);
        //mPager = (ViewPager) findViewById(R.id.viewPager);
        mpagerAdapter = new MpagerAdapter(layouts,this);
        mPager.setAdapter(mpagerAdapter);
        //View V =(View) mpagerAdapter.instantiateItem(mPager,0);
        //btCreateAccount=
                //(Button) findViewById(R.id.btCreateAccount);
        BnNext = (Button) findViewById(R.id.bnNext);
        BnNext.setOnClickListener(this);
      //  btCreateAccount.setOnClickListener(this);
        /*BnSkip = (Button) findViewById(R.id.bnSkip);

        BnSkip.setOnClickListener(this);*/
        if(mPager.getCurrentItem() == layouts.length-1)
        {
            //BnNext.setText("Start");
            BnNext.setBackgroundColor(13919574);
            BnNext.setVisibility(View.VISIBLE);
            //BnSkip.setVisibility(View.INVISIBLE);
        }
        else {
            //BnNext.setText("Next");
            //BnNext.setBackgroundColor(13919574);
            BnNext.setVisibility(View.INVISIBLE);
            //BnSkip.setVisibility(View.VISIBLE);
        }


        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == layouts.length-1)
                {
                    //BnNext.setText("Start");
                    BnNext.setBackgroundColor(13919574);
                    BnNext.setVisibility(View.VISIBLE);
                    //BnSkip.setVisibility(View.INVISIBLE);
                }
                else {
                    //BnNext.setText("Next");
                    //BnNext.setBackgroundColor(13919574);
                    BnNext.setVisibility(View.INVISIBLE);
                    //BnSkip.setVisibility(View.VISIBLE);
                    }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
        /*    case R.id.bnNext:
                loadNextSlide();
                break;
*/
         /*   case R.id.btCreateAccount:
                Toast.makeText(this, "On Create Button", Toast.LENGTH_SHORT).show();*/
            case R.id.bnNext:
                loadHome();
                new PreferenceManager(this).writePreference();
                break;
        }
    }

    private void loadHome()
    {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
    void onButtonClick(){
        Toast.makeText(this, "On Create Button", Toast.LENGTH_SHORT).show();
        //use current value of text variable as the latest edit text value in fragment1
    }
    private  void loadNextSlide()
    {
        int next_slide = mPager.getCurrentItem()+1;

        if(next_slide < layouts.length)
        {
            mPager.setCurrentItem(next_slide);
        }
        else {
            loadHome();
            new PreferenceManager(this).writePreference();
        }
    }
}