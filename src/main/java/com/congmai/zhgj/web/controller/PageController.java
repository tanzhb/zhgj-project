package com.congmai.zhgj.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.UserService;

/**
 * 
 * @ClassName PageController
 * @Description 视图控制器,返回jsp视图给前端
 * @author tanzb
 * @Date 2017年7月26日 下午2:43:47
 * @version 1.0.0
 */
@Controller
@RequestMapping("/page")
public class PageController {

	 @Resource
	 private UserService userService;
	 
	 @Resource
	 private ContractService contractService;
	 
    /**
     * 登录页
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * dashboard页
     */
    @RequestMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
    
    /**
     * header页
     */
    @RequestMapping("/header")
    public String header() {
        return "header";
    }
    
    /**
     * sidebar页
     */
    @RequestMapping("/sidebar")
    public String sidebar() {
        return "sidebar";
    }
    
    /**
     * themePanel页
     */
    @RequestMapping("/themePanel")
    public String themePanel() {
        return "themePanel";
    }
    
    /**
     * quickSidebar页
     */
    @RequestMapping("/quickSidebar")
    public String quickSidebar() {
        return "quickSidebar";
    }
    
    /**
     * footer页
     */
    @RequestMapping("/footer")
    public String footer() {
        return "footer";
    }
    
    /**
     * blank页
     */
    @RequestMapping("/blank")
    public String blank() {
        return "blank";
    }
    
    /**
     * 价格目录
     */
    @RequestMapping("/jgml")
    public String jgml() {
        return "jgml";
    }
    
    /**
     * 合同列表
     * @return 列表页面的url
     */
    @RequestMapping("/userContract")
    public String userContract() {
        return "contract/userContract";
    }
    
    /**
     * 采购预测
     */
    @RequestMapping("/purchaseForecast")
    public String purchaseForecast(){
    	return "purchase/purchaseForecast";
    }
    
    
    
    /**
     * 添加合同页面
     * @return 添加合同页面url
     */
    @RequestMapping("/addUserContract")
    public String addUserContract() {
        return "contract/addUserContract";
    }
    
    /**
     * 用户管理
     */
    @RequestMapping("/user")
    public String user() {
        return "user";
    }
    
    /**
     * 新增物料
     */
    @RequestMapping("/addMateriel")
    public String addMateriel(String serialNum,String view) {
        return "materiel/addMateriel";
    }
    
    /**
     * 物料信息
     */
    @RequestMapping("/materiel")
    public String materiel() {
        return "materiel/materiel";
    }
    
    /**
     * 新增销售订单
     */
    @RequestMapping("/addSaleOrder")
    public String addSaleOrder(String serialNum,String view) {
        return "order/addSaleOrder";
    }
    
    /**
     * 销售订单
     */
    @RequestMapping("/saleOrder")
    public String saleOrder() {
        return "order/saleOrder";
    }
    
    
    /**
     * 新增采购订单
     */
    @RequestMapping("/addBuyOrder")
    public String addBuyOrder(String serialNum,String view) {
        return "order/addBuyOrder";
    }
    
    /**
     * 查看采购订单
     */
    @RequestMapping("/viewBuyOrder")
    public String viewBuyOrder(String serialNum) {
        return "order/viewBuyOrder";
    }
    /**
     * 供应商查看订单
     */
    @RequestMapping("/viewSupplyOrder")
    public String viewSupplyOrder(String serialNum) {
        return "order/viewSupplyOrder";
    }
    
    /**
     * 采购订单提交申请
     */
    @RequestMapping("/submitBuyApply")
    public String submitBuyApply(String serialNum,String view) {
        return "order/submitBuyApply";
    }
    
    /**
     * 审批采购订单
     */
    @RequestMapping("/approvalBuyApply")
    public String approvalBuyApply(String serialNum,String view) {
        return "order/approvalBuyApply";
    }
    
    /**
     * 重新编辑采购订单申请
     */
    @RequestMapping("/editBuyApply")
    public String editBuyApply(String serialNum,String view) {
        return "order/editBuyApply";
    }
    
    
    
