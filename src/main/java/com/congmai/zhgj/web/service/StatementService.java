package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Statement;
import com.congmai.zhgj.web.model.StatementExample;

/**
 * 
 * @ClassName StatementService
 * @Description 对账单Service
 * @author qfzhao
 * @Date 2017年7月28日 下午3:04:17
 * @version 1.0.0
 */
public interface StatementService extends GenericService<Statement, String> {
	/**
     * 查询多个对象
     *
     * @return 对象集合
     */
    List<Statement> selectList(StatementExample m);

	public void deleteStatements(String ids);


}
