package id.ac.pnj.uasalvitmj5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 1); //factorycursor bernilai null karena di websitenya katanya bisa null saja sebagai default
    }

    //dipanggil saat database pertama kali dibuat, jadi harus ada code untuk bikin database baru.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_AGE + " INTEGER, " + COLUMN_ACTIVE_CUSTOMER + " BOOL)";

        db.execSQL(createTableStatement);

    }

    //dipanggil saat ada versi baru (upgrade version). Mencegah app user sebelumnya dari error saat ada perubahan desain database.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean addOne(CustomerModel customerModel) {

        SQLiteDatabase db = this.getWritableDatabase();  //getWDB berasal dari propertinya si SQLDBH
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER_NAME, customerModel.getName()); //memang sifatnya class ContentValue yang menyimpan data secara berpasangan >> "name", nilai name
        cv.put(COLUMN_CUSTOMER_AGE, customerModel.getAge());
        cv.put(COLUMN_ACTIVE_CUSTOMER, customerModel.isActive());

        long insert = db.insert(CUSTOMER_TABLE, null, cv); //Insert bernilai -1 jika insert gagal.
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean deleteOne(CustomerModel customerModel){
        //menemukan CustomerModel di database, jika ada hapus, dan memberi nilai balik true, jika tidak ya false

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM "+CUSTOMER_TABLE+" WHERE "+COLUMN_ID+" = "+ customerModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        }
        else {
            return  false;
        }

    }

    public List<CustomerModel> getEveryone() {

        List<CustomerModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null); //untuk mendapatkan nilai balik cursor digunakan rawQuery, ada param yang kurang boleh pakai null setelah cek dokumentasi google

        if(cursor.moveToFirst()) { //bergerak ke result pertama, artinya benar telah terjadi masukan
            //loop pada result dan membuat object customer baru tiap baris
            do {
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                int customerAge = cursor.getInt(2);
                boolean customerActive = cursor.getInt(3) == 1 ? true: false; //ternary untuk menangani nilai bool

                CustomerModel newCustomer = new CustomerModel(customerID, customerName,customerAge, customerActive);
                returnList.add(newCustomer);
            } while (cursor.moveToNext());

        }
        else{
            //Jika tidak ada result dari database, tidak ada yang ditambahkan ke list.
        }

        //membersihkan cursor dan menutup db jika selesai
        cursor.close();
        db.close();
        return returnList;
    }
}
