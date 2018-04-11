package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.generic.GenericDao;
import com.congmai.zhgj.core.generic.GenericServiceImpl;
import com.congmai.zhgj.web.dao.CompanyContactMapper;
import com.congmai.zhgj.web.dao.CompanyFinanceMapper;
import com.congmai.zhgj.web.dao.CompanyMapper;
import com.congmai.zhgj.web.dao.CompanyQualificationMapper;
import com.congmai.zhgj.web.dao.SupplyBuyVOMapper;
import com.congmai.zhgj.web.enums.ComType;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CompanyContact;
import com.congmai.zhgj.web.model.CompanyContactExample;
import com.congmai.zhgj.web.model.CompanyExample;
import com.congmai.zhgj.web.model.CompanyFinance;
import com.congmai.zhgj.web.model.CompanyFinanceExample;
import com.congmai.zhgj.web.model.CompanyQualification;
import com.congmai.zhgj.web.model.CompanyQualificationExample;
import com.congmai.zhgj.web.model.SupplyBuyVO;
import com.congmai.zhgj.web.model.SupplyBuyVOExample;
import com.congmai.zhgj.web.service.CompanyService;

@Service
public class CompanyServiceImpl extends GenericServiceImpl<Company, String> implements CompanyService{

	@Resource
	private CompanyMapper companyMapper;
	@Resource
	private CompanyContactMapper companyContactMapper;
	@Resource
	private CompanyFinanceMapper companyFinanceMapper;
	@Resource
	private CompanyQualificationMapper companyQualificationMapper;
	@Resource
	private SupplyBuyVOMapper supplyBuyVOMapper;
	
	@Override
	public GenericDao<Company, String> getDao() {
		
		return companyMapper;
	}


	@Override
	public Page<Company> selectByPage(Company company) {
		List<Company> list =  null;
		Integer count = 0;
		if(company != null&&company.getComId()==null){//平台登录时
			/*list = companyMapper.selectList(company);
			if(CollectionUtils.isNotEmpty(list)){
				for(Company vo:list){
					if(StringUtils.isNotEmpty(vo.getComType())){
						vo.setComTypeName(ComType.getInfo(vo.getComType()));
					}
				}
			}
			count = companyMapper.countList(company);*/
			CompanyExample ce=new CompanyExample();
			com.congmai.zhgj.web.model.CompanyExample.Criteria c=ce.createCriteria();
			c.andDelFlgEqualTo("0");
			if("other".equals(company.getComType())){
				c.andComTypeNotEqualTo("1").andComTypeNotEqualTo("2");
			}else{c.andComTypeEqualTo("buy".equals(company.getComType())?"1":"2");}
			list=companyMapper.selectByExample(ce);
			if(CollectionUtils.isNotEmpty(list)){
				for(Company vo:list){
					if(StringUtils.isNotEmpty(vo.getComType())){
						vo.setComTypeName(ComType.getInfo(vo.getComType()));
					}
				}
			}
			count=list.size();
		}
		if(company != null&&"1".equals(company.getComType())&&company.getComId()!=null){//采购商登录时
			 SupplyBuyVO svo=new SupplyBuyVO();
			 svo.setCreateId(company.getComId());
			 svo.setSupplyId("notnull");
			 svo.setPageIndex(company.getPageIndex());
			 svo.setPageSize(company.getPageSize());
			list = companyMapper.selectListByRole(svo);
			if(CollectionUtils.isNotEmpty(list)){
				for(Company vo:list){
					if(StringUtils.isNotEmpty(vo.getComType())){
						vo.setComTypeName(ComType.getInfo(vo.getComType()));
					}
				}
			}
			count = companyMapper.countList(company);
		}
		if(company != null&&"2".equals(company.getComType())&&company.getComId()!=null){//供应商登录时
			 SupplyBuyVO svo=new SupplyBuyVO();
			 svo.setCreateId(company.getComId());
			 svo.setBuyId("notnull");
			 svo.setPageIndex(company.getPageIndex());
			 svo.setPageSize(company.getPageSize());
			list = companyMapper.selectListByRole(svo);
			if(CollectionUtils.isNotEmpty(list)){
				for(Company vo:list){
					if(StringUtils.isNotEmpty(vo.getComType())){
						vo.setComTypeName(ComType.getInfo(vo.getComType()));
					}
				}
			}
			count = companyMapper.countList(company);
		}
		Page<Company>  page = new Page<Company>(company.getPageIndex(),company.getPageSize());
		page.setResult(list);
		page.setTotalCount(count);
		return page;
	}

	@Override
	public Company selectOne(String id) {
		Company com = companyMapper.selectByPrimaryKey(id);
		if(com !=null && StringUtils.isNotBlank(com.getComType())){
			com.setComTypeName(ComType.getInfo(com.getComType()));
		}
		return com;
	}


