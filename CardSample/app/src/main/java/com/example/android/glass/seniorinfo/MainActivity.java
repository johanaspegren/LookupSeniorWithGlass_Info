/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.glass.seniorinfo;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.android.glass.seniorinfo.fragments.BaseFragment;
import com.example.android.glass.seniorinfo.fragments.ColumnLayoutFragment;
import com.example.android.glass.seniorinfo.fragments.LastLayoutFragment;
import com.example.android.glass.seniorinfo.fragments.MainLayoutFragment;
import com.example.glass.ui.GlassGestureDetector.Gesture;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Main activity of the application. It provides viewPager to move between fragments.
 */
public class MainActivity extends BaseActivity {

    private List<BaseFragment> fragments = new ArrayList<>();
    private ViewPager viewPager;
    private ArrayList<Customer> customers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_layout);

        customers = new ArrayList<>();
        generateCustomers();

        //
        // private DatabaseReference mDatabase;
        //mDatabase = FirebaseDatabase.getInstance().getReference();
        //mDatabase.child("customers").child("userId").setValue(customers.get(1));


        String who = getIntent().getStringExtra("WHO");
        Customer c = lookup(who);
        Log.d("SKANNA", "receieved WHO as " + who);

        String currentInsats = "12.00 Lunch \nFiskpinnar";
        String nextInsats = "14.00 Lägg om sår på höger ben";

        final ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(
            getSupportFragmentManager());
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(screenSlidePagerAdapter);

        fragments.add(ColumnLayoutFragment
                .newInstance(
                        c.getImageResource(),
                        c.getName(),
                        c.getBackground(),
                        c.getRemember(),
                        c.getUid()));
        fragments.add(MainLayoutFragment
                .newInstance(currentInsats, getString(R.string.footnote_sample),
                        getString(R.string.timestamp_sample), null));
        fragments.add(MainLayoutFragment
                .newInstance(nextInsats, getString(R.string.footnote_sample),
                        getString(R.string.timestamp_sample), null));
        fragments.add(LastLayoutFragment
                .newInstance());

        screenSlidePagerAdapter.notifyDataSetChanged();

        final TabLayout tabLayout = findViewById(R.id.page_indicator);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @Override
    public boolean onGesture(Gesture gesture) {
        switch (gesture) {
            case TAP:
                fragments.get(viewPager.getCurrentItem()).onSingleTapUp();
                int p = viewPager.getCurrentItem();
                Log.d("SKANNA", "tappade:" + p + " size = " + fragments.size());
                if (p == (fragments.size() - 1)) {
                    // is last
                    Log.d("SKANNA", "tappade sista:" + p);
                    finish();
                }
                return true;
            default:
                return super.onGesture(gesture);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
    private void generateCustomers() {
        String bg = "Från Råå, jobbade som flygmekaniker. Har nyss fått barnbarnsbarn";
        String bg1 = "Skåning, jobbade som sjuksköterska.";
        String bg2 = "Tidigare amiral. Är väldigt intresserad av fotboll";
        String bg3 = "Gift med Sonia, artist. Fyller snart 80";
        String bg4 = "Trött på morgnarna";
        customers.add(new Customer("Nowhere Man",bg, "0000", "Ingen information", R.drawable.oldguy2));
        customers.add(new Customer("Agnes Johansson",bg1, "uid001", "Vill ha mjölk i kaffet", R.drawable.oldgal1));
        customers.add(new Customer("Bertil Brorsson",bg2, "uid002", "Tala om dottern, Anna", R.drawable.oldguy1));
        customers.add(new Customer("Ceasar Rubicon",bg3, "uid003", "Die hard HIF-fan", R.drawable.oldguy1));
        customers.add(new Customer("Dagny Larsson",bg4, "uid004", "Tycker om prylar", R.drawable.oldgal2));
    }

    private Customer lookup(String who) {
        for(Customer c : customers) {
            if(c.getUid().equals(who)){
                return c;
            }
        }
        return customers.get(0);
    }


}
