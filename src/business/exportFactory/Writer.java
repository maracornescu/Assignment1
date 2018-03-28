package business.exportFactory;

import java.util.List;

import business.model.TicketModel;

public interface Writer {
	public void write(List<TicketModel> tickets);
}
