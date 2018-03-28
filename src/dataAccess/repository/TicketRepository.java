package dataAccess.repository;


import java.util.List;

import dataAccess.dbmodel.TicketDto;

public class TicketRepository extends GenericRepository<TicketDto> implements ITicketRepository{
	
	public TicketDto findTicket(int id) {
		TicketRepository ticket = new TicketRepository();
		return ticket.findById(id);
	}
	
	public void deleteTicket(int id) {
		TicketRepository ticket = new TicketRepository();
		ticket.deleteById(id);
	}
	
	public void updateTicket(int id, TicketDto t) {
		TicketRepository ticket = new TicketRepository();
		ticket.update(id, t);
	}
	
	public void insertTicket(TicketDto t) {
		TicketRepository ticket = new TicketRepository();
		ticket.insert(t);
	}
	
	public List<TicketDto> findAllTickets() {
		TicketRepository ticket = new TicketRepository();
		List<TicketDto> tickets = ticket.findAll();
		return tickets;
	}
}
