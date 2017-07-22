package cn.jxh.ssm.controller;

import javax.servlet.http.HttpServletRequest;

import cn.jxh.ssm.common.constants.SysConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.jxh.ssm.common.page.PageProperty;
import cn.jxh.ssm.common.utils.Utils;

public abstract class BaseController   {

    protected final Log log = LogFactory.getLog(this.getClass());

    protected String getParameter(HttpServletRequest request, String param) {
        String value = Utils.trim(request.getParameter(param));
        return value;
    }

    protected String[] getParameters(HttpServletRequest request, String param) {
        String[] values = request.getParameterValues(param);
        return values;
    }

    protected void setSessionObject(HttpServletRequest request, String key, Object obj) {
        request.getSession().setAttribute(key, obj);
    }

    protected Object getSessionObject(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }

    protected void setPageInfo(HttpServletRequest req, PageProperty pp) {
        int pageSizeNum = SysConstants.PAGE_SIZE_DEFAULT;
        int pageNum = 1;
        String pageSizeStr = this.getParameter(req, "pagesize");// 获取每页数据条数
        System.out.println(pageSizeStr);
        String pageNumStr = this.getParameter(req, "page");

        if (!"".equals(pageNumStr)) {
            pageNum = Utils.parseInt(pageNumStr, 1); // 将字符串数字转化为int型数字,把pageNo传进去，转换为整型，默认为1
        }
        if (!"".equals(pageSizeStr)) {
            pageSizeNum = Utils.parseInt(pageSizeStr, 1);
        }

        pp.setNpage(pageNum); // 更新页码值
        pp.setNpagesize(pageSizeNum); // 更新页面查询数量值
    }

}
