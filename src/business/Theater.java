package business;

import java.util.ArrayList;
import java.util.List;

import business.model.ShowModel;
import business.model.TicketModel;
import business.model.UserModel;
import business.service.IUserService;
import business.service.ShowService;
import business.service.TicketService;
import business.service.UserService;

public class Theater {
	
	private final IUserService service;
	
	public Theater() {
		this.service = new UserService();
	}
	
	public UserModel findAdmin() {
		List<UserModel> users = service.findAllUsers();
		UserModel admin = new UserModel();
		
		for(UserModel u:users) {
			if(u.getIsAdmin() == 1) {
				admin = u;
			}
		}
		return admin;
	}
	
	public List<UserModel> findCashiers() {
		List<UserModel> users = service.findAllUsers();
		List<UserModel> cashiers = new ArrayList<UserModel>();
		
		for(UserModel u:users) {
			if(u.getIsAdmin() == 0) {
				cashiers.add(u);
			}
		}
		return cashiers;
	}
	
	public List<TicketModel> findAllTicketsForShow(int showId) {
		TicketService service = new TicketService();
		List<TicketModel> tickets = service.findAllTickets();
		List<TicketModel> result = new ArrayList<TicketModel>();
		
		for(TicketModel t:tickets) {
			if(t.getShowId() == showId) {
				result.add(t);
			}
		}
		
		return result;
	}
	
	public void sellTicket(int showId, int seatRow, int seatNumber, float price) {
		TicketService serviceTicket = new TicketService();
		ShowService serviceShow = new ShowService();
		
		TicketModel ticket = new TicketModel(showId,seatRow,seatNumber,price);
		ShowModel show = serviceShow.findShow(showId);
		
		serviceTicket.insertTicket(ticket);
		
		int newNumberOfTikets = show.getNumberOfTickets() - 1;
		
		show.setNumberOfTickets(newNumberOfTikets);
		//ShowModel sell = new ShowModel(show.getShowId(),show.getTitle(),show.getDistribution(),show.getGenre(),show.getDate(),newNumberOfTikets);
		serviceShow.updateShow(show.getShowId(), show);
	}
	
	public void cancelSelling(ShowModel s) {
		ShowService serviceShow = new ShowService();
		int newNumberOfTikets = s.getNumberOfTickets() + 1;
		
		ShowModel show = new ShowModel(s.getShowId(),s.getTitle(),s.getDistribution(),s.getGenre(),s.getDate(),newNumberOfTikets);
		serviceShow.updateShow(s.getShowId(), show);
	}
}
