package vn.com.lvvu.hocbanglaixemay.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * base của activity
 * Created by levan on 7/15/2019.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        ButterKnife.bind(this);

        initView();
        initData();
    }

    public abstract int getLayoutID();

    public abstract void initView();

    public abstract void initData();

}
