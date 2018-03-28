package presentation;

import java.lang.reflect.Field;
import java.util.List;

import javax.swing.JTable;

public class AbstractTable<T> {
	
	public JTable createTable(List<T> t) {
		
		Object[][] resultData = new Object[t.size()][t.get(0).getClass().getDeclaredFields().length];
		String[] resultColumnName = new String[t.get(0).getClass().getDeclaredFields().length];
		int i = 0;
		
		for(T objectTable: t) {
			int j = 0;
			for (Field field : objectTable.getClass().getDeclaredFields()) {
				field.setAccessible(true); 
				Object value;
				try {
					value = field.get(objectTable);
					resultData[i][j] = value;
					resultColumnName[j] = field.getName();

				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				++j;
			}
			++i;
		}
		
		return new JTable(resultData, resultColumnName);	
	}
	
}
