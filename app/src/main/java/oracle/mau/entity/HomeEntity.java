package oracle.mau.entity;

/**
 * Created by Administrator on 2017/3/6.
 */

public class HomeEntity {
    private  int userid;
    private int commandid;
    private String username;
    private int pic;
    private String commentcontent;
    private String time;
    private String commandname;
    private String content;

    public HomeEntity() {
        super();
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getCommandid() {
        return commandid;
    }

    public void setCommandid(int commandid) {
        this.commandid = commandid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getCommentcontent() {
        return commentcontent;
    }

    public void setCommentcontent(String commentcontent) {
        this.commentcontent = commentcontent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCommandname() {
        return commandname;
    }

    public void setCommandname(String commandname) {
        this.commandname = commandname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public HomeEntity(int userid, int commandid, String username, int pic, String commentcontent, String time, String commandname, String content, String likeNum, String hot, String sign) {
        this.userid = userid;
        this.commandid = commandid;
        this.username = username;
        this.pic = pic;
        this.commentcontent = commentcontent;
        this.time = time;
        this.commandname = commandname;
        this.content = content;
        this.likeNum = likeNum;
        this.hot = hot;
        this.sign = sign;
    }

    private String likeNum;
    private String hot;
    private String sign;


}
