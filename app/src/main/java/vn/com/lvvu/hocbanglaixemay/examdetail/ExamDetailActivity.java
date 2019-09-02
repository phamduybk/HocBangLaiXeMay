package vn.com.lvvu.hocbanglaixemay.examdetail;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import vn.com.lvvu.hocbanglaixemay.Common;
import vn.com.lvvu.hocbanglaixemay.R;
import vn.com.lvvu.hocbanglaixemay.ViewAnimationUtils;
import vn.com.lvvu.hocbanglaixemay.base.BaseActivity;
import vn.com.lvvu.hocbanglaixemay.databases.CommonSQlite;
import vn.com.lvvu.hocbanglaixemay.objects.Question;
import vn.com.lvvu.hocbanglaixemay.objects.TestExam;
import vn.com.lvvu.hocbanglaixemay.views.ConfirmDialog;
import vn.com.lvvu.hocbanglaixemay.views.GridViewDivider;

/**
 * Chi tiết đề thi thử
 * Created by levan on 7/14/2019.
 */

public class ExamDetailActivity extends BaseActivity implements View.OnClickListener {

    public static final String TEST_EXAM = "TEST_EXAM";

    public static final String DISPLAY_TYPE = "DISPLAY_TYPE";

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;

    @BindView(R.id.tv_toolbar_right)
    TextView tvStartOrStop;

    @BindView(R.id.rv_question)
    RecyclerView rvQuestion;

    @BindView(R.id.tv_previous)
    TextView tvPrevious;

    @BindView(R.id.tv_next)
    TextView tvNext;

    @BindView(R.id.iv_toolbar_ic_left)
    ImageView ivBack;

    @BindView(R.id.rv_all_question)
    RecyclerView rvAllQuestion;

    @BindView(R.id.layout_show_hide_all_question)
    LinearLayout layoutShowHideAllQuestion;

    @BindView(R.id.iv_dropdown)
    ImageView ivDropdown;

    @BindView(R.id.tv_question_number)
    TextView tvNumberQuestion;

    private AllQuestionAdapter allQuestionAdapter;

    private ExamDetailAdapter examDetailAdapter;

    //cờ đánh dấu là đang thi hay không
    private boolean isStart = false;

    private CountDownTimer countDownTimer;

    private TestExam testExam;

    @Override
    public int getLayoutID() {
        return R.layout.activity_exam_detail;
    }

