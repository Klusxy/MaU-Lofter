package oracle.mau.entity;

/**
 * Created by shadow on 2017/3/8.
 */

public class ChatEntity {
    private UserEntity user1;//当前用户
    private UserEntity user2;//对方用户
    private String text;//谈话的文本内容
    private String time;

    public UserEntity getUser1() {
        return user1;
    }

    public void setUser1(UserEntity user1) {
        this.user1 = user1;
    }

    public UserEntity getUser2() {
        return user2;
    }

    public void setUser2(UserEntity user2) {
        this.user2 = user2;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
