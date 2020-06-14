package pnj.ac.id.wahmulyadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pnj.ac.id.wahmulyadi.db.DatabaseMahasiswa;

public class Tambah_Mahasiswa extends AppCompatActivity implements View.OnClickListener {
EditText edtNim,edtNama,edtJurusan;
Button actionSimpan;
SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah__mahasiswa);
        edtNim=findViewById(R.id.edtNIM);
        edtNama=findViewById(R.id.edtNama);
        edtJurusan=findViewById(R.id.edtJurusan);
        actionSimpan=findViewById(R.id.actionSimpan);
        actionSimpan.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        ContentValues contentValues=new ContentValues();
        contentValues.put("nim",Integer.parseInt(edtNim.getText().toString()));
        contentValues.put("nama",edtNama.getText().toString());
        contentValues.put("jurusan",edtJurusan.getText().toString());


        database= new DatabaseMahasiswa(this).getWritableDatabase();
        long insert= database.insert("tb_mahasiswa",null,contentValues);
//        database.delete("tb_mahasiswa","nim=? or nama=?",new String[]{"123456","udin"});
//        database.update("tb_mahasiswa",contentValues,"nim=? or nama=?",new String[]{"123456","udin"});

        if (insert!=-1){
            Toast.makeText(this,"Simpan berhasil",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Simpan gagal",Toast.LENGTH_SHORT).show();
        }

    }
}
