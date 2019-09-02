package vn.com.lvvu.hocbanglaixemay.alltest;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import vn.com.lvvu.hocbanglaixemay.Common;
import vn.com.lvvu.hocbanglaixemay.R;
import vn.com.lvvu.hocbanglaixemay.ViewAnimationUtils;
import vn.com.lvvu.hocbanglaixemay.examdetail.ExamDetailAdapter;
import vn.com.lvvu.hocbanglaixemay.objects.TestExam;

/**
 * adapter hiển thị danh sách
 * Created by levan on 7/14/2019.
 */

public class AllTestAdapter extends RecyclerView.Adapter<AllTestAdapter.AllTestHolder> {

    private Context mContext;

    private List<TestExam> testExams;

    private ItemOnClick itemOnClick;

    public AllTestAdapter(Context context, List<TestExam> testExams, ItemOnClick itemOnClick) {
        this.mContext = context;
        this.testExams = testExams;
        this.itemOnClick = itemOnClick;
    }

    @Override
    public AllTestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_test_exam, parent, false);
        return new AllTestHolder(view);
    }

    @Override
    public void onBindViewHolder(AllTestHolder holder, int position) {
        try {
            TestExam testExam = testExams.get(position);
            holder.tvExamName.setText(testExam.getExamName());
            holder.bind(testExam);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    @Override
    public int getItemCount() {
        if (testExams != null) {
            return testExams.size();
        }
        return 0;
    }

    class AllTestHolder extends RecyclerView.ViewHolder {

        LinearLayout rootView;

        LinearLayout layoutExamDetail;

        ImageView ivShowHideDetail;

        TextView tvExamName;

        TextView tvViewExplain;

        AllTestHolder(final View itemView) {
            super(itemView);

            rootView = itemView.findViewById(R.id.root_view);
            layoutExamDetail = itemView.findViewById(R.id.layout_exam_detail);
            ivShowHideDetail = itemView.findViewById(R.id.iv_show_hide_detail);
            tvExamName = itemView.findViewById(R.id.tv_exam_name);
            tvViewExplain = itemView.findViewById(R.id.tv_view_explain);

        }

        void bind(final TestExam testExam) {
            try {
                rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            if (itemOnClick != null) {
                                itemOnClick.onItemSelected(testExam, ExamDetailAdapter.EDisplayType.NONE);
                            }
                        } catch (Exception e) {
                            Common.handleException(e);
                        }
                    }
                });

                ivShowHideDetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            if (layoutExamDetail.getVisibility() == View.VISIBLE) {
                                ViewAnimationUtils.rollbackRotatingImage(ivShowHideDetail, mContext);
                                ViewAnimationUtils.collapse(layoutExamDetail);
                            } else {
                                ViewAnimationUtils.startRotatingImage(ivShowHideDetail, mContext);
                                ViewAnimationUtils.expand(layoutExamDetail);
                            }
                        } catch (Exception e) {
                            Common.handleException(e);
                        }
                    }
                });

                tvViewExplain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            if (itemOnClick != null) {
                                itemOnClick.onItemSelected(testExam, ExamDetailAdapter.EDisplayType.EXPLAIN);
                            }
                        } catch (Exception e) {
                            Common.handleException(e);
                        }
                    }
                });
            } catch (Exception e) {
                Common.handleException(e);
            }
        }

    }

    public interface ItemOnClick {

        void onItemSelected(TestExam exam, ExamDetailAdapter.EDisplayType displayType);

    }

}
