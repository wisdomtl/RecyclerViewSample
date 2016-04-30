package telanx.cooee.recyclerviewsample.entity;

/**
 * Created by qq on 2016/4/16.
 */
public class ItemData {
    public static final int LAYOUT_1 = 1;
    public static final int LAYOUT_2 = 2;

    private String title;
    private String time;
    private int layoutType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(int layoutType) {
        this.layoutType = layoutType;
    }
}
