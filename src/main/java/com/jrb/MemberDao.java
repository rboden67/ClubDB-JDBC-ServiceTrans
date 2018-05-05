package com.jrb;

import java.util.List;

public interface MemberDao {
	public void insert(Member member);
    public void delete(String memid);
    public void updateMember (Member member);
    public Member find(String memid);
    public Member findByQuery(String memid);
    public List<Member> findByStatus(String status);
    public void addDuesPurchase(String memid, String purchasedt, String success);
}
