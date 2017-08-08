package com.congmai.zhgj.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.model.UserExample;

/**
 * 
 * @ClassName 合同Dao接口
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author czw
 */
public interface ContractMapper extends GenericDao<ContractVO, String> {
    
	//添加合同
    int insertContract(ContractVO record);

    //查询合同列表
    List<ContractVO> queryContractList(String userId);
    
    //删除合同
    public void deleteUserContractS(List<String> ids);
    
    //查询合同对象
    public ContractVO selectContractById(String id);
    
    //更新合同
    public void updateContract(ContractVO record);
}