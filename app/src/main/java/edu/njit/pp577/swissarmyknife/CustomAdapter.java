package edu.njit.pp577.swissarmyknife;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.sip.SipException;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.support.v4.app.ActivityCompat.requestPermissions;

/**
 * Created by ppatel on 2/2/2017.
 */

public class CustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    public CustomAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.contacts_list_item, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.text1);
        listItemText.setText(list.get(position));

        //Handle buttons and add onClickListeners
        Button call_button = (Button)view.findViewById(R.id.call_contact);
        Button sms_button = (Button)view.findViewById(R.id.sms_contact);

        call_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String contact_item = list.get(position);
                int start_index = contact_item.indexOf("number:");
                int end_index = contact_item.length();
                if(contact_item.contains("Email")){
                    end_index = contact_item.indexOf("Email");
                }
                String phone_num = contact_item.substring(start_index+6, end_index);
                phone_num = phone_num.replaceAll("-", "");
                phone_num = phone_num.replaceAll(" ", "");
                phone_num.trim();
                CallContact("tel:"+phone_num);
                notifyDataSetChanged();
            }
        });
        sms_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String contact_item = list.get(position);
                int start_index = contact_item.indexOf("number:");
                int end_index = contact_item.length();
                if(contact_item.contains("Email")){
                    end_index = contact_item.indexOf("Email");
                }
                String phone_num = contact_item.substring(start_index+6, end_index);
                phone_num = phone_num.replaceAll("-", "");
                phone_num = phone_num.replaceAll(" ", "");
                phone_num.trim();
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phone_num)));
                notifyDataSetChanged();
            }
        });

        return view;
    }

    private void CallContact(String phone_num){
        try{
            context.startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse(phone_num)));
        }catch (SecurityException e){
            Toast.makeText(context, "Could not dial the contact's number", Toast.LENGTH_LONG);
        }

    }

}