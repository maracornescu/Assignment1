package business.exportFactory;

import java.util.List;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import business.model.TicketModel;

public class XMLwriter implements Writer{
	
	public static final String xmlFilePath = "C:\\Users\\Mara\\Desktop\\xmlfile.xml";
	
	
	@Override
	public void write(List<TicketModel> tickets) {
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			
			
			Element root = document.createElement("Tickets");
			document.appendChild(root);
			
			for(TicketModel t:tickets) {
				Element ticket = document.createElement("Ticket");
				root.appendChild(ticket);
				Attr attr = document.createAttribute("TicketId");
				attr.setValue(String.valueOf(t.getTicketId()));
				ticket.setAttributeNode(attr);
				
				Element showId = document.createElement("ShowId");
				showId.appendChild(document.createTextNode(String.valueOf(t.getShowId())));
				ticket.appendChild(showId);
				
				Element seatRow = document.createElement("SeatRow");
				seatRow.appendChild(document.createTextNode(String.valueOf(t.getSeatRow())));
				ticket.appendChild(seatRow);
				
				Element seatNumber = document.createElement("SeatNumber");
				seatNumber.appendChild(document.createTextNode(String.valueOf(t.getSeatNumber())));
				ticket.appendChild(seatNumber);
				
				Element price = document.createElement("Price");
				price.appendChild(document.createTextNode(String.valueOf(t.getPrice())));
				ticket.appendChild(price);	
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource domSource = new DOMSource(document);
				StreamResult streamResult = new StreamResult(new File(xmlFilePath));
			
				transformer.transform(domSource, streamResult);
			}
			System.out.println("Done creating XML File");
		
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		
	}

}
