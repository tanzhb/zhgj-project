package com.congmai.zhgj.web.dao;

import java.util.List;
import java.util.Map;

import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.OrderInfo;

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
    
    
    public List<ContractVO> querySaleContractList(String userId);
    
    
    public List<ContractVO> queryBuyContractList(String userId);
    
    
    public List<OrderInfo> queryOrderInfo(String contractSerial);
    
    //删除合同
    public void deleteUserContractS(List<String> ids);
    
    //查询合同对象
    public ContractVO selectContractById(String id);
    
    //更新合同
    public void updateContract(ContractVO record);
    
    //订单中更新合同
    public void updateContract4order(ContractVO record);
    
    //签订销售合同 
    public void signSaleContract(ContractVO contractVO);
    
    //签订销售合同 后更新订单
    public void updateOrderAfterSign(Map<String,Object> map);
    
    
    String checkNum(ContractVO contractVO);

	List<ContractVO> selectList(ContractVO parm);
}