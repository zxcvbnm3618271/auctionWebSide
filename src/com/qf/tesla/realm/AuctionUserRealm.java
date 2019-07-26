package com.qf.tesla.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.qf.tesla.dao.AuctionDao;
import com.qf.tesla.dao.AuctionUserDao;
import com.qf.tesla.entity.AtPermission;
import com.qf.tesla.entity.AtRole;
import com.qf.tesla.entity.AuctionUser;

public class AuctionUserRealm extends AuthorizingRealm {

	AuctionUserDao auctionUserDao;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		String userName = arg0.toString();
		AuctionUser auctionUser = auctionUserDao.findAuctionUserByName(userName);
		//默认开起懒加载，所以auctionUser角色和权限model都不能在这里直接获得,修改dao层,使得能查到。
		List<String> rolList = new ArrayList<String>();
		List<String> permmsionList = new ArrayList<String>();
		for(AtRole atRole : auctionUser.getAtRoles()){
			rolList.add(atRole.getRolename());
			for(AtPermission atPermission : atRole.getAtPermissions()){
				permmsionList.add(atPermission.getPermissionname());
			}
		}
//		授权信息都在这里
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addRoles(rolList);
		authorizationInfo.addStringPermissions(permmsionList);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken token = (UsernamePasswordToken) arg0;
		String userName = (String) token.getPrincipal();
		// 吧用户名当成盐
		ByteSource salt = ByteSource.Util.bytes(userName);
		AuctionUser auctionUser = auctionUserDao
				.findAuctionUserByName(userName);
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				auctionUser.getUserName(), auctionUser.getUserPassWord(),
				salt, getName());
		return authenticationInfo;
	}

	public AuctionUserDao getAuctionUserDao() {
		return auctionUserDao;
	}

	public void setAuctionUserDao(AuctionUserDao auctionUserDao) {
		this.auctionUserDao = auctionUserDao;
	}

}
