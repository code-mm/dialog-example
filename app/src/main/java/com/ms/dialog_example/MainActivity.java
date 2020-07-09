package com.ms.dialog_example;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);


        final Dialog dialog = new Dialog(this, R.style.inAnimAnim);
        dialog.setContentView(R.layout.view_status_menu);
        dialog.setCanceledOnTouchOutside(true);

        ListView listView = dialog.findViewById(R.id.listView);

        listView.setAdapter(new CustomerServiceAdapter(MainActivity.this));

        Window window = dialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;

        button1.post(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: " + button1.getHeight());
                Log.e(TAG, "run: " + button1.getX());
                Log.e(TAG, "run: " + button1.getY());

                int[] location = new int[2];
                button1.getLocationInWindow(location);


                Log.e(TAG, "run: " + location[0]);
                Log.e(TAG, "run: " + location[1]);

                attributes.y = location[1] + button1.getHeight();
            }
        });


        window.setAttributes(attributes);
        window.setGravity(Gravity.TOP);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.show();

            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setAdapter(new CustomerServiceAdapter(MainActivity.this), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog.create().show();

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                PopupWindow popupWindow = new PopupWindow(MainActivity.this);

                popupWindow.setWidth(getWindow().getWindowManager().getDefaultDisplay().getWidth());


                View view = View.inflate(MainActivity.this, R.layout.view_status_menu, null);
                ListView listView = view.findViewById(R.id.listView);

                listView.setAdapter(new CustomerServiceAdapter(MainActivity.this));

                popupWindow.setContentView(view);

                // 为了避免部分机型不显示，我们需要重新设置一下宽高
                popupWindow.  setWidth(ViewGroup.LayoutParams.MATCH_PARENT);

                popupWindow. setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                // 设置pop透明效果
                popupWindow. setBackgroundDrawable(new ColorDrawable(0x0000));
                // 设置pop出入动画
                popupWindow. setAnimationStyle(R.style.rightPopUpWindowMenu);


                popupWindow.setFocusable(true);

                popupWindow.setTouchable(true);

                popupWindow.setOutsideTouchable(true);

                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        darkenBackground(1f);
                    }
                });



                popupWindow.showAsDropDown(button3);

            }
        });

        button4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {


                StatusMenuView.show(MainActivity.this,button4);

            }
        });
    }


    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgcolor;
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        this.getWindow().setAttributes(lp);
    }
}