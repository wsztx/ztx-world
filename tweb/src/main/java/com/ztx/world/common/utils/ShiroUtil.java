package com.ztx.world.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.PrincipalCollection;

import com.ztx.world.common.shiro.ShiroRealm;

public class ShiroUtil {
    
    /**
     * 指定principalCollection清除权限信息
     */
    public static void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
    	RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		ShiroRealm shiroRealm = (ShiroRealm)rsm.getRealms().iterator().next();
		shiroRealm.clearCachedAuthorizationInfo(principalCollection);
    }
    
    /**
     * 清空当前用户权限信息
     */
    public static void clearCachedAuthorizationInfo(){
    	RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		ShiroRealm shiroRealm = (ShiroRealm)rsm.getRealms().iterator().next();
		shiroRealm.clearCachedAuthorizationInfo();
    }
    
    /**
     * 清空所有权限信息
     */
    public static void clearAllCachedAuthorizationInfo() {
    	RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		ShiroRealm shiroRealm = (ShiroRealm)rsm.getRealms().iterator().next();
        Cache<Object, AuthorizationInfo> cache = shiroRealm.getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
}
