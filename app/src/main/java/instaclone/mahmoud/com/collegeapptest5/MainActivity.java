package instaclone.mahmoud.com.collegeapptest5;

import android.content.res.ColorStateList;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import instaclone.mahmoud.com.collegeapptest5.Utils.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViewPager();
    }

    private void setUpViewPager()
    {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AssignmentFragment());
        adapter.addFragment(new TimetableFragment());
        adapter.addFragment(new ModulesFragment());

        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.white));

        tabLayout.getTabAt(0).setText(R.string.assignments);
        tabLayout.getTabAt(1).setText(R.string.timetable);
        tabLayout.getTabAt(2).setText(R.string.modules);
    }
}

















