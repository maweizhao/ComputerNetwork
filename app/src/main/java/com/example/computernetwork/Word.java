package com.example.computernetwork;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Word {

    @Id
    private int ID;
    private String abbreviations;
    private String English;
    private String Chinese;
    private String explain;
    @Generated(hash = 1523312331)
    public Word(int ID, String abbreviations, String English, String Chinese,
            String explain) {
        this.ID = ID;
        this.abbreviations = abbreviations;
        this.English = English;
        this.Chinese = Chinese;
        this.explain = explain;
    }
    @Generated(hash = 3342184)
    public Word() {
    }
    public int getID() {
        return this.ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getAbbreviations() {
        return this.abbreviations;
    }
    public void setAbbreviations(String abbreviations) {
        this.abbreviations = abbreviations;
    }
    public String getEnglish() {
        return this.English;
    }
    public void setEnglish(String English) {
        this.English = English;
    }
    public String getChinese() {
        return this.Chinese;
    }
    public void setChinese(String Chinese) {
        this.Chinese = Chinese;
    }
    public String getExplain() {
        return this.explain;
    }
    public void setExplain(String explain) {
        this.explain = explain;
    }

}
