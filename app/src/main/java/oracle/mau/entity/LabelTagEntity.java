package oracle.mau.entity;

import java.io.Serializable;

/**
 * Created by 田帅 on 2017/2/28.
 */

public class LabelTagEntity implements Serializable {
    /**
     * 选中状态position
     */
    private int selectPosition;

    /**
     * 是否是拖动之后
     */
    private boolean isDrag;

    public boolean isDrag() {
        return isDrag;
    }

    public void setDrag(boolean drag) {
        isDrag = drag;
    }

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

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
