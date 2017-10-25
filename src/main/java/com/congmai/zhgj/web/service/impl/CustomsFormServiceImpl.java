package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.dao.CustomsFormMapper;
import com.congmai.zhgj.web.model.CustomsForm;
import com.congmai.zhgj.web.model.CustomsFormExample;
import com.congmai.zhgj.web.model.CustomsFormExample.Criteria;
import com.congmai.zhgj.web.service.CustomsFormService;

/**
 * 
 * @ClassName CustomsFormServiceImpl
 * @Description  报关单/清关单 ServiceImpl
 * @author zhaichao
 * @Date 2017年10月23日 下午3:06:52
 * @version 1.0.0
 */
@Service
public class CustomsFormServiceImpl extends GenericServiceImpl<CustomsForm, String> implements CustomsFormService {

	
	@Resource
	private  CustomsFormMapper customsFormMapper;

	
	@Override
	public GenericDao<CustomsForm, String> getDao() {
		// TODO Auto-generated method stub
		return customsFormMapper;
	}

	@Override
	public int updateStatus(CustomsForm customsForm) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CustomsForm> selectCustomsFormList(String type) {
		// TODO Auto-generated method stub
		CustomsFormExample   example=new CustomsFormExample();
		Criteria  criteria =example.createCriteria();
		criteria.andDelFlgEqualTo("0").andCustomsFormTypeEqualTo(type);
		return customsFormMapper.selectByExample(example);
	}

	@Override
	public void deleteCustomsForm(String serialNumList) {
		// TODO Auto-generated method stub
		List<String> idList = ApplicationUtils.getIdList(serialNumList);
		// TODO Auto-generated method stub
		customsFormMapper.deleteCustomsForm(idList);
		
	}

	
   


	

}
