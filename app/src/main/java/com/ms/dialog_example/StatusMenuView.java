package com.ms.dialog_example;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class StatusMenuView {

    private static final String TAG = "DialogMenuStatus";

    public static class MenuAdapter extends BaseAdapter {

        private Context context;
        public static String[] titles = new String[]{"待接单", "代配送/上门", "待服务", "待完成", "已完成"};

        public MenuAdapter(Context context) {
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


            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });


            return convertView;
        }

        static class ViewHolder {
            private ImageView imageViewAvatar;
            private TextView textViewName;
        }
    }


    public static void show(Activity activity, View view) {

        final Dialog dialog = new Dialog(activity, R.style.inAnimAnim);
        dialog.setContentView(R.layout.view_status_menu);
        dialog.setCanceledOnTouchOutside(true);

        ListView listView = dialog.findViewById(R.id.listView);
        listView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        listView.setAdapter(new CustomerServiceAdapter(activity));

        Window window = dialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;

        view.post(new Runnable() {
            @Override
            public void run() {
                int[] location = new int[2];
                view.getLocationInWindow(location);

                Log.e(TAG, "run: " + view.getHeight());
                Log.e(TAG, "run: " + location[1]);
                attributes.y = location[1] + view.getHeight();
            }
        });

        window.setAttributes(attributes);
//        window.setGravity(Gravity.TOP);

        dialog.show();
    }


}
