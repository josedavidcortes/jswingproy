package com.drago.ejercicio.cortesjose;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * Contenedor de elementos graficos y ventana principal de la aplicación
 *
 */
public class MarcoTabla extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTable csvTabla; 
	private CSVModel cvsModelo;

	/**
	 * En el constructor crearemos y configuraremos la GUI
	 */
	public MarcoTabla() {
		super("Visualizador archivos CSV");
		
		cvsModelo = new CSVModel();

		// Boton para cargar el archivo
		JButton btnCargar = new JButton("Cargar Archivo");

		// Contenedor del botón
		Box boxNorte = Box.createHorizontalBox();
		boxNorte.add(btnCargar);

		// JTable donde se mostraran los registros. Es la vista que proyectara
		// los datos del modelo (modeloTabla).
		csvTabla = new JTable(cvsModelo);

		// En el MarcoTabla (un JFrame) se insertan el contenedor del boton y la
		// tabla de resultados
		add(boxNorte, BorderLayout.NORTH);
		add(new JScrollPane(csvTabla), BorderLayout.CENTER);

		// Al botón de cargar se le asigna un listener encargado de cargar el
		// archivo.
		btnCargar.addActionListener(new ListenerBotonEnviar());

		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(cvsModelo);
		csvTabla.setRowSorter(sorter);

		// Tamaño del marco
		setSize(500, 250); // establece el tamaño de la ventana
		setVisible(true); // muestra la ventana

		// cierra la ventana cuando el usuario sale de la aplicación
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	/**
	 * Listener para capturar el evento del botón abriendo un "FileChooser" para
	 * seleccionar el archivo que se va a cargar en el modelo (modeloTabla).
	 *
	 */
	class ListenerBotonEnviar implements ActionListener {

		@Override
		// pasa la consulta al modelo de la tabla
		public void actionPerformed(ActionEvent evento) {

			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			int returnValue = jfc.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jfc.getSelectedFile();

				int totalLineas = cvsModelo.cargar(selectedFile.getAbsolutePath());
				
				cvsModelo.refrescar();
				
				if( totalLineas > 0 ){
					csvTabla.getRowSorter().toggleSortOrder(0);	
				}
			}

		}
	}

	/**
	 * Aquí comienza la aplicación
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		new MarcoTabla();
	}
}