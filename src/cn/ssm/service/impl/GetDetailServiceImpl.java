package cn.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ssm.mapper.GetDetailMapper;
import cn.ssm.po.GetDetail;
import cn.ssm.service.GetDetailService;
import cn.ssm.util.DataSource;

@Service
@DataSource("dataSource1")
public class GetDetailServiceImpl implements GetDetailService {
	@Autowired
	private GetDetailMapper getDetailMapper;
	
	
	//材料批次号显示
	@Override
	public String selectCailiaopicihao(String clientMaterialNo, String materialNo,String batchNo){		
		List<GetDetail> list=getDetailMapper.selectCailiaopicihao(clientMaterialNo,materialNo,batchNo);
		  String html="<select id='cailiaopicihao' name='materialBatchNo' class='mini-input multiselect multiselect1' multiple='multiple'>";
		
			for(int i=0;i<list.size();i++){
				html+="<option value="+list.get(i).getMaterialBatchNo()+" selected='selected'>"+list.get(i).getMaterialBatchNo()+"</option>";												
			}
		
		    	html=html+"</select>";
				return html;
				
	}

	


}
