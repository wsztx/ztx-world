package com.ztx.world.common.shiro;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ztx.world.common.config.ResponseData;
import com.ztx.world.common.constants.ResultCode;
import com.ztx.world.common.utils.ResponseUtil;

public class KickoutControlFilter extends AccessControlFilter {

	private static Logger log = LoggerFactory.getLogger(KickoutControlFilter.class);
	
    /**
     * 踢出状态，true标示踢出
     */
    final static String KICKOUT_STATUS = KickoutControlFilter.class.getCanonicalName() 
    		+ "_kickout_status"; 
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) 
			throws Exception {
        HttpServletRequest httpRequest = ((HttpServletRequest)request);
        HttpServletResponse httpResponse = ((HttpServletResponse)response);
        String requestType = httpRequest.getHeader("X-Requested-With");
        String url = httpRequest.getRequestURI();
        Subject subject = getSubject(request, response);
        // 如果没有登录就直接return true
        if(!subject.isAuthenticated() && !subject.isRemembered()){
            return true;
        }
        Session session = subject.getSession();
        Serializable sessionId = session.getId();
        // 判断是否已经踢出;如果是Ajax访问,那么给予json返回值提示;如果是普通请求,直接跳转到登录页
        Boolean marker = (Boolean)session.getAttribute(KICKOUT_STATUS);
        if (null != marker && marker) {
        	ResponseData data = new ResponseData();
            // 判断是不是Ajax请求
            if ("XMLHttpRequest".equals(requestType)) {
                data.setCode(ResultCode.SYS_OPERATION_FAILED);
                data.setMessage("您已经在其他地方登录,请重新登录!");
                ResponseUtil.writeJson(httpResponse, data);
            }
            return false;
        }
        
        //获取用户账号
        String userName = (String)subject.getPrincipal();
        
//        //从缓存获取用户Session信息 <userName,SessionId>
//        String jedisSessionId = JedisUtils.get(userName);
//        
//        //如果已经包含当前Session，并且是同一个用户，跳过。
//        if(null!=jedisSessionId && jedisSessionId.equals((String)sessionId)){
//            //更新存储到缓存1个小时（这个时间最好和session的有效期一致或者大于session的有效期）
//            JedisUtils.setex(userName, 3600, (String)sessionId);
//            return true;
//        }
//        //如果用户相同，Session不相同，那么就要处理了
//        /**
//         * 如果用户Id相同,Session不相同
//         * 1.获取到原来的session，并且标记为踢出。
//         * 2.继续走
//         */
//        if(null!=jedisSessionId && !jedisSessionId.equals((String)sessionId)){
//            //标记session已经踢出
//            session.setAttribute(KICKOUT_STATUS, Boolean.TRUE);
//            //存储到缓存1个小时（这个时间最好和session的有效期一致或者大于session的有效期）
//            JedisUtils.setex(userName, 3600, (String)sessionId);
//            return true;
//        }
//        
//        if(null==jedisSessionId){
//            //存储到缓存1个小时（这个时间最好和session的有效期一致或者大于session的有效期）
//            JedisUtils.setex(userName, 3600, (String)sessionId);
//        }
        return true;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) 
			throws Exception {
        // 退出,正常清除Session
        Subject subject = getSubject(request, response);
        subject.logout();
        WebUtils.getSavedRequest(request);
        // 重定向
        WebUtils.issueRedirect(request, response, "/tologin");
        return false;
	}

}
