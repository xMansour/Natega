package com.mansourappdevelopment.androidapp.natega.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mansourappdevelopment.androidapp.natega.R;

public class MainActivity extends AppCompatActivity {
    private static final String SERVER_NUMBER = "01096413974";
    private static final int REQUEST_CODE = 59;
    private EditText txtID;
    private Button btnSendMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtID = (EditText) findViewById(R.id.txtID);
        btnSendMessage = (Button) findViewById(R.id.btnSendMessage);

        requestPermission();

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //requestPermission();
                String academicNumber = txtID.getText().toString();
                if (academicNumber.equals("") || academicNumber.length() != 7)
                    txtID.setError("Enter a valid academic number");
                else
                    sendMessage(academicNumber);
            }
        });


    }

    private void sendMessage(String academicNumber) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(SERVER_NUMBER, null, academicNumber, null, null);
            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
        } catch (SecurityException e) {
            //Toast.makeText(this, "Permission needed", Toast.LENGTH_SHORT).show();
            requestPermission();
        }

    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_CODE);

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_CODE);

            }
        } else {

        }


    }

}