	@Override
	public void deleteBatch(List<String> comIdList) {
			companyMapper.deleteCompanyBatch(comIdList);
			
			
	
			//删除联系人
			CompanyContact contact = new CompanyContact();
			contact.setDelFlg("1");
			contact.setUpdateTime(new Date());
			CompanyContactExample contactExample = new CompanyContactExample();
			contactExample.createCriteria().andComIdIn(comIdList);
			companyContactMapper.updateByExampleSelective(contact, contactExample);
			
			//删除账号信息
			CompanyFinance finance = new CompanyFinance();
			finance.setDelFlg("1");
			finance.setUpdateTime(new Date());
			CompanyFinanceExample financeExample = new CompanyFinanceExample();
			financeExample.createCriteria().andComIdIn(comIdList);
			companyFinanceMapper.updateByExampleSelective(finance, financeExample);
			//删除资质信息
			CompanyQualification qualification = new CompanyQualification();
			qualification.setDelFlg("1");
			qualification.setUpdateTime(new Date());
			CompanyQualificationExample qualificationExample = new CompanyQualificationExample();
			qualificationExample.createCriteria().andComIdIn(comIdList);
			companyQualificationMapper.updateByExampleSelective(qualification, qualificationExample);
	}

	@Override
	public void insertBatch(List<Company> companyList) {
		companyMapper.insertSelectiveBatch(companyList);
	}


	@Override
	public List<Company> selectCompanyByComType(String comType,String searchKey) {
		Map<String,Object> map =new HashMap<String, Object>();
		map.put("comType", comType);
		if(StringUtils.isNotBlank(searchKey)){
			map.put("searchKey", searchKey);
		}else{
			map.put("searchKey", null);
		}
		
		return companyMapper.selectCompanyByComType(map);
	}


	@Override
	public String selectComIdByComName(String comName) {
		CompanyExample example = new CompanyExample();
		example.createCriteria().andComNameEqualTo(comName);
		List<Company> list = companyMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0).getComId();
		}
		return null;
	}


	@Override
	public int countCompanybySelective(CompanyExample example) {
	
		return companyMapper.countByExample(example);
	}


	@Override
	public void deleteCompany(String comId) {
		this.delete(comId);
		//删除联系人
		CompanyContact contact = new CompanyContact();
		contact.setDelFlg("1");
		CompanyContactExample contactExample = new CompanyContactExample();
		contactExample.createCriteria().andComIdEqualTo(comId);
		companyContactMapper.updateByExampleSelective(contact, contactExample);
		
		//删除账号信息
		CompanyFinance finance = new CompanyFinance();
		finance.setDelFlg("1");
		CompanyFinanceExample financeExample = new CompanyFinanceExample();
		financeExample.createCriteria().andComIdEqualTo(comId);
		companyFinanceMapper.updateByExampleSelective(finance, financeExample);
		//删除资质信息
		CompanyQualification qualification = new CompanyQualification();
		qualification.setDelFlg("1");
		CompanyQualificationExample qualificationExample = new CompanyQualificationExample();
		qualificationExample.createCriteria().andComIdEqualTo(comId);
		companyQualificationMapper.updateByExampleSelective(qualification, qualificationExample);
		
	}


	@Override
	public List<Company> selectAll() {
		Company company = new Company();
		company.setPageSize(-1);
		return companyMapper.selectList(company);
	}


	@Override
	public void insertSupplyBuy(SupplyBuyVO vo) {
		supplyBuyVOMapper.insert(vo);
		
	}


	@Override
	public List<SupplyBuyVO> getSupplyBuy(SupplyBuyVO vo) {
		SupplyBuyVOExample se=new  SupplyBuyVOExample ();
		SupplyBuyVOExample.Criteria  c=se.createCriteria();
		c.andDelFlgEqualTo("0");
		c.andCreateIdEqualTo(vo.getCreateId());
		if(StringUtils.isEmpty(vo.getBuyId())){//供应商查询采购商
			c.andBuyIdIsNotNull().andSupplyIdIsNull();
		}else{//供应商查询采购商
			c.andBuyIdIsNull().andSupplyIdIsNotNull();
		}
		return supplyBuyVOMapper.selectByExample(se);
	}


	@Override
	public void deleteSupplyBuy(String comId) {
		// TODO Auto-generated method stub
		SupplyBuyVOExample se=new  SupplyBuyVOExample ();
		SupplyBuyVOExample.Criteria  c=se.createCriteria();
		c.andCreateIdEqualTo(comId);
		supplyBuyVOMapper.deleteByExample(se);
	}



	
}
