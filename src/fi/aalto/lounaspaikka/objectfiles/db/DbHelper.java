package fi.aalto.lounaspaikka.objectfiles.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/** 
 * 
 * @author Valeriy Volodenkov
 *
 */
public class DbHelper extends SQLiteOpenHelper{
	
	public static final String DATA_TABLE_NAME = "restaurant_data";
	private static final int DATABASE_VERSION = 2;
	
	 public DbHelper(Context context) {
		super(context, "database.db", null, DATABASE_VERSION);
	 
	}

	public boolean isTableExists() {
		    SQLiteDatabase  mDatabase = getReadableDatabase();
	        if(!mDatabase.isReadOnly()) {
	                mDatabase.close();
	                mDatabase = getReadableDatabase();    
	        }
	        Cursor cursor = mDatabase.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"
	        					+DATA_TABLE_NAME+"'", null);
	        if(cursor!=null) {
	            if(cursor.getCount()>0) {
	                return true;
	            }
	        }
	        return false;
	    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createtable = "create table "+DATA_TABLE_NAME+
					 " ( ID integer primary key autoincrement not null, " 
					 + "data TEXT, date TEXT)";
		db.execSQL(createtable);
		}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " +DATA_TABLE_NAME);
		onCreate(db);
	}
	
 
	public void dropTable(SQLiteDatabase db){
		db.execSQL("DROP TABLE IF EXISTS " +DATA_TABLE_NAME);
	}
	
}
