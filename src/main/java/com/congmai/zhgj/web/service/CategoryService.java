package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Category;
import com.congmai.zhgj.web.model.CategoryExample;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.OrderInfoExample;

/**
 * 
 * @ClassName CategoryService
 * @Description 物料分类
 * @author qfzhao
 * @Date 2017年10月24日 上午10:31:55
 * @version 1.0.0
 */
public interface CategoryService extends GenericService<Category, String> {

	/**
	 * 查询分类list
	 * @param parent(包含父节点id)
	 */
	public List<Category> queryCategoryListByParent(String parentId);
	
	/**
	 * 查询子分类的最大序号
	 * @param parent(包含父节点id)
	 */
	public int selectMaxSortByParentId(String parentId);
	
    List<Category> selectList(CategoryExample m);
}
