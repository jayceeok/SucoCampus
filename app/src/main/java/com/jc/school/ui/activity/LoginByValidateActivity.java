package com.jc.school.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jc.school.R;
import com.jc.school.config.Constants;
import com.orhanobut.logger.Logger;

import cn.bmob.newsmssdk.BmobSMS;
import cn.bmob.newsmssdk.exception.BmobException;
import cn.bmob.newsmssdk.listener.RequestSMSCodeListener;
import cn.bmob.newsmssdk.listener.SMSCodeListener;
import cn.bmob.newsmssdk.listener.VerifySMSCodeListener;

/**
 * 通过手机验证码注册
 *
 * @author jiangchao
 *         created at 2018/4/19 下午1:15
 */
public class LoginByValidateActivity extends AppCompatActivity {

    private static final String TAG = "LoginByValidateActivity";
    EditText etCellphone;
    EditText etValidateCode;
    Button btnFastLogin;
    Button btnGetValidateCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setContentView(R.layout.activity_login_by_validate);
        BmobSMS.initialize(LoginByValidateActivity.this, Constants.Bmob_APPID, new MySMSCodeListener());
        btnFastLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateCode = etValidateCode.getText().toString();
                cellphone = etCellphone.getText().toString();
                BmobSMS.verifySmsCode(LoginByValidateActivity.this, cellphone, validateCode, new VerifySMSCodeListener() {
                    @Override
                    public void done(BmobException ex) {
                        if (ex == null) {
                            Toast.makeText(LoginByValidateActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginByValidateActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginByValidateActivity.this, ex.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        btnGetValidateCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fastLogin();
            }
        });

    }

    private void initView() {
        etCellphone = (EditText) findViewById(R.id.et_cellphone);
        etValidateCode = (EditText) findViewById(R.id.et_validate_code);
        btnFastLogin = (Button) findViewById(R.id.btn_fast_login);
        btnGetValidateCode = (Button) findViewById(R.id.btn_get_validate_code);
    }

    String cellphone, validateCode;

    private void fastLogin() {
        cellphone = etCellphone.getText().toString();
        BmobSMS.requestSMSCode(LoginByValidateActivity.this, cellphone, "sucocampus", new RequestSMSCodeListener() {
            @Override
            public void done(Integer smsId, BmobException ex) {
                //验证码发送成功
                if (ex == null) {
                    Toast.makeText(LoginByValidateActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();

                    //用于查询本次短信发送详情
                    Logger.i(TAG, "短信id：" + smsId);
                }
            }
        });
    }


    class MySMSCodeListener implements SMSCodeListener {

        @Override
        public void onReceive(String content) {
            Logger.i(TAG, content);
            if (etValidateCode != null) {
                etValidateCode.setText(content);
            }
        }

    }


}