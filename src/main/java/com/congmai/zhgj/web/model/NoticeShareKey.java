package com.congmai.zhgj.web.model;

public class NoticeShareKey {
    private String userId;

    private String noticeSerial;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getNoticeSerial() {
        return noticeSerial;
    }

    public void setNoticeSerial(String noticeSerial) {
        this.noticeSerial = noticeSerial == null ? null : noticeSerial.trim();
    }
}