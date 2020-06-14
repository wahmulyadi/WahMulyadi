package pnj.ac.id.wahmulyadi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
Button btn_login;
EditText txt_pwd, txt_nim;
SharedPreferences preferences;
SharedPreferences.Editor editor;
private long back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences=getSharedPreferences("login",MODE_PRIVATE);
        btn_login =findViewById(R.id.btn_login);
        txt_pwd=findViewById(R.id.txt_pwd);
        txt_nim=findViewById(R.id.txt_nim);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pwd= txt_pwd.getText().toString();
                String nim= txt_nim.getText().toString();
                if(nim.equals("4817071496")&& pwd.equals("4817071496")){

                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putBoolean("isLogin",true);
                    editor.commit();

                    Intent keprofile = new Intent(login.this,Profile.class);
                    keprofile.putExtra("Nama","Wahyu Mulyadi");
                    keprofile.putExtra("TTL","Bekasi,23-04-1999");
                    keprofile.putExtra("Agama","Islam");
                    keprofile.putExtra("Alamat","JL.Bojong Asri XV");
                    startActivity(keprofile);
                    finish();
                }
                else{
                    AlertDialog.Builder builder= new AlertDialog.Builder(login.this);
                    builder.setMessage("Username atau Password Salah1").setNegativeButton("Coba Lagi",null).create().show();
                }
            }
        });

    }

    @Override
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
