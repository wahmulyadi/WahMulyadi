package pnj.ac.id.wahmulyadi.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import pnj.ac.id.wahmulyadi.R;
import pnj.ac.id.wahmulyadi.utils.Config;

public class tambahpegawai extends AppCompatActivity {
EditText edtaddNamawb, edtaddJabatanwb,edtaddGajiwb;
Button actionaddpegawaiwb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahpegawai);
        edtaddGajiwb=findViewById(R.id.edtaddGajiwb);
        edtaddNamawb=findViewById(R.id.edtaddNamawb);
        edtaddJabatanwb=findViewById(R.id.edtaddJabatanwb);
        actionaddpegawaiwb=findViewById(R.id.actionaddpegawaiwb);

        actionaddpegawaiwb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtaddNamawb.getText().toString().length()>0&&
                        edtaddJabatanwb.getText().toString().length()>0&&
                        edtaddGajiwb.getText().toString().length()>0
                ){
                    addPegawai();
                }
            }
        });


    }

    void addPegawai(){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST
                , Config._ADD_PEGAWAI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(tambahpegawai.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            if (jsonObject.getString("status").equals("OK")) {
                                finish();

                            }

                        } catch (JSONException e) {
                            Toast.makeText(tambahpegawai.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(tambahpegawai.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>parameter=new HashMap<>();
                parameter.put("id","");
                parameter.put("name",edtaddNamawb.getText().toString());
                parameter.put("position",edtaddJabatanwb.getText().toString());
                parameter.put("salary",edtaddGajiwb.getText().toString());
                return parameter;

            }
        };
        requestQueue.add(stringRequest);
    }
}
