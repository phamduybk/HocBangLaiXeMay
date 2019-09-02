package vn.com.lvvu.hocbanglaixemay.objects;

/**
 * Created by levan on 7/25/2019.
 */

public class TipTrick {

    private int tipTrickDetailID;

    private int tipTypeID;

    private String title;

    private String imageDescription;

    private String explain1;
    private String explain2;
    private String explain3;
    private String explain4;

    public TipTrick(int tipTrickDetailID, int tipTypeID, String title, String imageDescription, String explain1, String explain2, String explain3, String explain4) {
        this.tipTrickDetailID = tipTrickDetailID;
        this.tipTypeID = tipTypeID;
        this.title = title;
        this.imageDescription = imageDescription;
        this.explain1 = explain1;
        this.explain2 = explain2;
        this.explain3 = explain3;
        this.explain4 = explain4;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public String getExplain1() {
        return explain1;
    }

    public void setExplain1(String explain1) {
        this.explain1 = explain1;
    }

    public String getExplain2() {
        return explain2;
    }

    public void setExplain2(String explain2) {
        this.explain2 = explain2;
    }

    public String getExplain3() {
        return explain3;
    }

    public void setExplain3(String explain3) {
        this.explain3 = explain3;
    }

    public String getExplain4() {
        return explain4;
    }

    public void setExplain4(String explain4) {
        this.explain4 = explain4;
    }
}
