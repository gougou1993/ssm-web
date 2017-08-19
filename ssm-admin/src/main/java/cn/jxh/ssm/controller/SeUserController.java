package cn.jxh.ssm.controller;

import cn.jxh.ssm.common.page.PageList;
import cn.jxh.ssm.common.page.PageProperty;
import cn.jxh.ssm.common.utils.DatetimeUtil;
import cn.jxh.ssm.common.utils.Utils;
import cn.jxh.ssm.entity.SeRole;
import cn.jxh.ssm.entity.SeUser;
import cn.jxh.ssm.service.ISeUserService;
import cn.jxh.ssm.service.ISeqService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty;

@Controller
public class SeUserController extends BaseController {

    @Autowired
    private ISeqService seqService;

    @Autowired
    private ISeUserService seUserService;

    @RequestMapping(value = "/se_user_list.do")
    public ModelAndView seUserListPageHandler(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException {
        Map<String, Object> info = new HashMap<>();
        return new ModelAndView("system/user/se_user_list", "info", info);
    }

    @RequestMapping(value = "/se_user_list_data.do", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String seUserListDataHandler(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException {
        PageProperty pp = new PageProperty();
        String[] enableSorts = {"id", "uname", "pname", "birthym", "email", "visible"};

        String pname = request.getParameter("pname");
        if (!Utils.strIsNull(pname)) {
            pp.putParamMap("pname", pname.replace("%", "/%"));
        }

        String uname = request.getParameter("uname");
        if (!Utils.strIsNull(uname)) {
            pp.putParamMap("uname", uname.replace("%", "/%"));
        }

        String visible = request.getParameter("visible");
        if (!Utils.strIsNull(visible)) {
            pp.putParamMap("visible", Integer.parseInt(visible));
        }

        setPageInfo(request, pp, enableSorts);
        PageList<SeUser> seUserList = seUserService.getPageList(pp);
        return JSON.toJSONString(seUserList, WriteNullStringAsEmpty);
    }

    @RequestMapping(value = "/se_user_add.do")
    public ModelAndView seUserAddPageHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Map<String, Object> info = new HashMap<>();
        return new ModelAndView("system/user/se_user_add", "info", info);
    }

    @RequestMapping(value = "/se_user_add_action.do", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String seUserAddActionDoHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Map<String, Object> info = new HashMap<>();
        String action = request.getParameter("action");
        if ("do".equals(action)) {
            try {
                Long id = seqService.getSeqPurNextVal();
                Date nowDate = new Date();

                //验证密码1 密码2 是否一致
                if (!request.getParameter("password1").equals(request.getParameter("password2"))) {
                    info.put("status", 0);
                    info.put("msg", "两次密码不相同！");
                    return JSON.toJSONString(info, WriteNullStringAsEmpty);
                }

                SeUser seUser = new SeUser();
                seUser.setId(id);
                seUser.setUsercode(Utils.getUUID());
                seUser.setPname(request.getParameter("pname"));
                seUser.setPassword(Utils.md5(request.getParameter("password1")));
                seUser.setUname(request.getParameter("uname"));
                seUser.setBirthym(DatetimeUtil.String2Date(request.getParameter("birthym"), "yyyy-MM-dd"));
                seUser.setTelcode(request.getParameter("telcode"));
                seUser.setEmail(request.getParameter("email"));
                seUser.setVisible(Utils.parseInt(request.getParameter("visible"), 0));
                seUser.setEntrytime(nowDate);
                seUser.setUpdatetime(nowDate);
                seUser.setUpdateid(id);

                seUserService.insert(seUser);

                info.put("status", 1);
                info.put("msg", "操作成功");
            } catch (Exception e) {
                info.put("status", 0);
                info.put("msg", "操作失败" + e.getLocalizedMessage());
            }
        }
        return JSON.toJSONString(info, WriteNullStringAsEmpty);
    }


    @RequestMapping(value = "/se_user_edit.do")
    public ModelAndView seUserEditPageHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Map<String, Object> info = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        String usercode = request.getParameter("usercode");
        if (!Utils.strIsNull(usercode)) {
            param.put("usercode", usercode);
            SeUser seUser = seUserService.get(param);
            info.put("seUser", seUser);
        }
        return new ModelAndView("system/user/se_user_edit", "info", info);
    }

    @RequestMapping(value = "/se_user_edit_action.do", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String seUserEditActionDoHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Map<String, Object> info = new HashMap<>();
        String action = request.getParameter("action");
        String usercode = request.getParameter("usercode");

        if ("do".equals(action)) {
            try {
                Long id = seqService.getSeqPurNextVal();
                Date nowDate = new Date();

                //验证密码1 密码2 是否一致
                if (!request.getParameter("password1").equals(request.getParameter("password2"))) {
                    info.put("status", 0);
                    info.put("msg", "两次密码不相同！");
                    return JSON.toJSONString(info, WriteNullStringAsEmpty);
                }

                SeUser seUser = new SeUser();
                seUser.setUsercode(usercode);
                seUser.setPname(request.getParameter("pname"));
                if (!Utils.strIsNull(request.getParameter("password1"))) {
                    seUser.setPassword(Utils.md5(request.getParameter("password1")));
                }
                seUser.setUname(request.getParameter("uname"));
                seUser.setBirthym(DatetimeUtil.String2Date(request.getParameter("birthym"), "yyyy-MM-dd"));
                seUser.setTelcode(request.getParameter("telcode"));
                seUser.setEmail(request.getParameter("email"));
                seUser.setVisible(Utils.parseInt(request.getParameter("visible"), 0));
                seUser.setUpdatetime(nowDate);
                seUser.setUpdateid(id);

                seUserService.update(seUser);

                info.put("status", 1);
                info.put("msg", "操作成功");
            } catch (Exception e) {
                info.put("status", 0);
                info.put("msg", "操作失败" + e.getLocalizedMessage());
            }
        }
        return JSON.toJSONString(info, WriteNullStringAsEmpty);
    }

    @RequestMapping(value = "/se_user_uname_check.do", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String seUserUnameCheckDoHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Map<String, Object> info = new HashMap<>();
        Map<String, Object> param = new HashMap<>();

        String uname = request.getParameter("uname");
        param.put("uname", uname);

        List<SeUser> seUserList = seUserService.list(param);
        if (seUserList.size() > 0) {
            info.put("status", 0);
        } else {
            info.put("status", 1);
        }

        return JSON.toJSONString(info, WriteNullStringAsEmpty);
    }

    @RequestMapping(value = "/se_user_del_action.do", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String seUserDelActionDoHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Map<String, Object> info = new HashMap<>();
        String usercode = request.getParameter("usercode");
        try {
            if (!Utils.strIsNull(usercode)) {
                Map<String, Object> param = new HashMap<>();
                param.put("usercode", usercode);
                seUserService.deleteUser(param);
            }

            info.put("status", 1);
            info.put("msg", "操作成功");
        } catch (Exception e) {
            info.put("status", 0);
            info.put("msg", "操作失败" + e.getLocalizedMessage());
        }
        return JSON.toJSONString(info, WriteNullStringAsEmpty);
    }

}
