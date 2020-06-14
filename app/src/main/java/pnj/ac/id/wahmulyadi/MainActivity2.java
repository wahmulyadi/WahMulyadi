package pnj.ac.id.wahmulyadi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    Button actionPindah, actionBukaBrowser;


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Pindah:
                Intent intent= new Intent(MainActivity2.this, HalamaUtama.class);
                intent.putExtra("nama","Wahyu Mulyadi");
                intent.putExtra("umur",20);
                intent.putExtra("status",false);
                startActivity(intent);
                finish();
            case R.id.BukaBrowser:
                Intent browser = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.wahmulyadi.my.id"));
                startActivity(browser);
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("Kondisi","onCreate");
        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("LOADING.....");
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        },3000);
        actionPindah = findViewById(R.id.Pindah);
        actionBukaBrowser = findViewById(R.id.BukaBrowser);
        actionPindah.setOnClickListener(this);
        actionBukaBrowser.setOnClickListener(this);
//     actionPindah.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View v) {
//
//         }
//     });
        actionPindah.setOnClickListener(fungsiPindah);
    }

    View.OnClickListener fungsiPindah = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(MainActivity2.this, HalamaUtama.class);
            intent.putExtra("nama","Wahyu Mulyadi");
            intent.putExtra("umur",20);
            intent.putExtra("status",false);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Kondisi","onStrart");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Kondisi","onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Kondisi","onStop");

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("apakah anda ingin keluuar?");
        alert.setTitle("KELUAR");
        alert.setIcon(android.R.drawable.ic_dialog_alert);
        alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity2.super.onBackPressed();
            }
        });
        alert.setNegativeButton("TIdak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Kondisi","onResume");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Kondisi","onDestroy");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("Kondisi","onRestart");
        Toast.makeText(this,"selamat datang masbro!",Toast.LENGTH_SHORT).show();

    }
}
