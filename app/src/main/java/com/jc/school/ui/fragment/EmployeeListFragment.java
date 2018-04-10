package com.jc.school.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jc.school.R;
import com.jc.school.adapter.QuickAdapter;
import com.jc.school.bean.PersonalInfo;
import com.jc.school.utils.CommonAdapter;
import com.jc.school.utils.ViewHolder;
import com.orhanobut.logger.Logger;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Jaycee on 16/7/3.
 * E-mail：jayceeok@foxmail.com
 */
public class EmployeeListFragment extends Fragment {

    private static final String TAG = "EmployeeListFragment";

    ListView listview;
    String departmentCode;
    protected QuickAdapter<PersonalInfo> personAdapter;
    TextView per_name, per_position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_employee, container, false);
        listview = (ListView) view.findViewById(R.id.lv_employee);
        per_name = (TextView) view.findViewById(R.id.per_name);
        per_position = (TextView) view.findViewById(R.id.per_position);
        Bundle bundle = getArguments();
        departmentCode = bundle.getString("department");
        Logger.i(TAG, "departmentCode" + departmentCode);
        BmobQuery<PersonalInfo> personalInfoBmobQuery = new BmobQuery<PersonalInfo>();
        personalInfoBmobQuery.addWhereEqualTo("department", departmentCode);
        personalInfoBmobQuery.findObjects(
                getActivity(), new FindListener<PersonalInfo>() {
                    @Override
                    public void onSuccess(final List<PersonalInfo> list) {
                        //                        for (PersonalInfo personInfo : list) {
                        //                            Logger.i(TAG, personInfo.getName().toString());
                        //                        }
                        listview.setAdapter(
                                new CommonAdapter<PersonalInfo>(
                                        getActivity(), list, R.layout.per_msg_layout
                                ) {
                                    @Override
                                    public void convert(ViewHolder holder,
                                                        PersonalInfo personInfo) {
                                        holder.setText(
                                                R.id.per_name, personInfo.getName()
                                        ).setText(
                                                R.id.per_position, personInfo.getPosition()
                                        );
                                    }
                                }
                        );
                        listview.setOnItemClickListener(
                                new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view,
                                                            int position, long id) {
                                        Bundle bundle = new Bundle();
                                        EmployeeInfoFragment employeeInfoFragment = new EmployeeInfoFragment();
                                        bundle.putString("name", list.get(position).getName());
                                        employeeInfoFragment.setArguments(bundle);
                                        getFragmentManager().beginTransaction().replace(
                                                R.id.container, employeeInfoFragment
                                        ).commit();

                                    }
                                }
                        );

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


}
