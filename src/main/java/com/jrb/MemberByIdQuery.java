package com.jrb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class MemberByIdQuery extends MappingSqlQuery<Member>{
	
	public MemberByIdQuery(DataSource dataSource){
		super(dataSource, "select MemID, LastName, FirstName, Status, MemDt from tblMembers where MemID = ?");
		declareParameter(new SqlParameter(Types.CHAR));
        compile();
	}

	@Override
	protected Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member();
		member.setMemid(rs.getString("MemID"));
		member.setLastname(rs.getString("LastName"));
		member.setFirstname(rs.getString("FirstName"));
		member.setStatus(rs.getString("Status"));
		member.setMemdt(rs.getDate("Memdt"));
		return member;
	}

}
