package de.fhworms.inf1954.wsp;

public class ConstantsDB {

	private ConstantsDB() {

	}

	public static final String TABLE_NAME = "table_Weight";
	public static final String WEIGHT_IN_KG = "Weight";
	public static final String EVENTTIME = "Time";
	public static final String[] COLUMNS = new String[] { EVENTTIME,
			WEIGHT_IN_KG };
	public static final String DATABASE_NAME = "wspTest4.db";
	public static final int VERSION = 1;
	public static final String TAG = "de.fhworms.inf1954.wsp";

}
