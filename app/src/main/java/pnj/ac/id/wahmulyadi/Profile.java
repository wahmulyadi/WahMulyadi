package pnj.ac.id.wahmulyadi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
EditText nama, ttl,agama,alamat;
SharedPreferences preferences;

    private long back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        preferences=getSharedPreferences("login",MODE_PRIVATE);
        nama=findViewById(R.id.nama);
        ttl =findViewById(R.id.ttl);
        agama=findViewById(R.id.agama);
        alamat=findViewById(R.id.alamat);

        Bundle extras=getIntent().getExtras();
        if (extras!=null){
            nama.setText(extras.getString("Nama",""));
            ttl.setText(extras.getString("TTL",""));
            agama.setText(extras.getString("Agama",""));
            alamat.setText(extras.getString("Alamat",""));

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_mahasiswa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.m1){
            Intent intent =new Intent(this,Tambah_Mahasiswa.class);
            startActivity(intent);
//            finish();
        }else if (item.getItemId()==R.id.m2){
            Intent intent =new Intent(this,ListMahasiswa.class);
            startActivity(intent);

        }else{
            SharedPreferences.Editor editor=preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent =new Intent(this,login.class);
            startActivity(intent);
            finish();
        }


        return true;
    }

    public void onBackPressed() {
        if(back+2000>System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
            Toast.makeText(getBaseContext(),"Tekan tombol kembali satu kali lagi untuk keluar",Toast.LENGTH_SHORT ).show();
        }
        back=System.currentTimeMillis();
    }
}
