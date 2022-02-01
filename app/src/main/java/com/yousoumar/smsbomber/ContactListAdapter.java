package com.yousoumar.smsbomber;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder>{
    private ArrayList<Contact> listdata;

    public ContactListAdapter(ArrayList<Contact> listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.fragment_contact_items, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Contact contact = listdata.get(position);
        final ContactListAdapter adapter = this;
        holder.textView.setText(listdata.get(position).getName());
        holder.textPhone.setText(listdata.get(position).getPhoneNumber());
        holder.textSms.setText(listdata.get(position).getSmsNumber() + " SMS reçus");
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public void MyUpdater() {
        this.notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public TextView textPhone;
        public TextView textSms;
        public LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.fragment_contact_item);
            this.textPhone = (TextView) itemView.findViewById(R.id.fragment_contact_phone);
            this.textSms = (TextView) itemView.findViewById(R.id.fragment_contact_sms_number);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayout);
        }
    }
}
