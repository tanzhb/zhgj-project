<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div class="page-sidebar navbar-collapse collapse">
    <!-- BEGIN SIDEBAR MENU -->
    <!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
    <!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
    <!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
    <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
    <!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
    <!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
    <ul class="page-sidebar-menu page-sidebar-menu-compact" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" ng-class="{'page-sidebar-menu-closed': settings.layout.pageSidebarClosed}">
   <!-- class="page-sidebar-menu" 工具栏风格为默认时的样式，上面改成紧凑的样式-->
   
	        <li class="start">
	            <a ui-sref="dashboard">
	                <i class="icon-home"></i>
	                <span class="title">首页</span>
	            </a>
	        </li>
        <li class="nav-item">
            <a href="javascript:;" class="nav-link nav-toggle">
                <i class="icon-user"></i>
                <span class="title">个人中心</span>
                <span class="arrow "></span>
            </a>
            <ul class="sub-menu">
                <li>
                    <a ui-sref="userInfo">
                         个人中心</span>
                    </a>
                </li>
                <li>
                    <a ui-sref="companyInfo">
                        公司信息</span>
                    </a>
                </li>
                <li>
                    <a ui-sref="accountSecurity">
                       账户安全</span>
                    </a>
                </li>
                <li>
                    <a ui-sref="operateLog">
                        操作日志</span>
                    </a>
                </li>
                <li>
                    <a ui-sref="myMessage">
                         消息</span>
                    </a>
                </li>
                <li>
                    <a ui-sref="myNotice">
                         公告</span>
                    </a>
                </li>
                <li>
                    <a ui-sref="#">
                         联系人</span>
                    </a>
                </li>
            </ul>
        </li>
        
        <shiro:hasPermission name="zhgj:baseData">
        <li class="nav-item">
            <a href="javascript:;" class="nav-link nav-toggle">
                <i class="icon-settings"></i>
                <span class="title">基础数据</span>
                <span class="arrow "></span>
            </a>
            <ul class="sub-menu">
            	<shiro:hasPermission name="zhgj:materiel:*">
                <li>
                    <a ui-sref="materiel">
                         物料信息</span>
                    </a>
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="zhgj:companyManage:*">
                <li>
                    <a ui-sref="company">
                        企业信息</span>
                    </a>
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="zhgj:price:*">
                <li>
                    <a ui-sref="priceList">
                       价格目录</span>
                    </a>
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="zhgj:contract:*">
                <li>
                    <a ui-sref="userContract">
                        合同管理</span>
                    </a>
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="zhgj:warehouse:*">
                <li>
                    <a ui-sref="warehouse">
                         仓库管理</span>
                    </a>
                </li>
                </shiro:hasPermission>
            </ul>
        </li>
        </shiro:hasPermission>
        <shiro:hasPermission name="zhgj:salesOrder">
        <li class="nav-item">
            <a href="javascript:;" class="nav-link nav-toggle" >
             <i class="icon-paper-plane"></i>
                <span class="title">销售订单</span>
                <span class="arrow "></span>
            </a>
            <ul class="sub-menu">
            	<shiro:hasPermission name="zhgj:demand:*">
                <li>
                    <a ui-sref="demandPlan">
                         需求计划</span>
                    </a>
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="zhgj:saleOrder:*">
                <li>
                    <a ui-sref="saleOrder">
                        销售订单</span>
                    </a>
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="zhgj:supplyOrder:*">
                <li>
                    <a ui-sref="supplyOrder">
                        订单</span>
                    </a>
                </li>
                </shiro:hasPermission>
                 <shiro:hasPermission name="zhgj:saleOrder:*">
                <li>
                    <a ui-sref="customsDeclarationForm">
                        报关单</span>
                    </a>
                </li>
                </shiro:hasPermission>
            </ul>
        </li>
        </shiro:hasPermission>
        <shiro:hasPermission name="zhgj:purchaseOrder">
        <li class="nav-item">
            <!-- <a ui-sref="todo">
                <i class="icon-check"></i>
                <span class="title">采购订单</span>
            </a> -->
            <a href="javascript:;" class="nav-link nav-toggle">
                <i class="icon-basket-loaded"></i>
                <span class="title">采购订单</span>
                <span class="arrow "></span>
            </a>
            <ul class="sub-menu">
            	<shiro:hasPermission name="zhgj:buyOrder:*">
                <li>
                    <a ui-sref="buyOrder">
                       采购订单</span>
                    </a>
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="zhgj:purchaseForecast:*">
                <li>
                    <a ui-sref="purchaseForecast">
                         采购预测</span>
                    </a>
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="zhgj:buyOrder:*">
                <li>
                    <a ui-sref="customsClearanceForm">
                      清关单</span>
                    </a>
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="zhgj:customerOrder:*">
                <li>
                <a ui-sref="customerOrder">
                        订单</span>
                    </a>
                </li>
                </shiro:hasPermission>
               <!--  <li>
                    <a ui-sref="">
                        <i class="icon-check"></i>收货单</span>
                    </a>
                </li> -->
            </ul>
        </li>
        </shiro:hasPermission>
        <shiro:hasPermission name="zhgj:storeHouse">
       <li class="nav-item">
            <a href="javascript:;" class="nav-link nav-toggle">
                <i class="icon-grid"></i>
                <span class="title">仓储</span>
                <span class="arrow "></span>
            </a> 
            
            <ul class="sub-menu">
            	<shiro:hasPermission name="zhgj:stock:*">
            	<li>
                    <a ui-sref="stock">
                         库存</span>
                    </a>
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="zhgj:takeDelivery">
                <li>
                    <a ui-sref="takeDelivery">
                       收货</span>
                    </a>
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="zhgj:check">
                <li>
                    <a ui-sref="stockInOutCheck">
                      检验</span>
                    </a>
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="zhgj:delivery">
                <li>
                    <a ui-sref="delivery">
                    发货</span>
                    </a>
                </li>
                </shiro:hasPermission>
            </ul>

        </li>
        </shiro:hasPermission>
        <shiro:hasPermission name="zhgj:statement">
        <li class="nav-item">
            <a href="javascript:;" class="nav-link nav-toggle">
                <i class="icon-docs"></i>
                <span class="title">对账单</span>
                <span class="arrow "></span>
            </a>
            <ul class="sub-menu">
				<%-- <shiro:hasPermission name="zhgj:buyStatement:*"> --%>
					<li><a ui-sref="supplyStatement">供应商对账单</a></li>
				<%-- </shiro:hasPermission>
				<shiro:hasPermission name="zhgj:supplyStatement:*"> --%>
					<li><a ui-sref="buyStatement">客户对账单</a></li>
				<%-- </shiro:hasPermission> --%>
			</ul>
        </li>
		</shiro:hasPermission>
		<shiro:hasPermission name="zhgj:paymentReceived">
       <li class="nav-item">
            <!-- <ul class="sub-menu">
				<li><a ui-sref="#"><i class="icon-paper-clip"></i>应收款</span></a></li>
				<li><a ui-sref="addPay"> <i class="icon-paper-clip"></i>应付款</span></a></li>
			</ul> -->
			 <a href="javascript:;" class="nav-link nav-toggle">
                <i class="icon-credit-card"></i>
                <span class="title">收付款</span>
               <span class="arrow "></span>
            </a> 
			<ul class="sub-menu">
				<shiro:hasPermission name="zhgj:accountReceivable:*">
					<li><a ui-sref="gatheringMoneyRecord">应收款</a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="zhgj:accountsPayable:*">
					<li><a ui-sref="paymentRecordC">应付款</a></li>
				</shiro:hasPermission>
			</ul>
		</li>  
		</shiro:hasPermission>  
		<shiro:hasPermission name="zhgj:bill">   
		<li class="nav-item">
            <a ui-sref="invoice">
                <i class="icon-layers"></i>
                <span class="title">发票</span>
            </a>
        </li>
        </shiro:hasPermission>
        <!-- <li class="nav-item">
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
        </li> -->
    </ul>
    <!-- END SIDEBAR MENU -->
</div>
