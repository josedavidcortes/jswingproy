package com.drago.ejercicio.cortesjose;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/**
 * Clase que encapsula la lógica del modelo para los datos.
 */
public class CSVModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	
	List<Vector<String>> lineas = new ArrayList<Vector<String>>();

	/**
	 * Constructor
	 * 
	 * @param selectedFile
	 */
	public int cargar(String selectedFile){
		
		lineas = Utils.readCSV(selectedFile);
		
		return lineas.size();
		
	}

	/**
	 * 
	 * 
	 * @param columna
	 * @return 
	 * @throws IllegalStateException
	 */
	public Class<String> getColumnClass(int columna) throws IllegalStateException {
		return String.class;
	}

	/**
	 * 
	 * 
	 * @return
	 * @throws IllegalStateException
	 */
	public int getColumnCount() throws IllegalStateException {
		
		if( lineas != null && lineas.size() > 0){
			return lineas.iterator().next().size();
		}

		return 0;
	}

	/**
	 * 
	 * 
	 * @param columna
	 * @return
	 * @throws IllegalStateException
	 */
	public String getColumnName(int columna) throws IllegalStateException {
		
		if( lineas != null && lineas.size() > 0){
			Vector<String> vector = lineas.iterator().next();
			
			return vector.get(columna);
		}

		return "";
	}
 
	/**
	 * Devuelve el número de filas en el objeto ResultSet
	 * 
	 * @return
	 * @throws IllegalStateException
	 */
	public int getRowCount() throws IllegalStateException {
		
		return lineas.size() - 1;
	}

	/**
	 * 
	 * 
	 * @param fila
	 * @param columna
	 * @return
	 * @throws IllegalStateException
	 */
	public Object getValueAt(int fila, int columna) throws IllegalStateException {
				
		if( lineas != null && lineas.size() > 0){
			
			Vector<String> vector = lineas.get(fila+ 1);
			
			return vector.get(columna);
		}else{
			lineas = new ArrayList<Vector<String>>();
		}

		return null;
	}

	/**
	 * Refresca el modelo. notifica al objeto JTable que el modelo ha cambiado
	 */
	public void refrescar(){

		fireTableStructureChanged();
	}

}
