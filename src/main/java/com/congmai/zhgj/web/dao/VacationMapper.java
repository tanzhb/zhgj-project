package com.congmai.zhgj.web.dao;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.Vacation;

/**
 * @author tanzb
 * @version 2017年8月9日 下午10:22:09
 */
public interface VacationMapper extends GenericDao<Vacation, Integer>{
	
	public List<Vacation> toList(Integer userId) throws Exception;
	
	public List<Vacation> findByStatus(Integer userId, String status) throws Exception; 
}
