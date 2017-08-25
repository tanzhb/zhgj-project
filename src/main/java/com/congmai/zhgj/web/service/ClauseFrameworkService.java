package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.ClauseFramework;
import com.congmai.zhgj.web.model.ClauseFrameworkExample;
import com.congmai.zhgj.web.model.Materiel;

/**
 * 
 * @ClassName ClauseFrameworkService
 * @Description 框架条款Service
 * @author qfzhao
 * @Date 2017年7月28日 下午3:04:17
 * @version 1.0.0
 */
public interface ClauseFrameworkService extends GenericService<ClauseFramework, String> {
	/**
     * 查询多个对象
     *
     * @return 对象集合
     */
    List<ClauseFramework> selectList(ClauseFrameworkExample m);

	public void deleteClauseFrameworks(String ids);

	void betchInsertBOM(List<ClauseFramework> clauseFramework);


}
