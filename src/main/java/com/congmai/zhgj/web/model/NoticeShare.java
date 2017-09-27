package com.congmai.zhgj.web.model;

public class NoticeShare extends NoticeShareKey {
    private String readFlg;

    private String delFlg;

    public String getReadFlg() {
        return readFlg;
    }

    public void setReadFlg(String readFlg) {
        this.readFlg = readFlg == null ? null : readFlg.trim();
    }

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg == null ? null : delFlg.trim();
    }
}