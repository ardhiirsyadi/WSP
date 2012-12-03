package de.fhworms.inf1954.wsp;

import java.util.Calendar;

import de.fhworms.inf1954.walkingskeletonpersistence.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WSPActivity extends Activity {

	private WeightDB db;
	private DatabaseHelper databaseHelper;
	private static final String LAST_WEIGHT = "lastWeight";
	private double doubleToRead;
	private static final String SHARED_PREFERENCES = "sharedPreferences";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wsp);

		// SharedPreferences sharedPreferences =
		// getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
		// doubleToRead = sharedPreferences.getFloat(LAST_WEIGHT, 0);

		try {
			db = new WeightDB(this);
		} catch (Exception e) {
			Log.e(ConstantsDB.TAG, "Could not instantiate database: "
					+ ConstantsDB.DATABASE_NAME);
		}
		if (db == null) {
			throw new IllegalStateException(
					"Reference to 'WeightDB' is 'null '");
		}

		final Button saveButton = (Button) this.findViewById(R.id.buttonID);

		final EditText weightText = (EditText) this
				.findViewById(R.id.editTextID);

		weightText.requestFocus();
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

		weightText
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {

					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == EditorInfo.IME_ACTION_DONE) {
							doubleToRead = Double.parseDouble(weightText
									.getText().toString());
							System.out.println(doubleToRead);
							saveButton
									.setOnClickListener(new OnClickListener() {

										public void onClick(View v) {

											Calendar c =  Calendar.getInstance();
											int year = c.get(Calendar.YEAR);
											int month = c.get(Calendar.MONTH);
											int day = c.get(Calendar.DAY_OF_MONTH);
											int hour = c.get(Calendar.HOUR);
											int minute = c.get(Calendar.MINUTE);
											int second = c.get(Calendar.SECOND);
											String calendar = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
											
											db.open();
											Log.d(ConstantsDB.TAG, "DB opened");

											db.create(doubleToRead, calendar);
											Log.d(ConstantsDB.TAG,
													"value inserted");

											db.close();
											Log.d(ConstantsDB.TAG, "DB closed");

											Toast.makeText(
													getApplicationContext(),
													"weight: "
															+ doubleToRead
															+ " kg has been inserted to database",
													Toast.LENGTH_LONG).show();

											
											
										}
									});

							hideKeyboard();
						}
						return false;
					}

					private void hideKeyboard() {
						InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
						imm.toggleSoftInput(
								InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

					}
				});

		// // insert a value (row) into table
		// db.open();
		// Log.d(ConstantsDB.TAG, "DB opened");
		//
		// db.create(84.6);
		// Log.d(ConstantsDB.TAG, "value inserted");
		//
		// db.close();
		// Log.d(ConstantsDB.TAG, "DB closed");

		// // read the value from the given ID
		// db.open();
		// Log.d(ConstantsDB.TAG, "DB opened");
		//
		// db.read(2);
		// Log.d(ConstantsDB.TAG, "value read");
		//
		// db.close();
		// Log.d(ConstantsDB.TAG, "DB closed");

		// // update the tablerow with the given ID
		// db.open();
		// Log.d(ConstantsDB.TAG, "DB opened");
		//
		// db.update(86.4, 1);
		// Log.d(ConstantsDB.TAG, "value updated");
		//
		// db.close();
		// Log.d(ConstantsDB.TAG, "DB closed");

		// delete the row where the weight as stated in parameter
		// db.open();
		// Log.d(ConstantsDB.TAG, "DB opened");
		//
		// db.delete(84.6);
		// Log.d(ConstantsDB.TAG, "value deleted");
		//
		// db.close();
		// Log.d(ConstantsDB.TAG, "DB closed");

	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.v(ConstantsDB.TAG, "onStart()");
		// The activity is about to become visible
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.v(ConstantsDB.TAG, "onResume()");
		// The activity has become visible (it is now "resumed")
	}

	@Override
	protected void onPause() {
		super.onPause();
		// SharedPreferences sharedPreferences =
		// getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
		// Editor editor = sharedPreferences.edit();
		// editor.putFloat(LAST_WEIGHT, (float)doubleToRead);
		// editor.commit();
		// Log.v(ConstantsDB.TAG, "onPause()");
		// Another activity is taking focus (this activity is about to be
		// "paused")
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.v(ConstantsDB.TAG, "onStop()");
		// The activity is no longer visible (it is now "stopped")
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// The activity is about to be destroyed.
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_wsp, menu);
		return true;
	}
}
