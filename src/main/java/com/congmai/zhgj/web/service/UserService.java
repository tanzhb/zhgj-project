package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.User;

/**
 * 
 * @ClassName UserService
 * @Description  用户 业务 接口
 * @author tanzb
 * @Date 2017年7月26日 下午2:55:25
 * @version 1.0.0
 */
public interface UserService extends GenericService<User, Long> {

    /**
     * 用户验证
     * 
     * @param user
     * @return
     */
    User authentication(User user);

    /**
     * 根据用户名查询用户
     * 
     * @param username
     * @return
     */
    User selectByUsername(String username);
    
    /**
     * 
     * @Description 查找所有用户信息
     * @return
     */
    List<User> findAllUsers();
    
    
   public void insertContract(ContractVO contractVO);
    
    
    
    public List<ContractVO> queryContractList(String userId);
    
    
    /**
     * 
     * @Description 批量删除
     * @param ids
     * @return
     */
	public void deleteUserContractS(String ids);
	
	
	public ContractVO selectConbtractById(String id);
	
	
	public void updateContract(ContractVO contractVO);
}
