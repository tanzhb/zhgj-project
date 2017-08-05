<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<div class="page-sidebar navbar-collapse collapse">
    <!-- BEGIN SIDEBAR MENU -->
    <!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
    <!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
    <!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
    <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
    <!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
    <!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
    <ul class="page-sidebar-menu" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" ng-class="{'page-sidebar-menu-closed': settings.layout.pageSidebarClosed}">
        <li class="start">
            <a ui-sref="dashboard">
                <i class="icon-home"></i>
                <span class="title">首页</span>
            </a>
        </li>
        <li class="nav-item">
            <a href="javascript:;" class="nav-link nav-toggle">
                <i class="icon-settings"></i>
                <span class="title">基础数据</span>
                <span class="arrow "></span>
            </a>
            <ul class="sub-menu">
                <li>
                    <a ui-sref="uibootstrap">
                        <i class="icon-puzzle"></i> 物料信息</span>
                    </a>
                </li>
                <li>
                    <a ui-sref="fileupload">
                        <i class="icon-paper-clip"></i> 企业信息</span>
                    </a>
                </li>
                <li>
                    <a ui-sref="jgml">
                        <i class="icon-check"></i> 价格目录</span>
                    </a>
                </li>
                <li>
                    <a ui-sref="uiselect">
                        <i class="icon-check"></i> 合同管理</span>
                    </a>
                </li>
                <li>
                    <a ui-sref="warehouse">
                        <i class="icon-check"></i> 仓库信息</span>
                    </a>
                </li>
            </ul>
        </li>
        <li class="nav-item">
            <a ui-sref="profile.dashboard" id="sidebar_menu_link_profile">
                <i class="icon-user"></i>
                <span class="title">销售订单</span>
            </a>
        </li>
        <li class="nav-item">
            <a ui-sref="todo">
                <i class="icon-check"></i>
                <span class="title">采购订单</span>
            </a>
        </li>
        <li class="nav-item">
            <a ui-sref="profile.dashboard" id="sidebar_menu_link_profile">
                <i class="icon-user"></i>
                <span class="title">库存</span>
            </a>
        </li>
        <li class="nav-item">
            <a ui-sref="todo">
                <i class="icon-check"></i>
                <span class="title">对账单</span>
            </a>
        </li>
        <li class="nav-item">
            <a ui-sref="profile.dashboard" id="sidebar_menu_link_profile">
                <i class="icon-user"></i>
                <span class="title">收付款</span>
            </a>
        </li>
        <li class="nav-item">
            <a ui-sref="todo">
                <i class="icon-check"></i>
                <span class="title">发票</span>
            </a>
        </li>
        <li class="nav-item">
            <a href="javascript:;" class="nav-link nav-toggle">
                <i class="icon-settings"></i>
                <span class="title">系统管理</span>
                <span class="arrow "></span>
            </a>
            <ul class="sub-menu">
                <li>
                    <a ui-sref="user">
                        <i class="icon-puzzle"></i> 用户管理</span>
                    </a>
                </li>
                <li>
                    <a ui-sref="fileupload">
                        <i class="icon-paper-clip"></i> 角色管理</span>
                    </a>
                </li>
                <li>
                    <a ui-sref="uiselect">
                        <i class="icon-check"></i> 资源管理</span>
                    </a>
                </li>
                <li>
                    <a ui-sref="uiselect">
                        <i class="icon-check"></i> 组织管理</span>
                    </a>
                </li>
            </ul>
        </li>
        <li class="nav-item">
            <a ui-sref="blank">
                <i class="icon-refresh"></i>
                <span class="title">Blank Page</span>
            </a>
        </li>
    </ul>
    <!-- END SIDEBAR MENU -->
</div>