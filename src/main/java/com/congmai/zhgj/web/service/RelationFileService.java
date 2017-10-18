package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.web.model.RelationFile;

public interface RelationFileService {
	
	List<RelationFile> getAttachFileInfo(String fileType,String relationSerial);

	void insertAttachFiles(List<RelationFile> files);
}
