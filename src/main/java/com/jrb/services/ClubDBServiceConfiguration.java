package com.jrb.services;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jrb.ClubDBConfiguration;
import com.jrb.MemberDao;

@Configuration
@EnableTransactionManagement
@Import(ClubDBConfiguration.class)
public class ClubDBServiceConfiguration {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/CLUB296?serverTimezone=UTC&useSSL=false");
		dataSource.setUsername("webapp_user");
		dataSource.setPassword("testing123");
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource());
		return transactionManager;
	}

	@Bean
	public MemberService memberService(MemberDao memberDao) {
		MemberServiceImpl bean = new MemberServiceImpl();
		bean.setMemberDao(memberDao);
		return bean;
	}
}
