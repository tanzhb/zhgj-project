package com.congmai.zhgj.web.model;

import java.util.List;
/**
 * 
 * @ClassName JsonTreeData
 * @Description 树节点对象
 * @author qfzhao
 * @Date 2017年8月2日 下午6:19:45
 * @version 1.0.0
 */
 
public class JsonTreeData {
 
    public String id;       //json id
    public String pid;      //父节点
    public String text;     //显示文本
    public String icon;     //图标
    public String state;    // 'open','closed'
    public boolean children;       //
     
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public boolean isChildren() {
		return children;
	}
	public void setChildren(boolean children) {
		this.children = children;
	}
	public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
}