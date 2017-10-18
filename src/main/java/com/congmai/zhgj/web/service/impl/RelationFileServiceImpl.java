package com.congmai.zhgj.web.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.Constants;
import com.congmai.zhgj.web.dao.RelationFileMapper;
import com.congmai.zhgj.web.model.RelationFile;
import com.congmai.zhgj.web.model.RelationFileExample;
import com.congmai.zhgj.web.service.RelationFileService;
@Service
public class RelationFileServiceImpl extends GenericServiceImpl<RelationFile, String> implements RelationFileService {

	@Autowired
	private RelationFileMapper relationFileMapper;
	
	@Override
	public GenericDao<RelationFile, String> getDao() {
		
		return relationFileMapper;
	}
	
	@Override
	public List<RelationFile> getAttachFileInfo(String fileType,
			String relationSerial) {
		RelationFileExample example = new RelationFileExample();
		example.createCriteria().andFileTypeEqualTo(fileType).andRelationSerialEqualTo(relationSerial).andDelFlgEqualTo(Constants.DEL_FLAG_DEFUALT);
		return relationFileMapper.selectByExample(example);
	}

	@Override
	public void insertAttachFiles(List<RelationFile> files) {
		if(CollectionUtils.isNotEmpty(files)){
			for(RelationFile file : files){
				relationFileMapper.insert(file);
			}
		}
		
	}



}
