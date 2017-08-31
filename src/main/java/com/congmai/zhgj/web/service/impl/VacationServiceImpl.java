package com.congmai.zhgj.web.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.Page;
import com.congmai.zhgj.web.dao.VacationMapper;
import com.congmai.zhgj.web.model.Vacation;
import com.congmai.zhgj.web.service.IVacationService;

@Service
public class VacationServiceImpl extends GenericServiceImpl<Vacation, Integer> implements IVacationService {
	
	@Resource
	private VacationMapper vacationMapper;
	
	@Override
	public GenericDao<Vacation, Integer> getDao() {
		return vacationMapper;
	}
	
	@Override
	public int doAdd(Vacation vacation) throws Exception {
		return vacationMapper.insertSelective(vacation);
	}

	@Override
	public void doUpdate(Vacation vacation) throws Exception {
		vacationMapper.updateByPrimaryKeySelective(vacation);
	}

	@Override
	public List<Vacation> toList(Integer userId) throws Exception {
		List<Vacation> list = vacationMapper.toList(userId);
		return list;
	}

	@Override
	public Vacation findById(Integer id) throws Exception {
		return vacationMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Vacation> findByStatus(Integer userId, String status) throws Exception {
		List<Vacation> list = vacationMapper.findByStatus(userId, status);
		return list;
	}
}
