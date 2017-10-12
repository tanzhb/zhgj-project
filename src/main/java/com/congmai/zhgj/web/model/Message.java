package com.congmai.zhgj.web.model;

import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Message {
    private String serialNum;

    private String objectSerial;

    private String actionName;

    private String messageType;

    private String templeteType;

    private String title;

    private String receiverId;
    
    private List<String> receiverIds;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;

    private String readFlg;

    private String delFlg;

    private String context;
    
    private Properties properties;
    private String creatorDelFlag;
    private String receiverDelFlag;
    
    private int pageIndex;
    
    private int pageSize;
    
    private int start;
    

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getObjectSerial() {
        return objectSerial;
    }

    public void setObjectSerial(String objectSerial) {
        this.objectSerial = objectSerial == null ? null : objectSerial.trim();
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName == null ? null : actionName.trim();
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType == null ? null : messageType.trim();
    }

    public String getTempleteType() {
        return templeteType;
    }

    public void setTempleteType(String templeteType) {
        this.templeteType = templeteType == null ? null : templeteType.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId == null ? null : receiverId.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getCreatorDelFlag() {
		return creatorDelFlag;
	}

	public void setCreatorDelFlag(String creatorDelFlag) {
		this.creatorDelFlag = creatorDelFlag;
	}

	public String getReceiverDelFlag() {
		return receiverDelFlag;
	}

	public void setReceiverDelFlag(String receiverDelFlag) {
		this.receiverDelFlag = receiverDelFlag;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public List<String> getReceiverIds() {
		return receiverIds;
	}

	public void setReceiverIds(List<String> receiverIds) {
		this.receiverIds = receiverIds;
	}
}