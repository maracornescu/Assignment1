package dataAccess.repository;

import java.util.List;

import dataAccess.dbmodel.ShowDto;

public interface IShowRepository {
	
	public ShowDto findShow(int id);
	public void deleteShow(int id);
	public void updateShow(int id, ShowDto s);
	public void insertShow(ShowDto s);
	public List<ShowDto> findAllShows();
	
}
