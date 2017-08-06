package cn.jxh.ssm.controller;

import cn.jxh.ssm.common.page.PageList;
import cn.jxh.ssm.common.page.PageProperty;
import cn.jxh.ssm.common.utils.Utils;
import cn.jxh.ssm.entity.SeRole;
import cn.jxh.ssm.service.ISeRoleService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty;

@Controller
public class SeRoleController extends BaseController {

    @Autowired
    private ISeRoleService seRoleService;

    @RequestMapping(value = "/se_role_list.do")
    public ModelAndView seRoleListHandler(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException {
        Map<String, Object> info = new HashMap<String, Object>();
        return new ModelAndView("system/role/se_role_list", "info", info);
    }

    @RequestMapping(value = "/se_role_data_list.do", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
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
        System.out.println(JSON.toJSONString(seRoleList, WriteNullStringAsEmpty));
        return JSON.toJSONString(seRoleList, WriteNullStringAsEmpty);
    }

}