    @Override
    public void initView() {
        try {
            tvStartOrStop.setText(getString(R.string.start));
            tvNumberQuestion.setText(String.format(getString(R.string.number_question), 1));
            rvQuestion.setLayoutManager(new LinearLayoutManager(ExamDetailActivity.this, RecyclerView.HORIZONTAL,
                    false));
            examDetailAdapter = new ExamDetailAdapter(ExamDetailActivity.this, null);
            rvQuestion.setAdapter(examDetailAdapter);

            PagerSnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(rvQuestion);
            rvQuestion.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    int positionSelected = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    tvNumberQuestion.setText(String.format(getString(R.string.number_question), positionSelected + 1));
                    allQuestionAdapter.setPositionSelected(positionSelected);
                }
            });


            allQuestionAdapter = new AllQuestionAdapter(ExamDetailActivity.this, null, new AllQuestionAdapter.OnQuestionSelectedListener() {
                @Override
                public void onQuestionSelected(int position) {
                    try {
                        rvQuestion.scrollToPosition(position);
                    } catch (Exception e) {
                        Common.handleException(e);
                    }
                }
            });
            rvAllQuestion.setLayoutManager(new GridLayoutManager(ExamDetailActivity.this, 5, RecyclerView.VERTICAL
                    , false));
            rvAllQuestion.setAdapter(allQuestionAdapter);
            rvAllQuestion.addItemDecoration(new GridViewDivider(10, 5));

            tvPrevious.setOnClickListener(this);
            tvNext.setOnClickListener(this);
            ivBack.setOnClickListener(this);
            layoutShowHideAllQuestion.setOnClickListener(this);
            tvStartOrStop.setOnClickListener(this);

            isStart = false;
            onStartOrStop();
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    @Override
    public void initData() {
        try {
            String testExamContent = getIntent().getStringExtra(TEST_EXAM);
            testExam = Common.convertStringToObject(testExamContent, TestExam.class);

            int displayType = getIntent().getIntExtra(DISPLAY_TYPE, ExamDetailAdapter.EDisplayType.NONE.getValue());
            ExamDetailAdapter.EDisplayType eDisplayType = ExamDetailAdapter.EDisplayType.getDisplayType(displayType);

            if (testExam != null) {
                tvToolbarTitle.setText(testExam.getExamName());

                List<Question> allQuestions = CommonSQlite.getAllQuestionWithParentID(testExam.getID());
                if (allQuestions != null && allQuestions.size() > 0) {

                    examDetailAdapter.seteDisplayType(eDisplayType);
                    examDetailAdapter.setData(allQuestions);
                    examDetailAdapter.notifyDataSetChanged();

                    //mặc định là xem câu đầu tiên
                    allQuestionAdapter.seteDisplayType(eDisplayType);
                    allQuestionAdapter.setData(allQuestions);
                    allQuestionAdapter.notifyDataSetChanged();
                }
            }
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    @Override
    public void onClick(View view) {
        try {
            Common.enableView(view);

            switch (view.getId()) {
                case R.id.tv_previous:
                    onClickPrevious();
                    break;

                case R.id.tv_next:
                    onClickNext();
                    break;

                case R.id.iv_toolbar_ic_left:
                    onBack();
                    break;

                case R.id.layout_show_hide_all_question:
                    showAndHideAllQuestion();
                    break;

                case R.id.tv_toolbar_right:
                    onStartOrStop();
                    break;
            }
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    private void onClickNext() {
        try {
            int currentPosition = ((LinearLayoutManager) rvQuestion.getLayoutManager()).findFirstVisibleItemPosition();
            if (currentPosition < examDetailAdapter.getItemCount() - 1) {
                rvQuestion.smoothScrollToPosition(currentPosition + 1);
            }
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    private void onClickPrevious() {
        try {
            int currentPosition = ((LinearLayoutManager) rvQuestion.getLayoutManager()).findFirstVisibleItemPosition();
            if (currentPosition > 0) {
                rvQuestion.smoothScrollToPosition(currentPosition - 1);
            }
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    /**
     * Bắt đầu hoặc kết thúc thi
     */
    private void onStartOrStop() {
        try {
            if (isStart) {
                //nếu đang thi mà click vào nút kết thúc thì dừng thi
                showDialogConfirmStopExam();
            } else {
                //bắt đầu thi
                showDialogConfirmStartExam();
            }
            isStart = !isStart;
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    private void showDialogConfirmStartExam() {
        try {
            ConfirmDialog confirmDialog = new ConfirmDialog(getString(R.string.start), "Bạn đã thực sự sẵn sàng!", new ConfirmDialog.OnConfirmListener() {
                @Override
                public void onAccept() {
                    try {
                        if (examDetailAdapter.geteDisplayType() != ExamDetailAdapter.EDisplayType.NORMAL) {
                            examDetailAdapter.seteDisplayType(ExamDetailAdapter.EDisplayType.NORMAL);
                            examDetailAdapter.refreshAllAnswer();
                            rvQuestion.scrollToPosition(0);
                            examDetailAdapter.notifyDataSetChanged();

                            allQuestionAdapter.seteDisplayType(ExamDetailAdapter.EDisplayType.NORMAL);
                            allQuestionAdapter.setPositionSelected(0);
                            allQuestionAdapter.notifyDataSetChanged();
                        }
                        //bật countdown lên
                        if (countDownTimer == null) {
                            countDownTimer = new CountDownTimer(900000, 1000) {
                                @Override
                                public void onTick(long l) {
                                    try {
                                        setTimeString((int) l);
                                    } catch (Exception e) {
                                        Common.handleException(e);
                                    }
                                }

                                @Override
                                public void onFinish() {
                                    try {
                                        //bắn dialog thông báo hết giờ
                                    } catch (Exception e) {
                                        Common.handleException(e);
                                    }
                                }
                            };
                        }
                        countDownTimer.start();
                        tvStartOrStop.setText(R.string.end);
                    } catch (Exception e) {
                        Common.handleException(e);
                    }
                }
            });
            confirmDialog.show(getSupportFragmentManager(), "ConfirmDialog");
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    /**
     * Cập nhật thời gian dạng phút giây
     *
     * @param l
     */
    private void setTimeString(int l) {

        try {
            int minutes = l / 60000;
            int seconds = (l % 60000) / 1000;

            String minutesString;
            if (minutes < 10) {
                minutesString = String.format("0%d", minutes);
            } else {
                minutesString = String.valueOf(minutes);
            }

            String secondsString;
            if (seconds < 10) {
                secondsString = String.format("0%d", seconds);
            } else {
                secondsString = String.valueOf(seconds);
            }

            String textTime = String.format("%s:%s", minutesString, secondsString);

            tvToolbarTitle.setText(textTime);

        } catch (Exception e) {
            Common.handleException(e);
        }

    }

    private void showDialogConfirmStopExam() {
        try {
            ConfirmDialog confirmDialog = new ConfirmDialog(getString(R.string.end), "Bạn đã thực sự muốn kết thúc!", new ConfirmDialog.OnConfirmListener() {
                @Override
                public void onAccept() {
                    try {
                        tvToolbarTitle.setText(testExam.getExamName());
                        //tắt countdown đi
                        countDownTimer.cancel();
                        tvStartOrStop.setText(getString(R.string.retry));

                        showResultDialog();
                    } catch (Exception e) {
                        Common.handleException(e);
                    }
                }
            });
            confirmDialog.show(getSupportFragmentManager(), "ConfirmDialog");
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    /**
     * Show kết quả thi
     */
    public void showResultDialog() {
        try {
            //Kiểm tra từng câu hỏi có trả lời đúng hay không, hoặc không trả lời
            int count = examDetailAdapter.checkQuestionCorrect();

            ConfirmDialog resultDialog = new ConfirmDialog("Kết quả", String.format(getString(R.string.result_message), count),
                    new ConfirmDialog.OnConfirmListener() {
                        @Override
                        public void onAccept() {
                            try {
                                examDetailAdapter.seteDisplayType(ExamDetailAdapter.EDisplayType.RESULT);
                                examDetailAdapter.notifyDataSetChanged();

                                allQuestionAdapter.seteDisplayType(ExamDetailAdapter.EDisplayType.RESULT);
                                allQuestionAdapter.notifyDataSetChanged();

                                if (rvAllQuestion.getVisibility() == View.GONE) {
                                    //hiển thị danh sách câu hỏi ở trạng thái đúng sai lên
                                    ViewAnimationUtils.rollbackRotatingImage(ivDropdown, ExamDetailActivity.this);
                                    ViewAnimationUtils.expand(rvAllQuestion);
                                }
                            } catch (Exception e) {
                                Common.handleException(e);
                            }
                        }
                    });
            resultDialog.show(getSupportFragmentManager(), "ConfirmDialog");
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    /**
     * Hiển thị danh sách toàn bộ câu hỏi
     */
    private void showAndHideAllQuestion() {
        try {
            if (rvAllQuestion.getVisibility() == View.VISIBLE) {
                ViewAnimationUtils.startRotatingImage(ivDropdown, ExamDetailActivity.this);
                ViewAnimationUtils.collapse(rvAllQuestion);
            } else {
                ViewAnimationUtils.rollbackRotatingImage(ivDropdown, ExamDetailActivity.this);
                ViewAnimationUtils.expand(rvAllQuestion);
            }
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    private void onBack() {
        try {
            finish();
        } catch (Exception e) {
            Common.handleException(e);
        }
    }
}
