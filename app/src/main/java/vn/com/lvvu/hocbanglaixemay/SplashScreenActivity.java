package vn.com.lvvu.hocbanglaixemay;

import android.content.Intent;
import android.os.Handler;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import vn.com.lvvu.hocbanglaixemay.base.BaseActivity;

/**
 * Created by levan on 7/27/2019.
 */

public class SplashScreenActivity extends BaseActivity {

    private InterstitialAd interstitialAd;

    @Override
    public int getLayoutID() {
        return R.layout.activity_splash_screen;
    }

    @Override
    public void initView() {
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            interstitialAd = new InterstitialAd(this);

            interstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_full_screen));
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    startMain(false);
                }
            });

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (interstitialAd.isLoaded()) {
                        interstitialAd.show();
                    } else {
                        startMain(true);
                    }
                }
            }, 3000);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    private void startMain(boolean isDelay) {
        try {
            if (isDelay) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                            startActivity(intent);
                        } catch (Exception e) {
                            Common.handleException(e);
                        }
                    }
                }, 1000);
            } else {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
            }
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    @Override
    public void initData() {

    }
}
