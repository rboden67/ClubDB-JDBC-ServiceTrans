package com.jrb.services;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ClubDBServiceConfiguration.class);
		
		MemberService memberService = applicationContext.getBean(MemberService.class);
		
		Scanner sc = new Scanner(System.in);
		String memid = null, success = null;
		
//		System.out.print("Update Password for member: ");
//		memid = sc.nextLine();
//		if (!memid.isEmpty() && memberService.showMember(memid)) {
//			System.out.print("New Password: ");
//			long pwd = Long.parseLong(sc.nextLine());
//			memberService.updatePassword(memid, pwd);
//		}
		
		System.out.print("Renew member (id): ");
		memid = sc.nextLine();
		if (!memid.isEmpty() && memberService.showMember(memid)) {
			System.out.print("Success? ");			
			success = sc.nextLine();
			memberService.renewMember(memid, success);
		}

		sc.close();
		applicationContext.close();
	}

}
