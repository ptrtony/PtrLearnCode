package com.zhaofan.viewpager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public class PageFragment extends Fragment {
    private static final String COLORS = "colors";
    private static final String POSITION = "position";
    private TextView mTvTitle;
    private RelativeLayout mContainer;
    public static Fragment getNewInstance(List<Integer> colors,int position){
        Bundle bundle = new Bundle();
        bundle.putSerializable(COLORS,(ArrayList<Integer>)colors);
        bundle.putInt(POSITION,position);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    private List<Integer> mColors;
    private int mPosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            mColors = (List<Integer>) getArguments().getSerializable(COLORS);
            mPosition = getArguments().getInt(POSITION);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_page,container,false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvTitle = view.findViewById(R.id.tvTitle);
        mContainer = view.findViewById(R.id.mContainer);
        mTvTitle.setText("item"+mPosition);
        mContainer.setBackgroundColor(getResources().getColor(mColors.get(mPosition)));

    }
}

