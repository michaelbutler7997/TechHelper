package com.example.techhelper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

class PreviewViewAdapter extends ArrayAdapter<String> {
    ArrayList<String> list;
    Context context;

    public PreviewViewAdapter(Context context, ArrayList<String>items){
        super(context, R.layout.preview_row, items);
        this.context = context;
        list = items;
    }

    //code to give function to items in list
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.preview_row, null);
        }

            TextView name = convertView.findViewById(R.id.name);
            name.setText(list.get(position));


        return convertView;
    }

}