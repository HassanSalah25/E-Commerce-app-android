    package com.example.firebaseproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

    public class MyAdapter extends BaseAdapter {
        ArrayList<User> list;
        Context context;

        public MyAdapter(ArrayList<User> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
        return list.size();
    }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
                convertView = LayoutInflater.from(context).inflate(R.layout.designcard, parent, false);
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference r = db.getReference("contact");
            TextView x = convertView.findViewById(R.id.getName);
            x.setText(list.get(position).getName());
            TextView y = convertView.findViewById(R.id.getPhone);
            y.setText(list.get(position).getPhone());
            ImageView e = convertView.findViewById(R.id.imageView);
            Picasso.get().load(list.get(position).getPhoto()).into(e);
            return convertView;
        }
}
