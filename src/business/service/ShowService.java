package business.service;

import java.util.ArrayList;
import java.util.List;

import business.model.ShowModel;
import dataAccess.dbmodel.ShowDto;
import dataAccess.repository.IShowRepository;
import dataAccess.repository.ShowRepository;

public class ShowService implements IShowService{
	
	private final IShowRepository repository;
	
	public ShowService() {
		this.repository = new ShowRepository();
	}
	
	public ShowService(IShowRepository repository) {
		
		this.repository = repository;
		
	}
	
	private ShowModel mapToModel(ShowDto show) {
		ShowModel model = new ShowModel();
		
		model.setShowId(show.getShowId());
		model.setTitle(show.getTitle());
		model.setDistribution(show.getDistribution());
		model.setGenre(show.getGenre());
		model.setDate(show.getDate());
		model.setNumberOfTickets(show.getNumberOfTickets());
		
		return model;
	}
	
	private ShowDto mapToDto(ShowModel model) {
		ShowDto show = new ShowDto();
		
		show.setShowId(model.getShowId());
		show.setTitle(model.getTitle());
		show.setDistribution(model.getDistribution());
		show.setGenre(model.getGenre());
		show.setDate(model.getDate());
		show.setNumberOfTickets(model.getNumberOfTickets());
		
		return show;
	}
	
	public List<ShowModel> findAllShows() {
		List<ShowDto> show = repository.findAllShows();	
		List<ShowModel> result = new ArrayList<ShowModel>();
		for(ShowDto s:show) {
			ShowModel m = mapToModel(s);
			result.add(m);
		}
		return result;
	}
	
	public ShowModel findShow(int id) {
		ShowDto show = repository.findShow(id);
		ShowModel result = mapToModel(show);		
		return result;
	}
	
	public void deleteShow(int id) {
		repository.deleteShow(id);
	}
	
	public void updateShow(int id, ShowModel model) {
		ShowDto s = mapToDto(model);
		validateNumberOfTickets(s.getNumberOfTickets());
		repository.updateShow(id, s);
	}
	
	public void insertShow(ShowModel model) {
		ShowDto s = mapToDto(model);
		validateNumberOfTickets(s.getNumberOfTickets());
		repository.insertShow(s);
	}
	
	private void validateNumberOfTickets(int numberOfTickets) {
		if(numberOfTickets < 0) {
			throw new IllegalArgumentException("All tickets were sold for this show!");
		}
	}
	
}
