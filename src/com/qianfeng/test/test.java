package com.qianfeng.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.qianfeng.entity.Auction;

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
}
