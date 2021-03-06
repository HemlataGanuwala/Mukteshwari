package com.example.dhanashri.mukteshwarigurupeethwardh;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProgramScheduleActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_schedule);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        tabLayout = (TabLayout)findViewById(R.id.tabs);

        NewViewPageProgramAdapter adapter = new NewViewPageProgramAdapter(getSupportFragmentManager());
        adapter.AddFragment(new SpecialProgramFragment(),"SpecialProgram");
        adapter.AddFragment(new MantradikshaFragment(),"Mantradiksha");
        adapter.AddFragment(new SukshmdyanFragment(),"Sukshmdyan");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);




    }
}
