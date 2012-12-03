package de.fhworms.inf1954.wsp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class WeightDB {

	private SQLiteDatabase db;
	private final DatabaseHelper databaseHelper;

	public WeightDB(Context context) {
		databaseHelper = new DatabaseHelper(context, ConstantsDB.DATABASE_NAME,
				null, ConstantsDB.VERSION);
		
	}

	public void close() {
		if (db != null && db.isOpen()) {
			db.close();
		}
	}

	public void open() throws SQLException {
		try {
			db = databaseHelper.getWritableDatabase();
		} catch (SQLiteException ex) {
			Log.v(ConstantsDB.TAG, ex.getMessage());
			db = databaseHelper.getReadableDatabase();
		}
	}

	public void create(double inputWeight, String time) {
		ContentValues value = new ContentValues();
		value.put(ConstantsDB.EVENTTIME, time);
		value.put(ConstantsDB.WEIGHT_IN_KG, inputWeight);
		db.insert(ConstantsDB.TABLE_NAME, null, value);
	}
	
	/**
	 * @param id
	 * @return
	 */
	// TODO implement comment and null pointer exception
	public double read(String time) {
		Cursor cursor = null;
		double result = 0;
		try {
			cursor = db.query(ConstantsDB.TABLE_NAME, ConstantsDB.COLUMNS,
					ConstantsDB.EVENTTIME + "='" + time+"'", null, null, null, null);
			if (cursor.getCount() == 1) {
				cursor.moveToFirst();
				result = cursor.getDouble(1);
			}
		} catch (SQLException ex) {
			Log.e(ConstantsDB.TAG, "Could not get value with time at " + time,ex);
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return result;
	}

	public void update(double value, String time) {
		ContentValues values = new ContentValues();
		values.put(ConstantsDB.WEIGHT_IN_KG, value);
		int rows = db.update(ConstantsDB.TABLE_NAME, values,
				ConstantsDB.EVENTTIME + "='" + time+"'", null);
		Log.d(ConstantsDB.TAG, "Updated value with time= " + time
				+ " | rows affected: " + rows);

	}

	public void delete(String time) {
		String SQL_DELETE = "DELETE FROM " + ConstantsDB.TABLE_NAME + " WHERE "
				+ ConstantsDB.EVENTTIME + "='" + time + "';";
		db.execSQL(SQL_DELETE);
	}

	public void delete(double weightTodelete) {
		String SQL_DELETE = "DELETE FROM " + ConstantsDB.TABLE_NAME + " WHERE "
				+ ConstantsDB.WEIGHT_IN_KG + "=" + weightTodelete + ";";
		db.execSQL(SQL_DELETE);
	}
	


	
	

}
