package pnj.ac.id.wahmulyadi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import pnj.ac.id.wahmulyadi.fragment.DetailProfileFragment;

public class FragmentActivityDetail extends AppCompatActivity {
    DetailProfileFragment detailProfileFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_detail);
        Log.e("activity","Detail");
        Bundle extras =getIntent().getExtras();
        detailProfileFragment=new DetailProfileFragment();
        if(extras!=null){
            String nim =extras.getString("nim","");
            String nama = extras.getString("nama","");
            String jurusan = extras.getString("jurusan","");
            Bundle data= new Bundle();
            data.putString("nim",nim);
            data.putString("nama", nama);
            data.putString("jurusan",jurusan);

            detailProfileFragment.setArguments(data);
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.containerLayout, detailProfileFragment);
        ft.commit();

    }
}
