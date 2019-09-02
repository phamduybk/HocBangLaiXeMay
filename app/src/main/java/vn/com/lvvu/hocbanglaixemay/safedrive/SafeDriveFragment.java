package vn.com.lvvu.hocbanglaixemay.safedrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import vn.com.lvvu.hocbanglaixemay.Common;
import vn.com.lvvu.hocbanglaixemay.R;
import vn.com.lvvu.hocbanglaixemay.base.BaseFragment;

/**
 * Kĩ năng lái xe an toàn
 * Created by levan on 7/22/2019.
 */

public class SafeDriveFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_safe1)
    TextView tvSafe1;

    @BindView(R.id.tv_safe2)
    TextView tvSafe2;

    @BindView(R.id.tv_safe3)
    TextView tvSafe3;

    @BindView(R.id.tv_safe4)
    TextView tvSafe4;

    @BindView(R.id.tv_safe5)
    TextView tvSafe5;

    @BindView(R.id.banner)
    AdView banner;

    public static SafeDriveFragment getInstance() {

        Bundle args = new Bundle();

        SafeDriveFragment fragment = new SafeDriveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView() {
        try {
            tvSafe1.setOnClickListener(this);
            tvSafe2.setOnClickListener(this);
            tvSafe3.setOnClickListener(this);
            tvSafe4.setOnClickListener(this);
            tvSafe5.setOnClickListener(this);

            Common.requestAds(banner);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_safe_drive;
    }

    @Override
    public void onClick(View view) {
        try {
            Common.enableView(view);
            switch (view.getId()) {
                case R.id.tv_safe1:
                    openSaveDrive1Activity();
                    break;

                case R.id.tv_safe2:
                    openSaveDrive2Activity();
                    break;

                case R.id.tv_safe3:
                    openSaveDrive3Activity();
                    break;

                case R.id.tv_safe4:
                    openSaveDrive4Activity();
                    break;

                case R.id.tv_safe5:
                    openSaveDrive5Activity();
                    break;
            }
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    private void openSaveDrive1Activity() {
        try {
            Intent intent = new Intent(getActivity(), SafeDriver1Activity.class);
            startActivity(intent);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    private void openSaveDrive2Activity() {
        try {
            Intent intent = new Intent(getActivity(), SafeDriver3Activity.class);
            startActivity(intent);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    private void openSaveDrive3Activity() {
        try {
            Intent intent = new Intent(getActivity(), SafeDriver3Activity.class);
            startActivity(intent);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    private void openSaveDrive4Activity() {
        try {
            Intent intent = new Intent(getActivity(), SafeDrive4Activity.class);
            startActivity(intent);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    private void openSaveDrive5Activity() {
        try {
            Intent intent = new Intent(getActivity(), SafeDrive5Activity.class);
            startActivity(intent);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }
}
