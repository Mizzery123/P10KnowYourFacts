package sg.edu.rp.c346.p10knowyourfacts;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> al;
    MyFragmentPagerAdapter adapter;
    ViewPager viewPager;
    Button btnLater;
    int reqCode = 12345;
    Integer currentFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        viewPager = findViewById(R.id.viewpager1);
        btnLater = findViewById(R.id.btnLater);

        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add(new Frag1());
        al.add(new Frag2());
        al.add(new Frag3());


        adapter = new MyFragmentPagerAdapter(fm, al);

        viewPager.setAdapter(adapter);

        btnLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MINUTE, 5);

                Intent intent = new Intent(MainActivity.this, Receiver.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, reqCode, intent,
                        PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_next) {
            int max = viewPager.getChildCount();
            if (viewPager.getCurrentItem() < max-1){
                int nextpage = viewPager.getCurrentItem() + 1;
                viewPager.setCurrentItem(nextpage, true);
            }
            return true;

        }else if (id == R.id.action_previous){
            if (viewPager.getCurrentItem() > 0){
                int previouspage = viewPager.getCurrentItem() - 1;
                viewPager.setCurrentItem(previouspage, true);
            }
            return true;

        }else if (id == R.id.action_random){
            // create random object
            setRandomPage();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    private void setRandomPage(){


        Random randomno = new Random();
        int page = randomno.nextInt(3);
        if (page != viewPager.getCurrentItem()){
            viewPager.setCurrentItem(page, true);
        }else{
            setRandomPage();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = prefs.edit();
        currentFragment = viewPager.getCurrentItem();
        prefEdit.putInt("last", currentFragment);
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        currentFragment = prefs.getInt("last", 0);
        viewPager.setCurrentItem(currentFragment);

    }
}
