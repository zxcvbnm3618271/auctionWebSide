package com.qianfeng.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.qianfeng.entity.Auction;
import com.qianfeng.entity.AuctionUser;

public class test {
	@Test
	public void test01(){
		SessionFactory sessionFactory=null;
		Session session=null;
		Transaction transaction=null;
		try {
			session=new Configuration().configure().buildSessionFactory().openSession();
			//hibernate 他的添加 删除 都是存粹的面向对象的思想
			//添加的话 必须要知道主键策略
			//native 根据不同的数据库采用不同主键生成策略 比如mysql主键自增
			//assigned 手动给主键赋值
			//数据库的增删改一定是在事务中完成的
			
			transaction=session.beginTransaction();
			Auction auction=new Auction();
			auction.setAuctionName("五一凡");
			//新增(注意主键策略)
			session.save(auction);
			//修改 (实体类的ID必须有值)
			session.update(auction);
			//删除 (实体类的id必须有值 只需要ID有值就可以)
			session.delete(auction);
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void test02(){
		//hibernate 在java中的三个状态
		//分别是 瞬时状态(自由状态)         持久状态         游离状态
		SessionFactory sessionFactory=null;
		Session session=null;
		Transaction transaction=null;
		try {
			sessionFactory=new Configuration().configure().buildSessionFactory();
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			//瞬时状态就是new一个对象 就是自由状态(说白了就是这个对象与hibernate 容器无关的对象 就是自由状态)
			//此时的Auction就是自由状态
			Auction auction=new Auction();
		//当这个对象被hibernate 容器所管理(session.get(),session.load(),session.save(),session.update(),session.saveorupdate(),session.list(),session.iterate())
		//这些函数所返回的对象 是持久态的对象 这个对象就是持久态
			auction=(Auction) session.get(Auction.class, 7);
			//什么是持久态?持久态就是 可以随时被持久化到数据库的对象 被称为 持久态的对象
			//游离态就是被hibernate容器摈弃掉的对象就是游离态对象
			session.evict(auction);
			//游离态和自由态的共同点 就是 对象的任何操作 都不会再被持久化到数据库
			//游离态和自由态的不同点就是 游离态具有ID 而自由态没有
			//游离态因为曾经被托管过,所以具有ID,因为游离态有ID,所以允许再次持久化
		transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void test03(){
		//HQL 两个特征
		//HQL 是半sql半面向对象的数据库查询语句
		//HQL 它是跨平台的查询语句 (不管是什么数据库 HQL 都可以执行 写法都一样)
		SessionFactory sessionFactory=null;
		Session session=null;
		try {
			sessionFactory=new Configuration().configure().buildSessionFactory();
			session=sessionFactory.openSession();
			//查找所有的拍卖品
			//如果查询部分字符 使用投影查询
			//写HQL需要注意一点 HQL 的思想实际是OOP 的思想 只是它的写法还是偏向sql语句的写法
			//Auction 一定要是我们的实体类
			//List<Auction> list=session.createQuery("from Auction").list();
			AuctionUser auctionUser=(AuctionUser) session.createQuery(
					"from AuctionUser as atus where atus.userName=? and atus.userPassWord=?"
					).setString(0, "test1").setString(1,"202cb962ac59075b964b07152d234b70" ).uniqueResult();
			System.out.println(auctionUser.getUserName());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//HQL 使用聚合函数
	@Test
	public void test4(){
		Session session=null;
		SessionFactory sessionFactory=null;
		sessionFactory=new Configuration().configure().buildSessionFactory();
		session=sessionFactory.openSession();
		List<Object> objects=session.createQuery(
				"select count(*),max(auctionStartPrice),min(auctionStartPrice),sum(auctionStartPrice),avg(auctionStartPrice) from Auction").list();
	Object[] objects2=(Object[]) objects.get(0);
	for (Object object : objects2) {
		System.out.println(object);
	}
	}
	
	@Test
	public void test5(){
		Session session=null;
		SessionFactory sessionFactory=null;
		sessionFactory=new Configuration().configure().buildSessionFactory();
		session=sessionFactory.openSession();
		//HQL实现分页
		//setFirstResult setMaxResult 和limit ?=setFirstResult,?=setMaxResults一样
		List<Auction> auctions=session.createQuery(
				"from Auction order by auctionStartPrice ASC").setFirstResult(0).setMaxResults(10).list();
		for (Auction auction : auctions) {
			System.out.println(auction.getAuctionName());
		}
	}
	//HIBERNATE实现动态查询
	@Test
	public void test6(){
		Session session=null;
		SessionFactory sessionFactory=null;
		sessionFactory=new Configuration().configure().buildSessionFactory();
		session=sessionFactory.openSession();
		//写死的例子,尝试动态查询
		String auctionName="青";
		String auctionStartTime="2011-11-11 11:11:11";
		String auctionEndTime="2012-11-11 11:11:11";
		String auctionStartPrice="1000";
		
		StringBuilder hql=new StringBuilder("from Auction as at where 1=1");
		Auction auction=new Auction();
		if (auctionName!=null&&!auctionName.equals(" ")) {
			//:auctionName 指的是按占位符 绑定
			//hibernate有两种占位符 一种是? 一种是: 对应的属性名 (动态查询 使用的就是:对应的属性名)
			hql.append("and at.auctionName like :auctionName ");
			auction.setAuctionName(auctionName+"%");
			
			
		}
		if (auctionStartTime!=null&&!auctionStartTime.equals(" ")) {
			hql.append("and at.auctionStartTime >= :auctionStartTime ");
			auction.setAuctionStartTime(Timestamp.valueOf(auctionStartTime));
			
		}
		if (auctionEndTime!=null&&!auctionEndTime.equals(" ")) {
			hql.append("and at.auctionEndTime <= :auctionEndTime ");
			auction.setAuctionEndTime(Timestamp.valueOf(auctionEndTime));
			
		}
		if (auctionStartPrice!=null&&!auctionStartPrice.equals(" ")) {
			hql.append("and at.auctionStartPrice >= :auctionStartPrice ");
			auction.setAuctionStartPrice(Double.valueOf(auctionStartPrice));
		}
		//HQL=form Auction as at and at.auctionName like : auctionName
		//:auctionName 这个关键字(占位符) 会去auction 中找对应的属性 并将其自动赋值
		List<Auction> auctions=session.createQuery(hql.toString())
				.setProperties(auction).list();
		for (Auction auction2 : auctions) {
			System.out.println(auction2.getAuctionName());
		}
	}
	
}
