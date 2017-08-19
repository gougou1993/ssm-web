package cn.jxh.ssm.controller;

import cn.jxh.ssm.common.page.PageList;
import cn.jxh.ssm.common.page.PageProperty;
import cn.jxh.ssm.common.utils.Utils;
import cn.jxh.ssm.entity.SeMenu;
import cn.jxh.ssm.service.ISeMenuService;
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
public class SeMenuController extends BaseController {


    @Autowired
    private ISeqService seqService;

    @Autowired
    private ISeMenuService seMenuService;

    @RequestMapping(value = "/se_menu_list.do")
    public ModelAndView seMenuListPageHandler(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException {
        Map<String, Object> info = new HashMap<String, Object>();
        return new ModelAndView("system/menu/se_menu_main", "info", info);
    }

    @RequestMapping(value = "/se_menu_tree.do", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String seMenuListTreeHandler(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException {
        Map<String, Object> param = new HashMap<>();
        param.put("visible", 1);
        List<SeMenu> seMenuList = seMenuService.list(param);
        return JSON.toJSONString(seMenuList, WriteNullStringAsEmpty);
    }

    @RequestMapping(value = "/se_menu_list_data.do", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String seMenuListDataHandler(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException {
        PageProperty pp = new PageProperty();
        String[] enableSorts = {"id", "captionname", "menucode", "visible", "sourceurl"};

        String parentcode = request.getParameter("parentcode");
        if (!Utils.strIsNull(parentcode)) {
            pp.putParamMap("parentcode", parentcode.replace("%", "/%"));
        }

        setPageInfo(request, pp, enableSorts);
        PageList<SeMenu> seMenuList = seMenuService.getPageList(pp);
        return JSON.toJSONString(seMenuList, WriteNullStringAsEmpty);
    }

    @RequestMapping(value = "/se_menu_add.do")
    public ModelAndView seMenuAddPageHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Map<String, Object> info = new HashMap<>();
        info.put("parentcode", request.getParameter("parentcode"));
        return new ModelAndView("system/menu/se_menu_add", "info", info);
    }

    @RequestMapping(value = "/se_menu_add_action.do", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String seMenuAddActionDoHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Map<String, Object> info = new HashMap<>();
        String action = request.getParameter("action");
        if ("do".equals(action)) {
            try {
                Long id = seqService.getSeqPurNextVal();
                Date nowDate = new Date();

                SeMenu seMenu = new SeMenu();
                seMenu.setId(id);
                seMenu.setMenucode(Utils.getUUID());
                seMenu.setParentcode(request.getParameter("parentcode"));
                seMenu.setCaptionname(request.getParameter("captionname"));
                seMenu.setMenuorder(Utils.parseLong(request.getParameter("menuorder"), null));
                seMenu.setSourceurl(request.getParameter("sourceurl"));
                seMenu.setClassname(request.getParameter("classname"));
                seMenu.setVisible(Utils.parseInt(request.getParameter("visible"), 0));
                seMenu.setEntrytime(nowDate);
                seMenu.setUpdatetime(nowDate);
                seMenu.setUpdateid(id);

                seMenuService.insert(seMenu);

                info.put("status", 1);
                info.put("msg", "操作成功");
            } catch (Exception e) {
                info.put("status", 0);
                info.put("msg", "操作失败" + e.getLocalizedMessage());
            }
        }
        return JSON.toJSONString(info, WriteNullStringAsEmpty);
    }

    @RequestMapping(value = "/se_menu_edit.do")
    public ModelAndView seMenuEditPageHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Map<String, Object> info = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        String menucode = request.getParameter("menucode");
        if (!Utils.strIsNull(menucode)) {
            param.put("menucode", menucode);
            SeMenu seMenu = seMenuService.get(param);
            info.put("seMenu", seMenu);
        }
        return new ModelAndView("system/menu/se_menu_edit", "info", info);
    }

    @RequestMapping(value = "/se_menu_edit_action.do", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String seMenuEditActionDoHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Map<String, Object> info = new HashMap<>();
        String action = request.getParameter("action");

        if ("do".equals(action)) {
            try {
                Long id = seqService.getSeqPurNextVal();
                Date nowDate = new Date();

                SeMenu seMenu = new SeMenu();

                seMenu.setMenucode( request.getParameter("menucode"));
                seMenu.setParentcode(request.getParameter("parentcode"));
                seMenu.setCaptionname(request.getParameter("captionname"));
                seMenu.setMenuorder(Utils.parseLong(request.getParameter("menuorder"), null));
                seMenu.setSourceurl(request.getParameter("sourceurl"));
                seMenu.setClassname(request.getParameter("classname"));
                seMenu.setVisible(Utils.parseInt(request.getParameter("visible"), 0));
                seMenu.setUpdatetime(nowDate);
                seMenu.setUpdateid(id);

                seMenuService.update(seMenu);

                info.put("status", 1);
                info.put("msg", "操作成功");
            } catch (Exception e) {
                info.put("status", 0);
                info.put("msg", "操作失败" + e.getLocalizedMessage());
            }
        }
        return JSON.toJSONString(info, WriteNullStringAsEmpty);
    }

    @RequestMapping(value = "/se_menu_del_action.do", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public String seMenuDelActionDoHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Map<String, Object> info = new HashMap<>();
        String menucode = request.getParameter("menucode");
        try {
            if (!Utils.strIsNull(menucode)) {
                Map<String, Object> param = new HashMap<>();
                param.put("parentcode", menucode);
                List<SeMenu> seMenuList = seMenuService.list(param);
                if(seMenuList.size()==0){
                    param.clear();
                    param.put("menucode", menucode);
                    seMenuService.deleteMenu(param);
                    info.put("status", 1);
                    info.put("msg", "操作成功");
                }else{
                    info.put("status", 0);
                    info.put("msg", "存在子节点，无法删除");
                }
            }

        } catch (Exception e) {
            info.put("status", 0);
            info.put("msg", "操作失败" + e.getLocalizedMessage());
        }
        return JSON.toJSONString(info, WriteNullStringAsEmpty);
    }

}
