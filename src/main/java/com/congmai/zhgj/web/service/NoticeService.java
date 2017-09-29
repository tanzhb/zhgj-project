package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Notice;

public interface NoticeService extends GenericService<Notice, String> {

	/**
	 * 
	 * @Description (获取公告管理列表)
	 * @param notice
	 * @return
	 */
	Page<Notice> selectByPage(Notice notice);

	/**
	 * 
	 * @Description (批量删除)
	 * @param serialNumArray
	 * @param currentLoginName
	 */
	void deleteBatch(List<String> serialNumArray,String currentLoginName);
	
	/**
	 * 
	 * @Description (获取我的公告列表)
	 * @param notice
	 * @return
	 */
	Page<Notice> selectMyNoticeByPage(Notice notice,String userId,String companyType);

	/**
	 * 
	 * @Description (删除个人公告)
	 * @param serialNum
	 */
	void deleteNoticeShare(String serialNum,String userId);
	
	/**
	 * 
	 * @Description (设为已读)
	 * @param serialNum
	 */
	void readNoticeShare(String serialNum,String userId);

}
