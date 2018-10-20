package com.ztx.world.common.shiro;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.alibaba.druid.util.StringUtils;
import com.ztx.world.base.entity.Permission;
import com.ztx.world.base.entity.Role;
import com.ztx.world.base.entity.User;
import com.ztx.world.base.entity.UserExample;
import com.ztx.world.base.mapper.DepartmentMapper;
import com.ztx.world.base.mapper.OrganizationMapper;
import com.ztx.world.base.mapper.UserMapper;
import com.ztx.world.base.service.PermissionService;
import com.ztx.world.base.service.RoleService;
import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.constants.BaseConstants;

public class ShiroReaml extends AuthorizingRealm {
	
	private static Logger log = LoggerFactory.getLogger(ShiroReaml.class);
	
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private DepartmentMapper departmentMapper;
    
    @Autowired
    private OrganizationMapper organizationMapper;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private PermissionService permissionService;
    
	public ShiroReaml() {
		super();
	}
	
	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) 
			throws AuthenticationException {
		
		ShiroToken token = (ShiroToken) authcToken;
		UserExample example = new UserExample();
		example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
			.andUserCodeEqualTo(token.getUsername())
			.andPasswordEqualTo(token.getPswd());
		List<User> userList = userMapper.selectByExample(example);
		User user = null;
		if(CollectionUtils.isEmpty(userList)){
			throw new AccountException("帐号或密码不正确！");
		}else if(userList.get(0).getUserStatus() != BaseConstants.UserStatusType.USER_NORMAL){
			throw new DisabledAccountException("帐号被禁止登录！");
		}else{
			user = userList.get(0);
			user.setLastLoginTime(new Date());
			userMapper.updateByPrimaryKeySelective(user);
		}
		
		CustomSession customSession = new CustomSession();
		customSession.setUser(user);
		customSession.setOrg(organizationMapper.selectByPrimaryKey(user.getOrgId()));
		customSession.setDept(departmentMapper.selectByPrimaryKey(user.getDeptId()));
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(customSession, user.getPassword(), getName());
		return info;
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        CustomSession customSession = (CustomSession)principals.getPrimaryPrincipal();
        log.info("shiro权限检查,usercode:" + customSession.getUser().getUserCode());
        
		User user = customSession.getUser();
        List<Role> roleList = roleService.findRoleByUserId(user.getId());
        if(!CollectionUtils.isEmpty(roleList)){
        	for(Role role : roleList){
        		if(!StringUtils.isEmpty(role.getRoleCode())){
        			authorizationInfo.addRole(role.getRoleCode());
        			//log.info("角色:" + role.getRoleCode());
        		}
        		
                List<Permission> permissionList = permissionService
                		.findPermissionByRoleId(role.getId());
                if(!CollectionUtils.isEmpty(permissionList)){
                	for (Permission permission : permissionList){
                        if (!StringUtils.isEmpty(permission.getPermissionValue())) {
                        	//log.info("权限:" + permission.getPermissionValue());
                            authorizationInfo.addStringPermission(permission.getPermissionValue());
                        }
                	}
                }
        	}
        }
        
        return authorizationInfo;
	}
	
    /**
     * 清空当前用户权限信息
     */
    public  void clearCachedAuthorizationInfo() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
    
    /**
     * 指定principalCollection清除
     */
    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
}
