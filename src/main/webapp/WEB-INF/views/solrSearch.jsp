<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
        .page-list .pagination {float:left;}
        .page-list .pagination span {cursor: pointer;}
        .page-list .pagination .separate span{cursor: default; border-top:none;border-bottom:none;}
        .page-list .pagination .separate span:hover {background: none;}
        .page-list .page-total {float:left; margin: 25px 20px;}
        .page-list .page-total input, .page-list .page-total select{height: 26px; border: 1px solid #ddd;}
        .page-list .page-total input {width: 40px; padding-left:3px;}
        .page-list .page-total select {width: 50px;}
    </style>

<div class="search-page search-content-2">
	<div class="row">
		
		<div class="col-md-12">
			<div class="search-container ">
				<ul class="search-container">
					<div ng-repeat="searchItem in searchItems">
				         <li class="search-item clearfix">
							<div class="search-content text-left">
								<h2 class="search-title">
									<a href="javascript:;" ng-click="viewBuyOrder(searchItem.id)">{{searchItem.title}}</a>
								</h2>
								<p class="search-desc" ng-bind-html="TrustDangerousSnippet(searchItem)">
									{{searchItem.search_fields}}
								</p>
								<!-- <p class="search-desc">
									id号:{{searchItem.id}};{{searchItem.comName==null?"物料名称 ":"企业名称"}}:
									<span ng-bind-html="TrustDangerousSnippet(searchItem)">{{searchItem.search_fields}}</span>
									更新日期:{{searchItem.updateTime}}
								</p> -->
							</div>
						</li>        
				     </div>
				</ul>
				<!-- 分页控件指令 ，可以是元素 或者 属性 -->
    			<tm-pagination conf="paginationConf"></tm-pagination>
			</div>
		</div>
	</div>
</div>

<sript type="text/javascript">
	
</sript>