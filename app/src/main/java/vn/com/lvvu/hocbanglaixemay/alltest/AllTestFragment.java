package vn.com.lvvu.hocbanglaixemay.alltest;

import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.google.android.gms.ads.AdView;

import java.util.List;

import butterknife.BindView;
import vn.com.lvvu.hocbanglaixemay.Common;
import vn.com.lvvu.hocbanglaixemay.R;
import vn.com.lvvu.hocbanglaixemay.base.BaseFragment;
import vn.com.lvvu.hocbanglaixemay.base.DividerItemDecorationBase;
import vn.com.lvvu.hocbanglaixemay.databases.CommonSQlite;
import vn.com.lvvu.hocbanglaixemay.examdetail.ExamDetailActivity;
import vn.com.lvvu.hocbanglaixemay.examdetail.ExamDetailAdapter;
import vn.com.lvvu.hocbanglaixemay.objects.TestExam;

/**
 * Màn hình danh sách các bài thi thử lí thuyết
 * Created by levan on 7/14/2019.
 */

public class AllTestFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.rc_all_test)
    RecyclerView rvAllTest;

    @BindView(R.id.banner)
    AdView banner;

    public static Fragment getInstance() {
        return new AllTestFragment();
    }

    @Override
    public void initView() {
        try {
            //đọc thông tin đề thi từ DB lên
            List<TestExam> examList = CommonSQlite.getAllExams();
            AllTestAdapter allTestAdapter = new AllTestAdapter(getContext(), examList, new AllTestAdapter.ItemOnClick() {
                @Override
                public void onItemSelected(TestExam exam, ExamDetailAdapter.EDisplayType eDisplayType) {
                    try {
                        Intent intent = new Intent(getActivity(), ExamDetailActivity.class);
                        intent.putExtra(ExamDetailActivity.TEST_EXAM, Common.convertObjectToString(exam));
                        intent.putExtra(ExamDetailActivity.DISPLAY_TYPE, eDisplayType.getValue());
                        startActivity(intent);
                    } catch (Exception e) {
                        Common.handleException(e);
                    }
                }
            });
            rvAllTest.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            rvAllTest.setAdapter(allTestAdapter);

            RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorationBase(ContextCompat.getDrawable(getContext(),
                    R.drawable.divider));
            rvAllTest.addItemDecoration(dividerItemDecoration);

            //load quảng cáo
            Common.requestAds(banner);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_all_test;
    }


    @Override
    public void onClick(View v) {
        try {
            Common.enableView(v);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

}
