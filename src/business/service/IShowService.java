package business.service;

import java.util.List;

import business.model.ShowModel;

public interface IShowService {
	
	public List<ShowModel> findAllShows();
	public ShowModel findShow(int id);
	public void deleteShow(int id);
	public void updateShow(int id, ShowModel model);
	public void insertShow(ShowModel model);

}
