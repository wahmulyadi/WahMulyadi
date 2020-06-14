package pnj.ac.id.wahmulyadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import pnj.ac.id.wahmulyadi.adapter.adapterMahasiswa;
import pnj.ac.id.wahmulyadi.db.DatabaseMahasiswa;
import pnj.ac.id.wahmulyadi.model.MahasiswaData;

public class ListMahasiswa extends AppCompatActivity {
    ListView listView;
    SQLiteDatabase database;
    pnj.ac.id.wahmulyadi.adapter.adapterMahasiswa adapterMahasiswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);
        listView=findViewById(R.id.listview);

        adapterMahasiswa =new adapterMahasiswa(this,R.layout.item_list_view);
        listView.setAdapter(adapterMahasiswa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.e("onItemClick", "Suksess");
                MahasiswaData data = (MahasiswaData) parent.getAdapter().getItem(position);
                Intent pindahHalaman = new Intent(ListMahasiswa.this,
                        FragmentActivityDetail.class);
                pindahHalaman.putExtra("nim",data.getNim());
                pindahHalaman.putExtra("nama",data.getNama());
                pindahHalaman.putExtra("jurusan",data.getJurusan());
                startActivity(pindahHalaman);
            }
        });

        buatData();

    }
    void buatData(){
        ArrayList<MahasiswaData> data=new ArrayList<>();
        database = new DatabaseMahasiswa(this).getWritableDatabase();
        Cursor cursor= database.rawQuery("SELECT *FROM tb_mahasiswa",null);

        if (cursor.moveToFirst()){
            do {
                MahasiswaData itemMahasiswa =new MahasiswaData();
                itemMahasiswa.setNim(""+cursor.getInt(0));
                itemMahasiswa.setNama(cursor.getString(1));
                itemMahasiswa.setJurusan(cursor.getString(2));
                data.add(itemMahasiswa);
            }while (cursor.moveToNext());
        }
//
//        MahasiswaData mahasiswaData1= new MahasiswaData();
//        mahasiswaData1.setNim("123123");
//        mahasiswaData1.setNama("wwwww");
//        mahasiswaData1.setJurusan("TIK");
//
//        MahasiswaData mahasiswaData2= new MahasiswaData();
//        mahasiswaData2.setNim("123123");
//        mahasiswaData2.setNama("wwwww");
//        mahasiswaData2.setJurusan("TIK");
//
//        MahasiswaData mahasiswaData3= new MahasiswaData();
//        mahasiswaData3.setNim("123123");
//        mahasiswaData3.setNama("wwwww");
//        mahasiswaData3.setJurusan("TIK");
//
//        MahasiswaData mahasiswaData4= new MahasiswaData();
//        mahasiswaData4.setNim("123123");
//        mahasiswaData4.setNama("wwwww");
//        mahasiswaData4.setJurusan("TIK");
//
//        data.add(mahasiswaData1);
//        data.add(mahasiswaData1);
//        data.add(mahasiswaData1);
//        data.add(mahasiswaData1);
        database.close();
        adapterMahasiswa.addAll(data);
        adapterMahasiswa.notifyDataSetChanged();


    }

}
