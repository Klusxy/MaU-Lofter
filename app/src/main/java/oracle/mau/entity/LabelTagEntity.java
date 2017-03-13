package oracle.mau.entity;

import java.io.Serializable;
import java.util.List;

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


    private int tagId;
    private String tagTitle;
    private List<ArticleEntity> articleEntityListList;

    public List<ArticleEntity> getArticleEntityListList() {
        return articleEntityListList;
    }

    public void setArticleEntityListList(List<ArticleEntity> articleEntityListList) {
        this.articleEntityListList = articleEntityListList;
    }

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
