package vn.com.lvvu.hocbanglaixemay.theory;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import vn.com.lvvu.hocbanglaixemay.Common;
import vn.com.lvvu.hocbanglaixemay.R;
import vn.com.lvvu.hocbanglaixemay.base.BaseActivity;
import vn.com.lvvu.hocbanglaixemay.databases.CommonSQlite;
import vn.com.lvvu.hocbanglaixemay.objects.TipTrick;

/**
 * Created by levan on 7/25/2019.
 */

public class TipTrickNoticeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_toolbar_ic_left)
    ImageView ivBack;

    @BindView(R.id.rv_tip_trick)
    RecyclerView rvTipTrick;

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;

    private TheoryTipTrickAdapter theoryTipTrickAdapter;

    public static final String TIP_TRICK_TYPE_KEY = "TIP_TRICK_TYPE_KEY";

    @Override
    public void onClick(View view) {
        try {
            Common.enableView(view);
            switch (view.getId()) {
                case R.id.iv_toolbar_ic_left:
                    finish();
                    break;
            }
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    @Override
    public void initView() {
        try {
            theoryTipTrickAdapter = new TheoryTipTrickAdapter(TipTrickNoticeActivity.this, null);
            rvTipTrick.setLayoutManager(new LinearLayoutManager(TipTrickNoticeActivity.this,
                    LinearLayoutManager.HORIZONTAL, false));
            rvTipTrick.setAdapter(theoryTipTrickAdapter);

            PagerSnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(rvTipTrick);

            ivBack.setOnClickListener(this);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    @Override
    public void initData() {
        try {
            int tipTrickType = getIntent().getIntExtra(TIP_TRICK_TYPE_KEY, 1);
            if (tipTrickType == 1) {
                tvToolbarTitle.setText("Mẹo thi phần biển báo");
            } else if (tipTrickType == 2) {
                tvToolbarTitle.setText("Mẹo thi phần luật giao thông");
            } else if (tipTrickType == 3) {
                tvToolbarTitle.setText("Mẹo thi phần sa hình");
            }
            List<TipTrick> tipTrickList = CommonSQlite.getAllTipTrickFromTipID(tipTrickType);
            theoryTipTrickAdapter.setData(tipTrickList);
            theoryTipTrickAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_tip_trick_notice_board;
    }
}
