package dataAccess.dbmodel;

public class TicketDto {
	
	private int ticketId;
	private int showId;
	private int seatRow;
	private int seatNumber;
	private float price;
	
	public TicketDto(int ticketId, int showId, int seatRow, int seatNumber, float price) {
		this.ticketId = ticketId;
		this.showId = showId;
		this.seatRow = seatRow;
		this.seatNumber = seatNumber;
		this.price = price;
	}
	
	public TicketDto(int showId, int seatRow, int seatNumber, float price) {
		this.showId = showId;
		this.seatRow = seatRow;
		this.seatNumber = seatNumber;
		this.price = price;
	}
	
	public TicketDto() {
		
	}
	
	public int getTicketId() {
		return ticketId;
	}
	
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	
	public int getShowId() {
		return showId;
	}
	
	public void setShowId(int showId) {
		this.showId = showId;
	}
	
	public int getSeatRow() {
		return seatRow;
	}
	
	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}
	
	public int getSeatNumber() {
		return seatNumber;
	}
	
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
}
