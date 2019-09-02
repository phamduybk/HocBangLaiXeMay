package vn.com.lvvu.hocbanglaixemay.practice;

import butterknife.BindView;
import vn.com.lvvu.hocbanglaixemay.Common;
import vn.com.lvvu.hocbanglaixemay.R;
import vn.com.lvvu.hocbanglaixemay.base.BaseFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

/**
 * Kỹ năng thi phần lý thuyết
 * Created by levan on 7/22/2019.
 */

public class PracticeFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_part1)
    TextView tvPart1;

    @BindView(R.id.tv_part2)
    TextView tvPart2;

    @BindView(R.id.tv_part3)
    TextView tvPart3;

    @BindView(R.id.tv_part4)
    TextView tvPart4;

    @BindView(R.id.banner)
    AdView banner;

    public static Fragment getInstance() {
        Bundle args = new Bundle();

        PracticeFragment fragment = new PracticeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View view) {
        try {
            Common.enableView(view);
            switch (view.getId()) {
                case R.id.tv_part1:
                    openPart1Activity();
                    break;

                case R.id.tv_part2:
                    openPart2Activity();
                    break;

                case R.id.tv_part3:
                    openPart3Activity();
                    break;

                case R.id.tv_part4:
                    openPart4Activity();
                    break;
            }
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    private void openPart1Activity() {
        try {
            Intent intent = new Intent(getActivity(), Part1Activity.class);
            startActivity(intent);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    private void openPart4Activity() {
        try {
            Intent intent = new Intent(getActivity(), Part4Activity.class);
            startActivity(intent);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    private void openPart2Activity() {
        try {
            Intent intent = new Intent(getActivity(), Part3Activity.class);
            startActivity(intent);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    private void openPart3Activity() {
        try {
            Intent intent = new Intent(getActivity(), Part3Activity.class);
            startActivity(intent);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    @Override
    public void initView() {
        try {
            tvPart1.setOnClickListener(this);
            tvPart2.setOnClickListener(this);
            tvPart3.setOnClickListener(this);
            tvPart4.setOnClickListener(this);

            Common.requestAds(banner);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_practice;
    }
}
