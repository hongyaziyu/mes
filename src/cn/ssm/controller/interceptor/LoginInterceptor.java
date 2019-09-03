package cn.ssm.controller.interceptor;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import cn.ssm.mapper.PermissionMapper;
import cn.ssm.mapper.UrlMapper;
import cn.ssm.po.Person;
import cn.ssm.util.DynamicDataSourceHolder;

public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	private UrlMapper urlMapper;
	@Autowired
	private PermissionMapper permissionMapper;
	//在执行handler之前来执行的
	//用于用户认证校验、用户权限校验
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String  url = request.getRequestURI();
		//获取查询的字符串比如客户端发送http://localhost/test.do?a=b&c=d&e=f，通过request.getQueryString()得到的是：a=b&c=d&e=f
		String  param=request.getQueryString();
		//判断用户身份在session中是否存在
		HttpSession session = request.getSession();
		session.setAttribute("To_url", url);
		if(param!=null){
			session.setAttribute("param", param);
		}
		Person person =  (Person) session.getAttribute("user");
		//如果用户身份不在session中存在则拦截
		if(person==null){
			session.setAttribute("relogin_error", "请先登录！");
			request.getRequestDispatcher("/WEB-INF/jsp/relogin.jsp").forward(request, response);
			return false;
		}
		
		//转换到dataSource1数据库
		DynamicDataSourceHolder.setDataSource("dataSource1");
		
		//权限管理
		//1.获取公开访问路径的url即“员工”对应的url
		List<String> publicUrls=urlMapper.selectUrlByParam("员工");
		
		//转换到dataSource1数据库
		DynamicDataSourceHolder.setDataSource("dataSource1");
		
		//2.获取权限范围的路径的url即“permission表中person”对应的url
		List<String> privateUrls=permissionMapper.selectUrlByPerson(person);
		for(String publicUrl:publicUrls){
			//访问路径url是否在公用路径中，在则给true-放行
			if(url.indexOf(publicUrl)>=0){
				//true-放行
				return true;
			}
		}
		for(String privateUrl:privateUrls){
			//访问路径url是否在私用路径中，在则给true-放行
			if(url.indexOf(privateUrl)>=0){
				return true;
			}			
			
			/*if(privateUrl.indexOf(url)>=0){
				return true;
			}*/	
		}
		//不然全部跳转到无权访问的页面，给false-不放行
		request.getRequestDispatcher("/WEB-INF/jsp/wuquanfangwen.jsp").forward(request, response);
	
		//如果返回false表示拦截不继续执行handler，如果返回true表示放行
		return false;
		
		
	}
	//在执行handler返回modelAndView之前来执行
	//如果需要向页面提供一些公用 的数据或配置一些视图信息，使用此方法实现 从modelAndView入手
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("HandlerInterceptor1...postHandle");
		
	}
	//执行handler之后执行此方法
	//作系统 统一异常处理，进行方法执行性能监控，在preHandle中设置一个时间点，在afterCompletion设置一个时间，两个时间点的差就是执行时长
	//实现 系统 统一日志记录
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("HandlerInterceptor1...afterCompletion");
	}

}
