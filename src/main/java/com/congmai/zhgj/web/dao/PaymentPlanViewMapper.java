package com.congmai.zhgj.web.dao;

import com.congmai.zhgj.web.model.PaymentPlanExample;
import com.congmai.zhgj.web.model.PaymentPlanView;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PaymentPlanViewMapper {
    int countByExample(PaymentPlanExample example);

    int deleteByExample(PaymentPlanExample example);

    int deleteByPrimaryKey(String serialNum);

    int insert(PaymentPlanView record);

    int insertSelective(PaymentPlanView record);

    List<PaymentPlanView> selectByExample(PaymentPlanExample example);

    PaymentPlanView selectByPrimaryKey(String serialNum);

    int updateByExampleSelective(@Param("record") PaymentPlanView record, @Param("example") PaymentPlanExample example);

    int updateByExample(@Param("record") PaymentPlanView record, @Param("example") PaymentPlanExample example);

    int updateByPrimaryKeySelective(PaymentPlanView record);

    int updateByPrimaryKey(PaymentPlanView record);
}