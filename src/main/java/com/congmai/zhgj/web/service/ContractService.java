package com.congmai.zhgj.web.service;

import java.util.List;
import java.util.Map;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.OrderInfo;
import com.congmai.zhgj.web.model.User;

/**
 * 
 * @ClassName ContractService
 * @Description  合同 业务 接口
 * @author czw
 */
public interface ContractService extends GenericService<ContractVO, String> {
	
	/**
	 * 添加用户合同
	 * @param contractVO
	 */
    public void insertContract(ContractVO contractVO);
    
    
    /**
     * 查询用户合同
     * @param userId（用户id）
     * @return
     */
    public List<ContractVO> queryContractList(String userId);
    
    
    
    
    
    
    public List<ContractVO> querySaleContractList(String userId);
    
    
    
    
    public List<ContractVO> queryBuyContractList(String userId);
    
    
    
    public List<OrderInfo> queryOrderInfo(String contractSerial);
    
    
    /**
     * 
     * @Description 批量删除 合同
     * @param ids
     * @return
     */
	public void deleteUserContractS(String ids);
	
	
	/**
	 * 根据id查询合同对象
	 * @param id
	 * @return
	 */
	public ContractVO selectConbtractById(String id);
	
	
	/**
	 * 更新用户合同
	 * @param contractVO
	 */
	public void updateContract(ContractVO contractVO);
	
	
	/**
	 * 签订销售合同
	 * @param contractVO
	 */
	public void signSaleContract(ContractVO contractVO);
	
	
	/**
	 * 签订销售合同后更新订单
	 * @param contractVO
	 */
	public void updateOrderAfterSign(Map<String,Object> map);
	
	/**
	 * @Description 查找付款节点生成付款记录更新订单状态
	 * @param orderString
	 * @param nodeString
	 */
	public void findPaymentNode(String orderString, String nodeString);
}
