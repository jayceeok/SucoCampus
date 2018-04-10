package com.jc.school.ui.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jc.school.R;
import com.jc.school.bean.MobilePhoneInfo;
import com.jc.school.config.Constants;
import com.jc.school.utils.GsonUtils;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jaycee on 16/7/2.
 * E-mail：jayceeok@foxmail.com
 */
public class QueryTelephoneNumberFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "QueryTelephoneNumberFragment";
    TextView mTextView;
    EditText mEditText;
    Button mButton;
    private String url = "http://apis.juhe.cn/mobile/get?phone=";
    private String tel;
    private String last_url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_query_tel, container, false);
        mButton = (Button) view.findViewById(R.id.btn_query);
        mEditText = (EditText) view.findViewById(R.id.et_query_tel);
        mTextView = (TextView) view.findViewById(R.id.show_tel_info);
        mButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onClick(View v) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("请稍候...");

        tel = mEditText.getText().toString();
        last_url = url + tel + "&key=" + Constants.JUHE_APPID;
        Toast.makeText(getActivity(), "get!", Toast.LENGTH_LONG).show();
        RequestParams requestParams = new RequestParams(last_url);
        final Callback.Cancelable cancelable = x.http().get(
                requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Logger.i(TAG, result);
                        progressDialog.cancel();
                        MobilePhoneInfo mobilePhone = GsonUtils.jsonToBean(
                                result, MobilePhoneInfo.class
                        );
                        if ("200".equals(mobilePhone.getResultcode())) {
                            mTextView.setText(mobilePhone.getResult().getCard()+"归属地:"+mobilePhone.getResult().getProvince()+
                                              mobilePhone.getResult().getCity() + mobilePhone.getResult()
                                                                                   .getCompany()
                            );
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                }
        );
    }
}
