package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.NoticeMapper;
import com.congmai.zhgj.web.model.Notice;
import com.congmai.zhgj.web.model.NoticeExample;
import com.congmai.zhgj.web.model.NoticeExample.Criteria;
import com.congmai.zhgj.web.service.NoticeService;
@Service
public class NoticeServiceImpl extends GenericServiceImpl<Notice, String> implements
		NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public GenericDao<Notice, String> getDao() {
		
		return this.noticeMapper;
	}

	@Override
	public Page<Notice> selectByPage(Notice notice) {
		NoticeExample noticeExample = new NoticeExample();
		//example.setPageIndex(0);
		//example.setPageSize(-1);
		Criteria c =  noticeExample.createCriteria();
		c.andDelFlgEqualTo("0");
		Page<Notice> page = new Page<Notice>();
		page.setResult(noticeMapper.selectByExample(noticeExample));
		page.setTotalCount(noticeMapper.countByExample(noticeExample));
		return page;
	}

	@Override
	public void deleteBatch(List<String> serialNumArray,String currentLoginName) {
		NoticeExample noticeExample = new NoticeExample();
		noticeExample.createCriteria().andSerialNumIn(serialNumArray);
		Notice notice = new Notice();
		notice.setDelFlg("1"); //删除
		notice.setUpdater("");
		notice.setUpdateTime(new Date());
		noticeMapper.updateByExampleSelective(notice, noticeExample);
		
	}



}
