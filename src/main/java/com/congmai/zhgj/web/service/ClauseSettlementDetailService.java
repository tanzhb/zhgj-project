package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.ClauseSettlementDetail;
import com.congmai.zhgj.web.model.ClauseSettlementDetailExample;

/**
 * 
 * @ClassName ClauseSettlementDetailService
 * @Description 结算条款明细Service
 * @author qfzhao
 * @Date 2017年7月28日 下午3:04:17
 * @version 1.0.0
 */
public interface ClauseSettlementDetailService extends GenericService<ClauseSettlementDetail, String> {
	/**
     * 查询多个对象
     *
     * @return 对象集合
     */
    List<ClauseSettlementDetail> selectList(ClauseSettlementDetailExample m);

	public void deleteClauseSettlementDetails(String ids);

	void betchInsertClauseSettlementDetails(List<ClauseSettlementDetail> clauseSettlementDetails);


}
