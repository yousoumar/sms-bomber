package com.yousoumar.smsbomber;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.yousoumar.smsbomber.ContactFragment;
import com.yousoumar.smsbomber.SettingsFragment;
import com.yousoumar.smsbomber.SmsFragment;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ContactFragment contactFragment = new ContactFragment();
                return contactFragment;
            case 1:
                SmsFragment SmsFragment = new SmsFragment();
                return SmsFragment;
            case 2:
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: 
                return "Contacts";
            case 1:
                return "Réçu";
            case 2:
                return "Envoi";
            default:
                return null;
        }
    }
}