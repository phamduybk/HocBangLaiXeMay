package vn.com.lvvu.hocbanglaixemay.objects;

import java.util.List;

import vn.com.lvvu.hocbanglaixemay.examdetail.ExamDetailAdapter;

/**
 * Câu hỏi
 * Created by levan on 7/15/2019.
 */

public class Question {

    private int ID;

    private String content;

    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    private List<Integer> corrects;

    private boolean[] listChoose = new boolean[]{false, false, false, false};

    private String urlImage;

    private int parentID;

    private ExamDetailAdapter.EAnswerType eAnswerType = ExamDetailAdapter.EAnswerType.EMPTY;

    public Question(int ID, String content, String answer1, String answer2, String answer3,
                    String answer4, List<Integer> corrects, String urlImage, int parentID) {
        this.ID = ID;
        this.content = content;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.corrects = corrects;
        this.urlImage = urlImage;
        this.parentID = parentID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public List<Integer> getCorrects() {
        return corrects;
    }

    public void setCorrects(List<Integer> corrects) {
        this.corrects = corrects;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public boolean[] getListChoose() {
        return listChoose;
    }

    /**
     * Kiểm tra xem câu hỏi đã được trả lời hay chưa
     *
     * @return true nếu câu hỏi đã được trả lời
     */
    public boolean checkIsReallyAnswer() {
        for (int i = 0; i < listChoose.length; i++) {
            if (listChoose[i]) return true;
        }
        return false;
    }

    /**
     * Kiểm tra xem đáp án có phải là đáp án đúng hay không
     *
     * @param position vị trí đáp án
     * @return true nếu là đáp án đúng
     */
    public boolean checkAnswerIsCorrect(int position) {
        if (corrects != null && corrects.size() > 0) {
            if (corrects.contains(position)) return true;
        }
        return false;
    }

    public ExamDetailAdapter.EAnswerType geteAnswerType() {
        return eAnswerType;
    }

    public void seteAnswerType(ExamDetailAdapter.EAnswerType eAnswerType) {
        this.eAnswerType = eAnswerType;
    }
}
