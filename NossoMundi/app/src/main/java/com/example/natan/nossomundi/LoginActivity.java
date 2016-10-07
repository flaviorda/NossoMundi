package com.example.natan.nossomundi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public boolean loginOk(String username , String password){
        return false;
    }

    public void loginCheck(View view){
        EditText mUser  = (EditText)findViewById(R.id.inputUsernameEditText);
        EditText mPassword   = (EditText)findViewById(R.id.inputPasswordEditText);
        if( loginOk(mUser.getText().toString() , mPassword.getText().toString())){
            Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            }
        }
        else {
                Context context = getApplicationContext();
                CharSequence text ="Usuário ou senha incorreto!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

    }
    //itent to a map location
        /*Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }*/
}
