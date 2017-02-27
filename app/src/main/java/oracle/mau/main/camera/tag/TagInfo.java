package oracle.mau.main.camera.tag;

import java.io.Serializable;

public final class TagInfo implements Serializable {
    private static final long serialVersionUID = -2939266917839493174L;

    //标签的内容
    public String bname = "";
    //标签图片的id
    public int bid = 0;

    //标签中标签的方向
    public Direction direct = Direction.Left;

    public int leftMargin;
    public int topMargin;
    public int rightMargin;
    public int bottomMargin;

    public enum Direction {
        Left("left"), Right("right");

        Direction(String valString) {
            this.valueString = valString;
        }

        public static int size() {
            return Direction.values().length;
        }

        public String valueString;

        public String toString() {
            return valueString;
        }

        public static Direction valueof(String vaString) {
            if (vaString.equals("left")) {
                return Direction.Left;
            } else if (vaString.equals("right")) {
                return Direction.Right;
            } else {
                return null;
            }
        }
    }
}