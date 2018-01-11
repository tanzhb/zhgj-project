package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.ContractFile;
import com.congmai.zhgj.web.model.ContractFileExample;

/**
 * 
 * @ClassName ContractFileService
 * @Description 合同附件Service
 * @author qfzhao
 * @Date 2017年7月28日 下午3:04:17
 * @version 1.0.0
 */
public interface ContractFileService extends GenericService<ContractFile, String> {
	/**
     * 查询多个对象
     *
     * @return 对象集合
     */
    List<ContractFile> selectList(ContractFileExample m);

	public void deleteContractFiles(String ids);

	void betchInsertContractFiles(List<ContractFile> orderFiles);


}
