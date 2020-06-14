package pnj.ac.id.wahmulyadi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class LatihanStorage extends AppCompatActivity implements View.OnClickListener {
public static  final  String FILENAME ="namafile.txt";
Button actionBacaFile, actionUbahFile,actionBuatFile,actionHapusFile;
TextView txtBaca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latihan_storage);
        actionBacaFile=findViewById(R.id.actionBacaFile);
        actionBuatFile=findViewById(R.id.actionBuatFIle);
        actionUbahFile=findViewById(R.id.actionUbahFIle);
        actionHapusFile=findViewById(R.id.actionHapusFile);
        txtBaca=findViewById(R.id.txtBaca);


        actionBacaFile.setOnClickListener(this);
        actionBuatFile.setOnClickListener(this);
        actionUbahFile.setOnClickListener(this);
        actionHapusFile.setOnClickListener(this);
    }


    void actionBuatFile(){
            String isi="isi file txt";
        File file= new File(getFilesDir(),FILENAME);
        FileOutputStream outputStream=null;
        try {
            file.createNewFile();
            outputStream= new FileOutputStream(file,true);
                    outputStream.write(isi.getBytes());
                    outputStream.flush();
                    outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    void actionUbahFile(){
        String ubah="ubah isi text";
        File file=new File(getFilesDir(),FILENAME);
        FileOutputStream outputStream=null;
        try {
            file.createNewFile();
            outputStream=new FileOutputStream(file, false);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void actionBacaFile(){
        File sdcard =getFilesDir();
        File file= new File(sdcard,FILENAME);
        if (file.exists()){
            StringBuilder text= new StringBuilder();
            try {
                BufferedReader br = new BufferedReader( new FileReader(file));
                String line =br.readLine();
                while (line !=null){
                    text.append(line);
                    line=br.readLine();
                }
                br.close();
            }catch (IOException e){
                System.out.println("error"+ e.getMessage());
            }
            txtBaca.setText(text.toString());
        }
    }

    void actionHapusFile(){
        File file=new File(getFilesDir(),FILENAME);
        if (file.exists()){
            file.delete();
        }
    }
    @Override
    public void onClick(View v)  {
        jalankanPerintah(v.getId());
    }
    public void  jalankanPerintah(int id){
        switch (id){
            case R.id.actionBuatFIle:
            actionBuatFile();
            break;
            case  R.id.actionBacaFile:
                actionBacaFile();
                break;
            case R.id.actionUbahFIle:
                actionUbahFile();
                break;
            case R.id.actionHapusFile:
                actionHapusFile();
                break;
        }
    }
}
