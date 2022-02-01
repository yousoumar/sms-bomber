package com.yousoumar.smsbomber;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class SmsFragment extends Fragment {
    private ArrayList<Sms> listSms = new ArrayList<Sms>();
    private SmsListAdapter mAdapter = new SmsListAdapter(listSms);
    private Boolean creation = true;
    private ArrayList<String> listSmsAuthor = new ArrayList<String>();

    public SmsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver, new IntentFilter("SMS_ARRIVED"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sms, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //recycler
        RecyclerView recyclerView = view.findViewById(R.id.sms_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        if(creation) {
            this.retrieveMessages(getContext().getContentResolver());
            mAdapter.notifyDataSetChanged();
            creation = false;
        }
        this.sendSmsList();

        // define an adapter
        recyclerView.setAdapter(mAdapter);
    }

    private void retrieveMessages(ContentResolver contentResolver)
    {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            this.askForPermission();
        }
        else {
            listSms.clear();
            listSmsAuthor.clear();
            final Cursor cursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
            if (cursor == null) {
                return;
            }
            if (cursor.moveToFirst()) {
                do {
                    String message = cursor.getString(cursor.getColumnIndexOrThrow("body"));
                    String phone = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                    if(phone != null && message != null) {
                        Sms sms = new Sms(phone, message);
                        listSms.add(sms);
                        listSmsAuthor.add(phone);
                    }

                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }

    private void askForPermission()
    {
        requestPermissions(new String[] {Manifest.permission.READ_SMS }, 3);
    }

    private void sendSmsList(){
        final Intent intent = new Intent("DATA_ACTION");
        intent.putExtra("listSmsAuthor", listSmsAuthor);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
    }

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            if ("SMS_ARRIVED".equals(intent.getAction()))
            {
                retrieveMessages(getContext().getContentResolver());
                mAdapter.notifyDataSetChanged();
                sendSmsList();
            }
        }
    };

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver);
    }



}
