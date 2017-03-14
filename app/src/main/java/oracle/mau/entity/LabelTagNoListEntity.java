package oracle.mau.entity;

import java.io.Serializable;

/**
 * Created by 田帅 on 2017/3/14.
 */

public class LabelTagNoListEntity implements Serializable {
    /**
     * 选中状态position
     */
    private int selectPosition;

    /**
     * 是否是拖动之后
     */
    private boolean isDrag;


    private int tagId;
    private String tagTitle;

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    public boolean isDrag() {
        return isDrag;
    }

    public void setDrag(boolean drag) {
        isDrag = drag;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagTitle() {
        return tagTitle;
    }

    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle;
    }
}
