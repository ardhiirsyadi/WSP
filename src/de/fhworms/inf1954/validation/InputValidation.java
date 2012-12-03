package de.fhworms.inf1954.validation;

import de.fhworms.inf1954.wsp.ConstantsDB;

public class InputValidation {
	
	public static enum DataType{
		WEIGHT,
		BLOOD_PRESSURE,
		BODY_TEMPERATURE
	}
	
	public static class Constant{
		private Constant(){
			
		}
		
		public static final int WEIGHT_LOWER_LIMIT = 20;
		public static final int WEIGHT_UPPER_LIMIT = 200;
		public static final int BLOOD_PRESSURE_LOWER_LIMIT = 30;
		public static final int BLOOD_PRESSURE_UPPER_LIMIT = 300;
		public static final int BODY_TEMPERATURE_LOWER_LIMIT = 35;
		public static final int BODY_TEMPERATURE_UPPER_LIMIT = 42;
	}
	
	public static boolean isValidated(DataType dataType, int data){
		boolean result = false;
		switch (dataType) {
		case WEIGHT:
			if(Constant.WEIGHT_LOWER_LIMIT <= data && data <= Constant.WEIGHT_UPPER_LIMIT){
				result = true;
			}
			break;
		case BLOOD_PRESSURE:
			if(Constant.BLOOD_PRESSURE_LOWER_LIMIT <= data && data <= Constant.BLOOD_PRESSURE_UPPER_LIMIT){
				result = true;
			}
			break;
			
		case BODY_TEMPERATURE:
			if(Constant.BODY_TEMPERATURE_LOWER_LIMIT <= data && data <= Constant.BODY_TEMPERATURE_UPPER_LIMIT){
				result = true;
			}
			break;

		default: throw new IllegalStateException("Implement DataType: '"+ dataType+"'");
		}
		return result;
	}

}