    /**
     * 采购订单
     */
    @RequestMapping("/buyOrder")
    public String buyOrder() {
        return "order/buyOrder";
    }
    
    /**
     * 供应商的销售订单
     */
    @RequestMapping("/supplyOrder")
    public String supplyOrder() {
        return "order/supplyOrder";
    }
    
    /**
     * 新增客户对账单
     */
    @RequestMapping("/addBuyStatement")
    public String addBuyStatement(String serialNum,String view) {
        return "statement/addBuyStatement";
    }
    
    /**
     * 新增供应商对账单
     */
    @RequestMapping("/addSupplyStatement")
    public String addSupplyStatement(String serialNum,String view) {
        return "statement/addSupplyStatement";
    }
    
    /**
     * 对账单
     */
    @RequestMapping("/statement")
    public String statement() {
        return "statement/statement";
    }
    
    
    /**
     * 添加发货页面
     * @return 添加发货页面url
     */
    @RequestMapping("/addDelivery")
    public String addDelivery() {
        return "delivery/addDelivery";
    }
    
    /**
     * 添加付款页面
     * @return 添加付款页面url
     */
    @RequestMapping("/addPay")
    public String addPay() {
        return "pay/addPay";
    }
    
    
    /**
     * 付款详情页面
     * @return 付款详情页面url
     */
    @RequestMapping("/viewPay")
    public String viewPay() {
        return "pay/viewPay";
    }
    
    /**
     * 应付款申请页面
     * @return 
     */
    @RequestMapping("/applyPay")
    public String applyPay() {
        return "pay/applyPay";
    }
    
    /**
     * 应付款审批页面
     * @return 
     */
    @RequestMapping("/auditPay")
    public String auditPay() {
        return "pay/auditPay";
    }
    
    /**
     * 调整应付款申请页面
     * @return 
     */
    @RequestMapping("/editAuditPay")
    public String editAuditPay() {
        return "pay/editAuditPay";
    }
    
    
    /**
     * 收款详情页面
     * @return 收款详情页面url
     */
    @RequestMapping("/viewGatheringMoney")
    public String viewGatheringMoney() {
        return "gatheringMoney/viewGatheringMoney";
    }
    
    
    /**
     * 添加付款页面
     * @return 添加付款页面url
     */
    @RequestMapping("/paymentRecord")
    public String paymentRecord() {
        return "pay/paymentRecord";
    }
    
    /**
     * 编辑付款页面
     * @return 编辑付款页面url
     */
    @RequestMapping("/editPay")
    public String editPay() {
        return "pay/editPay";
    }
    
    
    /**
     * 编辑收款页面
     * @return 编辑收款页面url
     */
    @RequestMapping("/editGatheringMoney")
    public String editGatheringMoney() {
        return "gatheringMoney/editGatheringMoney";
    }
    
    /**
     * 添加收款页面
     * @return 添加收款页面url
     */
    @RequestMapping("/addGatheringMoney")
    public String addGatheringMoney() {
        return "gatheringMoney/addGatheringMoney";
    }
    
    /**
     * 收款列表页面
     * @return 收款列表页面url
     */
    @RequestMapping("/gatheringMoneyRecord")
    public String gatheringMoneyRecord() {
        return "gatheringMoney/gatheringMoneyRecord";
    }
    
    /**
     * 添加发货页面
     * @return 添加发货页面url
     */
    @RequestMapping("/delivery")
    public String delivery() {
        return "delivery/delivery";
    }
    
    /**
     * 请假
     */
    @RequestMapping("/addVacation")
    public String addVacation() {
        return "vacation/addVacation";
    }
    
    /**
     * 404页
     */
    @RequestMapping("/404")
    public String error404() {
        return "404";
    }

    /**
     * 401页
     */
    @RequestMapping("/401")
    public String error401() {
        return "401";
    }

    /**
     * 500页
     */
    @RequestMapping("/500")
    public String error500() {
        return "500";
    }

}
