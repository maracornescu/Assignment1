package business.exportFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import business.model.TicketModel;

public class CSVwriter implements Writer{
	
	private static final String COMMA_DELIMITER = ",";
    private static final String LINE_SEPARATOR = "\n";
    
    private static final String HEADER = "TicketId,ShowId,SeatRow,SeatNumber,Price";

	@Override
	public void write(List<TicketModel> tickets) {
		FileWriter fileWriter = null;
		try
    	{
    		fileWriter = new FileWriter("Tickets.csv");
    		
    		//Adding the header
    		fileWriter.append(HEADER);
    		//New Line after the header
    		fileWriter.append(LINE_SEPARATOR);
    		
    		//Iterate the empList
    		Iterator<TicketModel> it = tickets.iterator();
    		while(it.hasNext())
    		{
    			TicketModel t = (TicketModel)it.next();
    			fileWriter.append(String.valueOf(t.getTicketId()));
    			fileWriter.append(COMMA_DELIMITER);
    			fileWriter.append(String.valueOf(t.getShowId()));
    			fileWriter.append(COMMA_DELIMITER);
    			fileWriter.append(String.valueOf(t.getSeatRow()));
    			fileWriter.append(COMMA_DELIMITER);
    			fileWriter.append(String.valueOf(t.getSeatNumber()));
    			fileWriter.append(COMMA_DELIMITER);
    			fileWriter.append(String.valueOf(t.getPrice()));
    			fileWriter.append(LINE_SEPARATOR);
    		}
    		System.out.println("Write to CSV file Succeeded!!!");
    	}
    	catch(Exception ee)
    	{
    		ee.printStackTrace();
    	}
    	finally
    	{
    		try
    		{
    			fileWriter.close();
    		}
    		catch(IOException ie)
    		{
    			System.out.println("Error occured while closing the fileWriter");
    			ie.printStackTrace();
    		}
    	}
    }
		
}
    



	/*public void write(List<TicketModel> tickets) {
		String csvFile = "desktop/file.csv";
		//FileWriter writer = new FileWriter(csvFile);
		
		//CSVUtils.writeLine(writer, Arrays.asList("TicketId", "ShowId", "SeatRow", "SeatNumber", "Price"));
		
		for(TicketModel t:tickets) {
			List<String> list = new ArrayList<String>();
			list.add(String.valueOf(t.getTicketId()));
			list.add(String.valueOf(t.getShowId()));
			list.add(String.valueOf(t.getSeatRow()));
			list.add(String.valueOf(t.getSeatNumber()));
			list.add(String.valueOf(t.getPrice()));
			
			//CSVUtils.writeLine(writer, list);
		}
		
		 //writer.flush();
	     //writer.close();
	}*/
	

	

