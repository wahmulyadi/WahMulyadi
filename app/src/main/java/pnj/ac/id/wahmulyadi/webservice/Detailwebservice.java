package pnj.ac.id.wahmulyadi.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import pnj.ac.id.wahmulyadi.R;
import pnj.ac.id.wahmulyadi.utils.Config;

public class Detailwebservice extends AppCompatActivity {
EditText edtNamawb,edtGajiwb,edtJabatanwb;
Button actionUpdatewb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailwebservice);
        edtNamawb =findViewById(R.id.edtNamawb);
        edtGajiwb =findViewById(R.id.edtGajiwb);
        edtJabatanwb=findViewById(R.id.edtJabatanwb);
        actionUpdatewb=findViewById(R.id.actionUpdatewb);

        getData();

        actionUpdatewb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNamawb.getText().toString().length()>0&&
                    edtJabatanwb.getText().toString().length()>0&&
                        edtGajiwb.getText().toString().length()>0
                ){
                  Update();
                }
            }
        });
}
    void getData(){
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.GET,
                Config._DETAIL_PEGAWAI + "?id=" + getIntent().getExtras().getString("id", "0"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    try {
                        JSONObject jsonObject= new JSONObject(response);
                        edtNamawb.setText(jsonObject.getString("name"));
                        edtJabatanwb.setText(jsonObject.getString("position"));
                        edtGajiwb.setText(jsonObject.getString("salary"));
                    }catch (JSONException e){
                        Toast.makeText(Detailwebservice.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                    }
                }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Detailwebservice.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                        }
        });
        requestQueue.add(stringRequest);
    }

    void Update() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Config._UPDATE_PEGAWAI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(Detailwebservice.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            if (jsonObject.getString("status").equals("OK")) {
                                finish();
                            }

                        } catch (JSONException e) {
                            Toast.makeText(Detailwebservice.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Detailwebservice.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> parameter= new HashMap<>();
                parameter.put("id",getIntent().getExtras().getString("id","0"));
                parameter.put("name",edtNamawb.getText().toString());
                parameter.put("position",edtJabatanwb.getText().toString());
                parameter.put("salary",edtGajiwb.getText().toString());
                return parameter;
            }
        };
            requestQueue.add(stringRequest);
    }


}
