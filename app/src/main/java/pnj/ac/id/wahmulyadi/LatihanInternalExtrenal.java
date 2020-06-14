package pnj.ac.id.wahmulyadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LatihanInternalExtrenal extends AppCompatActivity implements View.OnClickListener{
    Button internalStorage,eksternalStorage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latihan_internal_extrenal);
        internalStorage = findViewById(R.id.internalStorage);
        eksternalStorage = findViewById(R.id.externalStorage);
        internalStorage.setOnClickListener(this);
        eksternalStorage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.internalStorage:
                Intent internal = new Intent(LatihanInternalExtrenal.this, InternalActivity.class);
                startActivity(internal);
                break;
            case R.id.externalStorage:
                Intent eksternal = new Intent(LatihanInternalExtrenal.this, EksternalActivity.class);
                startActivity(eksternal);
                break;
        }

    }

}
