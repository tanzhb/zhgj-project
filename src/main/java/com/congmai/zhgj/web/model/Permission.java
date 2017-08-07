package com.congmai.zhgj.web.model;

/**
 * 
 * @ClassName Permission
 * @Description 权限模型
 * @author tanzb
 * @Date 2017年7月26日 下午2:51:04
 * @version 1.0.0
 */
public class Permission {
    private Long id;

    private String permissionName;

    private String permissionSign;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getPermissionSign() {
        return permissionSign;
    }

    public void setPermissionSign(String permissionSign) {
        this.permissionSign = permissionSign == null ? null : permissionSign.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        return "Permission [id=" + id + ", permissionName=" + permissionName + ", permissionSign=" + permissionSign + ", description=" + description + "]";
    }

}