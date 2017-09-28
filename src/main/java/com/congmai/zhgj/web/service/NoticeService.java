package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Notice;

public interface NoticeService extends GenericService<Notice, String> {

	Page<Notice> selectByPage(Notice notice);

	void deleteBatch(List<String> serialNumArray,String currentLoginName);

}
