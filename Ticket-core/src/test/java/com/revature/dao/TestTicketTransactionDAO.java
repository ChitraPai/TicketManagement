package com.revature.dao;

import java.util.Iterator;
import java.util.List;

import com.revature.model.TicketTransaction;

public class TestTicketTransactionDAO {

	public static void main(String[] args) {
		TicketTransaction ticket = new TicketTransaction();
		TicketTransactionDAO ticketDAO = new TicketTransactionDAO();
		// List<TicketTransaction> listAll = ticketDAO.listAll();
		// Iterator<TicketTransaction> i = listAll.iterator();
		// while (i.hasNext()) {
		// TicketTransaction tic = (TicketTransaction) i.next();
		// System.out.println(tic.getId() + "\t" + tic.getUserId() + "\t" +
		// tic.getSubject() + "\t"
		// + tic.getDescription() + "\t" + tic.getCreatedDate().toLocalDate() +
		// "\t"
		// + tic.getDepartmentId().getId() + "\t" +
		// tic.getAssignedEmployeeId().getId() + "\t"
		// + tic.getResolvedDate().toLocalDate() + "\t" + tic.getStatus());

		// }
		ticket = ticketDAO.listByUserId(1);
		System.out.println(ticket.getSubject() + "\t" + ticket.getDescription() + "\t" + ticket.getStatus());
	}

}