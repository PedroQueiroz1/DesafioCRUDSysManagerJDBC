package br.com.syscrud.converter;

public class DoubleConverter {

	public static double parseDouble(String input) {
		input = input.replace(",", ".");
		
		double value = 0;
		
		try {
			value = Double.parseDouble(input);
		}catch(NumberFormatException e) {
			System.out.println("Valor inválido...");
			e.printStackTrace();
		}
		
		return value;
	}
	
	
}
