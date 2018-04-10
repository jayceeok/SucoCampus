package com.jc.school.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jc.school.R;
import com.jc.school.ui.widget.SideBar;
import com.orhanobut.logger.Logger;

/**
 * Created by Jaycee on 16/7/2.
 * E-mail：jayceeok@foxmail.com
 */
public class ContractFragment extends Fragment {
    private static final String TAG ="ContractFragment" ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_all_person, container, false);
        SideBar sideBar = (SideBar)view. findViewById(R.id.sideBar);
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
                                                       @Override
                                                       public void onTouchingLetterChanged(String s) {
                                                           Logger.i(TAG, "select " + s);
                                                       }
                                                   });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
