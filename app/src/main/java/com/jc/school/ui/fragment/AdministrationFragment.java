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
public class AdministrationFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "AdministrationFragment";
    Button mButton1, mButton2, mButton3, mButton4, mButton5, mButton6, mButton7, mButton8;
    public static final String[] otherDepartment = new String[] {"","学院领导", "学院办公室", "教务处", "学工处", "组织人事处", "科技处", "财务处", "后勤保卫处"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_administration, container, false);
        mButton1 = (Button) view.findViewById(R.id.btn1);
        mButton2 = (Button) view.findViewById(R.id.btn2);
        mButton3 = (Button) view.findViewById(R.id.btn3);
        mButton4 = (Button) view.findViewById(R.id.btn4);
        mButton5 = (Button) view.findViewById(R.id.btn5);
        mButton6 = (Button) view.findViewById(R.id.btn6);
        mButton7 = (Button) view.findViewById(R.id.btn7);
        mButton8 = (Button) view.findViewById(R.id.btn8);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mButton8.setOnClickListener(this);


        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle;EmployeeListFragment employeeListFragment;
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
            case R.id.btn4:
                bundle = new Bundle();
                employeeListFragment = new EmployeeListFragment();
                bundle.putString("department", otherDepartment[4]);
                employeeListFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                                    .replace(R.id.container, employeeListFragment)
                                    .commit();
                break;
            case R.id.btn5:
                bundle = new Bundle();
                employeeListFragment = new EmployeeListFragment();
                bundle.putString("department", otherDepartment[5]);
                employeeListFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                                    .replace(R.id.container, employeeListFragment)
                                    .commit();
                break;
            case R.id.btn6:
                bundle = new Bundle();
                employeeListFragment = new EmployeeListFragment();
                bundle.putString("department", otherDepartment[6]);
                employeeListFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                                    .replace(R.id.container, employeeListFragment)
                                    .commit();
                break;
            case R.id.btn7:
                bundle = new Bundle();
                employeeListFragment = new EmployeeListFragment();
                bundle.putString("department", otherDepartment[7]);
                employeeListFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                                    .replace(R.id.container, employeeListFragment)
                                    .commit();
                break;
            case R.id.btn8:
                bundle = new Bundle();
                employeeListFragment = new EmployeeListFragment();
                bundle.putString("department", otherDepartment[8]);
                employeeListFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                                    .replace(R.id.container, employeeListFragment)
                                    .commit();
                break;

        }
    }
}
