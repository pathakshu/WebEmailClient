package com.neu.project;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.neu.project.dao.HibernateUtil;
import com.neu.project.model.EmailAccount;
import com.neu.project.model.Messages;
import com.neu.project.model.User;



public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();


		Transaction t = session.beginTransaction();


		User u = new User();
		EmailAccount e= new EmailAccount();
		Messages m = new Messages();

		

		session.persist(u);

		session.persist(e);

		session.persist(m);


		t.commit();


		session.close();


		System.out.println("Success");

	}

}
