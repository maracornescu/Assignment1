package business.service;

import java.util.ArrayList;
import java.util.List;

import business.model.TicketModel;
import dataAccess.dbmodel.TicketDto;
import dataAccess.repository.ITicketRepository;
import dataAccess.repository.TicketRepository;

public class TicketService implements ITicketService{
	
	private final ITicketRepository repository;
	
	public TicketService() {
		this.repository = new TicketRepository();
	}
	
	private TicketModel mapToModel(TicketDto ticket) {
		TicketModel model = new TicketModel();
		
		model.setTicketId(ticket.getTicketId());
		model.setShowId(ticket.getShowId());
		model.setSeatRow(ticket.getSeatRow());
		model.setSeatNumber(ticket.getSeatNumber());
		model.setPrice(ticket.getPrice());
		
		return model;
	}
	
	private TicketDto mapToDto(TicketModel model) {
		TicketDto ticket = new TicketDto();
		
		ticket.setTicketId(model.getTicketId());
		ticket.setShowId(model.getShowId());
		ticket.setSeatRow(model.getSeatRow());
		ticket.setSeatNumber(model.getSeatNumber());
		ticket.setPrice(model.getPrice());
		
		return ticket;
	}
	
	public List<TicketModel> findAllTickets() {
		List<TicketDto> ticket = repository.findAllTickets();	
		List<TicketModel> result = new ArrayList<TicketModel>();
		for(TicketDto t:ticket) {
			TicketModel m = mapToModel(t);
			result.add(m);
		}
		return result;
	}
	
	public TicketModel findTicket(int id) {
		TicketDto ticket = repository.findTicket(id);
		TicketModel result = mapToModel(ticket);		
		return result;
	}
	
	public void deleteTicket(int id) {
		repository.deleteTicket(id);;
	}
	
	public void updateTicket(int id, TicketModel model) {
		validateSeat(model);
		TicketDto t = mapToDto(model);
		repository.updateTicket(id, t);
	}
	
	public void insertTicket(TicketModel model) {
		validateSeat(model);
		TicketDto t = mapToDto(model);
		repository.insertTicket(t);
	}
	
	private void validateSeat(TicketModel ticket) {
		TicketService serviceTicket = new TicketService();
		List<TicketModel> tickets = serviceTicket.findAllTickets();
		
		for(TicketModel t:tickets) {
			if(t.getSeatNumber() == ticket.getSeatNumber() && t.getSeatRow() == ticket.getSeatRow()) {
				throw new IllegalArgumentException("The seat has already been taken!");
			}
		}
		
		if(ticket.getSeatRow() > TicketModel.MAX_ROW_NUMBER || ticket.getSeatNumber() > TicketModel.MAX_SEAT_NUMBER) {
			throw new IllegalArgumentException("The seat does not exist!");
		}
	}
	
	
}
