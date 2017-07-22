package cn.jxh.ssm.controller;

import cn.jxh.ssm.common.constants.SysConstants;
import cn.jxh.ssm.common.utils.MenuTree;
import cn.jxh.ssm.entity.SeMenu;
import cn.jxh.ssm.entity.SeRole;
import cn.jxh.ssm.entity.SessionUser;
import cn.jxh.ssm.service.ISeRolemenuService;
import cn.jxh.ssm.service.ISeRoleuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PageController extends BaseController {

    @Autowired
    private ISeRolemenuService seRolemenuService;

    @RequestMapping("/home.do")
    public String homeHandler(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException {

        SessionUser sessionUser = (SessionUser) this.getSessionObject(request, SysConstants.SESSION_USER_KEY);
        model.put("sessionUser", sessionUser);
        Map<String, Object> param = new HashMap<String, Object>();

        List<SeRole> seRoleList = sessionUser.getSeRoleList();

        if (seRoleList != null && seRoleList.size() > 0) {
            param.put("seRoleList", seRoleList);
            List<SeMenu> listUserMenu = seRolemenuService.listSeMenuByUsercode(param);

            if (listUserMenu != null && listUserMenu.size() > 0) {
                // 拼接菜单
                SeMenu rootSeMenu = listUserMenu.remove(0);
                MenuTree tree = new MenuTree(rootSeMenu,listUserMenu);
                model.put("listUserMenu", tree.buildTree());
                model.put("url", listUserMenu.get(0));
            }
        }
        return "page/home";
    }

}
