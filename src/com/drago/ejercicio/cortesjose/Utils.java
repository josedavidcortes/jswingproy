package com.drago.ejercicio.cortesjose;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Utils {

	/**
	 * Dada la ruta del CSV, devuelve una colección de lineas.
	 * 
	 * @param csvPathFile
	 * @return
	 */
	public static List<Vector<String>> readCSV(String csvPathFile){
		
		List<Vector<String>> lines = new ArrayList<Vector<String>>();
		
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        try {

            br = new BufferedReader(new FileReader(csvPathFile));
            Vector<String> vector = null;
            while ((line = br.readLine()) != null) {

                String[] splitLine = line.split(cvsSplitBy);

            	vector = new Vector<String>();

            	for(String word : splitLine){
            		vector.add(word);
            	}
                
            	lines.add(vector);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }		
		
		return lines;
	}
	
}
