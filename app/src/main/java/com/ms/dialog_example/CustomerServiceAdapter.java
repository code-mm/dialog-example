package com.ms.dialog_example;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomerServiceAdapter extends BaseAdapter {

    private Context context;
    public static String[] titles = new String[]{"待接单", "代配送/上门", "待服务", "待完成", "已完成"};

    public CustomerServiceAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return titles.length;
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


        ViewHolder holder = null;

        if (holder == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_view_status_menu, null);
            holder.textViewName = convertView.findViewById(R.id.textViewName);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textViewName.setText(titles[position]);
        //Glide.with(context).load(images[position]).into(holder.imageViewAvatar);


        return convertView;
    }

    static class ViewHolder {
        private ImageView imageViewAvatar;
        private TextView textViewName;
    }
}
