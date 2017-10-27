package com.congmai.zhgj.web.model;

public class CompanyCode {
	
	
	//物料功能分类
	public static final String FUNCTIONTYPE = "functionType";
		
		
    private String moduleType;

    private String comId;

    private String fieldCode;

    private String fieldValue;

    private String dropValue;

    private Integer sort;

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType == null ? null : moduleType.trim();
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId == null ? null : comId.trim();
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode == null ? null : fieldCode.trim();
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue == null ? null : fieldValue.trim();
    }

    public String getDropValue() {
        return dropValue;
    }

    public void setDropValue(String dropValue) {
        this.dropValue = dropValue == null ? null : dropValue.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}