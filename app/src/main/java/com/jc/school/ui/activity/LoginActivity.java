package com.jc.school.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jc.school.R;
import com.orhanobut.logger.Logger;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.InsertListener;

/**
 */
public class LoginActivity extends AppCompatActivity {
    private static final java.lang.String TAG = "LoginActivity";
    //    @BindView(R.id.login_progress)
    ProgressBar loginProgress;
    //    @BindView(R.id.et_email)
    EditText etEmail;
    //    @BindView(R.id.et_password)
    EditText etPassword;
    //    @BindView(R.id.btn_login)
    Button btnLogin;
    //    @BindView(R.id.btn_register)
    Button btnRegister;

    EditText etUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        ButterKnife.bind(this);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin = (Button) findViewById(R.id.btn_login);
        etPassword = (EditText) findViewById(R.id.et_password);
        etEmail = (EditText) findViewById(R.id.et_email);
        etUserName = (EditText) findViewById(R.id.et_username);
        loginProgress = (ProgressBar) findViewById(R.id.login_progress);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser bmobUser = new BmobUser();
                bmobUser.setEmail(etEmail.getText().toString());
                bmobUser.setPassword(etPassword.getText().toString());
                bmobUser.setUsername(etUserName.getText().toString());
                bmobUser.signUp(LoginActivity.this, new InsertListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        login();
                    }

                    @Override
                    public void onFailure(String s) {
                        Logger.i(TAG, s);
                        Toast.makeText(LoginActivity.this, "注册失败" + s, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }

        });
    }

    private void login() {

        BmobUser bmobUser = new BmobUser();
        bmobUser.setEmail(etEmail.getText().toString());
        bmobUser.setPassword(etPassword.getText().toString());
        bmobUser.setUsername(etUserName.getText().toString());
        bmobUser.login(LoginActivity.this, new InsertListener() {
            @Override
            public void onSuccess() {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(String s) {
                Logger.i(TAG, s);
            }
        });
    }
}