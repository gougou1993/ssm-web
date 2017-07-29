package cn.jxh.ssm.controller;

import javax.servlet.http.HttpServletRequest;

import cn.jxh.ssm.common.constants.SysConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.jxh.ssm.common.page.PageProperty;
import cn.jxh.ssm.common.utils.Utils;

public abstract class BaseController {

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

    protected void setPageInfo(HttpServletRequest request, PageProperty pp, String[] enableSorts) {
        int pageSizeNum = SysConstants.PAGE_SIZE_DEFAULT;

        if (!Utils.strIsNull(this.getParameter(request, "start"))) {
            pp.setStartRow(Integer.parseInt(this.getParameter(request, "start")));
        } else {
            pp.setStartRow(0);
        }

        if (!Utils.strIsNull(this.getParameter(request, "length"))) {
            pp.setPageSize(Integer.parseInt(this.getParameter(request, "length")));
        } else {
            pp.setPageSize(pageSizeNum);
        }

        if (!Utils.strIsNull(this.getParameter(request, "order[0][column]"))) {
            String order = this.getParameter(request, "order[0][column]");

            //增加排序列判断 防止SQL注入
            for (String enableSort : enableSorts) {
                if (this.getParameter(request, "columns[" + order + "][data]").equals(enableSort.toLowerCase())) {
                    pp.setOrderColumn(this.getParameter(request, "columns[" + order + "][data]"));
                    break;
                }
            }

            if (this.getParameter(request, "order[0][dir]").equals("desc")) {
                pp.setOrderDir("desc");
            } else {
                pp.setOrderDir("asc");
            }

        }

        if (!Utils.strIsNull(this.getParameter(request, "draw"))) {
            pp.setDraw(Integer.parseInt(this.getParameter(request, "draw")));
        }

        //自带搜索框的值
        String searchvalue = this.getParameter(request, "search[value]");

    }

}
