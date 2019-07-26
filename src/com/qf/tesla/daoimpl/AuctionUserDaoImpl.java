package com.qf.tesla.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.qf.tesla.dao.AuctionUserDao;
import com.qf.tesla.entity.AtPermission;
import com.qf.tesla.entity.AtRole;
import com.qf.tesla.entity.AuctionUser;
import com.qf.tesla.util.JDBCUtil;

import javax.ejb.TransactionManagement;
import javax.persistence.Entity;

import org.apache.shiro.SecurityUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
 
@Transactional
public class AuctionUserDaoImpl extends HibernateDaoSupport implements AuctionUserDao {

	@Override
	public AuctionUser auctionLogin(String userName, String passWord) {
		// TODO Auto-generated method stub
		AuctionUser auctionUser= null;
		try {
			//事务管理的session,自动关闭，增删改操作不需要提交，控制好持久状态即可
			//getSessionFactory().getCurrentSession()
			//注意事项：不要对持久态的对象进行set等属性修改，否则会自动保存到数据库中。
		
			auctionUser = (AuctionUser)getSessionFactory().getCurrentSession().createQuery("from AuctionUser where userName=? and userPassword=?")
					.setString(0, userName)
					.setString(1, passWord).uniqueResult();
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return auctionUser;
	}

	@Override
	public AuctionUser findAuctionUserByName(String userName) {
		// TODO Auto-generated method stub
		AuctionUser auctionUser= null;
		try {
			//事务管理的session,自动关闭，增删改操作不需要提交，控制好持久状态即可
			//getSessionFactory().getCurrentSession()
			//注意事项：不要对持久态的对象进行set等属性修改，否则会自动保存到数据库中。
		
			auctionUser = (AuctionUser)getSessionFactory().getCurrentSession().createQuery("from AuctionUser where userName=?")
					.setString(0, userName)
					.uniqueResult();
			
//			Boolean boolean1 = SecurityUtils.getSubject().isAuthenticated();
			if (auctionUser!=null) {
				//将角色集合和权限操作实例化
				for(AtRole atRole : auctionUser.getAtRoles()){
					System.out.println(atRole.getRolename());
					for(AtPermission atPermission : atRole.getAtPermissions()){
						System.out.println(atPermission.getPermissionname());
					}
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return auctionUser;
	}
}
