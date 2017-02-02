package com.revature.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import com.revature.model.IssueSolution;
import com.revature.model.TicketTransaction;
import com.revature.util.ConnectionUtil;

public class IssueSolutionDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public List<IssueSolution> listAll() {
		String sql = "select id,ticket_id,solution from issue_solutions";

		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			IssueSolution issue = new IssueSolution();
			issue.setId(rs.getInt("id"));
			TicketTransaction ticket = new TicketTransaction();
			ticket.setId(rs.getInt("ticket_id"));
			issue.setTicketId(ticket);
			issue.setSolution(rs.getString("solution"));
			return issue;

		});

	}

}
