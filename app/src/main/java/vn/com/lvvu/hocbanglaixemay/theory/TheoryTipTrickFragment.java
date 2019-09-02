package vn.com.lvvu.hocbanglaixemay.theory;

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
 * Màn hình danh sách mẹo thi lý thuyết
 * Created by levan on 7/25/2019.
 */

public class TheoryTipTrickFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_notice_board)
    TextView tvNoticeBoard;

    @BindView(R.id.tv_law)
    TextView tvLaw;

    @BindView(R.id.tv_sahinh)
    TextView tvSahinh;

    @BindView(R.id.banner)
    AdView banner;

    public static Fragment getInstance() {
        Bundle args = new Bundle();

        TheoryTipTrickFragment fragment = new TheoryTipTrickFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View view) {
        try {
            Common.enableView(view);
            switch (view.getId()) {
                case R.id.tv_notice_board:
                    openNoticeBoardFragment();
                    break;

                case R.id.tv_law:
                    openLawFragment();
                    break;

                case R.id.tv_sahinh:
                    openSaHingFragment();
                    break;
            }
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    private void openLawFragment() {
        try {
            Intent intent = new Intent(getActivity(), TipTrickNoticeActivity.class);
            intent.putExtra(TipTrickNoticeActivity.TIP_TRICK_TYPE_KEY, 2);
            startActivity(intent);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    private void openNoticeBoardFragment() {
        try {
            Intent intent = new Intent(getActivity(), TipTrickNoticeActivity.class);
            intent.putExtra(TipTrickNoticeActivity.TIP_TRICK_TYPE_KEY, 1);
            startActivity(intent);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    private void openSaHingFragment() {
        try {
            Intent intent = new Intent(getActivity(), TipTrickNoticeActivity.class);
            intent.putExtra(TipTrickNoticeActivity.TIP_TRICK_TYPE_KEY, 3);
            startActivity(intent);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    @Override
    public void initView() {
        try {
            tvNoticeBoard.setOnClickListener(this);
            tvLaw.setOnClickListener(this);
            tvSahinh.setOnClickListener(this);

            Common.requestAds(banner);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_theory_tip_trick;
    }
}
