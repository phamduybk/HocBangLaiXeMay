package vn.com.lvvu.hocbanglaixemay.objects;

/**
 * Created by levan on 7/26/2019.
 */

public class TipTrickType {

    private int tipID;

    private String title;

    public TipTrickType(int tipID, String title) {
        this.tipID = tipID;
        this.title = title;
    }

    public int getTipID() {
        return tipID;
    }

    public void setTipID(int tipID) {
        this.tipID = tipID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
