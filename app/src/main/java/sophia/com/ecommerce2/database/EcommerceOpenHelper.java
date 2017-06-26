package sophia.com.ecommerce2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sophia.com.ecommerce2.Category;
import sophia.com.ecommerce2.model.Product;


/**
 * Created by archimede on 26/06/17.
 */

public class EcommerceOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ecommerce_db";
    private static final String CATEGORIES_LIST_TABLE = "category";
    public static final String  KEY_ID = "_id";
    public static final String  KEY_IMAGEPATH = "imagePath";
    public static final String  KEY_TITLE = "title";
    public static final String  KEY_SUBTITLE = "subtitle";
    private static final String[] CATEGORY_COLUMNS = {KEY_ID, KEY_IMAGEPATH, KEY_TITLE, KEY_SUBTITLE};
    private static final String TAG = "ecommerceDb";

    //private static final String CATEGORY_LIST_TABLE_CREATE_2 = "CREATE TABLE \"category\" (\"_id\" INTEGER PRIMARY KEY  NOT NULL , \"title\" VARCHAR NOT NULL , \"subtitle\" TEXT, \"imagePath\" VARCHAR)";

    private static final String CATEGORY_LIST_TABLE_CREATE =
            "CREATE TABLE " + CATEGORIES_LIST_TABLE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY, " +
                    KEY_TITLE + " VARCHAR(100) NOT NULL, " +
                    KEY_SUBTITLE + " TEXT, " +
                    KEY_IMAGEPATH + " VARCHAR(255) );";
    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;


    private static final String PRODUCT_LIST_TABLE = "Product";
    private static final String KEY_DESCRIPTION = "Description";
    private static final String KEY_IMG_PRODUCT = "Img_product";
    private static final String KEY_PRICE = "Price";
    private static final String KEY_RATING = "Rating";

    private static final String PRODUCT_LIST_TABLE_CREATE =
            "CREATE TABLE " + PRODUCT_LIST_TABLE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY, " +
                    KEY_TITLE + " VARCHAR(100) NOT NULL, " +
                    KEY_DESCRIPTION + " TEXT, " +
                    KEY_IMG_PRODUCT + " VARCHAR(255), " +
                    KEY_PRICE + " FLOAT, "  +
                    KEY_RATING + " INTEGER );";



    public Category query(int position){
        String query = "SELECT * FROM " + CATEGORIES_LIST_TABLE +
                " ORDER BY " + KEY_ID + " ASC " +
                "LIMIT " + position + " ,1;";
        Cursor cursor = null;

        Category entry = new Category();

        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            entry.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
            entry.setSubTitle(cursor.getString(cursor.getColumnIndex(KEY_SUBTITLE)));
            entry.setImagePath(cursor.getString(cursor.getColumnIndex(KEY_IMAGEPATH)));

        }catch (Exception e) {
            Log.d(TAG, "QUERY ERROR! " + e.getMessage());

        }finally {
            if (cursor!=null)
                cursor.close();
            return entry;
        }
    }


    public EcommerceOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private void fillDatabaseWithData(SQLiteDatabase db){
        ContentValues values = new ContentValues();

        for (int  i = 0; i < 10; i++){
            values.put(KEY_TITLE, "titolo " + i);
            values.put(KEY_SUBTITLE, "sottotitolo " + i);
            values.put(KEY_IMAGEPATH, "http://www.visionealchemica.com/wp-content/uploads/2016/04/9194635-Sorridente-sole-isolato-su-sfondo-bianco-illustrazione-vettoriale-Archivio-Fotografico.jpg");
            db.insert(CATEGORIES_LIST_TABLE, null, values);
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CATEGORY_LIST_TABLE_CREATE);

        fillDatabaseWithData(db);

    }

    private void fillDatabaseProductWithData(SQLiteDatabase db){
        ContentValues values = new ContentValues();

        for (int  i = 0; i < 10; i++){
            values.put(KEY_TITLE, "titolo " + i);
            values.put(KEY_DESCRIPTION, "sottotitolo " + i);
            values.put(KEY_IMAGEPATH, "http://natale25.com/wp-content/uploads/2015/10/stella-gialla.jpg");
            values.put(KEY_PRICE, "prezzo " + i);
            values.put(KEY_RATING, "valutazione " + i);
            db.insert(PRODUCT_LIST_TABLE, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PRODUCT_LIST_TABLE_CREATE);

        fillDatabaseProductWithData(db);
    }

    public List<Product> getAllProduct() {
        String query = "SELECT * FROM " + PRODUCT_LIST_TABLE;
        Cursor cursor = null;
        List<Product> productlist = new ArrayList<>();

        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();

            while (cursor.moveToNext()) {
                Product entry = new Product();

                entry.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                entry.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
                entry.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
                entry.setImg_product(cursor.getString(cursor.getColumnIndex(KEY_IMAGEPATH)));
                entry.setPrice(cursor.getString(cursor.getColumnIndex(KEY_PRICE)));
                entry.setRating(cursor.getInt(cursor.getColumnIndex(KEY_RATING)));

                productlist.add(entry);

            }
        } catch (Exception e) {
            Log.d(TAG, "QUERY ERROR! " + e.getMessage());

        } finally {
            if (cursor != null)
                cursor.close();
            return productlist;
        }
    }
    public List<Category> getAllCategories() {
        String query = "SELECT * FROM " + PRODUCT_LIST_TABLE;

        Cursor cursor = null;
        List<Category> categorylist = new ArrayList<>();




        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();

            while (cursor.moveToNext()){
                Category entry = new Category();

                entry.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                entry.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
                entry.setSubTitle(cursor.getString(cursor.getColumnIndex(KEY_SUBTITLE)));
                entry.setImagePath(cursor.getString(cursor.getColumnIndex(KEY_IMAGEPATH)));
                categorylist.add(entry);
            }



        }catch (Exception e) {
            Log.d(TAG, "QUERY ERROR! " + e.getMessage());

        }finally {
            if (cursor!=null)
                cursor.close();
            return categorylist;
        }


    }
}
