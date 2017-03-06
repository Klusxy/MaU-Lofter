package oracle.mau.entity;

import java.io.Serializable;

/**
 * Created by 田帅 on 2017/2/28.
 */

public class LabelTagEntity implements Serializable{
    private int tagId;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    private String tagTitle;
    private int[] imgs;

    public String getTagTitle() {
        return tagTitle;
    }

    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle;
    }

    public int[] getImgs() {
        return imgs;
    }

    public void setImgs(int[] imgs) {
        this.imgs = imgs;
    }
}
