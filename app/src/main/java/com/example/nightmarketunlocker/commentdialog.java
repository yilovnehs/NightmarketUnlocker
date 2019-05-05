package com.example.nightmarketunlocker;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class commentdialog extends Dialog {
    public Context context;
    private String title;     //这里定义个title，一会可以看到是指向上面xml文件的控件title的，也就是我们可以通过这个进行动态修改title
    private AdapterView.OnItemClickListener   onItemClickListener;      //这里定义了一个监听是为了实现内部的监听接口处理，从而实现代码分层管理
    public commentdialog( Context context, int themeResId) {
        super(context, themeResId);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        if (onItemClickListener != null)
            this.onItemClickListener = onItemClickListener;
    }

    RatingBar dialog_ratingBar;
    TextView dialog_text;
    Button button;
    EditText comment;
    private void init() {
        //以view的方式引入，然后回调activity方法，setContentView，实现自定义布局
        View view = LayoutInflater.from(context).inflate(R.layout.send_comment, null);
        setContentView(view);
        button=(Button) view.findViewById(R.id.button);
        button.setOnClickListener(listner);
        //comment.setOnClickListener(listner1);

        //设置dialog大小，这里是一个小赠送，模块好的控件大小设置
        Window dialogWindow = getWindow();
        WindowManager manager = ((Activity) context).getWindowManager();
        WindowManager.LayoutParams params = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        dialogWindow.setGravity(Gravity.CENTER);//设置对话框位置
        Display d = manager.getDefaultDisplay(); // 获取屏幕宽、高度
        params.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.65，根据实际情况调整
        dialogWindow.setAttributes(params);

    }
    private Button.OnClickListener listner=new Button.OnClickListener() {


        @Override
        public void onClick(View v) {
            dismiss();
        }
    };
    private EditText.OnClickListener listner1=new EditText.OnClickListener(){


        @Override
        public void onClick(View v) {
            InputMethodManager imm = (InputMethodManager) context.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);


        }
    };
}
