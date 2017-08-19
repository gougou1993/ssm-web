package cn.jxh.ssm.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jxh.ssm.common.constants.SysConstants;
import cn.jxh.ssm.common.utils.Utils;
import cn.jxh.ssm.entity.SeRole;
import cn.jxh.ssm.entity.SeUser;
import cn.jxh.ssm.entity.SessionUser;
import cn.jxh.ssm.service.ISeRoleService;
import cn.jxh.ssm.service.ISeRoleuserService;
import cn.jxh.ssm.service.ISeUserService;
import cn.jxh.ssm.service.ISeqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private ISeqService seqService;

    @Autowired
    private ISeUserService seUserService;

    @Autowired
    private ISeRoleuserService seRoleuserService;

    /**
     * 登陆方法
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws ServletException
     */
    @RequestMapping("/login.do")
    public String loginHandler(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException {
        String action = this.getParameter(request, "action");
        if ("do".equals(action)) {
            String uname = this.getParameter(request, "uname");
            String password = this.getParameter(request, "password");

            if (!"".equals(uname) && !"".equals(password)) {
                // 查询用户基本信息
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("uname", uname);
                param.put("password", Utils.md5(password));
                SeUser seUser = seUserService.get(param);

                if (seUser != null) {

                    if (seUser.getVisible() != 1) {
                        model.put("errMsg", "此用户名处于无效状态！");
                        return "page/login";
                    }

                    param.clear();
                    param.put("usercode", seUser.getUsercode());
                    List<SeRole> roleIds = seRoleuserService.listSeRolesByUsercode(param);
                    if (roleIds == null || roleIds.size() == 0) {
                        model.put("errMsg", "此用户名尚未分配角色！");
                        return "page/login";
                    }

                    // 存放session会话信息
                    SessionUser sessionUser = new SessionUser();

                    sessionUser.setUserid(seUser.getId());
                    sessionUser.setUsercode(seUser.getUsercode());
                    sessionUser.setUname(seUser.getUname());
                    sessionUser.setPname(seUser.getPname());
                    sessionUser.setEmail(seUser.getEmail());
                    sessionUser.setTelcode(seUser.getTelcode());
                    sessionUser.setSeRoleList(roleIds);

                    this.setSessionObject(request, SysConstants.SESSION_USER_KEY, sessionUser);

                    return "redirect:home.do";

                } else {
                    model.put("errMsg", "用户名或密码错误！");
                    return "page/login";
                }
            } else {
                // 用户名或密码为空，返回登录页面并提示用户
                model.put("errMsg", "用户名和密码不能为空！");
                return "page/login";
            }
        }

        return "page/login";
    }


    @RequestMapping("/logout.do")
    public String logoutHandler(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException {
        // 清除session会话
        request.getSession().removeAttribute(SysConstants.SESSION_USER_KEY);
        return "redirect:login.do";
    }


}
