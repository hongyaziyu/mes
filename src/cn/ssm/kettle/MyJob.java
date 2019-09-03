package cn.ssm.kettle;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

/**
 * 自定义作业类
 * 
 * @author lihailin 
 *
 */

public class MyJob {
	
	
	public static String getXmlPath()
	{
		//file:/D:/JavaWeb/.metadata/.me_tcat/webapps/TestBeanUtils/WEB-INF/classes/ 
		String path=Thread.currentThread().getContextClassLoader().getResource("").toString();
		path=path.replace('/', '\\'); // 将/换成\
		path=path.replace("file:", ""); //去掉file:
		path=path.replace("classes\\", ""); //去掉class\
		path=path.substring(1); //去掉第一个\,如 \D:\JavaWeb...
		path+="kettle/红桥数据库同步.ktr";
	//	System.out.println(path);
		return path;
	}


	public void runTrans() {
		
		System.out.println("自定义的作业类执行了：" + 
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));			
		  
		try {
				KettleEnvironment.init();   
				TransMeta transMeta = new TransMeta(getXmlPath());   
				Trans trans = new Trans(transMeta);   
				trans.prepareExecution(null);   
				trans.startThreads();   
				trans.waitUntilFinished();   
				if (trans.getErrors()!=0) {   
				 System.out.println("Error");   
				}
				} catch (KettleXMLException e) {
				
				e.printStackTrace();
				} catch (KettleException e) {
				
				e.printStackTrace();
				} 
	}
}
