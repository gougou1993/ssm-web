package cn.jxh.ssm.controller;

import cn.jxh.ssm.common.page.PageList;
import cn.jxh.ssm.common.page.PageProperty;
import cn.jxh.ssm.common.utils.Utils;
import cn.jxh.ssm.entity.*;
import cn.jxh.ssm.service.*;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty;

@Controller
public class SeRoleController extends BaseController {

    @Autowired
    private ISeqService seqService;

    @Autowired
    private ISeRoleService seRoleService;

    @Autowired
    private ISeMenuService seMenuService;

    @Autowired
    private ISeRoleuserService seRoleuserService;

    @Autowired
    private ISeRolemenuService seRolemenuService;

    @RequestMapping(value = "/se_role_list.do")
    public ModelAndView seRoleListHandler(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException {
        Map<String, Object> info = new HashMap<String, Object>();
        return new ModelAndView("system/role/se_role_list", "info", info);
    }

    @RequestMapping(value = "/se_role_data_list.do", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String seRoleDataListHandler(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException {
        PageProperty pp = new PageProperty();
        String[] enableSorts = {"id", "roledesc", "content", "visible"};

        String roledesc = request.getParameter("roledesc");
        if (!Utils.strIsNull(roledesc)) {
            pp.putParamMap("roledesc", roledesc.replace("%", "/%"));
        }

        String visible = request.getParameter("visible");
        if (!Utils.strIsNull(visible)) {
            pp.putParamMap("visible", Integer.parseInt(visible));
        }

        setPageInfo(request, pp, enableSorts);
        PageList<SeRole> seRoleList = seRoleService.getPageList(pp);
        return JSON.toJSONString(seRoleList, WriteNullStringAsEmpty);
    }


    @RequestMapping(value = "/se_role_add.do")
    public ModelAndView seRoleAddPageHandler(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException {
        Map<String, Object> info = new HashMap<>();
        return new ModelAndView("system/role/se_role_add", "info", info);
    }


    @RequestMapping(value = "/se_role_add_action.do", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String seRoleAddActionHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Map<String, Object> info = new HashMap<>();
        String action = request.getParameter("action");
        if ("do".equals(action)) {
            try {
                Long id = seqService.getSeqPurNextVal();
                Date nowDate = new Date();
                String rolecode = Utils.getUUID();
                String roledesc = request.getParameter("roledesc");
                String content = request.getParameter("content");
                String visible = request.getParameter("visible");

                SeRole seRole = new SeRole();

                seRole.setId(id);
                seRole.setRolecode(rolecode);
                seRole.setRoledesc(roledesc);
                seRole.setContent(content);
                if (!Utils.strIsNull(visible)) {
                    seRole.setVisible(Utils.parseInt(visible, 0));
                }
                seRole.setUpdateid(id);
                seRole.setUpdatetime(nowDate);
                seRole.setEntrytime(nowDate);
                seRoleService.insert(seRole);

                info.put("status", 1);
                info.put("msg", "操作成功");
            } catch (Exception e) {
                info.put("status", 0);
                info.put("msg", "操作失败" + e.getLocalizedMessage());
            }
        }
        return JSON.toJSONString(info, WriteNullStringAsEmpty);
    }

    @RequestMapping(value = "/se_role_edit.do")
    public ModelAndView seRoleEditPageHandler(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException {
        Map<String, Object> info = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        String rolecode = request.getParameter("rolecode");
        if (!Utils.strIsNull(rolecode)) {
            param.put("rolecode", rolecode);
            SeRole seRole = seRoleService.get(param);
            info.put("seRole", seRole);
        }
        return new ModelAndView("system/role/se_role_edit", "info", info);
    }


    @RequestMapping(value = "/se_role_edit_action.do", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String seRoleEditActionDoHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Map<String, Object> info = new HashMap<>();
        String action = request.getParameter("action");
        if ("do".equals(action)) {
            try {
                Long id = seqService.getSeqPurNextVal();
                Date nowDate = new Date();

                String rolecode = request.getParameter("rolecode");
                String roledesc = request.getParameter("roledesc");
                String content = request.getParameter("content");
                String visible = request.getParameter("visible");

                SeRole seRole = new SeRole();
                //赋值参数
                if (!Utils.strIsNull(rolecode)) {
                    seRole.setRolecode(rolecode);
                }
                seRole.setRoledesc(roledesc);
                seRole.setContent(content);
                if (!Utils.strIsNull(visible)) {
                    seRole.setVisible(Utils.parseInt(visible, 0));
                }
                seRole.setUpdateid(id);
                seRole.setUpdatetime(nowDate);

                seRoleService.update(seRole);

                info.put("status", 1);
                info.put("msg", "操作成功");
            } catch (Exception e) {
                info.put("status", 0);
                info.put("msg", "操作失败" + e.getLocalizedMessage());
            }
        }
        return JSON.toJSONString(info, WriteNullStringAsEmpty);
    }

    @RequestMapping(value = "/se_role_del_action.do", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String seRoleDelActionDoHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Map<String, Object> info = new HashMap<>();
        String rolecode = request.getParameter("rolecode");
        try {
            if (!Utils.strIsNull(rolecode)) {
                Map<String, Object> param = new HashMap<>();
                param.put("rolecode", rolecode);
                seRoleService.deleteRole(param);
            }

            info.put("status", 1);
            info.put("msg", "操作成功");
        } catch (Exception e) {
            info.put("status", 0);
            info.put("msg", "操作失败" + e.getLocalizedMessage());
        }
        return JSON.toJSONString(info, WriteNullStringAsEmpty);
    }


    @RequestMapping(value = "/se_role_user.do")
    public ModelAndView seRoleUserPageHander(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> info = new HashMap<>();
        Map<String, Object> param = new HashMap<>();

        String rolecode = request.getParameter("rolecode");
        param.put("rolecode", rolecode);
        info.put("rolecode", rolecode);

        if (!Utils.strIsNull(rolecode)) {
            List<SeUser> seUserList = seRoleuserService.listSeUserByRolecode(param);
            info.put("seUserList", seUserList);
        }
        return new ModelAndView("system/role/se_role_user", "info", info);
    }

    @RequestMapping(value = "/se_role_user_search.do", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String seRoleUserSearchHander(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> info = new HashMap<>();
        Map<String, Object> param = new HashMap<>();

        String name = request.getParameter("name");
        param.put("name", name);

        if (!Utils.strIsNull(name)) {
            List<SeUser> seUserList = seRoleuserService.listSeUserByName(param);
            info.put("seUserList", seUserList);
        }
        return JSON.toJSONString(info, WriteNullStringAsEmpty);
    }

    @RequestMapping(value = "/se_role_user_action.do", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String seRoleUserActionHander(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Map<String, Object> info = new HashMap<>();
        Map<String, Object> param = new HashMap<>();

        String action = request.getParameter("action");
        String rolecode = request.getParameter("rolecode");
        String userList = request.getParameter("vlaues");

        if ("do".equals(action)) {
            try {
                if (!Utils.strIsNull(rolecode)) {
                    param.put("userList", userList);
                    param.put("rolecode", rolecode);
                    seRoleuserService.relationRoleUser(param);
                }
                info.put("status", 1);
                info.put("msg", "操作成功");
            } catch (Exception e) {
                info.put("status", 0);
                info.put("msg", "操作失败" + e.getLocalizedMessage());
            }
        }

        return JSON.toJSONString(info, WriteNullStringAsEmpty);
    }

    @RequestMapping(value = "/se_role_menu.do")
    public ModelAndView seRoleMenuPageHander(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> info = new HashMap<>();

        String rolecode = request.getParameter("rolecode");
        info.put("rolecode", rolecode);

        return new ModelAndView("system/role/se_role_menu", "info", info);
    }

    @RequestMapping(value = "/se_role_menu_data.do", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String seRoleMenuDataHander(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        //返回值
        List<Map<String, Object>> seMenuList = new ArrayList<>();
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("visible", 1);
            List<SeMenu> seMenuAllList = seMenuService.list(param);

            param.clear();
            String rolecode = request.getParameter("rolecode");
            param.put("rolecode", rolecode);
            List<SeRolemenu> seRolemenuList = seRolemenuService.list(param);

            for (SeMenu seMenu : seMenuAllList) {
                Map<String, Object> seMenuTemp = new HashMap<String, Object>();
                for (SeRolemenu seRolemenu : seRolemenuList) {
                    String seRoleMenuCode = seMenu.getMenucode();
                    if (seRolemenu.getMenucode().equals(seRoleMenuCode)) {
                        seMenuTemp.put("checked", true);
                    }
                }
                seMenuTemp.put("id", seMenu.getId());
                seMenuTemp.put("menucode", seMenu.getMenucode());
                seMenuTemp.put("parentcode", seMenu.getParentcode());
                seMenuTemp.put("captionname", seMenu.getCaptionname());

                seMenuList.add(seMenuTemp);
            }

        } catch (Exception e) {
            seMenuList.get(0).put("msg", "操作失败" + e.getLocalizedMessage());
        }
        return JSON.toJSONString(seMenuList, WriteNullStringAsEmpty);

    }

    @RequestMapping(value = "/se_role_menu_action.do", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String seRoleMenuActionHander(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Map<String, Object> info = new HashMap<>();
        Map<String, Object> param = new HashMap<>();

        String action = request.getParameter("action");
        String rolecode = request.getParameter("rolecode");
        String menuIds = request.getParameter("menuIds");

        if ("do".equals(action)) {
            try {
                if (!Utils.strIsNull(rolecode)) {
                    param.put("menuIds", menuIds);
                    param.put("rolecode", rolecode);
                    seRolemenuService.relationRoleMenu(param);
                }
                info.put("status", 1);
                info.put("msg", "操作成功");
            } catch (Exception e) {
                info.put("status", 0);
                info.put("msg", "操作失败" + e.getLocalizedMessage());
            }
        }

        return JSON.toJSONString(info, WriteNullStringAsEmpty);
    }

}
