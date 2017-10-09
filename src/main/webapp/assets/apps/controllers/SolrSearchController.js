/* Setup SolrSearch page controller */
angular
		.module('MetronicApp', [ 'tm.pagination' ])
		.controller(
				'SolrSearchController',
				[
						'$rootScope',
						'$scope',
						'settings',
						'$sce', 'UserService', 
						function($rootScope, $scope, settings, $sce, UserService) {
							$scope
									.$on(
											'$viewContentLoaded',
											function() {
												// initialize core components
												App.initAjax();

												if ($rootScope.searchList != undefined) {//从header.jsp页面接收searchList，并设置翻页控件的数据总数
													$scope.searchItems = $rootScope.searchList;// 将查询到的结果放在solrSearch.jsp页面循环显示
													$scope.paginationConf.totalItems = $rootScope.totalItems;
												}
											});

							$scope.TrustDangerousSnippet = function(post) {// ng-repeat循环含有html标签时，添加html信任，显示标签
								return $sce.trustAsHtml(post.search_fields);
							};
							//配置分页基本参数
							$scope.paginationConf = {
								currentPage : 1,
								totalItems : 0,
								itemsPerPage : 10,
								pagesLength : 15,
								perPageOptions : [ 10, 20, 30, 40, 50 ],
								onChange : function() {
									console.log("$rootScope.searchType = " + $rootScope.searchType);
									
									console.log("$scope.paginationConf.itemsPerPage = " + $scope.paginationConf.itemsPerPage);
									var start = ($scope.paginationConf.currentPage - 1)*10;
									console.log("start = " + start);
									UserService
									.solrSearch($rootScope.queryStr,$rootScope.searchType,start,$scope.paginationConf.itemsPerPage)
									.then(
											function(data) {
												$scope.searchItems = data[0].datas
											},
											function(errResponse) {
												toastr.warning("未找到相关信息！");
												console
														.error('Error while search');
											}
									);
								}
							};
							
							var GetAll = function () {//不加此处，$scope.paginationConf.onChange貌似不执行
//								console.log("&&&&&&&&&&&&&&&" + $scope.paginationConf.currentPage);
//								console.log("&&&&&&&&&&&&&&&" + $scope.paginationConf.itemsPerPage);
							}
							
							
							
							/***************************************************************
					        当页码和页面记录数发生变化时监控后台查询
					        如果把currentPage和itemsPerPage分开监控的话则会触发两次后台事件。
					        ***************************************************************/
					        $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', GetAll);
							
							
							

						} ]);
