package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.GetDetail;

public interface GetDetailMapper {
    int deleteByPrimaryKey(Integer detailId);

    int insert(GetDetail record);

    int insertSelective(GetDetail record);

    GetDetail selectByPrimaryKey(Integer detailId);

    int updateByPrimaryKeySelective(GetDetail record);

    //1.根据getMaterialId查询功能
    List<GetDetail> selectByKey(Integer getMaterialId);
    
    //2.查询材料批次号
    List<GetDetail> selectCailiaopicihao(@Param("clientMaterialNo")String clientMaterialNo, @Param("materialNo")String materialNo,@Param("batchNo")String batchNo);

    //3.更新该批次号下的申请领料表(副表-getdetail)
    int updateByGetDetail(String material_num,Integer getMaterialId);
}