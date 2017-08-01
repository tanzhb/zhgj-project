package com.congmai.zhgj.web.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.MaterielExample.Criteria;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.MaterielService;
import com.congmai.zhgj.web.service.UserService;

/**
 * 
 * @ClassName MaterielController
 * @Description 物料信息业务处理
 * @author qfzhao
 * @Date 2017年7月28日 下午2:57:52
 * @version 1.0.0
 */
@Controller
@RequestMapping("/materiel")
public class MaterielController {
	
    @Resource
    private MaterielService materielService;

    /**
     * 保存
     */
    @RequestMapping("/save")
    @ResponseBody
    public String save(Materiel materiel, HttpServletRequest request) {
    	materiel.setSerialNum(UUID.randomUUID().toString().replaceAll("-", ""));
    	materiel.setMaterielId(UUID.randomUUID().toString().replaceAll("-", ""));
    	User user = (User) request.getSession().getAttribute("userInfo");
    	if(user!=null){
    		materiel.setCreator(user.getId().toString());
    		materiel.setUpdater(user.getId().toString());
    	}
    	materiel.setCreateTime(new Date());
    	materiel.setUpdateTime(new Date());
    	materiel.setIsLatestVersion("1");
    	materiel.setVersionNO("1");
    	materiel.setStatus("1");
    	
    	materielService.insert(materiel);
    	return "materiel/addMateriel";
    }

    /**
     * @param oredCriteria 
     * @Description 查询物料列表
     * @param materiel
     * @return
     */
    @RequestMapping("/findMaterielList")
    @ResponseBody
    public List<Materiel> findMaterielList(String start,String limit) {
    	MaterielExample m =new MaterielExample();
    	Criteria criteria =  m.createCriteria();
    	criteria.andIsLatestVersionEqualTo("1");
    	Criteria orcriteria =  m.createCriteria();
    	orcriteria.andStatusEqualTo("0");
    	m.or(orcriteria);
    	m.setOrderByClause("updateTime");
    	List<Materiel> materielList = materielService.selectList(m);
    	return materielList;
    }
    
    
}