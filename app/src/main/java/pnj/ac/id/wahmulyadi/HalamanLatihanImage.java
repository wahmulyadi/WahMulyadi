package pnj.ac.id.wahmulyadi;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HalamanLatihanImage extends AppCompatActivity {
ImageButton actionImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_latihan_image);
        actionImage=findViewById(R.id.actionImage);
        registerForContextMenu(actionImage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menuz,menu);
        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.m1){
            Toast.makeText(this,"menu1",Toast.LENGTH_SHORT).show();
        }else if (item.getItemId()==R.id.m2){

            Toast.makeText(this,"menu2",Toast.LENGTH_SHORT).show();
        }else if (item.getItemId()==R.id.m3){
            Toast.makeText(this,"menu3",Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {


        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menuz,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.m1){
            Toast.makeText(this,"menu1",Toast.LENGTH_SHORT).show();
        }else if (item.getItemId()==R.id.m2){

            Toast.makeText(this,"menu2",Toast.LENGTH_SHORT).show();
        }else if (item.getItemId()==R.id.m3){
            Toast.makeText(this,"menu3",Toast.LENGTH_SHORT).show();
        }
return true;
    }
}
