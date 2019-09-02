package vn.com.lvvu.hocbanglaixemay.examdetail;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.com.lvvu.hocbanglaixemay.Common;
import vn.com.lvvu.hocbanglaixemay.R;
import vn.com.lvvu.hocbanglaixemay.objects.Question;

/**
 * danh sách câu hỏi
 * Created by levan on 7/16/2019.
 */

public class AllQuestionAdapter extends RecyclerView.Adapter<AllQuestionAdapter.AllQuestionHolder> {

    private List<Question> questions;

    private Context context;

    private int positionSelected = 0;

    private OnQuestionSelectedListener onQuestionSelectedListener;

    private ExamDetailAdapter.EDisplayType eDisplayType = ExamDetailAdapter.EDisplayType.NORMAL;

    public AllQuestionAdapter(Context context, List<Question> questions, OnQuestionSelectedListener onQuestionSelectedListener) {
        this.context = context;
        this.questions = questions;
        this.onQuestionSelectedListener = onQuestionSelectedListener;
    }

    @NonNull
    @Override
    public AllQuestionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_question, viewGroup, false);
        return new AllQuestionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllQuestionHolder allQuestionHolder, int position) {
        try {
            allQuestionHolder.bind(questions.get(position), position);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    @Override
    public int getItemCount() {
        if (questions != null) {
            return questions.size();
        }
        return 0;
    }

    class AllQuestionHolder extends RecyclerView.ViewHolder {

        TextView tvQuestionNumber;

        ImageView ivStateAnswer;

        AllQuestionHolder(@NonNull View itemView) {
            super(itemView);

            tvQuestionNumber = itemView.findViewById(R.id.tv_question_number);
            ivStateAnswer = itemView.findViewById(R.id.iv_state_answer);
        }

        void bind(final Question question, final int position) {
            try {
                tvQuestionNumber.setText(String.valueOf(position + 1));
                if (eDisplayType == ExamDetailAdapter.EDisplayType.NORMAL || eDisplayType == ExamDetailAdapter.EDisplayType.EXPLAIN
                        || eDisplayType == ExamDetailAdapter.EDisplayType.NONE) {
                    if (position != positionSelected) {
                        //kiểm tra xem câu hỏi này đã được trả lời hay chưa
                        if (question.checkIsReallyAnswer()) {
                            tvQuestionNumber.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_with_stroke_primary));
                            tvQuestionNumber.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                        } else {
                            tvQuestionNumber.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_gray));
                            tvQuestionNumber.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                        }
                    } else {
                        tvQuestionNumber.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                        tvQuestionNumber.setTextColor(context.getResources().getColor(android.R.color.white));
                    }
                    ivStateAnswer.setVisibility(View.GONE);
                } else {

                    tvQuestionNumber.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_gray));
                    tvQuestionNumber.setTextColor(context.getResources().getColor(R.color.text_color_primary));

                    ivStateAnswer.setVisibility(View.VISIBLE);
                    if (question.geteAnswerType() == ExamDetailAdapter.EAnswerType.EMPTY) {
                        ivStateAnswer.setImageResource(R.drawable.ic_warning);
                        ivStateAnswer.setColorFilter(ContextCompat.getColor(context, android.R.color.holo_orange_light),
                                android.graphics.PorterDuff.Mode.SRC_IN);

                    } else if (question.geteAnswerType() == ExamDetailAdapter.EAnswerType.WRONG) {
                        ivStateAnswer.setImageResource(R.drawable.ic_clear24dp);
                        ivStateAnswer.setColorFilter(ContextCompat.getColor(context, android.R.color.holo_red_light),
                                android.graphics.PorterDuff.Mode.SRC_IN);
                    } else {
                        ivStateAnswer.setImageResource(R.drawable.ic_check_black_24dp);
                        ivStateAnswer.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary),
                                android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }

                tvQuestionNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            if (position != positionSelected) {

                                tvQuestionNumber.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                                tvQuestionNumber.setTextColor(context.getResources().getColor(android.R.color.white));
                                int oldTemp = positionSelected;
                                positionSelected = position;

                                notifyItemChanged(oldTemp);
                                notifyItemChanged(positionSelected);

                                if (onQuestionSelectedListener != null) {
                                    onQuestionSelectedListener.onQuestionSelected(position);
                                }
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

    public void setData(List<Question> questions) {
        this.questions = questions;
    }

    public ExamDetailAdapter.EDisplayType geteDisplayType() {
        return eDisplayType;
    }

    public void seteDisplayType(ExamDetailAdapter.EDisplayType eDisplayType) {
        this.eDisplayType = eDisplayType;
    }

    public interface OnQuestionSelectedListener {

        void onQuestionSelected(int position);

    }

    public void setPositionSelected(int positionSelected) {
        int oldPosition = this.positionSelected;
        this.positionSelected = positionSelected;

        notifyItemChanged(oldPosition);
        notifyItemChanged(this.positionSelected);
    }

}
