package com.yousoumar.smsbomber;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class SmsReceiver extends BroadcastReceiver {
    public SmsReceiver() {
        Log.v("SMSReceiver", "SmsReceiver start");
    }

    @Override
    public void onReceive(Context context, Intent intent){
        Log.d("Youssouf", "SMS reçu");
        final Intent intent2 = new Intent("SMS_ARRIVED");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent2);
    }
}
