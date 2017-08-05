<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<div class="toggler tooltips" data-container="body" data-placement="left" data-html="true" data-original-title="页面风格设置">
    <i class="icon-settings"></i>
</div>
<div class="toggler-close">
    <i class="icon-close"></i>
</div>
<div class="theme-options">
    <div class="theme-option theme-colors clearfix">
        <span> 主题颜色 </span>
        <ul>
            <li class="color-default current tooltips" data-style="default" data-container="body" data-original-title="Default"> </li>
            <li class="color-grey tooltips" data-style="grey" data-container="body" data-original-title="Grey"> </li>
            <li class="color-blue tooltips" data-style="blue" data-container="body" data-original-title="Blue"> </li>
            <li class="color-dark tooltips" data-style="dark" data-container="body" data-original-title="Dark"> </li>
            <li class="color-light tooltips" data-style="light" data-container="body" data-original-title="Light"> </li>
        </ul>
    </div>
    <div class="theme-option">
        <span> 主题风格 </span>
        <select class="layout-style-option form-control input-small">
            <option value="square" selected="selected">直角</option>
            <option value="rounded">圆角</option>
        </select>
    </div>
    <div class="theme-option">
        <span> 布局 </span>
        <select class="layout-option form-control input-small">
            <option value="fluid" selected="selected">顺序</option>
            <option value="boxed">盒状</option>
        </select>
    </div>
    <div class="theme-option">
        <span> 标题 </span>
        <select class="page-header-option form-control input-small">
            <option value="fixed" selected="selected">固定</option>
            <option value="default">默认</option>
        </select>
    </div>
    <div class="theme-option">
        <span> 顶部下拉</span>
        <select class="page-header-top-dropdown-style-option form-control input-small">
            <option value="light" selected="selected">明亮</option>
            <option value="dark">黑色</option>
        </select>
    </div>
    <div class="theme-option">
        <span> 工具栏模式</span>
        <select class="sidebar-option form-control input-small">
            <option value="fixed">固定</option>
            <option value="default" selected="selected">默认</option>
        </select>
    </div>
    <div class="theme-option">
        <span> 工具栏风格</span>
        <select class="sidebar-style-option form-control input-small">
            <option value="default" selected="selected">默认</option>
            <option value="compact">紧凑</option>
        </select>
    </div>
    <div class="theme-option">
        <span> 工具栏菜单 </span>
        <select class="sidebar-menu-option form-control input-small">
            <option value="accordion" selected="selected">折叠</option>
            <option value="hover">悬停</option>
        </select>
    </div>
    <div class="theme-option">
        <span> 工具栏位置 </span>
        <select class="sidebar-pos-option form-control input-small">
            <option value="left" selected="selected">靠左</option>
            <option value="right">靠右</option>
        </select>
    </div>
    <div class="theme-option">
        <span> 页脚 </span>
        <select class="page-footer-option form-control input-small">
            <option value="fixed">固定</option>
            <option value="default" selected="selected">默认</option>
        </select>
    </div>
</div>