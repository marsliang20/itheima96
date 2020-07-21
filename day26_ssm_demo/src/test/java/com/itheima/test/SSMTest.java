package com.itheima.test;

import com.itheima.dao.AccountDao;
import com.itheima.pojo.Account;
import com.itheima.service.AccountService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SSMTest {

    @Test
    public void testSpring(){
        ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring-service.xml");
        AccountService service = app.getBean(AccountService.class);
        service.queryAll();
    }

    @Test
    public void testMybatis() throws IOException {
        //加载Mybatis的核心配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //使用构造者构造一个SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //使用工厂生产一个SqlSession对象
        SqlSession session = factory.openSession();
        AccountDao dao = session.getMapper(AccountDao.class);
        List<Account> accounts = dao.queryAll();
        for (Account account : accounts) {
            System.out.println(account);
        }

        session.close();
        is.close();
    }

    @Test
    public void testMybatisAndString(){
        //从IoC容器里获取accountDao的代理对象
        ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring-dao.xml");
        AccountDao dao = app.getBean(AccountDao.class);
        List<Account> accounts = dao.queryAll();
        for (Account account : accounts) {
            System.out.println(account);
        }

    }
}
