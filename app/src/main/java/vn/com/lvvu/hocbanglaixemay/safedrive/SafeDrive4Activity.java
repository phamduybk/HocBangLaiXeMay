package vn.com.lvvu.hocbanglaixemay.safedrive;

import butterknife.BindView;
import vn.com.lvvu.hocbanglaixemay.Common;
import vn.com.lvvu.hocbanglaixemay.R;
import vn.com.lvvu.hocbanglaixemay.base.BaseActivity;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by levan on 7/22/2019.
 */

public class SafeDrive4Activity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_toolbar_ic_left)
    ImageView ivBack;

    @Override
    public int getLayoutID() {
        return R.layout.activity_safe_drive4;
    }

    @Override
    public void initView() {
        try {
            ivBack.setOnClickListener(this);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        try {
            Common.enableView(view);
            switch (view.getId()) {
                case R.id.iv_toolbar_ic_left:
                    finish();
            }
        } catch (Exception e) {
            Common.handleException(e);
        }
    }
}
