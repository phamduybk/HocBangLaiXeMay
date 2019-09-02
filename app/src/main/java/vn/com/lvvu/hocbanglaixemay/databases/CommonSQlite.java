package vn.com.lvvu.hocbanglaixemay.databases;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.com.lvvu.hocbanglaixemay.Common;
import vn.com.lvvu.hocbanglaixemay.objects.Question;
import vn.com.lvvu.hocbanglaixemay.objects.TestExam;
import vn.com.lvvu.hocbanglaixemay.objects.TipTrick;
import vn.com.lvvu.hocbanglaixemay.objects.TipTrickType;

/**
 * Created by levan on 7/17/2019.
 */

public class CommonSQlite {

    private static String ExamTable = "DeThi";
    private static String QuestionTable = "CauHoi";
    private static String TipTrickTable = "TipTrickType";
    private static String TipTrickDetailTable = "TipTrick";

    //thao tác với bảng danh sách đề thi
    public static List<TestExam> getAllExams() {
        try {
            //mở database để đọc
            SQLiteDatabase db = AssetDatabaseOpenHelper.getInstance().openDatabaseForRead();
            Cursor cursor = db.rawQuery("SELECT * FROM " + ExamTable, null);
            if (cursor != null) {
                List<TestExam> testExams = new ArrayList<>();
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    TestExam testExam = new TestExam(cursor.getInt(0), cursor.getString(1));
                    testExams.add(testExam);
                    cursor.moveToNext();
                }

                return testExams;
            }
            db.close();
        } catch (Exception e) {
            Common.handleException(e);
        }
        return null;
    }

    /**
     * Lấy các câu hỏi của đề thi
     *
     * @param parentID mã đề thi
     */
    public static List<Question> getAllQuestionWithParentID(int parentID) {
        try {
            //mở database để đọc
            SQLiteDatabase db = AssetDatabaseOpenHelper.getInstance().openDatabaseForRead();

            Cursor cursor = db.rawQuery("SELECT * FROM " + QuestionTable + " WHERE ParentID=?", new String[]{String.valueOf(parentID)});

            if (cursor != null) {
                List<Question> questions = new ArrayList<>();
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {

                    Question question = new Question(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                            cursor.getString(3), cursor.getString(4), cursor.getString(5),
                            Common.getCorrectAnswerFromString(cursor.getString(6)), cursor.getString(7), cursor.getInt(8));
                    questions.add(question);

                    cursor.moveToNext();
                }

                return questions;
            }

            db.close();
        } catch (Exception e) {
            Common.handleException(e);
        }
        return null;
    }

    //thao tác với bảng danh sách đề thi
    public static List<TipTrick> getAllTipTrickFromTipID(int tipTypeID) {
        try {
            //mở database để đọc
            SQLiteDatabase db = AssetDatabaseOpenHelper.getInstance().openDatabaseForRead();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TipTrickDetailTable + " WHERE tipTypeID=?", new String[]{String.valueOf(tipTypeID)});
            if (cursor != null) {
                List<TipTrick> tipTrickList = new ArrayList<>();
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    TipTrick tipTrick = new TipTrick(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                            cursor.getString(3), cursor.getString(4)
                            , cursor.getString(5), cursor.getString(6), cursor.getString(7));
                    tipTrickList.add(tipTrick);
                    cursor.moveToNext();
                }

                return tipTrickList;
            }
            db.close();
        } catch (Exception e) {
            Common.handleException(e);
        }
        return null;
    }

    //thao tác với bảng danh sách đề thi
    public static List<TipTrickType> getAllTipTrickType() {
        try {
            //mở database để đọc
            SQLiteDatabase db = AssetDatabaseOpenHelper.getInstance().openDatabaseForRead();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TipTrickTable, null);
            if (cursor != null) {
                List<TipTrickType> tipTrickTypeList = new ArrayList<>();
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    TipTrickType tipTrickType = new TipTrickType(cursor.getInt(0), cursor.getString(1));
                    tipTrickTypeList.add(tipTrickType);
                    cursor.moveToNext();
                }

                return tipTrickTypeList;
            }
            db.close();
        } catch (Exception e) {
            Common.handleException(e);
        }
        return null;
    }

}
