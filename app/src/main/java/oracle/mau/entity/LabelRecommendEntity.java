package oracle.mau.entity;

import java.util.List;

/**
 * Created by 田帅 on 2017/3/5.
 */

public class LabelRecommendEntity {
    private int lrId;
    private String lrTitle;
    private int lrParticipationNum;
    private String lrBackground;
    private List<String> lrImgs;

    public int getLrId() {
        return lrId;
    }

    public void setLrId(int lrId) {
        this.lrId = lrId;
    }

    public String getLrTitle() {
        return lrTitle;
    }

    public void setLrTitle(String lrTitle) {
        this.lrTitle = lrTitle;
    }

    public int getLrParticipationNum() {
        return lrParticipationNum;
    }

    public void setLrParticipationNum(int lrParticipationNum) {
        this.lrParticipationNum = lrParticipationNum;
    }

    public String getLrBackground() {
        return lrBackground;
    }

    public void setLrBackground(String lrBackground) {
        this.lrBackground = lrBackground;
    }

    public List<String> getLrImgs() {
        return lrImgs;
    }

    public void setLrImgs(List<String> lrImgs) {
        this.lrImgs = lrImgs;
    }
}
