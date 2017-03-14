package oracle.mau.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 田帅 on 2017/3/13.
 */

public class SpecialEntity implements Serializable{
    private int specialId;
    private String specialContent;
    private String specialTitle;
    private String specialDate;
    private ArrayList<String> piclist;

    public ArrayList<String> getPiclist() {
        return piclist;
    }

    public void setPiclist(ArrayList<String> piclist) {
        this.piclist = piclist;
    }

    public int getSpecialId() {
        return specialId;
    }

    public void setSpecialId(int specialId) {
        this.specialId = specialId;
    }

    public String getSpecialContent() {
        return specialContent;
    }

    public void setSpecialContent(String specialContent) {
        this.specialContent = specialContent;
    }

    public String getSpecialTitle() {
        return specialTitle;
    }

    public void setSpecialTitle(String specialTitle) {
        this.specialTitle = specialTitle;
    }

    public String getSpecialDate() {
        return specialDate;
    }

    public void setSpecialDate(String specialDate) {
        this.specialDate = specialDate;
    }
}
