package com.congmai.zhgj.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.congmai.zhgj.web.dao.CategoryMapper;
import com.congmai.zhgj.web.model.Category;
import com.congmai.zhgj.web.model.CategoryExample.Criteria;
import com.congmai.zhgj.web.model.CategoryExample;
import com.congmai.zhgj.web.service.CategoryService;

/**
 * 
 * @ClassName CategoryServiceImpl
 * @Description 物料分类
 * @author qfzhao
 * @Date 2017年10月24日 上午10:39:48
 * @version 1.0.0
 */
@Service
public class CategoryServiceImpl  implements
CategoryService {

	@Resource
	private CategoryMapper categoryMapper;

	@Override
	public int insert(Category model) {
		return categoryMapper.insert(model);
	}

	@Override
	public int update(Category model) {
		return categoryMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return categoryMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public Category selectById(String serialNum) {
		return categoryMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public Category selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> selectList() {
		return categoryMapper.selectByExample(null);
	}

	@Override
	public List<Category> queryCategoryListByParent(String parentId) {
		CategoryExample ex = new CategoryExample();
		Criteria criteria =  ex.createCriteria();
    	criteria.andParentIdEqualTo(parentId);
    	criteria.andDelFlgEqualTo("0");
    	ex.setOrderByClause("sort asc");
		List<Category> list = categoryMapper.selectByExample(ex);
		return list;
	}

	@Override
	public int selectMaxSortByParentId(String parentId) {
		return categoryMapper.selectMaxSortByParentId(parentId);
	}



}