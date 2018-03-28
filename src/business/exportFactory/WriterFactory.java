package business.exportFactory;

public class WriterFactory {
	
	public static Writer getReader(String writerType) {

		 Writer writer = null;

		 if (writerType.equalsIgnoreCase("XML")) {
			 writer = new XMLwriter();
		 } else if (writerType.equalsIgnoreCase("CSV")) {
			 writer = new CSVwriter();
		 } 
		 return writer;
	}

}
	

