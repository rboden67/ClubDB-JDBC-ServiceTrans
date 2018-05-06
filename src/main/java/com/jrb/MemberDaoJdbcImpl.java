package com.jrb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.MappingSqlQuery;

public class MemberDaoJdbcImpl implements MemberDao {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private MappingSqlQuery<Member> memberByIdQuery;

	public void setMemberByIdQuery(MappingSqlQuery<Member> memberByIdQuery) {
		this.memberByIdQuery = memberByIdQuery;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}

	public void insert(Member member) {
		PreparedStatementCreatorFactory psCreatorFactory = new PreparedStatementCreatorFactory(
				"insert into tblMembers (MemId, LastName, FirstName, MiddleName, Status, Memdt, Password) values(?,?,?,?,?,?,?)",
				new int[] { Types.CHAR, Types.CHAR, Types.CHAR, Types.CHAR, Types.CHAR, Types.DATE, Types.CHAR });
		int count = jdbcTemplate.update(psCreatorFactory.newPreparedStatementCreator(
				new Object[] { member.getMemid(), member.getLastname(), member.getFirstname(), member.getMiddlename(),
						member.getStatus(), member.getMemdt(), member.getPassword() }));
		if (count != 1)
			throw new UpdateFailedException("Cannot insert member");
	}

	public void updateMember(Member member) {
		try {
			int count = jdbcTemplate.update(
					"Update tblMembers set LastName = ?, FirstName = ?, MiddleName = ?, "
							+ "Status = ?, Memdt = ?, Expdt = ?, Password = ? where MemId = ?",
					member.getLastname(), member.getFirstname(), member.getMiddlename(), member.getStatus(),
					member.getMemdt(), member.getExpdt(), member.getPassword(), member.getMemid());
			if (count != 1) {
				throw new UpdateFailedException("No member update for " + member.getMemid() + " count = " + count);
			}
		} catch (Exception e) {
			throw new UpdateFailedException(e.getMessage());
		}
	}

	public void addDuesPurchase(String memid, String purchasedt, String success) {
		int count;
		PreparedStatementCreatorFactory psCreatorFactory = new PreparedStatementCreatorFactory(
				"insert into tblPurchases (MemId, PurchaseDt, TransType, TransCd, Amount) values(?,?,?,?,?)",
				new int[] { Types.CHAR, Types.DATE, Types.CHAR, Types.CHAR, Types.DOUBLE });
		if (success.compareToIgnoreCase("Y") == 0) {
			count = jdbcTemplate.update(
					psCreatorFactory.newPreparedStatementCreator(new Object[] { memid, purchasedt, "D", "01", 100 }));
		} else {
			count = jdbcTemplate.update(psCreatorFactory
					.newPreparedStatementCreator(new Object[] { memid, purchasedt, "D", "01", "BAD DATA" }));
		}
		if (count != 1)
			throw new UpdateFailedException("Dues insert failed");
	}

	public void delete(String memid) {
		int count = jdbcTemplate.update("delete from tblMembers where MemId = ?", memid);
		if (count != 1)
			throw new UpdateFailedException("Cannot delete account");
	}

	public Member find(String memid) {
		return jdbcTemplate.queryForObject(
				"select MemID, LastName, FirstName, Status, MemDt, Expdt, Password from tblMembers where MemID = ?",
				new RowMapper<Member>() {
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member();
						member.setMemid(rs.getString("MemID"));
						member.setLastname(rs.getString("LastName"));
						member.setFirstname(rs.getString("FirstName"));
						member.setStatus(rs.getString("Status"));
						member.setMemdt(rs.getDate("Memdt"));
						member.setExpdt(rs.getDate("Expdt"));
						member.setPassword(rs.getInt("Password"));
						return member;
					}
				}, memid);
	}

	public Member findByQuery(String memid) {
		return memberByIdQuery.findObject(memid);
	}

	public List<Member> findByStatus(String status) {
		PreparedStatementCreatorFactory psCreatorFactory = new PreparedStatementCreatorFactory(
				"select * from tblMembers where status = ?", new int[] { Types.CHAR });
		return jdbcTemplate.query(psCreatorFactory.newPreparedStatementCreator(new Object[] { status }),
				new RowMapper<Member>() {
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member();
						member.setMemid(rs.getString("MemID"));
						member.setLastname(rs.getString("LastName"));
						member.setFirstname(rs.getString("FirstName"));
						member.setStatus(rs.getString("Status"));
						member.setMemdt(rs.getDate("Memdt"));
						return member;
					}

				});

	}

}
