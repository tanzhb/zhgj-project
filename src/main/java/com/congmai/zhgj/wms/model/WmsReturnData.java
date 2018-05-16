package com.congmai.zhgj.wms.model;

import java.util.List;

public class WmsReturnData{
    private String id;

    /**
     * 返回码，1 成功， 0 失败
     */
    private String flag;
    
    
    private String total;
    
    private List<String> items;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}


}
