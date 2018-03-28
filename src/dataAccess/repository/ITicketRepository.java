package dataAccess.repository;

import java.util.List;

import dataAccess.dbmodel.TicketDto;

public interface ITicketRepository {
	
	public TicketDto findTicket(int id);
	public void deleteTicket(int id);
	public void updateTicket(int id, TicketDto t);
	public void insertTicket(TicketDto t);
	public List<TicketDto> findAllTickets();
	
}
