package presentation;

import java.util.List;

import javax.swing.JTable;

import business.Theater;
import business.model.UserModel;
import business.service.IShowService;
import business.service.ShowService;

public class UserTable extends AbstractTable<UserModel> {
	
	private Theater service;
	private List<UserModel> cashiers;
	
	public JTable create(List<UserModel> users) {
		UserTable table = new UserTable();
		JTable cashierTable = table.createTable(users);
		return cashierTable;
	}
	
	public JTable createTable() {
		service = new Theater();
		cashiers = service.findCashiers();
		
		return create(cashiers);
	}
}
