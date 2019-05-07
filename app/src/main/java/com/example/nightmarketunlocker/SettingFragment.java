package com.example.nightmarketunlocker;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.nightmarketunlocker.adapter.MultipleItemQuickAdapter;
import com.example.nightmarketunlocker.bean.MultipleItem;
import com.example.nightmarketunlocker.widget.CircleImageView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.yechaoa.yutils.LogUtil;
import com.yechaoa.yutils.YUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class SettingFragment extends Fragment {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private MultipleItem multipleItem = null;

    private List<MultipleItem> itemDataList;

    private MultipleItemQuickAdapter multipleItemQuickAdapter;

    //add 20190507 by yi
    GoogleSignInClient mGoogleSignInClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        mSwipeRefreshLayout = getActivity().findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = getActivity().findViewById(R.id.recyclerView);

        initSwipeRefreshLayout();

        initItemData();

        initRecyclerView();

        initListener();

    }


    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        multipleItemQuickAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                        YUtils.showToast("刷新ing");
                    }
                }, 2000);
            }
        });
    }


    private void initItemData() {
        itemDataList = new ArrayList<>();

        multipleItem = new MultipleItem(MultipleItem.TYPE_COUNT, 5);
        multipleItem.mString1 = "收藏";
        multipleItem.mString2 = "关注";
        itemDataList.add(multipleItem);

        multipleItem = new MultipleItem(MultipleItem.TYPE_ORDER_HEADER, 5);
        multipleItem.mString2 = "type2";
        itemDataList.add(multipleItem);

        for (int i = 0; i < 5; i++) {
            multipleItem = new MultipleItem(MultipleItem.TYPE_ORDER, 1);
            multipleItem.mString1 = "待付款";
            if (i % 2 == 0) {
                multipleItem.isShow = true;
                multipleItem.count = 6;
            } else {
                multipleItem.isShow = false;
                multipleItem.count = 0;
            }
            itemDataList.add(multipleItem);
        }


        multipleItem = new MultipleItem(MultipleItem.TYPE_BALANCE, 5);
        multipleItem.mString1 = "￥9999.00";
        itemDataList.add(multipleItem);

        multipleItem = new MultipleItem(MultipleItem.TYPE_TOOLS_HEADER, 5);
        multipleItem.mString1 = "type5";
        itemDataList.add(multipleItem);

        for (int i = 0; i < 5; i++) {
            multipleItem = new MultipleItem(MultipleItem.TYPE_TOOLS, 1);
            multipleItem.mString1 = "使用帮助";
            if (i % 2 == 0) {
                multipleItem.isShow = true;
                multipleItem.count = 100;
            } else {
                multipleItem.isShow = false;
                multipleItem.count = 0;
            }
            itemDataList.add(multipleItem);
        }
    }

    /*

     */
    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 5);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        multipleItemQuickAdapter = new MultipleItemQuickAdapter(itemDataList);

        View headerView = getHeaderView(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.my_header_image:
                        YUtils.showToast("這是icon");
                        break;
                    case R.id.my_header_settings:
                        YUtils.showToast("這是設置");
                        break;
                }
            }
        });

        multipleItemQuickAdapter.addHeaderView(headerView);

        mRecyclerView.setAdapter(multipleItemQuickAdapter);
    }

    private View getBalanceView(View.OnClickListener listener){
        View balanceView = getLayoutInflater().inflate(R.layout.layout_my_balance, (ViewGroup) mRecyclerView.getParent(),false);

        CircleImageView balance_icon = balanceView.findViewById(R.id.my_balance_image);
        balance_icon.setImageResource(R.drawable.header_image);
        balance_icon.setOnClickListener(listener);

        return balanceView;
    }

    private View getHeaderView(View.OnClickListener listener) {
        View headerView = getLayoutInflater().inflate(R.layout.layout_my_header, (ViewGroup) mRecyclerView.getParent(), false);

        CircleImageView myHeaderImage = headerView.findViewById(R.id.my_header_image);
        myHeaderImage.setImageResource(R.drawable.header_image);
        myHeaderImage.setOnClickListener(listener);

        TextView myHeaderName = headerView.findViewById(R.id.my_header_name);
        myHeaderName.setText("Gareth");

        TextView myHeaderMobile = headerView.findViewById(R.id.my_header_mobile);
        myHeaderMobile.setText("電話：0903395194");

        ImageView myHeaderSettings = headerView.findViewById(R.id.my_header_settings);
        myHeaderSettings.setOnClickListener(listener);

        return headerView;
    }


    private void initListener() {
        multipleItemQuickAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return itemDataList.get(position).getSpanSize();
            }
        });

        multipleItemQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                YUtils.showToast("第  " + position);


                if (itemDataList.get(position).getItemType() == MultipleItem.TYPE_TOOLS) {
                    if (itemDataList.get(position).isShow) {
                        itemDataList.get(position).isShow = false;
                        LogUtil.i("count  =  " + itemDataList.get(position).count);
                        multipleItemQuickAdapter.notifyItemChanged(position + 1);
                    } else
                        itemDataList.get(position).isShow = false;
                }

            }
        });

        multipleItemQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.my_favorites:
                        YUtils.showToast("收藏店家");
                        break;
                    case R.id.my_bands:
                        YUtils.showToast("關注");
                        break;
                    case R.id.ll_my_order:
                        YUtils.showToast("查看所有評論");
                        break;
                    case R.id.my_balance_btn:
                        YUtils.showToast("充值");
                        break;
                }
            }
        });

    }

}


