package com.yimi.cn.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yimi.cn.constant.ConstantsEnum;
import com.yimi.cn.model.Admin;
import com.yimi.cn.service.IAdminService;
import com.yimi.cn.util.PagerUtil;
import com.yimi.cn.util.ReslutData;
import com.yimi.cn.util.ResultUtil;

/**
 * Created by on 2017/4/15.
 */

@Controller
public class LoginController {

	@Autowired
	IAdminService adminServiceImpl;

	@RequestMapping("/admin")
	public ModelAndView gotoLoginIndex(String home, HttpServletRequest request) {

		ModelAndView view = new ModelAndView();

		if (request.getSession().getAttribute(ConstantsEnum.USER_SESSION_NAME.getName()) != null) {

			view.addObject("login", true);

		} else {
			view.addObject("login", false);
		}

		view.setViewName("index");

		return view;
	}

	@RequestMapping("/")
	public ModelAndView gotoHomendex(String home, HttpServletRequest request) {

		ModelAndView view = new ModelAndView();
		view.setViewName("home");
		return view;
	}

	@RequestMapping("/login/index")
	@ResponseBody
	public ReslutData adminLogin(Admin admin, HttpServletRequest request) {
		String kaptchaExpected = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY); // 这里是得到相应的验证码
		String kachepa = request.getParameter("code");
		if (!kachepa.equalsIgnoreCase(kaptchaExpected)) { // 验证码不区分大小写
			return ResultUtil.ERRO(ConstantsEnum.SECURITY_CODE_ERRO);
		}
		if (admin == null) {
			return ResultUtil.ERRO(ConstantsEnum.LOGIN_ERRO);
		}

		Admin adminDb = adminServiceImpl.findAdminByNmae(admin);

		if (adminDb == null) {
			return ResultUtil.ERRO(ConstantsEnum.UNAME_ERRO);
		}

		if (!StringUtils.equals(PagerUtil.getMD5(admin.getPassword()), adminDb.getPassword())) {
			return ResultUtil.ERRO(ConstantsEnum.PASSWORD_ERRO);
		}

		request.getSession().setAttribute(ConstantsEnum.USER_SESSION_NAME.getName(), adminDb);

		return ResultUtil.SUCCESS(ConstantsEnum.API_SUCCESS);
	}

	@RequestMapping("/admin/add")
	@ResponseBody
	public ReslutData adminAdd(Admin admin, String pwdrepeat, HttpServletRequest request) {

		if (admin == null) {
			return ResultUtil.ERRO(101, "参数错误");
		}
		if (StringUtils.isEmpty(admin.getUname())) {
			return ResultUtil.ERRO(102, "用户名不能为空");
		}
		if (StringUtils.isEmpty(admin.getPassword())) {
			return ResultUtil.ERRO(103, "密码不能为空");
		}
		if (!StringUtils.equals(admin.getPassword(), pwdrepeat)) {
			return ResultUtil.ERRO(104, "两次密码不一致");
		}

		Admin adminDb = adminServiceImpl.findAdminByNmae(admin);

		if (adminDb != null && admin.getId() == 0) {
			return ResultUtil.ERRO(105, "用户名已存在");
		}

		admin.setPassword(PagerUtil.getMD5(admin.getPassword()));

		if (admin.getId() != 0) {
			int r = adminServiceImpl.updateAdmin(admin);
		} else {

			admin.setCreatTime(new Date());
			int r = adminServiceImpl.addAdmin(admin);
		}
		return ResultUtil.SUCCESS(ConstantsEnum.API_SUCCESS);
	}

	@RequestMapping("/admin/list")
	@ResponseBody
	public ReslutData adminList(Admin admin, HttpServletRequest request) {

		List datas = adminServiceImpl.getAdminList();

		return ResultUtil.SUCCESS(datas);
	}

	@RequestMapping("/admin/passwd/set")
	@ResponseBody
	public ReslutData adminPasswdSet(Admin admin, HttpServletRequest request) {

		int r = adminServiceImpl.updateAdmin(admin);

		return ResultUtil.SUCCESS(ConstantsEnum.API_SUCCESS);
	}

	@RequestMapping("/admin/get")
	@ResponseBody
	public ReslutData adminData(Admin admin, HttpServletRequest request) {

		Admin datas = adminServiceImpl.findAdmin(admin.getId());

		return ResultUtil.SUCCESS(datas);
	}

	@RequestMapping("/admin/delete")
	@ResponseBody
	public ReslutData adminDelete(Admin admin, HttpServletRequest request) {

		int datas = adminServiceImpl.deleteAdmin(admin.getId());

		return ResultUtil.SUCCESS(datas);
	}

	@RequestMapping("/login/out")
	@ResponseBody
	public ReslutData loginOut(Admin admin, HttpServletRequest request) {

		request.getSession().removeAttribute(ConstantsEnum.USER_SESSION_NAME.getName());

		return ResultUtil.SUCCESS(ConstantsEnum.API_SUCCESS);
	}
}
