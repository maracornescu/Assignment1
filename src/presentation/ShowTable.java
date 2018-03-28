package presentation;

import java.util.List;

import javax.swing.JTable;

import business.model.ShowModel;
import business.service.IShowService;
import business.service.ShowService;

public class ShowTable extends AbstractTable<ShowModel>{
	
	private IShowService service;
	private List<ShowModel> shows;
	
	public JTable create(List<ShowModel> show) {
		ShowTable table = new ShowTable();
		JTable cashierTable = table.createTable(show);
		return cashierTable;
	}
	
	public JTable createTable() {
		service = new ShowService();
		shows = service.findAllShows();
		
		return create(shows);
	}
	
}
