package dataAccess.dbmodel;

import java.sql.Timestamp;

public class ShowDto {
	
	private int showId;
	private String title;
	private String distribution;
	private String genre;
	private Timestamp date;
	private int numberOfTickets;
	
	public ShowDto(int showId,String title, String distribution, String genre, Timestamp date, int numberOfTickets) {
		this.showId = showId;
		this.title = title;
		this.distribution = distribution;
		this.genre = genre;
		this.date = date;
		this.numberOfTickets = numberOfTickets;
	}
	
	public ShowDto(String title, String distribution, String genre, Timestamp date, int numberOfTickets) {
		this.title = title;
		this.distribution = distribution;
		this.genre = genre;
		this.date = date;
		this.numberOfTickets = numberOfTickets;
	}
	
	public ShowDto() {

	}
	
	public int getShowId() {
		return showId;
	}
	
	public void setShowId(int showId) {
		this.showId = showId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDistribution() {
		return distribution;
	}
	
	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public Timestamp getDate() {
		return date;
	}
	
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public int getNumberOfTickets() {
		return numberOfTickets;
	}
	
	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}
}
