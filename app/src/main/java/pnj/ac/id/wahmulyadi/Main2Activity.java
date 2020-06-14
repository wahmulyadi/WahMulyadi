package pnj.ac.id.wahmulyadi;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class Main2Activity extends AppCompatActivity implements  MessageListener  {
    private static final int PERMISSION_REQUEST_CODE = 200;
    Button actionLewatApps;
    Button actionSmsBackground;
    TextView txtMessage;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        MessageReceiver.bindListener(this);
        txtMessage = findViewById(R.id.txtMessage);
        actionSmsBackground = findViewById(R.id.actionSmsBackground);
        actionLewatApps = findViewById(R.id.actionLewatApps);
        actionSmsBackground.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Main2Activity.this.sendSmsByManager();
            }
        });
        actionLewatApps.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Main2Activity.this.sendSmsBySIntent();
            }
        });
    }
    @Override
    public void messageReceived(String message) {
        Toast.makeText(this, "New Message Received: " + message, Toast.LENGTH_SHORT).show();
        this.txtMessage.setText(message);
    }

    public void sendSmsBySIntent() {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:082211560933"));
        intent.putExtra("sms_body", "Coba SMS");
        startActivity(intent);
    }

    public void sendSmsByManager() {
        try {
            SmsManager.getDefault().sendTextMessage("082211560933", null, "Hallo coba sms", null, null);
            Toast.makeText(getApplicationContext(), "SMS Berhasil Dikirim!", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Pengiriman SMS Gagal...", Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        if (checkPermission()) {
            Toast.makeText(this, "Permission already granted.", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
        }
    }

//    public void messageReceived(String message) {
//        Log.e("SMS Masuk", message);
//        Toast.makeText(this,"New Message :"+ message, Toast.LENGTH_SHORT).show();
//        this.txtMessage.setText(message);
//    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.RECEIVE_SMS") == 0 && ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.SEND_SMS") == 0;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.RECEIVE_SMS", "android.permission.SEND_SMS"}, PERMISSION_REQUEST_CODE);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.length > 0) {
            boolean sendsms = true;
            boolean receivesms = grantResults[0] == 0;
            if (grantResults[1] != 0) {
                sendsms = false;
            }
            if (receivesms && sendsms) {
                Toast.makeText(this, "Permission Granted, Now you can access sms and send sms.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Denied, You cannot access sms and send sms..", Toast.LENGTH_SHORT).show();
                if (VERSION.SDK_INT >= 23 && shouldShowRequestPermissionRationale("android.permission.RECEIVE_SMS")) {
                    showMessageOKCancel("You need to allow access to both the permissions", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (VERSION.SDK_INT >= 23) {
                                Main2Activity.this.requestPermissions(new String[]{"android.permission.RECEIVE_SMS", "android.permission.SEND_SMS"}, Main2Activity.PERMISSION_REQUEST_CODE);
                            }
                        }
                    });
                }
            }
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new Builder(this).setMessage(message).setPositiveButton("OK", okListener).setNegativeButton("Cancel", null).create().show();
    }
}
