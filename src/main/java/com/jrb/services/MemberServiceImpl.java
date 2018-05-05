package com.jrb.services;

import org.springframework.transaction.annotation.Transactional;

import com.jrb.Member;
import com.jrb.MemberDao;
import com.jrb.UpdateFailedException;

public class MemberServiceImpl implements MemberService {
	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Transactional
	public void updatePassword(String memid, long password) {
		try {
			Member m = memberDao.find(memid);
			if (m.getPassword() == password) {
				System.out.println("No Password Change : same password");
				return;
			}
			m.setPassword(password);
			memberDao.updateMember(m);
			System.out.println("Member " + memid + " updated");
		} catch (Exception e) {
			throw new UpdateFailedException("Update fail for " + memid + " " + e.getMessage());
		}
	}

	public boolean showMember(String memid) {
		try {
			Member m = memberDao.find(memid);
			System.out.println("Member found: " + m.toString());
			return true;
		} catch (Exception e) {
			System.out.println("Member not found: " + memid);
		}
		return false;
	}
	
	@Transactional
	public void renewMember(String memid, String success) {
		try {
			Member m = memberDao.find(memid);
			//renew exp date
			//m.renew();
			memberDao.updateMember(m);
			
			//create purchase for dues renewal
			//call memberDao.addDuesPurchase();
			
		} catch (Exception e) {
			throw new UpdateFailedException("Update fail for " + memid + " " + e.getMessage());
		}
	}
}
