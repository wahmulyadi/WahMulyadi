package pnj.ac.id.wahmulyadi.webservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pnj.ac.id.wahmulyadi.R;
import pnj.ac.id.wahmulyadi.adapter.AdapterPegawai;
import pnj.ac.id.wahmulyadi.model.ModelPegawai;
import pnj.ac.id.wahmulyadi.utils.Config;

public class mainwebservice extends AppCompatActivity {
ListView listView;
AdapterPegawai adapterPegawai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainwebservice);

        listView=findViewById(R.id.listviewwebservice);
        adapterPegawai=new AdapterPegawai(this,R.layout.item_webservice);
        listView.setAdapter(adapterPegawai);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ModelPegawai modelPegawai=(ModelPegawai) parent.getAdapter().getItem(position);

                Intent intent = new Intent(mainwebservice.this,Detailwebservice.class);
                intent.putExtra("id",modelPegawai.getId());
                startActivity(intent);
            }
        });
listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        final ModelPegawai modelPegawai=(ModelPegawai) parent.getAdapter().getItem(position);
        new AlertDialog.Builder(mainwebservice.this)
                .setIcon(android.R.drawable.ic_delete).setTitle("Yakin Hapus?")
                .setMessage("Apakah yakin untuk menghapus data pegawai?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete(adapterPegawai.getItem(position).getId());
            }
        }).setNegativeButton("Tidak",null).show();


        return true;
    }
});

    }

    private void delete(final String id) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Config._DELETE_PEGAWAI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(mainwebservice.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            if (jsonObject.getString("status").equals("OK")) {
                                onResume();
                            }

                        } catch (JSONException e) {
                            Toast.makeText(mainwebservice.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mainwebservice.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> parameter= new HashMap<>();
                parameter.put("id",id);
                return parameter;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu_web,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menu_pegawai){
            Intent intent = new Intent(mainwebservice.this,tambahpegawai.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        RequestQueue queue= Volley.newRequestQueue(this);
//        String url="http://192.168.43.18/tampilsemuapgw.php";
        StringRequest request= new StringRequest(Request.Method.GET, Config._LIST_PEGAWAI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("responses:", "" + response);

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("pegawai");
                    ArrayList<ModelPegawai> datas=new ArrayList<>();

                    for (int i=0; i<jsonArray.length();i++){
                        JSONObject item= jsonArray.getJSONObject(i);

                        ModelPegawai modelPegawai= new ModelPegawai();
                        modelPegawai.setId(item.getString("id"));
                        modelPegawai.setNama(item.getString("name"));
                        modelPegawai.setPosition(item.getString("position"));
                        modelPegawai.setGaji(item.getString("gaji"));
                        datas.add(modelPegawai);
                    }
                    adapterPegawai.clear();
                    adapterPegawai.addAll(datas);
                    adapterPegawai.notifyDataSetChanged();




                }catch (JSONException ex){
                    Log.e("responses:", "" + ex.getMessage());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("respon:",""+error.getMessage());
            }
        });
        queue.add(request);
    }


}

