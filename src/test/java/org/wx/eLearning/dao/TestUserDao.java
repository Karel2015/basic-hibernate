package org.wx.eLearning.dao;

import java.io.IOException;
import java.sql.SQLException;

import javax.inject.Inject;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.wx.eLearning.model.User;
import org.wx.eLearning.util.AbstractDbUnitTestCase;
import org.wx.eLearning.util.EntitiesHelper;

import com.github.springtestdbunit.DbUnitTestExecutionListener;


@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration("/beans.xml")
@TestExecutionListeners({DbUnitTestExecutionListener.class,DependencyInjectionTestExecutionListener.class})
public class TestUserDao extends AbstractDbUnitTestCase{
	@Inject
	private SessionFactory sessionFactory;
	@Inject
	private IUserDao userDao;
	
	
	@org.junit.Before
	public void setUp() throws DataSetException,SQLException,IOException{
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
		bakcupOneTable("tb_user");
	}	
	@org.junit.Test
	public void testSelect() throws DatabaseUnitException,SQLException,IOException{
		IDataSet ds = createDateSet("tb_user");
		org.dbunit.operation.DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
		User u = userDao.select(1);
		EntitiesHelper.assertUser(u);
	}
	@org.junit.After
	public void tearDown() throws DatabaseUnitException,SQLException,IOException{
		SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
		Session s = holder.getSession();
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory);
		resumesTable();
	}
	
	
}
