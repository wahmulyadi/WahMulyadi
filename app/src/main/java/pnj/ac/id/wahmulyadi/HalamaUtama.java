package pnj.ac.id.wahmulyadi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HalamaUtama extends AppCompatActivity {
Button actionbackhome;
TextView txtnama,txtumur,txtstatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halama_utama);
        actionbackhome=findViewById(R.id.backhome);
        txtnama=findViewById(R.id.txtnama);
        txtumur=findViewById(R.id.txtumur);
        txtstatus=findViewById(R.id.txtstatus);
        actionbackhome.setOnClickListener(fungsibackhome );

        Bundle extras= getIntent().getExtras();
        if ((extras !=null)){
            txtnama.setText("Nama:"+extras.getString("nama",""));
            txtumur.setText("Umur:"+extras.getInt("umur",0));
            String status = extras.getBoolean("status",false)?"Menikah":"Lajang";
            txtstatus.setText("Status:"+status);
        }
    }

    View.OnClickListener fungsibackhome = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(HalamaUtama.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
