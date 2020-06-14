package pnj.ac.id.wahmulyadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class MainActivity extends AppCompatActivity {
private static int splashload=5000;
SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        preferences= getSharedPreferences("login",MODE_PRIVATE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (preferences.getBoolean("isLogin",false)){
                //Intent home= new Intent(MainActivity.this,Profile.class);
                    Intent keprofile = new Intent(MainActivity.this,Profile.class);
                    keprofile.putExtra("Nama","Wahyu Mulyadi");
                    keprofile.putExtra("TTL","Bekasi,23-04-1999");
                    keprofile.putExtra("Agama","Islam");
                    keprofile.putExtra("Alamat","JL.Bojong Asri XV");
                    startActivity(keprofile);
//                startActivity(home);
                finish();
            }else{
                    Intent home1= new Intent(MainActivity.this,login.class);
                    startActivity(home1);
                }
            }
        }, splashload);

        //
//
    }
}
