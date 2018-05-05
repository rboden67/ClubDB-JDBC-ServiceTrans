package com.jrb;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class Main {
	public static void main(String[] args) throws SQLException {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ClubDBConfiguration.class);

		MemberDao memberDao = applicationContext.getBean(MemberDao.class);

		Scanner sc = new Scanner(System.in);
		// find by member Id with inline anonymous class declaration
		Member m = memberDao.find("Z005");
		System.out.println("\nOutput for find with inline anonymous class");
		System.out.println(m.toString());

		System.out.print("Lastnm: ");
		String lnm = sc.nextLine();
		m.setLastname(lnm);

		System.out.print("Firstnm: ");
		String fnm = sc.nextLine();
		m.setFirstname(fnm);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		System.out.print("Expdt: ");
		String edt = sc.nextLine();
		try {
			m.setExpdt(df.parse(edt));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(m.toString());
		memberDao.updateMember(m);

		applicationContext.close();
	}
}