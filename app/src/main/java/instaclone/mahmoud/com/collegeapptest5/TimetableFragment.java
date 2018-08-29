package instaclone.mahmoud.com.collegeapptest5;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;


import java.util.Calendar;
import java.util.TimeZone;

import instaclone.mahmoud.com.collegeapptest5.Utils.SectionsPagerAdapter;

public class TimetableFragment extends Fragment {

    TextView timetableModule;
    TextView timetableRoom;
    TextView timetableTime;
    TextView today;
    TextView nextDay;
    TextView previousDay;

    ViewFlipper vf;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        sectionsPagerAdapter.addFragment(new MondayFragment());
        sectionsPagerAdapter.addFragment(new TuesdayFragment());
        sectionsPagerAdapter.addFragment(new WednesdayFragment());
        sectionsPagerAdapter.addFragment(new ThursdayFragment());
        sectionsPagerAdapter.addFragment(new FridayFragment());

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.container_main);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tabs2);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.white));

        tabLayout.getTabAt(0).setText("Mon");
        tabLayout.getTabAt(1).setText("Tue");
        tabLayout.getTabAt(2).setText("Wed");
        tabLayout.getTabAt(3).setText("Thur");
        tabLayout.getTabAt(4).setText("Fri");

        return view;
    }

}
