package tests;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import business.Encryption;
import business.Theater;
import business.model.ShowModel;
import business.model.TicketModel;
import business.service.ShowService;
import dataAccess.dbmodel.ShowDto;
import dataAccess.repository.ShowRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MyTests {

	@Test
	public void numberOfTicketsIsExceeded() {
		
		Theater t = new Theater();
		/*
		try {
			
			t.sellTicket(1, 8, 9, 200);
			assertEquals(0, 0);
			
		} catch (IllegalArgumentException e){
			
			assertEquals(0, 1);
			
		}*/
		
		try {		
			t.sellTicket(1, 1, 2, 200);
			assertEquals(0, 1);	
		} catch (IllegalArgumentException e){		
			assertEquals(0, 0);	
		}
	}
	
	@Test
	public void isPasswordEncrypted() {
		
		
		String myPassword = "mara123";
		String hashedPassword = Encryption.get_SHA_1_SecurePassword(myPassword);
	
		assertEquals(hashedPassword, "ac7d32e3c0f61ebc7dfd1ee010f24537314eec2e");
		
		
	}
	
	
	@Test
	public void allShowsMustBeFound() {
		
		
		List<ShowDto> dtoShows = new ArrayList<ShowDto>();
		List<ShowModel> modelShows = new ArrayList<ShowModel>();
		
		ShowDto s1 = new ShowDto(1, "Danseaza liber", "Ariel Moore - Catinca Nistor", "musical", Timestamp.valueOf("2018-03-30 12:00:00"), 500);
		ShowDto s2 = new ShowDto(2, "Familia fara zahar", "Mihaela Radescu, Viorel Cojanu", "teatru muzical", Timestamp.valueOf("2018-03-31 19:00:00"), 500);
		
		dtoShows.add(s1);
		dtoShows.add(s2);
		
		ShowRepository mockito = mock(ShowRepository.class);
		when(mockito.findAllShows()).thenReturn(dtoShows);
		
		
		ShowService showService = new ShowService(mockito);
		modelShows = showService.findAllShows();
		
		assertEquals(modelShows.size(), dtoShows.size());
		
		int size = dtoShows.size();
		
		for(int i = 0; i < size; ++i ) {
			assertTrue(dtoShows.get(i).getShowId() == modelShows.get(i).getShowId());
			assertTrue(dtoShows.get(i).getTitle().equals(modelShows.get(i).getTitle()));
			assertTrue(dtoShows.get(i).getDistribution().equals(modelShows.get(i).getDistribution()));
			assertTrue(dtoShows.get(i).getGenre().equals(modelShows.get(i).getGenre()));
			assertTrue(dtoShows.get(i).getDate().equals(modelShows.get(i).getDate()));
			assertTrue(dtoShows.get(i).getNumberOfTickets() == modelShows.get(i).getNumberOfTickets());	
		}		
	}
	

}
