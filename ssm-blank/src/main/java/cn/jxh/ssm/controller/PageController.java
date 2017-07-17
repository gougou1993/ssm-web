package cn.jxh.ssm.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jxh.ssm.service.ISeqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PageController extends BaseController {

    @Autowired
    private ISeqService seqService;

    @RequestMapping("/test.do")
    public ModelAndView homeHandler(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException {
        Map<String, Object> info = new HashMap<>();

        info.put("seq",seqService.getSeqPurNextVal());

        return new ModelAndView("test/home", "info", info);
    }


}
