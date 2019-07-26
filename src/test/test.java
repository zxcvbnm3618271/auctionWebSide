package test;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;


import com.qf.tesla.biz.AuctionBIZ;
import com.qf.tesla.bizimpl.AuctionBIZImpl;
import com.qf.tesla.dao.AuctionDao;
import com.qf.tesla.daoimpl.AuctionDaoImpl;
import com.qf.tesla.entity.Auction;
import com.qf.tesla.entity.AuctionRecord;
import com.qf.tesla.entity.AuctionUser;
import com.qf.tesla.util.StringUtil;

public class test {
	


	public void test01() {


		SessionFactory sessionFactory = null;
		Session session = null;

		try {
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			// 主键策略native,根据不同的数据库采用不同的策略，比如mysql就是主键自增，oracle就是序列自增的。
			// 数据库的增删改一定是在事务中的
			// assingned手动给主键
			// 新增注意主键策略
			// 部分字段使用投影查询
			// userName一定要和类的属性名一样
//			AuctionUser auctionUser = (AuctionUser)session
//					.createQuery(
//							"from AuctionUser as atus where atus.userName=? and atus.userPassWord = ?")
//					.setString(0, "weng")
//					.setString(1, "202cb962ac59075b964b07152d234b70").uniqueResult();
////			
//			System.out.println(auctionUser.getUserName());
			
			
//			 List<Auction> aList = (List<Auction>)session.createQuery("from Auction").list();
//			 for (Auction a : aList) {
//			System.out.println(a.getAuctionName());
//			 }
			
//			List<Object> objects = session.createQuery("select count(*) , max(auctionStartPrice)," +
//					"sum(auctionStartPrice),avg(auctionStartPrice) from Auction").list();
//			Object[] objects2 = (Object[])objects.get(0);
//			
//			
//			for(Object o :objects2){
//				System.out.println(o);
//			}
			
//			
			 List<Auction> aList = session.createQuery("from Auction").setFirstResult(1).setMaxResults(3).list();
			 for (Auction a : aList) {
			System.out.println(a.getAuctionName());
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null){
				session.close();
			}
			
		}
	}
    @Test
	@SuppressWarnings("unchecked")
	public void test02(){
		SessionFactory sessionFactory = null;
		Session session = null;
		List<Auction> auctions = null;
		Auction auction = new Auction();

		AuctionDao auctionDao = new AuctionDaoImpl();
		String auctionName="少" ;
		String auctionStartTime=""; String auctionEndTime="";
		String auctionStratPrice="";
		StringBuilder hqlBuilder = new StringBuilder(
				"from Auction where 1=1 ");
		if (StringUtil.notEmpty(auctionName)) {
			hqlBuilder.append("and AUCTIONNAME like :auctionName ");
			auction.setAuctionName(auctionName+"%");
		}
		if (StringUtil.notEmpty(auctionStartTime)) {
			hqlBuilder.append("and AUCTIONSTARTTIME >= :auctionStartTime ");
			auction.setAuctionStartTime(Timestamp.valueOf(auctionStartTime));
		}
		if (StringUtil.notEmpty(auctionEndTime)) {
			hqlBuilder.append("and AUCTIONENDTIME >=  :auctionEndTime ");
			auction.setAuctionEndTime(Timestamp.valueOf(auctionEndTime));
		}
		if (StringUtil.notEmpty(auctionStratPrice)) {
			hqlBuilder.append("and AUCTIONSTARTPRICE <= :auctionStratPrice ");
			auction.setAuctionStartPrice(Double.valueOf(auctionStratPrice));
		}
		
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			auctions = session.createQuery(hqlBuilder.toString()).setProperties(auction).list();
			for(Auction auction2 :auctions){
				System.out.println(auction2);
			}
			
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
			if(session!=null){
				session.close();
			}                                                     
		}
		
	}
	@Test
	public void test03(){
		SessionFactory sessionFactory = null;
		Session session = null;
		
		try {
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			AuctionRecord auctionRecord = (AuctionRecord)session.get(AuctionRecord.class, 28);
			System.out.println(auctionRecord.getAuction().getAuctionName());
			System.out.println(auctionRecord.getAuctionUser().getUserName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void test04(){
		SessionFactory sessionFactory = null;
		Session session = null;
		
		try {
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			
//			Auction auction = (Auction)session.get(Auction.class, 292);
//			System.out.println(auction.getAuctionName());
//			session.close();
//			Session session2 = sessionFactory.openSession();
//			Auction auction2 = (Auction)session2.get(Auction.class, 292);
//	
//			System.out.println(auction2.getAuctionName());
//			
//			session2.close();
			
			Auction auction = (Auction)session.createQuery("from Auction where auctionName=?").setString(0, "少少").setCacheable(true).uniqueResult();
			System.out.println(auction.getAuctionName());
			
			Auction auction1 = (Auction)session.createQuery("from Auction where auctionName=?").setString(0, "少少").setCacheable(true).uniqueResult();
			System.out.println(auction1.getAuctionName());
			
			
			//查询语句的缓存依赖于二级缓存
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void test05(){
		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		List<Auction> endAuctions = auctionBIZ.searchEndAuctionList();
		for(Auction a:endAuctions){
			System.out.println(a);
		}
//		List<Auction> notEndAuctions = auctionBIZ.searchNotEndAuctionList();
//		for(Auction a:notEndAuctions){
//			System.out.println(a);
//		}
	}
}
