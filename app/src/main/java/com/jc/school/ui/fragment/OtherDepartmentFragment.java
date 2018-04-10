package com.jc.school.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jc.school.R;

/**
 * Created by Jaycee on 16/7/2.
 * E-mail：jayceeok@foxmail.com
 */
public class OtherDepartmentFragment extends Fragment implements View.OnClickListener {
    public static final String[] otherDepartment = new String[] {"", "图书馆", "现代教育技术中心", "后勤服务中心"};
    Button mButton1, mButton2, mButton3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_other, container, false);
        mButton1 = (Button) view.findViewById(R.id.btn1);
        mButton2 = (Button) view.findViewById(R.id.btn2);
        mButton3 = (Button) view.findViewById(R.id.btn3);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle;
        EmployeeListFragment employeeListFragment;

        switch (v.getId()) {
            case R.id.btn1:
                bundle = new Bundle();
                employeeListFragment = new EmployeeListFragment();
                bundle.putString("department", otherDepartment[1]);
                employeeListFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                                    .replace(R.id.container, employeeListFragment)
                                    .commit();
                break;
            case R.id.btn2:
                bundle = new Bundle();
                employeeListFragment = new EmployeeListFragment();
                bundle.putString("department", otherDepartment[2]);
                employeeListFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                                    .replace(R.id.container, employeeListFragment)
                                    .commit();

                break;
            case R.id.btn3:
                bundle = new Bundle();
                employeeListFragment = new EmployeeListFragment();
                bundle.putString("department", otherDepartment[3]);
                employeeListFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                                    .replace(R.id.container, employeeListFragment)
                                    .commit();
                break;
        }
    }
}
