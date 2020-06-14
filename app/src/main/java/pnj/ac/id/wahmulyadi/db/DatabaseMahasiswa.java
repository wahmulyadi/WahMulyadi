package pnj.ac.id.wahmulyadi.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseMahasiswa extends SQLiteOpenHelper {
    public static String _NAMA_DATABASE= "db_mahasiswa";
    public static  int _VERSION=1;
    public static  String tb_mahasiswa ="CREATE TABLE tb_mahasiswa(nim  INTEGER  PRIMARY KEY , nama TEXT, jurusan TEXT)";


    public DatabaseMahasiswa(@Nullable Context context) {
        super(context, _NAMA_DATABASE, null,_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tb_mahasiswa);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    if (oldVersion != 1){
        db.execSQL("DROP TABLE tb_mahasiswa");
        db.execSQL(tb_mahasiswa);

        }

    }

}
