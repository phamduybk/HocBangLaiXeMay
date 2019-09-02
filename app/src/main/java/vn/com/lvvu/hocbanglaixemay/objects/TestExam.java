package vn.com.lvvu.hocbanglaixemay.objects;

/**
 * Đề thi
 * Created by levan on 7/14/2019.
 */

public class TestExam {

    private int ID;

    private String ExamName;

    public TestExam(int ID, String examName) {
        this.ID = ID;
        ExamName = examName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getExamName() {
        return ExamName;
    }

    public void setExamName(String examName) {
        ExamName = examName;
    }
}
