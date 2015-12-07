package lk.ac.mrt.cse.dbs.simpleexpensemanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Created by Liyanage Anushika on 12/7/2015.
 */
public class DBhandler extends SQLiteOpenHelper {

    protected static final String dbName = "130335J_database";
    private static DBhandler DBhandler = null;
    private static final int database_version = 1;

    public static final String ACCOUNTS_TABLE = "AccountsTable";
    public static final String ACCOUNT_NO = "ACCOUNT_NO";
    public static final String BANK_NAME = "BANK_NAME";
    public static final String ACCOUNT_HOLDER_NAME = "ACCOUNT_HOLDER_NAME";
    public static final String BALANCE = "BALANCE";

    public static final String TRANSATIONS_TABLE = "transationsTable";
    public static final String transaction_id = "transaction_id";
    public static final String DATE = "DATE";
    public static final String EXPENSE_TYPE = "EXPENSE_TYPE";
    public static final String AMOUNT = "AMOUNT";


    public DBhandler(Context context) {
        super(context, dbName, null, database_version);
    }

    public static DBhandler getInstance(Context context) {
        if (DBhandler == null)
            DBhandler = new DBhandler(context);
        return DBhandler;
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String accountTable = String.format("CREATE TABLE %s(%s VARCHAR(20) NOT NULL PRIMARY KEY,%s VARCHAR(100) NULL,%s VARCHAR(100) NULL,%s DECIMAL(10,2) NULL )", "Accounts", ACCOUNT_NO, BANK_NAME, ACCOUNT_HOLDER_NAME, BALANCE);

        String transactionTable = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,%s VARCHAR(100) NOT NULL,%s DATE NULL,%s DECIMAL(10,2) NULL,%s VARCHAR(100) NULL, FOREIGN KEY(%s) REFERENCES %s(%s))", "transactions", transaction_id, ACCOUNT_NO, DATE, AMOUNT, EXPENSE_TYPE, ACCOUNT_NO, accountTable, ACCOUNT_NO);

        sqLiteDatabase.execSQL(accountTable);
        sqLiteDatabase.execSQL(transactionTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int j) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TRANSATIONS_TABLE);
        onCreate(sqLiteDatabase);

    }
}
