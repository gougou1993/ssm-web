package cn.jxh.ssm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jxh.ssm.common.constants.SysConstants;
import cn.jxh.ssm.entity.SessionUser;

/**
 * 检查用户登录状态的Filter，过滤所有的url请求
 */
public class CheckSessionOutFilter implements Filter {
    protected FilterConfig filterConfig = null;

    /**
     * Take this filter out of service.
     */
    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hsrq = (HttpServletRequest) request;
        HttpServletResponse hsrp = (HttpServletResponse) response;
        String clientType = hsrq.getParameter("clientType");
        if ("WORD".equals(clientType)) {
            chain.doFilter(request, response);
        } else {
            SessionUser person = null;
            String reqPage = hsrq.getServletPath();
            if (!reqPage.trim().equals("/login.do") && !reqPage.trim().equals("/login_out.do")
                    ) {

                person = (SessionUser) hsrq.getSession().getAttribute(SysConstants.SESSION_USER_KEY);
                if (person == null) {
                    hsrp.sendRedirect("login.do");
                    return;
                }
            }
            chain.doFilter(request, response);
        }

    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

}
