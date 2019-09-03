package cn.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.mapper.MoldRecordMapper;
import cn.ssm.mapper.TrackCardMapper;
import cn.ssm.mapper.WorkCardMapper;
import cn.ssm.po.MoldRecord;
import cn.ssm.po.TrackCard;
import cn.ssm.service.MoldRecordService;
import cn.ssm.util.DataSource;

@Service
@DataSource("dataSource1")
public class MoldRecordServiceImpl implements MoldRecordService {

	@Autowired
	private MoldRecordMapper moldRecordMapper;
	@Autowired
	private TrackCardMapper trackCardMapper;
	@Autowired
	private WorkCardMapper  workCardMapper;
	
	//查询（根据图号，物料号，模具名称）
	//模具出入库记录分页查询
	@Override
	public List<MoldRecord> selectByPrimary(int startPos, int pageSize, String materialNo,
			String batchNo,String moldNo) {
		
	
		List<MoldRecord> listMoldRecord=moldRecordMapper.selectByPrimary(startPos, pageSize,materialNo,batchNo,moldNo);
			for(int i = 0;i<listMoldRecord.size();i++){
				batchNo=listMoldRecord.get(i).getBatchNo();
				moldNo=listMoldRecord.get(i).getMoldNo();
				TrackCard	trackCard = trackCardMapper.selectByBatchNo(batchNo);				
			    String time = workCardMapper.SelectBycardId1( trackCard.getCardId(),moldNo);
				listMoldRecord.get(i).setPlanNo(trackCard.getPlanNo());
				listMoldRecord.get(i).setClientMaterialNo(trackCard.getClientMaterialNo());
				listMoldRecord.get(i).setUseTimes(time);
				
			}
		
				return listMoldRecord;
		
	}

}
