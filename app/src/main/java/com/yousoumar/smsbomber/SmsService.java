package com.yousoumar.smsbomber;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;


public class SmsService extends Service {
    private static final String TAG = "SMSService";
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    private boolean active;
    private int coutSmsReceived;

    public void onCreate(){
        super.onCreate();
        IntentFilter filter = new IntentFilter(SMS_RECEIVED);
        registerReceiver(new SmsReceiver(),filter);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
