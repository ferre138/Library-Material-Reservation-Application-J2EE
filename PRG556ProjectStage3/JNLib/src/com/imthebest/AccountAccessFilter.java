//Nam Nguyen
package com.imthebest;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.on.senecac.prg556.common.StringHelper;
import ca.senecacollege.prg556.limara.bean.AccountOwner;

/**
 * Servlet Filter implementation class AccountAccessFilter
 */
public class AccountAccessFilter implements Filter {
	private String loginPage = "/librarylogin.jspx.jspx";
	
    /**
     * Default constructor. 
     */
    public AccountAccessFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		//HttpServletResponse res = (HttpServletResponse) response;
		//HttpServletRequest req = (HttpServletRequest) request;
		HttpServletRequest request = (HttpServletRequest)req;
		//HttpServletResponse res = (HttpServletResponse) response;
		String uriLogin = request.getContextPath() + getLoginPage();
		if (null == request.getSession().getAttribute("userSession") && !uriLogin.equals(request.getRequestURI())) // null means not logged in, don't redirect /login.jspx
			((HttpServletResponse)res).sendRedirect(uriLogin);
		else
		chain.doFilter(request, res);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		if (StringHelper.isNotNullOrEmpty(config.getInitParameter("login")))
			setLoginPage(StringHelper.stringPrefix(config.getInitParameter("login"), "/"));
	}
	public synchronized String getLoginPage() {
		return loginPage;
	}

	private synchronized void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
	public class UserSession
	{
		private AccountOwner user;
		public UserSession(AccountOwner user)
		{
			setUser(user);
		}
		public AccountOwner getUser()
		{
			return user;
		}
		private void setUser(AccountOwner user)
		{
			this.user = user;
		}

	}
}
