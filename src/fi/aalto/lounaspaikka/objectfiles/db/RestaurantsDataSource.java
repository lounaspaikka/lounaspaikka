package fi.aalto.lounaspaikka.objectfiles.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
/**
 * 
 * @author Valeriy Volodenkov
 *
 */
public class RestaurantsDataSource {
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	
	List<String> dataList;
	List<String>  dates;
	
	public List<String> getDataList() {
		return dataList;
	}

	public List<String> getDates() {
		return dates;
	}

	public RestaurantsDataSource(Context context){
		dbHelper = new DbHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	/**
	 * 
	 * @param data
	 */
	public void SaveData(String data, String date){
		//clear table first so only there is only one record in it
		dbHelper.onUpgrade(database, 1, 2);
		
		
		ContentValues values = new ContentValues();
		values.put("data", data);
		values.put("date", date);
	    database.insert(DbHelper.DATA_TABLE_NAME, null,
				values);
	 
		
	}
	/**
	 * 
	 * @return
	 */
	public List<String> getAllData() {
		dataList = new ArrayList<String>();
		dates = new ArrayList<String>();
		String[] columns = {"data","date"};
		Cursor cursor = database.query(DbHelper.DATA_TABLE_NAME,
				columns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			String comment = (cursor.getString(0));
			dataList.add(comment);
			String date = (cursor.getString(1));
			dates.add(date);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return dataList;
	}
	
 
	public void dropTable(){
		dbHelper.dropTable(database);
	}
	
	public void clearDB() {
		dbHelper.onUpgrade(database, 0, 1);
	}
	
}
