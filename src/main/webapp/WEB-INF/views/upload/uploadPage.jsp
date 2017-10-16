<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="basicMaterielInfoII" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content" style="width: 800px;">
		
				<div class="portlet-title" style="min-height: 48px;">
					<div class="tools" style="float:right;margin-top:20px;" id="noprintdiv">
						<button type="submit" ng-click="saveFile(fileIndex)"
							class="btn green  btn-circle  btn-sm" >
							<i class="fa fa-save"></i> 保存
						</button>
						<button  data-dismiss="modal" type="button"
							class="btn defualt  btn-circle  btn-sm">
							<i class="fa fa-undo"></i> 取消
						</button>
					</div>
				</div>
				<div class="portlet-body form">
					<form id="form_sample_4">
						<div class="table-scrollable">
							<table class="table table-bordered table-hover" id="fileTable">
								<thead>
									<tr>
										<th>文件</th>
										<th>备注</th>
										<th>上传人</th>
										<th>上传时间</th>
										<th style="width: 100px;"></th>
									</tr>
								</thead>

								<tbody>
									<tr ng-repeat="_file in file track by $index"
										ng-mouseover="showOperation('file',$index)"
										ng-mouseleave="hideOperation('file',$index)">
										<td>
											<div 
												ng-if="file[$index].file==null||file[$index].file==''"
												class="fileinput fileinput-new" data-provides="fileinput">
												<span class="btn blue btn-outline btn-file"> <span
													class="fileinput-new">上传附件</span> <span
													class="fileinput-exists">更改</span> <input type="file"
													name="..." nv-file-select uploader="uploader"
													onchange="angular.element(this).scope().up(this.files[0])"
													ng-model="file[$index].file" ng-click="uploadFile($index)">
												</span> <span class="fileinput-filename">{{_file.file.substring(_file.file.indexOf("_")+1)}}</span>
												&nbsp; <a href="javascript:;" class="close fileinput-exists"
													ng-click="removefile($index)" data-dismiss="fileinput">
												</a>
											</div>
											<div 
												ng-if="file[$index].file!=null&&file[$index].file!=''"
												class="fileinput fileinput-exists" data-provides="fileinput">
												<span class="btn blue btn-outline btn-file"> <span
													class="fileinput-new">上传附件</span> <span
													class="fileinput-exists">更改</span> <input type="file"
													name="..." nv-file-select uploader="uploader"
													onchange="angular.element(this).scope().up(this.files[0])"
													ng-model="file[$index].file" ng-click="uploadFile($index)">
												</span> <span class="fileinput-filename">{{_file.file.substring(_file.file.indexOf("_")+1)}}</span>
												&nbsp; <a href="javascript:;" class="close fileinput-exists"
													ng-click="removefile($index)" data-dismiss="fileinput">
												</a>
											</div> <label ng-show="fileInfoShow"
											ng-if="file[$index].file==null||file[$index].file==''"
											class="c_edit">未上传附件</label> <label ng-show="fileInfoShow"
											ng-if="file[$index].file!=null&&file[$index].file!=''"
											class="c_edit"><a href="javascript:;"
												ng-click="downloadFile(file[$index])">{{_file.file.substring(_file.file.indexOf("_")+1)}}</a></label>
										</td>
										<td>
											<p class="form-control-static" ng-show="fileInfoShow">{{_file.remark}}</p>
											<input type="text" name="remark[$index]" name="remark"
											class="form-control" 
											ng-model="file[$index].remark">
										</td>
										<td><p class="form-control-static">{{_file.uploader}}</p></td>
										<td><p class="form-control-static">{{_file.uploadDate}}</p></td>


										<td ng-show="operation_f{{$index}}"><a
											href="javascript:;" class="btn red btn-sm"
											 ng-click="deleteFile($index)"> <i
												class="fa fa-close"></i>
										</a></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="form-actions right">
							<a class="btn blue btn-sm" 
								ng-click="addFile()"> <i class="fa fa-plus"></i> 增加
							</a>
						</div>
					</form>
				</div>
		</div>
	</div>
</div>