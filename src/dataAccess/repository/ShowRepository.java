package dataAccess.repository;

import java.util.List;

import dataAccess.dbmodel.ShowDto;

public class ShowRepository extends GenericRepository<ShowDto> implements IShowRepository{
	
	public ShowDto findShow(int id) {
		ShowRepository show = new ShowRepository();
		return show.findById(id);
	}
	
	public void deleteShow(int id) {
		ShowRepository show = new ShowRepository();
		show.deleteById(id);
	}
	
	
	public void updateShow(int id, ShowDto s) {
		ShowRepository show = new ShowRepository();
		show.update(id,s);
	}
	
	public void insertShow(ShowDto s) {
		ShowRepository show = new ShowRepository();
		show.insert(s);
	}
	
	public List<ShowDto> findAllShows() {
		ShowRepository show = new ShowRepository();
		List<ShowDto> shows = show.findAll();
		return shows;
	}
	
}
