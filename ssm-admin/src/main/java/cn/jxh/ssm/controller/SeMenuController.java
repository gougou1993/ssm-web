package cn.jxh.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SeMenuController extends BaseController {

    @RequestMapping(value = "/se_menu_list.do")
    public ModelAndView seMenuListHandler(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException {
        Map<String, Object> info = new HashMap<String, Object>();
        return new ModelAndView("system/menu/se_menu_main", "info", info);
    }

}
