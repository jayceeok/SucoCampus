package com.jc.school.ui.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jc.school.R;
import com.jc.school.bean.PersonalInfo;
import com.orhanobut.logger.Logger;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by Jaycee on 16/7/2.
 * E-mail：jayceeok@foxmail.com
 */
public class EmployeeInfoFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "EmployeeInfoFragment";
    private String name;
    ImageView mHead;
    TextView mName, mPosition, mOfficeTel, mOfficeShortTel, mMobilePhone, mMobileShortPhone, mHomeTel, mEmail, mQq, mDepartment;
    MaterialDialog mMaterialDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_person_info, container, false);

        mHead = (ImageView) view.findViewById(R.id.iv_head);

        mName = (TextView) view.findViewById(R.id.tv_name);
        mPosition = (TextView) view.findViewById(R.id.tv_position);
        mOfficeTel = (TextView) view.findViewById(R.id.tv_office_tel);
        mOfficeShortTel = (TextView) view.findViewById(R.id.tv_office_short_tel);
        mMobilePhone = (TextView) view.findViewById(R.id.tv_mobile_phone);
        mMobileShortPhone = (TextView) view.findViewById(R.id.tv_mobile_short_phone);
        mHomeTel = (TextView) view.findViewById(R.id.tv_home_tel);
        mEmail = (TextView) view.findViewById(R.id.tv_email);
        mQq = (TextView) view.findViewById(R.id.tv_qq);

        mDepartment = (TextView) view.findViewById(R.id.tv_department);
        mOfficeTel.setOnClickListener(this);
        mOfficeShortTel.setOnClickListener(this);
        mMobilePhone.setOnClickListener(this);
        mMobileShortPhone.setOnClickListener(this);
        mHomeTel.setOnClickListener(this);
        mEmail.setOnClickListener(this);


        Bundle bundle = getArguments();
        name = bundle.getString("name");
        Logger.i(TAG, "name" + name);
        BmobQuery<PersonalInfo> personalInfoBmobQuery = new BmobQuery<PersonalInfo>();
        personalInfoBmobQuery.addWhereEqualTo("name", name);
        personalInfoBmobQuery.findObjects(
                getActivity(), new FindListener<PersonalInfo>() {
                    @Override
                    public void onSuccess(List<PersonalInfo> list) {
                        for (PersonalInfo ls : list) {
                            Logger.i(TAG, ls.getName());
                            //                            Picasso.with(getActivity()).load(
                            //                                    ls.getHead_url()
                            //                            ).placeholder(R.drawable.avatar)//没有加载图片时显示的默认图像
                            //                                    .error(R.drawable.avatar)
                            //                                            // 图像加载错误时显示的图像
                            //                                    .resize(160, 160).into(mHead);
                            ImageOptions imageOptions = new ImageOptions.Builder().setFadeIn(true)
                                                                                  .setCircular(true)
                                                                                  .setIgnoreGif(
                                                                                          false
                                                                                  )
                                                                                  .build();
                            x.image().bind(mHead, ls.getHead_url(), imageOptions);


                            mName.setText(ls.getName());
                            mPosition.setText(ls.getPosition());
                            mOfficeTel.setText(ls.getOffice_tel());
                            mOfficeShortTel.setText(ls.getOffice_short_tel());
                            mMobilePhone.setText(ls.getMobile_phone());
                            mMobileShortPhone.setText(ls.getMobile_short_phone());
                            mHomeTel.setText(ls.getHome_tel());
                            mEmail.setText(ls.getEmail());
                            mQq.setText(ls.getQq());
                            mDepartment.setText(ls.getDepartment());
                        }
                        Logger.i(TAG, list.toString());
                    }

                    @Override
                    public void onError(String s) {

                    }
                }
        );
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    Intent intent;

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.tv_office_tel:
                mMaterialDialog = new MaterialDialog(getActivity()).setTitle("友情提示")
                                                                   .setMessage("是否给" + name + "打电话")
                                                                   .setPositiveButton(
                                                                           "确认拨号",
                                                                           new View.OnClickListener() {
                                                                               @Override
                                                                               public void onClick(
                                                                                       View v) {
                                                                                   mMaterialDialog.dismiss();
                                                                                   intent = new Intent();
                                                                                   intent.setAction(
                                                                                           intent.ACTION_CALL
                                                                                   );
                                                                                   intent.setData(
                                                                                           Uri.parse(
                                                                                                   "tel:" + mOfficeTel
                                                                                           )
                                                                                   );
                                                                                   Toast.makeText(
                                                                                           getActivity(),
                                                                                           "正在准备给" + name + "打电话",
                                                                                           Toast.LENGTH_LONG
                                                                                   ).show();
                                                                                   (getActivity()).startActivity(
                                                                                           intent
                                                                                   );
                                                                               }
                                                                           }
                                                                   )
                                                                   .setNegativeButton(
                                                                           "取消拨号",
                                                                           new View.OnClickListener() {
                                                                               @Override
                                                                               public void onClick(
                                                                                       View v) {
                                                                                   mMaterialDialog.dismiss();

                                                                               }
                                                                           }
                                                                   );

                mMaterialDialog.show();


                break;
            case R.id.tv_office_short_tel:
                mMaterialDialog = new MaterialDialog(getActivity()).setTitle("友情提示")
                                                                   .setMessage("是否给" + name + "打电话")
                                                                   .setPositiveButton(
                                                                           "确认拨号",
                                                                           new View.OnClickListener() {
                                                                               @Override
                                                                               public void onClick(
                                                                                       View v) {
                                                                                   mMaterialDialog.dismiss();
                                                                                   intent = new Intent();
                                                                                   intent.setAction(
                                                                                           intent.ACTION_CALL
                                                                                   );
                                                                                   intent.setData(
                                                                                           Uri.parse(
                                                                                                   "tel:" + mOfficeShortTel
                                                                                           )
                                                                                   );
                                                                                   Toast.makeText(
                                                                                           getActivity(),
                                                                                           "正在准备给" + name + "打电话",
                                                                                           Toast.LENGTH_LONG
                                                                                   ).show();
                                                                                   (getActivity()).startActivity(
                                                                                           intent
                                                                                   );
                                                                               }
                                                                           }
                                                                   )
                                                                   .setNegativeButton(
                                                                           "取消拨号",
                                                                           new View.OnClickListener() {
                                                                               @Override
                                                                               public void onClick(
                                                                                       View v) {
                                                                                   mMaterialDialog.dismiss();

                                                                               }
                                                                           }
                                                                   );

                mMaterialDialog.show();
                break;
            case R.id.tv_mobile_phone:
                mMaterialDialog = new MaterialDialog(getActivity()).setTitle("友情提示")
                                                                   .setMessage("是否给" + name + "打电话")
                                                                   .setPositiveButton(
                                                                           "确认拨号",
                                                                           new View.OnClickListener() {
                                                                               @Override
                                                                               public void onClick(
                                                                                       View v) {
                                                                                   mMaterialDialog.dismiss();
                                                                                   intent = new Intent();
                                                                                   intent.setAction(
                                                                                           intent.ACTION_CALL
                                                                                   );
                                                                                   intent.setData(
                                                                                           Uri.parse(
                                                                                                   "tel:" + mMobilePhone
                                                                                           )
                                                                                   );
                                                                                   Toast.makeText(
                                                                                           getActivity(),
                                                                                           "正在准备给" + name + "打电话",
                                                                                           Toast.LENGTH_LONG
                                                                                   ).show();
                                                                                   (getActivity()).startActivity(
                                                                                           intent
                                                                                   );
                                                                               }
                                                                           }
                                                                   )
                                                                   .setNegativeButton(
                                                                           "取消拨号",
                                                                           new View.OnClickListener() {
                                                                               @Override
                                                                               public void onClick(
                                                                                       View v) {
                                                                                   mMaterialDialog.dismiss();

                                                                               }
                                                                           }
                                                                   );

                mMaterialDialog.show();
                break;
            case R.id.tv_mobile_short_phone:
                mMaterialDialog = new MaterialDialog(getActivity()).setTitle("友情提示")
                                                                   .setMessage("是否给" + name + "打电话")
                                                                   .setPositiveButton(
                                                                           "确认拨号",
                                                                           new View.OnClickListener() {
                                                                               @Override
                                                                               public void onClick(
                                                                                       View v) {
                                                                                   mMaterialDialog.dismiss();
                                                                                   intent = new Intent();
                                                                                   intent.setAction(
                                                                                           intent.ACTION_CALL
                                                                                   );
                                                                                   intent.setData(
                                                                                           Uri.parse(
                                                                                                   "tel:" + mMobileShortPhone
                                                                                           )
                                                                                   );
                                                                                   Toast.makeText(
                                                                                           getActivity(),
                                                                                           "正在准备给" + name + "打电话",
                                                                                           Toast.LENGTH_LONG
                                                                                   ).show();
                                                                                   (getActivity()).startActivity(
                                                                                           intent
                                                                                   );
                                                                               }
                                                                           }
                                                                   )
                                                                   .setNegativeButton(
                                                                           "取消拨号",
                                                                           new View.OnClickListener() {
                                                                               @Override
                                                                               public void onClick(
                                                                                       View v) {
                                                                                   mMaterialDialog.dismiss();

                                                                               }
                                                                           }
                                                                   );

                mMaterialDialog.show();
                break;
            case R.id.tv_home_tel:
                mMaterialDialog = new MaterialDialog(getActivity()).setTitle("友情提示")
                                                                   .setMessage("是否给" + name + "打电话")
                                                                   .setPositiveButton(
                                                                           "确认拨号",
                                                                           new View.OnClickListener() {
                                                                               @Override
                                                                               public void onClick(
                                                                                       View v) {
                                                                                   mMaterialDialog.dismiss();
                                                                                   intent = new Intent();
                                                                                   intent.setAction(
                                                                                           intent.ACTION_CALL
                                                                                   );
                                                                                   intent.setData(
                                                                                           Uri.parse(
                                                                                                   "tel:" + mHomeTel
                                                                                           )
                                                                                   );
                                                                                   Toast.makeText(
                                                                                           getActivity(),
                                                                                           "正在准备给" + name + "打电话",
                                                                                           Toast.LENGTH_LONG
                                                                                   ).show();
                                                                                   (getActivity()).startActivity(
                                                                                           intent
                                                                                   );
                                                                               }
                                                                           }
                                                                   )
                                                                   .setNegativeButton(
                                                                           "取消拨号",
                                                                           new View.OnClickListener() {
                                                                               @Override
                                                                               public void onClick(
                                                                                       View v) {
                                                                                   mMaterialDialog.dismiss();

                                                                               }
                                                                           }
                                                                   );

                mMaterialDialog.show();
                break;
            //            case R.id.tv_email:
            //                intent = new Intent();
            //                intent.setAction(intent.ACTION_SENDTO);
            //                intent.setData(Uri.parse("mailto:" + mEmail));
            //                Toast.makeText(getActivity(), "正在准备给" + name + "发邮件", Toast.LENGTH_LONG).show();
            //                (getActivity()).startActivity(intent);
            //                break;
        }
    }
}
