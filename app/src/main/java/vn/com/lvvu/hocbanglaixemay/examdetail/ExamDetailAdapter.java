package vn.com.lvvu.hocbanglaixemay.examdetail;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
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
import vn.com.lvvu.hocbanglaixemay.objects.Question;

/**
 * Hiển thị câu hỏi
 * Created by levan on 7/15/2019.
 */

public class ExamDetailAdapter extends RecyclerView.Adapter<ExamDetailAdapter.ExamDetailHolder> {

    private Context context;

    private List<Question> questions;

    private EDisplayType eDisplayType = EDisplayType.NONE;

    ExamDetailAdapter(Context context, List<Question> questions) {
        this.context = context;
        this.questions = questions;
    }

    @NonNull
    @Override
    public ExamDetailHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_question, viewGroup, false);
        return new ExamDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(final @NonNull ExamDetailHolder examDetailHolder, final int positon) {
        try {
            final Question question = questions.get(positon);

            examDetailHolder.tvQuestionTitle.setText(String.format(context.getString(R.string.question_content), positon + 1,
                    question.getContent()));
            if (!Common.isTextNullOrEmpty(question.getUrlImage())) {
                //nếu có ảnh mô tả đi kèm
                examDetailHolder.ivDescription.setVisibility(View.VISIBLE);
                examDetailHolder.ivDescription.setImageURI(Uri.parse("android.resource://vn.com.lvvu.hocbanglaixemay/drawable/" + question.getUrlImage()));
            } else {
                //nếu không có ảnh mô tả đi kèm
                examDetailHolder.ivDescription.setVisibility(View.GONE);
            }

            if (!Common.isTextNullOrEmpty(question.getAnswer1())) {
                examDetailHolder.layoutAnswer1.setVisibility(View.VISIBLE);
                examDetailHolder.tvAnswer1Content.setText(question.getAnswer1());

                //trường hợp hiển thị khi đang thi bình thường
                if (eDisplayType == EDisplayType.NORMAL || eDisplayType == EDisplayType.NONE) {
                    if (checkAnswerIsChoose(question, 1)) {
                        //nếu đáp án này đang được chọn
                        examDetailHolder.tvNumber1.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                        examDetailHolder.tvNumber1.setTextColor(context.getResources().getColor(android.R.color.white));
                        examDetailHolder.tvAnswer1Content.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                    } else {
                        //nếu đáp án này không được chọn
                        examDetailHolder.tvNumber1.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_with_stroke_gray));
                        examDetailHolder.tvNumber1.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                        examDetailHolder.tvAnswer1Content.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                    }
                } else if (eDisplayType == EDisplayType.RESULT) {
                    //trường hợp hiển thị sau khi kết thúc bài thi
                    if (question.getListChoose()[0]) {
                        //nếu đáp án này được chọn
                        if (question.checkAnswerIsCorrect(1)) {
                            //nếu là đáp án đúng
                            examDetailHolder.tvNumber1.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                            examDetailHolder.tvNumber1.setTextColor(context.getResources().getColor(android.R.color.white));
                            examDetailHolder.tvAnswer1Content.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                        } else {
                            //nếu là đáp án sai
                            examDetailHolder.tvNumber1.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                            examDetailHolder.tvNumber1.setTextColor(context.getResources().getColor(android.R.color.white));
                            examDetailHolder.tvAnswer1Content.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
                        }
                    } else {
                        //nếu đáp án này không được chọn
                        if (question.checkAnswerIsCorrect(1)) {
                            examDetailHolder.tvNumber1.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                            examDetailHolder.tvNumber1.setTextColor(context.getResources().getColor(android.R.color.white));
                            examDetailHolder.tvAnswer1Content.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                        } else {
                            examDetailHolder.tvNumber1.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_with_stroke_gray));
                            examDetailHolder.tvNumber1.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                            examDetailHolder.tvAnswer1Content.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                        }
                    }
                } else {
                    //trường hợp vào xem giải thích đề
                    if (question.checkAnswerIsCorrect(1)) {
                        //nếu đây là đáp án
                        examDetailHolder.tvNumber1.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                        examDetailHolder.tvNumber1.setTextColor(context.getResources().getColor(android.R.color.white));
                        examDetailHolder.tvAnswer1Content.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    } else {
                        //nếu đây không phải là đáp án
                        examDetailHolder.tvNumber1.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_with_stroke_gray));
                        examDetailHolder.tvNumber1.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                        examDetailHolder.tvAnswer1Content.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                    }
                }

                examDetailHolder.layoutAnswer1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (eDisplayType == EDisplayType.NORMAL) {
                            if (checkAnswerIsChoose(question, 1)) {
                                removeAnswerChoose(question, 1);
                                examDetailHolder.tvNumber1.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_with_stroke_gray));
                                examDetailHolder.tvNumber1.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                            } else {
                                addAnswerChoose(question, 1);
                                examDetailHolder.tvNumber1.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                                examDetailHolder.tvNumber1.setTextColor(context.getResources().getColor(android.R.color.white));
                            }

                            ViewAnimationUtils.scaleTextViewAnimation(examDetailHolder.tvAnswer1Content, context);
                        }
                    }
                });
            } else {
                examDetailHolder.layoutAnswer1.setVisibility(View.GONE);
            }

            if (!Common.isTextNullOrEmpty(question.getAnswer2())) {
                examDetailHolder.layoutAnswer2.setVisibility(View.VISIBLE);
                examDetailHolder.tvAnswer2Content.setText(question.getAnswer2());
                examDetailHolder.divider1.setVisibility(View.VISIBLE);

                //trường hợp hiển thị khi đang thi bình thường
                if (eDisplayType == EDisplayType.NORMAL || eDisplayType == EDisplayType.NONE) {
                    if (checkAnswerIsChoose(question, 2)) {
                        //nếu đáp án này đang được chọn
                        examDetailHolder.tvNumber2.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                        examDetailHolder.tvNumber2.setTextColor(context.getResources().getColor(android.R.color.white));
                        examDetailHolder.tvAnswer2Content.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                    } else {
                        //nếu đáp án này không được chọn
                        examDetailHolder.tvNumber2.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_with_stroke_gray));
                        examDetailHolder.tvNumber2.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                        examDetailHolder.tvAnswer2Content.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                    }
                } else if (eDisplayType == EDisplayType.RESULT) {
                    //trường hợp hiển thị sau khi kết thúc bài thi
                    if (question.getListChoose()[1]) {
                        //nếu đáp án này được chọn
                        if (question.checkAnswerIsCorrect(2)) {
                            //nếu là đáp án đúng
                            examDetailHolder.tvNumber2.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                            examDetailHolder.tvNumber2.setTextColor(context.getResources().getColor(android.R.color.white));
                            examDetailHolder.tvAnswer2Content.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                        } else {
                            //nếu là đáp án sai
                            examDetailHolder.tvNumber2.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                            examDetailHolder.tvNumber2.setTextColor(context.getResources().getColor(android.R.color.white));
                            examDetailHolder.tvAnswer2Content.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
                        }
                    } else {
                        //nếu đáp án này không được chọn
                        if (question.checkAnswerIsCorrect(2)) {
                            examDetailHolder.tvNumber2.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                            examDetailHolder.tvNumber2.setTextColor(context.getResources().getColor(android.R.color.white));
                            examDetailHolder.tvAnswer2Content.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                        } else {
                            examDetailHolder.tvNumber2.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_with_stroke_gray));
                            examDetailHolder.tvNumber2.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                            examDetailHolder.tvAnswer2Content.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                        }
                    }
                } else {
                    //trường hợp vào xem giải thích đề
                    if (question.checkAnswerIsCorrect(2)) {
                        //nếu đây là đáp án
                        examDetailHolder.tvNumber2.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                        examDetailHolder.tvNumber2.setTextColor(context.getResources().getColor(android.R.color.white));
                        examDetailHolder.tvAnswer2Content.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    } else {
                        //nếu đây không phải là đáp án
                        examDetailHolder.tvNumber2.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_with_stroke_gray));
                        examDetailHolder.tvNumber2.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                        examDetailHolder.tvAnswer2Content.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                    }
                }

                examDetailHolder.layoutAnswer2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (eDisplayType == EDisplayType.NORMAL) {
                            if (checkAnswerIsChoose(question, 2)) {
                                removeAnswerChoose(question, 2);
                                examDetailHolder.tvNumber2.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_with_stroke_gray));
                                examDetailHolder.tvNumber2.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                            } else {
                                addAnswerChoose(question, 2);
                                examDetailHolder.tvNumber2.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                                examDetailHolder.tvNumber2.setTextColor(context.getResources().getColor(android.R.color.white));
                            }

                            ViewAnimationUtils.scaleTextViewAnimation(examDetailHolder.tvAnswer2Content, context);
                        }
                    }

                });
            } else {
                examDetailHolder.layoutAnswer2.setVisibility(View.GONE);
                examDetailHolder.divider1.setVisibility(View.GONE);
            }

            if (!Common.isTextNullOrEmpty(question.getAnswer3())) {
                examDetailHolder.layoutAnswer3.setVisibility(View.VISIBLE);
                examDetailHolder.tvAnswer3Content.setText(question.getAnswer3());
                examDetailHolder.divider2.setVisibility(View.VISIBLE);

                //trường hợp hiển thị khi đang thi bình thường
                if (eDisplayType == EDisplayType.NORMAL || eDisplayType == EDisplayType.NONE) {
                    if (checkAnswerIsChoose(question, 3)) {
                        //nếu đáp án này đang được chọn
                        examDetailHolder.tvNumber3.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                        examDetailHolder.tvNumber3.setTextColor(context.getResources().getColor(android.R.color.white));
                        examDetailHolder.tvAnswer3Content.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                    } else {
                        //nếu đáp án này không được chọn
                        examDetailHolder.tvNumber3.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_with_stroke_gray));
                        examDetailHolder.tvNumber3.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                        examDetailHolder.tvAnswer3Content.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                    }
                } else if (eDisplayType == EDisplayType.RESULT) {
                    //trường hợp hiển thị sau khi kết thúc bài thi
                    if (question.getListChoose()[2]) {
                        //nếu đáp án này được chọn
                        if (question.checkAnswerIsCorrect(3)) {
                            //nếu là đáp án đúng
                            examDetailHolder.tvNumber3.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                            examDetailHolder.tvNumber3.setTextColor(context.getResources().getColor(android.R.color.white));
                            examDetailHolder.tvAnswer3Content.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                        } else {
                            //nếu là đáp án sai
                            examDetailHolder.tvNumber3.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                            examDetailHolder.tvNumber3.setTextColor(context.getResources().getColor(android.R.color.white));
                            examDetailHolder.tvAnswer3Content.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
                        }
                    } else {
                        //nếu đáp án này không được chọn
                        if (question.checkAnswerIsCorrect(3)) {
                            examDetailHolder.tvNumber3.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                            examDetailHolder.tvNumber3.setTextColor(context.getResources().getColor(android.R.color.white));
                            examDetailHolder.tvAnswer3Content.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                        } else {
                            examDetailHolder.tvNumber3.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_with_stroke_gray));
                            examDetailHolder.tvNumber3.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                            examDetailHolder.tvAnswer3Content.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                        }
                    }
                } else {
                    //trường hợp vào xem giải thích đề
                    if (question.checkAnswerIsCorrect(3)) {
                        //nếu đây là đáp án
                        examDetailHolder.tvNumber3.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                        examDetailHolder.tvNumber3.setTextColor(context.getResources().getColor(android.R.color.white));
                        examDetailHolder.tvAnswer3Content.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    } else {
                        //nếu đây không phải là đáp án
                        examDetailHolder.tvNumber3.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_with_stroke_gray));
                        examDetailHolder.tvNumber3.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                        examDetailHolder.tvAnswer3Content.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                    }
                }

                examDetailHolder.layoutAnswer3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (eDisplayType == EDisplayType.NORMAL) {
                            if (checkAnswerIsChoose(question, 3)) {
                                removeAnswerChoose(question, 3);
                                examDetailHolder.tvNumber3.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_with_stroke_gray));
                                examDetailHolder.tvNumber3.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                            } else {
                                addAnswerChoose(question, 3);
                                examDetailHolder.tvNumber3.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                                examDetailHolder.tvNumber3.setTextColor(context.getResources().getColor(android.R.color.white));
                            }

                            ViewAnimationUtils.scaleTextViewAnimation(examDetailHolder.tvAnswer3Content, context);
                        }
                    }
                });
            } else {
                examDetailHolder.layoutAnswer3.setVisibility(View.GONE);
                examDetailHolder.divider2.setVisibility(View.GONE);
            }

            if (!Common.isTextNullOrEmpty(question.getAnswer4())) {
                examDetailHolder.layoutAnswer4.setVisibility(View.VISIBLE);
                examDetailHolder.tvAnswer4Content.setText(question.getAnswer4());
                examDetailHolder.divider3.setVisibility(View.VISIBLE);

                //trường hợp hiển thị khi đang thi bình thường
                if (eDisplayType == EDisplayType.NORMAL || eDisplayType == EDisplayType.NONE) {
                    if (checkAnswerIsChoose(question, 4)) {
                        //nếu đáp án này đang được chọn
                        examDetailHolder.tvNumber4.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                        examDetailHolder.tvNumber4.setTextColor(context.getResources().getColor(android.R.color.white));
                        examDetailHolder.tvAnswer4Content.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                    } else {
                        //nếu đáp án này không được chọn
                        examDetailHolder.tvNumber4.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_with_stroke_gray));
                        examDetailHolder.tvNumber4.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                        examDetailHolder.tvAnswer4Content.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                    }
                } else if (eDisplayType == EDisplayType.RESULT) {
                    //trường hợp hiển thị sau khi kết thúc bài thi
                    if (question.getListChoose()[3]) {
                        //nếu đáp án này được chọn
                        if (question.checkAnswerIsCorrect(4)) {
                            //nếu là đáp án đúng
                            examDetailHolder.tvNumber4.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                            examDetailHolder.tvNumber4.setTextColor(context.getResources().getColor(android.R.color.white));
                            examDetailHolder.tvAnswer4Content.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                        } else {
                            //nếu là đáp án sai
                            examDetailHolder.tvNumber4.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                            examDetailHolder.tvNumber4.setTextColor(context.getResources().getColor(android.R.color.white));
                            examDetailHolder.tvAnswer4Content.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
                        }
                    } else {
                        //nếu đáp án này không được chọn
                        if (question.checkAnswerIsCorrect(4)) {
                            examDetailHolder.tvNumber4.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                            examDetailHolder.tvNumber4.setTextColor(context.getResources().getColor(android.R.color.white));
                            examDetailHolder.tvAnswer4Content.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                        } else {
                            examDetailHolder.tvNumber4.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_with_stroke_gray));
                            examDetailHolder.tvNumber4.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                            examDetailHolder.tvAnswer4Content.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                        }
                    }
                } else {
                    //trường hợp vào xem giải thích đề
                    if (question.checkAnswerIsCorrect(4)) {
                        //nếu đây là đáp án
                        examDetailHolder.tvNumber4.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                        examDetailHolder.tvNumber4.setTextColor(context.getResources().getColor(android.R.color.white));
                        examDetailHolder.tvAnswer4Content.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    } else {
                        //nếu đây không phải là đáp án
                        examDetailHolder.tvNumber4.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_with_stroke_gray));
                        examDetailHolder.tvNumber4.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                        examDetailHolder.tvAnswer4Content.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                    }
                }

                examDetailHolder.layoutAnswer4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (eDisplayType == EDisplayType.NORMAL) {
                            if (checkAnswerIsChoose(question, 4)) {
                                removeAnswerChoose(question, 4);
                                examDetailHolder.tvNumber4.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_with_stroke_gray));
                                examDetailHolder.tvNumber4.setTextColor(context.getResources().getColor(R.color.text_color_primary));
                            } else {
                                addAnswerChoose(question, 4);
                                examDetailHolder.tvNumber4.setBackground(context.getResources().getDrawable(R.drawable.bg_circle_color_primary));
                                examDetailHolder.tvNumber4.setTextColor(context.getResources().getColor(android.R.color.white));
                            }

                            ViewAnimationUtils.scaleTextViewAnimation(examDetailHolder.tvAnswer4Content, context);
                        }
                    }
                });
            } else {
                examDetailHolder.layoutAnswer4.setVisibility(View.GONE);
                examDetailHolder.divider3.setVisibility(View.GONE);
            }

            if (eDisplayType == EDisplayType.NORMAL || eDisplayType == EDisplayType.NONE) {
                examDetailHolder.layoutExplainTitle.setVisibility(View.GONE);
                examDetailHolder.tvExplainContent.setVisibility(View.GONE);
            } else {
                examDetailHolder.layoutExplainTitle.setVisibility(View.GONE);
                examDetailHolder.tvExplainContent.setVisibility(View.GONE);
            }

        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    private void removeAnswerChoose(Question question, int position) {
        try {
            boolean[] listAnswerChoose = question.getListChoose();
            listAnswerChoose[position - 1] = false;
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    /**
     * Kiểm tra xem câu trả lời này được chọn hay chưa
     *
     * @param question câu hỏi
     * @param position vị trí câu trả lời: 1,2,3 or 4
     * @return true nếu đáp án này được chọn
     */
    private boolean checkAnswerIsChoose(Question question, int position) {
        boolean[] listChoose = question.getListChoose();
        return listChoose[position - 1];
    }

    private void addAnswerChoose(Question question, int position) {
        boolean[] listChoose = question.getListChoose();
        listChoose[position - 1] = true;

    }

    @Override
    public int getItemCount() {
        if (questions != null) {
            return questions.size();
        }
        return 0;
    }

    /**
     * refresh lại toàn bộ đáp án đã chọn
     */
    void refreshAllAnswer() {

        if (questions != null && questions.size() > 0) {
            for (Question question : questions) {
                for (int i = 0; i < question.getListChoose().length; i++) {
                    question.getListChoose()[i] = false;
                    question.seteAnswerType(EAnswerType.EMPTY);
                }
            }
        }

    }

    /**
     * Kiểm tra xem câu trả lời có chính xác hay không
     *
     * @return số câu trả lời đúng
     */
    int checkQuestionCorrect() {
        int count = 0;
        try {
            for (Question question : questions) {

                EAnswerType eAnswerType = EAnswerType.EMPTY;

                boolean[] listChoose = question.getListChoose();
                List<Integer> listCorrect = question.getCorrects();

                int totalAnswer = listCorrect.size();
                int totalChoose = 0;

                for (boolean aListChoose : listChoose) {
                    if (aListChoose) {
                        totalChoose++;
                    }
                }

                if (totalAnswer == totalChoose) {

                    for (Integer integer : listCorrect) {
                        if (!listChoose[integer - 1]) {
                            eAnswerType = EAnswerType.WRONG;
                            break;
                        }
                    }

                    if (eAnswerType != EAnswerType.WRONG) {
                        eAnswerType = EAnswerType.CORRECT;
                        count++;
                    }

                } else {
                    if (totalChoose == 0) {
                        eAnswerType = EAnswerType.EMPTY;
                    } else {
                        eAnswerType = EAnswerType.WRONG;
                    }
                }

                question.seteAnswerType(eAnswerType);

            }
        } catch (Exception e) {
            Common.handleException(e);
        }

        return count;
    }

    class ExamDetailHolder extends RecyclerView.ViewHolder {

        TextView tvQuestionTitle;

        LinearLayout layoutAnswer1, layoutAnswer2, layoutAnswer3, layoutAnswer4;

        TextView tvNumber1, tvNumber2, tvNumber3, tvNumber4;

        TextView tvAnswer1Content, tvAnswer2Content, tvAnswer3Content, tvAnswer4Content;

        View divider1, divider2, divider3;

        LinearLayout layoutExplainTitle;

        TextView tvExplainContent;

        ImageView ivDescription;

        ExamDetailHolder(@NonNull View itemView) {
            super(itemView);

            tvQuestionTitle = itemView.findViewById(R.id.tv_question_title);

            layoutAnswer1 = itemView.findViewById(R.id.layout_answer1);
            layoutAnswer2 = itemView.findViewById(R.id.layout_answer2);
            layoutAnswer3 = itemView.findViewById(R.id.layout_answer3);
            layoutAnswer4 = itemView.findViewById(R.id.layout_answer4);

            tvNumber1 = itemView.findViewById(R.id.tv_number1);
            tvNumber2 = itemView.findViewById(R.id.tv_number2);
            tvNumber3 = itemView.findViewById(R.id.tv_number3);
            tvNumber4 = itemView.findViewById(R.id.tv_number4);

            tvAnswer1Content = itemView.findViewById(R.id.tv_answer1_content);
            tvAnswer2Content = itemView.findViewById(R.id.tv_answer2_content);
            tvAnswer3Content = itemView.findViewById(R.id.tv_answer3_content);
            tvAnswer4Content = itemView.findViewById(R.id.tv_answer4_content);

            divider1 = itemView.findViewById(R.id.divider1);
            divider2 = itemView.findViewById(R.id.divider2);
            divider3 = itemView.findViewById(R.id.divider3);

            layoutExplainTitle = itemView.findViewById(R.id.layout_explain_title);
            tvExplainContent = itemView.findViewById(R.id.tv_explain_content);

            ivDescription = itemView.findViewById(R.id.iv_description);
        }
    }

    public void setData(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getData() {
        return this.questions;
    }

    EDisplayType geteDisplayType() {
        return eDisplayType;
    }

    void seteDisplayType(EDisplayType eDisplayType) {
        this.eDisplayType = eDisplayType;
    }

    public enum EDisplayType {

        NONE(0), NORMAL(1), RESULT(2), EXPLAIN(3);

        private int value;

        EDisplayType(int value) {
            this.value = value;
        }

        public static EDisplayType getDisplayType(int value) {
            switch (value) {
                case 0:
                    return NONE;
                case 1:
                    return NORMAL;
                case 2:
                    return RESULT;
                case 3:
                    return EXPLAIN;
                default:
                    return NORMAL;
            }
        }

        public int getValue() {
            return value;
        }

    }

    public enum EAnswerType {

        EMPTY, WRONG, CORRECT

    }
}
