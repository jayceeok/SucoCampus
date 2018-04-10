package com.jc.school.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jc.school.R;
import com.jc.school.bean.Feedback;

import cn.bmob.v3.listener.InsertListener;


public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {
//    @ViewInject(R.id.et_name)
    EditText mEtName;
//    @ViewInject(R.id.feedback)
    EditText mEtFeedback;
//    @ViewInject(R.id.submit)
    Button mButton;

    String name, feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_feedback);
        mButton= (Button) findViewById(R.id.submit);
        mEtName= (EditText) findViewById(R.id.et_name);
        mEtFeedback= (EditText) findViewById(R.id.et_feedback);

        mButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

       if(v.getId()== R.id.submit){
           name = mEtName.getText().toString();
           feedback = mEtFeedback.getText().toString();
           Toast.makeText(
                   FeedbackActivity.this,
                   "name--->" + name + "feedback--->" + feedback,
                   Toast.LENGTH_SHORT
           ).show();
           if (name.equals("") || feedback.equals("")) {
               return;
           }
           Feedback feedbackObj = new Feedback();
           feedbackObj.setName(name);
           feedbackObj.setFeedback(feedback);
           feedbackObj.insertObject(
                   FeedbackActivity.this, new InsertListener() {
                       @Override
                       public void onSuccess() {
                           Toast.makeText(FeedbackActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                       }

                       @Override
                       public void onFailure(String s) {
                           Toast.makeText(FeedbackActivity.this, "添加失败" + s, Toast.LENGTH_SHORT)
                                .show();

                       }
                   }
           );
       }

    }
}
