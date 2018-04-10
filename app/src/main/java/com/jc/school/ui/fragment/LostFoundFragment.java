package com.jc.school.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jc.school.R;
import com.jc.school.adapter.BaseAdapterHelper;
import com.jc.school.adapter.QuickAdapter;
import com.jc.school.bean.Found;
import com.jc.school.bean.Lost;
import com.jc.school.config.Constants;
import com.jc.school.interf.IPopupItemClick;
import com.jc.school.ui.activity.AddActivity;
import com.jc.school.ui.widget.EditPopupWindow;

import org.xutils.view.annotation.ContentView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;
import me.drakeet.materialdialog.MaterialDialog;

import static com.jc.school.R.id.tv_describe;
import static com.jc.school.R.id.tv_photo;
import static com.jc.school.R.id.tv_time;
import static com.jc.school.R.id.tv_title;

/**
 * Created by Jaycee on 16/7/2.
 * E-mail：jayceeok@foxmail.com
 */

@ContentView(R.layout.layout_main)
public class LostFoundFragment extends Fragment implements View.OnClickListener,
        AdapterView.OnItemLongClickListener, IPopupItemClick {

    //    @ViewInject(R.id.layout_action)
    RelativeLayout layout_action;
    //    @ViewInject(R.id.layout_all)
    LinearLayout layout_all;
    //    @ViewInject(R.id.tv_lost)
    TextView tv_lost;
    //    @ViewInject(R.id.list_lost)
    ListView listview;
    //    @ViewInject(R.id.btn_add)
    Button btn_add;
    //    @ViewInject(R.id.progress)
    RelativeLayout progress;
    //    @ViewInject(R.id.layout_no)
    LinearLayout layout_no;
    //    @ViewInject(R.id.tv_no)
    TextView tv_no;

    private Button layout_found;
    private Button layout_lost;

    PopupWindow morePop;

    protected QuickAdapter<Lost> LostAdapter;// 失物

    protected QuickAdapter<Found> FoundAdapter;// 招领
    MaterialDialog mMaterialDialog;
    private Intent intent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_main, container, false);
        progress = (RelativeLayout) view.findViewById(R.id.progress);
        layout_no = (LinearLayout) view.findViewById(R.id.layout_no);
        tv_no = (TextView) view.findViewById(R.id.tv_no);
        layout_action = (RelativeLayout) view.findViewById(R.id.layout_action);
        layout_all = (LinearLayout) view.findViewById(R.id.layout_all);


        // 默认是失物界面
        tv_lost = (TextView) view.findViewById(R.id.tv_lost);
        tv_lost.setTag("Lost");
        listview = (ListView) view.findViewById(R.id.list_lost);
        btn_add = (Button) view.findViewById(R.id.btn_add);
        initEditPop();
        listview.setOnItemLongClickListener(this);
        btn_add.setOnClickListener(this);
        layout_all.setOnClickListener(this);
        if (LostAdapter == null) {
            LostAdapter = new QuickAdapter<Lost>(getActivity(), R.layout.item_list) {
                @Override
                protected void convert(BaseAdapterHelper helper, Lost lost) {
                    helper.setText(R.id.tv_title, lost.getTitle())
                          .setText(R.id.tv_describe, lost.getDescribe())
                          .setText(R.id.tv_time, lost.getCreatedAt())
                          .setText(R.id.tv_photo, lost.getPhone());
                    helper.getView(R.id.tv_photo).setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    mMaterialDialog = new MaterialDialog(getActivity()).setTitle(
                                            "友情提示"
                                    ).setMessage("是否联系").setPositiveButton(
                                            "确认拨号", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    mMaterialDialog.dismiss();
                                                    intent = new Intent();
                                                    intent.setAction(
                                                            intent.ACTION_CALL
                                                    );
                                                    intent.setData(
                                                            Uri.parse(
                                                                    "tel:" + tv_photo
                                                            )
                                                    );
                                                    Toast.makeText(
                                                            getActivity(), "正在拨号", Toast.LENGTH_LONG
                                                    ).show();
                                                    (getActivity()).startActivity(
                                                            intent
                                                    );
                                                }
                                            }
                                    ).setNegativeButton(
                                            "取消拨号", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    mMaterialDialog.dismiss();

                                                }
                                            }
                                    );

                                    mMaterialDialog.show();


                                }
                            }
                    );
                }
            };


        }

        if (FoundAdapter == null) {
            FoundAdapter = new QuickAdapter<Found>(getActivity(), R.layout.item_list) {
                @Override
                protected void convert(BaseAdapterHelper helper, Found found) {
                    helper.setText(tv_title, found.getTitle())
                          .setText(tv_describe, found.getDescribe())
                          .setText(tv_time, found.getCreatedAt())
                          .setText(tv_photo, found.getPhone());
                    helper.getView(R.id.tv_photo).setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    mMaterialDialog = new MaterialDialog(getActivity()).setTitle(
                                            "友情提示"
                                    ).setMessage("是否联系").setPositiveButton(
                                            "确认拨号", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    mMaterialDialog.dismiss();
                                                    intent = new Intent();
                                                    intent.setAction(
                                                            intent.ACTION_CALL
                                                    );
                                                    intent.setData(
                                                            Uri.parse(
                                                                    "tel:" + tv_photo
                                                            )
                                                    );
                                                    Toast.makeText(
                                                            getActivity(),
                                                            "正在拨号",
                                                            Toast.LENGTH_LONG
                                                    ).show();
                                                    (getActivity()).startActivity(
                                                            intent
                                                    );
                                                }
                                            }
                                    ).setNegativeButton(
                                            "取消拨号", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    mMaterialDialog.dismiss();

                                                }
                                            }
                                    );

                                    mMaterialDialog.show();


                                }
                            }
                    );

                }
            };
        }
        listview.setAdapter(LostAdapter);
        // 默认加载失物界面
        queryLosts();
        return view;
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == layout_all) {
            showListPop();
        } else if (v == btn_add) {
            Intent intent = new Intent(getActivity(), AddActivity.class);
            intent.putExtra("from", tv_lost.getTag().toString());
            startActivityForResult(intent, Constants.REQUESTCODE_ADD);
        } else if (v == layout_found) {
            changeTextView(v);
            morePop.dismiss();
            queryFounds();
        } else if (v == layout_lost) {
            changeTextView(v);
            morePop.dismiss();
            queryLosts();
        }
    }


    private void changeTextView(View v) {
        if (v == layout_found) {
            tv_lost.setTag("Found");
            tv_lost.setText("Found");
        } else {
            tv_lost.setTag("Lost");
            tv_lost.setText("Lost");
        }
    }

    @SuppressWarnings("deprecation")
    private void showListPop() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_lost, null);
        // 注入
        layout_found = (Button) view.findViewById(R.id.layout_found);
        layout_lost = (Button) view.findViewById(R.id.layout_lost);
        layout_found.setOnClickListener(this);
        layout_lost.setOnClickListener(this);
        morePop = new PopupWindow(view, mScreenWidth, 600);

        morePop.setTouchInterceptor(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                            morePop.dismiss();
                            return true;
                        }
                        return false;
                    }
                }
        );

        morePop.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        morePop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        morePop.setTouchable(true);
        morePop.setFocusable(true);
        morePop.setOutsideTouchable(true);
        morePop.setBackgroundDrawable(new BitmapDrawable());
        // 动画效果 从顶部弹下
        morePop.setAnimationStyle(R.style.MenuPop);
        morePop.showAsDropDown(layout_action, 0, -dip2px(getActivity(), 2.0F));
    }

    private void initEditPop() {
        mPopupWindow = new EditPopupWindow(getActivity(), 200, 48);
        mPopupWindow.setOnPopupItemClickListner(this);
    }

    EditPopupWindow mPopupWindow;
    int position;

    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        position = arg2;
        int[] location = new int[2];
        arg1.getLocationOnScreen(location);
        mPopupWindow.showAtLocation(
                arg1, Gravity.RIGHT | Gravity.TOP, location[0], getStateBar() + location[1]
        );
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1) {
            return;
        }
        switch (requestCode) {
            case Constants.REQUESTCODE_ADD:// 添加成功之后的回调
                String tag = tv_lost.getTag().toString();
                if (tag.equals("Lost")) {
                    queryLosts();
                } else {
                    queryFounds();
                }
                break;
        }
    }

    /**
     * 查询全部失物信息 queryLosts
     *
     * @return void
     * @throws
     */
    private void queryLosts() {
        showView();
        BmobQuery<Lost> query = new BmobQuery<Lost>();
        query.order("-createdAt");// 按照时间降序
        query.findObjects(
                getActivity(), new FindListener<Lost>() {

                    @Override
                    public void onSuccess(List<Lost> losts) {
                        // TODO Auto-generated method stub
                        LostAdapter.clear();
                        FoundAdapter.clear();
                        if (losts == null || losts.size() == 0) {
                            showErrorView(0);
                            LostAdapter.notifyDataSetChanged();
                            return;
                        }
                        progress.setVisibility(View.GONE);
                        LostAdapter.addAll(losts);
                        listview.setAdapter(LostAdapter);
                    }

                    @Override
                    public void onError(String arg0) {
                        // TODO Auto-generated method stub
                        showErrorView(0);
                    }
                }
        );
    }

    public void queryFounds() {
        showView();
        BmobQuery<Found> query = new BmobQuery<Found>();
        query.order("-createdAt");// 按照时间降序
        query.findObjects(
                getActivity(), new FindListener<Found>() {

                    @Override
                    public void onSuccess(List<Found> arg0) {
                        // TODO Auto-generated method stub
                        LostAdapter.clear();
                        FoundAdapter.clear();
                        if (arg0 == null || arg0.size() == 0) {
                            showErrorView(1);
                            FoundAdapter.notifyDataSetChanged();
                            return;
                        }
                        FoundAdapter.addAll(arg0);
                        listview.setAdapter(FoundAdapter);
                        progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(String arg0) {
                        // TODO Auto-generated method stub
                        showErrorView(1);
                    }
                }
        );
    }

    /**
     * 请求出错或者无数据时候显示的界面 showErrorView
     *
     * @return void
     * @throws
     */
    private void showErrorView(int tag) {
        progress.setVisibility(View.GONE);
        listview.setVisibility(View.GONE);
        layout_no.setVisibility(View.VISIBLE);
        if (tag == 0) {
            tv_no.setText(getResources().getText(R.string.list_no_data_lost));
        } else {
            tv_no.setText(getResources().getText(R.string.list_no_data_found));
        }
    }

    private void showView() {
        listview.setVisibility(View.VISIBLE);
        layout_no.setVisibility(View.GONE);
    }

    @Override
    public void onEdit(View v) {
        // TODO Auto-generated method stub
        String tag = tv_lost.getTag().toString();
        Intent intent = new Intent(getActivity(), AddActivity.class);
        String title = "";
        String describe = "";
        String phone = "";
        if (tag.equals("Lost")) {
            title = LostAdapter.getItem(position).getTitle();
            describe = LostAdapter.getItem(position).getDescribe();
            phone = LostAdapter.getItem(position).getPhone();
        } else {
            title = FoundAdapter.getItem(position).getTitle();
            describe = FoundAdapter.getItem(position).getDescribe();
            phone = FoundAdapter.getItem(position).getPhone();
        }
        intent.putExtra("describe", describe);
        intent.putExtra("phone", phone);
        intent.putExtra("title", title);
        intent.putExtra("from", tag);
        startActivityForResult(intent, Constants.REQUESTCODE_ADD);
    }

    @Override
    public void onDelete(View v) {
        // TODO Auto-generated method stub
        String tag = tv_lost.getTag().toString();
        if (tag.equals("Lost")) {
            deleteLost();
        } else {
            deleteFound();
        }
    }

    protected int mScreenWidth;
    protected int mScreenHeight;

    private void deleteLost() {
        Lost lost = new Lost();
        lost.setObjectId(LostAdapter.getItem(position).getObjectId());
        lost.deleteObject(
                getActivity(), new DeleteListener() {

                    @Override
                    public void onSuccess() {
                        // TODO Auto-generated method stub
                        LostAdapter.remove(position);
                    }

                    @Override
                    public void onFailure(String arg0) {
                        // TODO Auto-generated method stub

                    }
                }
        );
    }

    private void deleteFound() {
        Found found = new Found();
        found.setObjectId(FoundAdapter.getItem(position).getObjectId());
        found.deleteObject(
                getActivity(), new DeleteListener() {

                    @Override
                    public void onSuccess() {
                        // TODO Auto-generated method stub
                        FoundAdapter.remove(position);
                    }

                    @Override
                    public void onFailure(String arg0) {
                        // TODO Auto-generated method stub

                    }
                }
        );
    }

    /**
     * 获取当前状态栏的高度
     * getStateBar
     *
     * @throws
     * @Title: getStateBar
     */
    public int getStateBar() {
        Rect frame = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        return statusBarHeight;
    }

    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (scale * dipValue + 0.5f);
    }


}
