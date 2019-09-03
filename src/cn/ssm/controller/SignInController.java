package cn.ssm.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ssm.mapper.GetMaterialMapper;
import cn.ssm.mapper.GetSecMaterialsMapper;
import cn.ssm.mapper.TrackCardMapper;
import cn.ssm.po.Person;
import cn.ssm.service.SignInService;
import cn.ssm.util.MD5;

@Controller
@RequestMapping("/user")
public class SignInController {

	@Autowired
	private SignInService signInService;

	// 车间主任未审批提示
	@Autowired
	private GetMaterialMapper getMaterialMapper;
	@Autowired
	private GetSecMaterialsMapper getSecMaterialsMapper;
	@Autowired
	private TrackCardMapper trackCardMapper;

	@RequestMapping("/signin")
	public String signIn(String loginid, String password, HttpSession session,
			Model model) throws Exception {
		password = new MD5().getPassword(password);

		Map<String, String> map = new HashMap<String, String>();
		map.put("loginid", loginid);
		map.put("password", password);
		List<Person> listPerson = signInService.signIn(map);
		if (listPerson.size() < 1) {
			model.addAttribute("error", "用户名或密码错误");
			return "index";
			// 或者方法2：转发方式（在request域中存值）
			// request.setAttribute("error", "用户名或密码错误");
			// request.getRequestDispatcher("/jsp/index.jsp").forward(request,respnse);

		}
		// 为这次会话设置时长为60分钟（即：在60分钟内session中的信息是有效的）
		session.setMaxInactiveInterval(60 * 60);
		// 将登录人员的信息存入服务端开辟的session空间中
		session.setAttribute("user", listPerson.get(0));

		// 车间主任需求：添加对审批数量的查询
		Integer num1 = getMaterialMapper.selectGetMaterialCount();
		Integer num2 = getSecMaterialsMapper.selectSecGetMaterialCount();
		Integer num3 = trackCardMapper.selectTempriceCount();
		session.setAttribute("num1", num1);
		session.setAttribute("num2", num2);
		session.setAttribute("num3", num3);
		return "homePage";
	}

	// (定时刷新)车间主任需求：添加对审批数量的查询
	@RequestMapping("/refresh")
	public String refresh(HttpServletResponse response, HttpSession session)
			throws Exception {
		// 车间主任需求：添加对审批数量的查询
		Integer num1 = getMaterialMapper.selectGetMaterialCount();
		Integer num2 = getSecMaterialsMapper.selectSecGetMaterialCount();
		Integer num3 = trackCardMapper.selectTempriceCount();
		session.setAttribute("num1", num1);
		session.setAttribute("num2", num2);
		session.setAttribute("num3", num3);

		return "headandfoot";
	}

	
	@RequestMapping("/login")
	public String toSignIn() throws Exception {
		return "index";
	}

	@RequestMapping("/dashboard")
	public String dashboard() throws Exception {
		return "dashboard";
	}

	@RequestMapping("/register")
	public String register(Person person, Model model) throws Exception {

		String password = new MD5().getPassword(person.getPassword());
		person.setPassword(password);
		List<Person> person1 = signInService
				.selectByLoginId(person.getNumber());
		List<Person> person2 = signInService.selectByEmail(person.getEmail());
		if (person1.size() >= 1) {

			return "register";
		}
		if (person2.size() >= 1) {

			return "register";
		}

		signInService.insertPerson(person);
		String e3 = "注册成功,请重新登录！";
		model.addAttribute("e3", e3);
		return "index";

	}

	@RequestMapping("/toreg")
	public String toreg() throws Exception {
		return "register";
	}

	@RequestMapping("/relogin")
	public String relogin(String loginid, String password, HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		password = new MD5().getPassword(password);

		Map<String, String> map = new HashMap<String, String>();
		map.put("loginid", loginid);
		map.put("password", password);

		session.removeAttribute("relogin_error");
		List<Person> person = signInService.signIn(map);
		if (person.size() < 1) {
			model.addAttribute("error", "用户名或密码错误");
			return "relogin";
		}
		session.setMaxInactiveInterval(60 * 60);
		session.setAttribute("user", person.get(0));

		// 重新登录获取登录时的url，如果有参数返回？后的参数param,没有则只返回地址url
		String param = null;
		String To_url = session.getAttribute("To_url").toString();
		if (session.getAttribute("param") != null) {
			param = session.getAttribute("param").toString();
		}
		String url = To_url.substring(4);
		if (param != null) {
			return "redirect:" + url + "?" + param;
		}

		return "redirect:" + url;

	}

	@RequestMapping("/ajax")
	public void ajax(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String loginid = request.getParameter("loginid");
		String email = request.getParameter("email");
		List<Person> person1 = signInService.selectByLoginId(loginid);
		List<Person> person2 = signInService.selectByEmail(email);
		PrintWriter out = response.getWriter();
		if (person1.size() >= 1) {
			out.print("<font style='color:red;font-size:18px';>工号已存在!</font>");

		}
		if (person2.size() >= 1) {
			out.print("<font style='color:red;font-size:18px';>邮箱被占用!</font>");

		}

		// 清除缓存（流）中信息
		out.flush();
		// 关闭流
		out.close();

	}

	// 用户退出
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {

		// session（销毁）失效
		session.invalidate();

		return "index";

	}

	// 修改密码
	@RequestMapping("/editPassword")
	@ResponseBody
	public int editPassword(String oldPassword, String newPassword,
			HttpSession session) throws Exception {
		oldPassword = new MD5().getPassword(oldPassword);
		newPassword = new MD5().getPassword(newPassword);
		Person person = (Person) session.getAttribute("user");
		int flag = 0;
		if (person.getPassword().equals(oldPassword)) {
			person.setPassword(newPassword);
			flag = signInService.updatePassword(person);
		}
		return flag;
	}

	// 重置密码
	@RequestMapping("/resetPassword")
	@ResponseBody
	public int resetPassword(String number, String inputEMAIL,
			String newPassword, HttpSession session) throws Exception {
		newPassword = new MD5().getPassword(newPassword);
		List<Person> list = signInService.selectByLoginId(number);
		int flag = 0;
		if (list.size() > 0) {
			Person person = list.get(0);
			if (person.getEmail().equals(inputEMAIL)) {
				person.setPassword(newPassword);
				flag = signInService.updatePassword(person);
			}
		}
		return flag;
	}
}
