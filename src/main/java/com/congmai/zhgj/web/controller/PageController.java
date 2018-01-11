
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
import com.congmai.zhgj.web.service.OrderService;
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
     * 新增销售框架
     */
    @RequestMapping("/addSaleFrame")
    public String addSaleFrame(String serialNum,String view) {
        return "frame/addSaleFrame";
    }
    
    /**
     * 客户端新增订单
     */
    @RequestMapping("/addCustomerOrder")
    public String addCustomerOrder(String serialNum,String view) {
        return "order/addCustomerOrder";
    }
    /**
     * 客户端新增提货订单
     */
    @RequestMapping("/addCustomerDeliveryOrder")
    public String addCustomerDeliveryOrder(String serialNum,String view) {
    	return "takeDelivery/customerDeliveryOrderAdd";
    }
    /**
     * 客户端查看提货订单
     */
    @RequestMapping("/viewCustomerDeliveryOrder")
    public String viewCustomerDeliveryOrder(String serialNum,String view) {
    	return "takeDelivery/viewCustomerDeliveryoOrder";
    }
    /**
     * 销售订单
     */
    @RequestMapping("/saleOrder")
    public String saleOrder() {
        return "order/saleOrder";
    }
    
    /**
     * 销售框架
     */
    @RequestMapping("/saleFrame")
    public String saleFrame() {
        return "frame/saleFrame";
    }
    
    /**
     * 客户端订单
     */
    @RequestMapping("/customerOrder")
    public String customerOrder() {
        return "order/customerOrder";
    }
    
    /**
     * 客户端框架协议
     */
    @RequestMapping("/customerFrame")
    public String customerFrame() {
        return "frame/customerFrame";
    }
    
    /**
     * 操作日志
     */
    @RequestMapping("/operateLog")
    public String operateLog() {
        return "operateLog/operateLog";
    }
    
    
    /**
     * 新增采购订单
     */
    @RequestMapping("/addBuyOrder")
    public String addBuyOrder(String serialNum,String view,String numCode) {
        return "order/addBuyOrder";
    }
    
    /**
     * 新增采购计划
     */
    @RequestMapping("/addProcurementPlan")
    public String addProcurementPlan(String serialNum,String view,String numCode) {
        return "procurementPlan/addProcurementPlan";
    }
    
    /**
     * 新增采购框架
     */
    @RequestMapping("/addBuyFrame")
    public String addBuyFrame(String serialNum,String view,String numCode) {
        return "frame/addBuyFrame";
    }
    /**
     * 修改平台采购订单
     */
    @RequestMapping("/addSupplyOrder")
    public String addSupplyOrder(String serialNum,String view,String numCode) {
        return "order/addSupplyOrder";
    }
    /**
     * 修改平台采购框架
     */
    @RequestMapping("/addSupplyFrame")
    public String addSupplyFrame(String serialNum,String view,String numCode) {
        return "frame/addSupplyFrame";
    }
    /**
     * 查看采购订单
     */
    @RequestMapping("/viewBuyOrder")
    public String viewBuyOrder(String serialNum) {
        return "order/viewBuyOrder";
    }
    
    /**
     * 查看采购计划
     */
    @RequestMapping("/viewProcurementPlan")
    public String viewProcurementPlan(String serialNum) {
        return "procurementPlan/viewProcurementPlan";
    }
    /**
     * 查看采购框架
     */
    @RequestMapping("/viewBuyFrame")
    public String viewBuyFrame(String serialNum) {
        return "frame/viewBuyFrame";
    }
    /**
     * 查看销售订单
     */
    @RequestMapping("/viewSaleOrder")
    public String viewSaleOrder(String serialNum) {
        return "order/viewSaleOrder";
    }
    /**
     * 查看销售框架
     */
    @RequestMapping("/viewSaleFrame")
    public String viewSaleFrame(String serialNum) {
        return "frame/viewSaleFrame";
    }
    
    /**
     * 客户端查看订单
     */
    @RequestMapping("/viewCustomerOrder")
    public String viewCustomerOrder(String serialNum) {
        return "order/viewCustomerOrder";
    }
    
    /**
     * 客户端查看框架协议
     */
    @RequestMapping("/viewCustomerFrame")
    public String viewCustomerFrame(String serialNum) {
        return "frame/viewCustomerFrame";
    }
    
    /**
     * 供应商查看订单
     */
    @RequestMapping("/viewSupplyOrder")
    public String viewSupplyOrder(String serialNum) {
        return "order/viewSupplyOrder";
    }
    
    /**
     * 供应商查看框架协议
     */
    @RequestMapping("/viewSupplyFrame")
    public String viewSupplyFrame(String serialNum) {
        return "frame/viewSupplyFrame";
    }
    
    /**
     * 采购订单提交申请
     */
    @RequestMapping("/submitBuyApply")
    public String submitBuyApply(String serialNum,String view) {
        return "order/submitBuyApply";
    }
    
    /**
     * 销售订单提交申请
     */
    @RequestMapping("/submitSaleApply")
    public String submitSaleApply(String serialNum,String view) {
        return "order/submitSaleApply";
    }
    
    /**
     * 审批采购订单
     */
    @RequestMapping("/approvalBuyApply")
    public String approvalBuyApply(String serialNum,String view) {
        return "order/approvalBuyApply";
    }
    
    /**
     * 审批销售订单
     */
    @RequestMapping("/approvalSaleApply")
    public String approvalSaleApply(String serialNum,String view) {
        return "order/approvalSaleApply";
    }
    
    /**
     * 重新编辑采购订单申请
     */
    @RequestMapping("/editBuyApply")
    public String editBuyApply(String serialNum,String view) {
        return "order/editBuyApply";
    }
    
    /**
     * 重新编辑销售订单申请
     */
    @RequestMapping("/editSaleApply")
    public String editSaleApply(String serialNum,String view) {
        return "order/editSaleApply";
    }
    
    /**
     * 采购框架提交申请
     */
    @RequestMapping("/submitBuyFrameApply")
    public String submitBuyFrameApply(String serialNum,String view) {
        return "frame/submitBuyFrameApply";
    }
    
    /**
     * 销售框架提交申请
     */
    @RequestMapping("/submitSaleFrameApply")
    public String submitSaleFrameApply(String serialNum,String view) {
        return "frame/submitSaleFrameApply";
    }
    
    /**
     * 审批采购框架
     */
    @RequestMapping("/approvalBuyFrameApply")
    public String approvalBuyFrameApply(String serialNum,String view) {
        return "frame/approvalBuyFrameApply";
    }
    
    /**
     * 审批销售框架
     */
    @RequestMapping("/approvalSaleFrameApply")
    public String approvalSaleFrameApply(String serialNum,String view) {
        return "frame/approvalSaleFrameApply";
    }
    
    /**
     * 重新编辑采购框架申请
     */
    @RequestMapping("/editBuyFrameApply")
    public String editBuyFrameApply(String serialNum,String view) {
        return "frame/editBuyFrameApply";
    }
    
    /**
     * 重新编辑销售框架申请
     */
    @RequestMapping("/editSaleFrameApply")
    public String editSaleFrameApply(String serialNum,String view) {
        return "frame/editSaleFrameApply";
    }
    /**
     * 采购订单
     */
    @RequestMapping("/buyOrder")
    public String buyOrder() {
        return "order/buyOrder";
    }
    
    /**
     * 采购计划
     */
    @RequestMapping("/procurementPlan")
    public String procurementPlan() {
        return "procurementPlan/procurementPlan";
    }
    
    /**
     * 采购框架
     */
    @RequestMapping("/buyFrame")
    public String buyFrame() {
        return "frame/buyFrame";
    }
    
    /**
     * 供应商的销售订单
     */
    @RequestMapping("/supplyOrder")
    public String supplyOrder() {
        return "order/supplyOrder";
    }
    
    /**
     * 供应商的框架协议
     */
    @RequestMapping("/supplyFrame")
    public String supplyFrame() {
        return "frame/supplyFrame";
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
     * 全文检索页面
     */
    @RequestMapping("/solrSearch")
    public String solrSearch() {
        return "solrSearch";
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
    /**
     * 价格提交申请
     */
    @RequestMapping("/submitPriceApply")
    public String submitPriceApply(String serialNum,String priceType) {
        return "priceList/submitPriceApply";
    }
    
    /**
     * 审批采购订单
     */
    @RequestMapping("/approvalPriceApply")
    public String approvalPriceApply(String serialNum,String priceType) {
        return "priceList/approvalPriceApply";
    }
    
    /**
     * 重新编辑价格申请
     */
    @RequestMapping("/editPriceApply")
    public String editPriceApply(String serialNum,String priceType) {
        return "priceList/editPriceApply";
    }
    /**
     * 发票提交申请
     */
    @RequestMapping("/submitInvoiceApply")
    public String submitInvoiceApply(String serialNum,String invoiceType) {
        return "invoice/submitInvoiceApply";
    }
    
    /**
     * 审批发票
     */
    @RequestMapping("/approvalInvoiceApply")
    public String approvalInvoiceApply(String serialNum,String invoiceType) {
        return "invoice/approvalInvoiceApply";
    }
    
    /**
     * 重新编辑发票申请
     */
    @RequestMapping("/editInvoiceApply")
    public String editInvoiceApply(String serialNum,String invoiceType) {
        return "invoice/editInvoiceApply";
    }    
    /**
     * 个人中心
     */
    @RequestMapping("/userInfo")
    public String userInfo(){
    	return "user/userInfo";
    }
    /**
     * 企业信息
     */
    @RequestMapping("/companyInfo")
    public String companyInfo(){
    	return "user/companyInfo";
    }
    /**
     * 账户安全
     */
    @RequestMapping("/accountSecurity")
    public String accountSecurity(){
    	return "user/accountSecurity";
    }
    /**
     * 收款水单详情页面
     * @return 收款水单详情页面url
     */
    @RequestMapping("/viewReceiveMemo")
    public String viewReceiveMemo() {
        return "receivePayMemo/viewReceiveMemo";
    }
    /**
     * 添加/修改收款水单页面
     * @return 添加/修改收款水单页面url
     */
    @RequestMapping("/addReceiveMemo")
    public String addReceiveMemo() {
        return "receivePayMemo/addReceiveMemo";
    }
    /**
     * 收款水单列表页面
     * @return 收款水单列表页面url
     */
    @RequestMapping("/receiveMemo")
    public String receiveMemoRecord() {
        return "receivePayMemo/receiveMemoRecord";
    }
    /**
     * 付款水单详情页面
     * @return 付款水单详情页面url
     */
    @RequestMapping("/viewMemoPay")
    public String viewPayMemo() {
        return "receivePayMemo/viewMemoPay";
    }
    /**
     * 添加/修改付款水单页面
     * @return 添加/修改付款水单页面url
     */
    @RequestMapping("/addPayMemo")
    public String addPayMemo() {
        return "receivePayMemo/addPayMemo";
    }
    /**
     * 付款水单列表页面
     * @return 付款水单列表页面url
     */
    @RequestMapping("/payMemo")
    public String payMemoRecord() {
        return "receivePayMemo/payMemoRecord";
    }
    /**
     * 发货计划提交申请
     */
    @RequestMapping("/submitDeliveryPlanApply")
    public String submitDeliveryPlanApply() {
        return "delivery/submitDeliveryPlanApply";
    }
    
    /**
     * 审批发货计划
     */
    @RequestMapping("/approvalDeliveryPlanApply")
    public String approvalDeliveryPlanApply() {
        return "delivery/approvalDeliveryPlanApply";
    }
    
    /**
     * 重新编辑发货计划申请
     */
    @RequestMapping("/editDeliveryPlanApply")
    public String editDeliveryPlanApply() {
        return "delivery/editDeliveryPlanApply";
    }    
}
