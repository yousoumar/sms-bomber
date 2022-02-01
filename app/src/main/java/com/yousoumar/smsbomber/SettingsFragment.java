package com.yousoumar.smsbomber;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {



    public SettingsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final View vip = view;

        //recycler
        RecyclerView recyclerView = view.findViewById(R.id.settings_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);




        Button bomber = (Button) view.findViewById(R.id.make_bomber);
        bomber.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v){
               EditText messageBomber = vip.findViewById(R.id.message_bomber);
               EditText phoneNumber = vip.findViewById(R.id.message_bomber_phone_number);
               EditText messageNumber = vip.findViewById(R.id.message_bomber_number);
               String message = messageBomber.getText().toString();
               String phone = phoneNumber.getText().toString();
               String number = messageNumber.getText().toString();
               if (!message.equals("") && !phone.equals("") && !number.equals("")){
                   Integer n = Integer.parseInt(number);
                   sendSMS(phone, message, n);
                   messageBomber.setText("");
                   phoneNumber.setText("");
                   messageNumber.setText("");
               }
           }
        });


    }

    public void sendSMS(String phoneNumber, String message, int number)
    {
        PendingIntent pi = PendingIntent.getActivity(getContext(), 0,
                new Intent("SEND_SMS"), 0);
        SmsManager sms = SmsManager.getDefault();
        for(int i = 0; i < number; i++) {
            sms.sendTextMessage(phoneNumber, null, message, pi, null);
        }
    }



    @Override
    public void onDestroy()
    {
        super.onDestroy();

    }

}
