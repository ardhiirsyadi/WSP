package de.fhworms.inf1954.wsp;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
//		final String CREATE_TABLE_WEIGHT = "CREATE TABLE "+ConstantsDB.TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT,"+ConstantsDB.WEIGHT_IN_KG+" REAL);";
		final String CREATE_TABLE_WEIGHT = "CREATE TABLE "+ConstantsDB.TABLE_NAME+" ( "+ConstantsDB.EVENTTIME+" , "+ConstantsDB.WEIGHT_IN_KG+" REAL);";
		db.execSQL(CREATE_TABLE_WEIGHT);
		Log.d(ConstantsDB.TAG, "created table: "+ConstantsDB.TABLE_NAME);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(ConstantsDB.TAG, "Upgrading from version "+oldVersion+" to "+newVersion+", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS "+ConstantsDB.TABLE_NAME);
		onCreate(db);
	}
	
}
