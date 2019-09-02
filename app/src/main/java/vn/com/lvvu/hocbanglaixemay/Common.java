package vn.com.lvvu.hocbanglaixemay;

import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Xử lí common
 * Created by levan on 7/14/2019.
 */

public class Common {

    private static final Gson gson = new Gson();

    public static void handleException(Exception e) {
        //todo
    }

    public static void enableView(View view) {
        //todo
    }

    /**
     * Kiểm tra chuỗi rỗng hay không
     *
     * @param content nội dung chuỗi
     * @return true nếu chuỗi rỗng và ngược lại
     */
    public static boolean isTextNullOrEmpty(String content) {
        if (content == null || content.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Convert từ object sang String
     *
     * @param object
     * @return
     */
    public static String convertObjectToString(Object object) {
        return gson.toJson(object);
    }

    /**
     * Convert từ String sang object
     *
     * @param content
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T convertStringToObject(String content, Class<T> type) {

        if (isTextNullOrEmpty(content)) {
            return null;
        }

        return gson.fromJson(content, type);

    }

    public static List<Integer> getCorrectAnswerFromString(String content) {
        if (!isTextNullOrEmpty(content)) {
            String[] strings = content.split(";");
            if (strings != null && strings.length > 0) {
                List<Integer> integers = new ArrayList<>();
                for (int i = 0; i < strings.length; i++) {
                    integers.add(Integer.parseInt(strings[i]));
                }
                return integers;
            }
        }
        return null;
    }

    public static void requestAds(AdView adView){
        try {
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
                }catch (Exception e){
                    Common.handleException(e);
                }
    }

}
