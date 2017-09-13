package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.MaterielSelectExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MaterielMapper {
    int countByExample(MaterielExample example);

    int deleteByExample(MaterielExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(Materiel record);

    int insertSelective(Materiel record);

    List<Materiel> selectByExample(MaterielExample example);
    
    List<Materiel> selectBySelectExample(MaterielSelectExample example);

    Materiel selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") Materiel record, @Param("example") MaterielExample example);

    int updateByExample(@Param("record") Materiel record, @Param("example") MaterielExample example);

    int updateByPrimaryKeySelective(Materiel record);
    
    void deleteMateriels(List<String> ids);
    
    List<Materiel> selectMaterielByOrderSerial(Map<String,String>map);//根据订单流水和发票流水查找订单物料

}