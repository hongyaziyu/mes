package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.SaSaleorder;
import cn.ssm.vo.SaleOrder;

public interface SaSaleorderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SaSaleorder record);

    int insertSelective(SaSaleorder record);

    SaSaleorder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SaSaleorder record);

    int updateByPrimaryKeyWithBLOBs(SaSaleorder record);

    int updateByPrimaryKey(SaSaleorder record);
    
    
    //1、查询订单总数量(分页使用)
    int selectSaleOrderCount(@Param("code") String code,@Param("start_date") String start_date,@Param("end_date") String end_date);
    
    //2、查询订单
    List<SaleOrder> selectSaleOrder(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("code") String code,@Param("start_date") String start_date,@Param("end_date") String end_date);
    
    //3、1查看详细信息-基础信息
    SaSaleorder selectOrderdetail(Integer id);
    
    //3、2查看详细信息-具体信息
    List<SaSaleorder> selectSaleOrderdetail(Integer id);
    
    //4、Ajax-周计划-查询是否存在该订单号
    List<SaSaleorder> selectOrderNoIsNull(String orderNo);
    
    //5、Ajax-周计划-根据订单号查询客户信息
    SaSaleorder selectClient(String orderNo);
    
    //6、Ajax-周计划-根据订单号得到图号下拉显示
    List<String> selectClientMaterialNo(String orderNo);
    
    //7、根据订单号和存货id查询得到订单数量
    SaSaleorder selectOrdernum(String orderNo,Integer inventoryid);
    
    //8、根据订单号得到所有成品的存货id
    List<Integer> selectOrderNoToIdInventory(String orderNo);
}
