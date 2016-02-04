package devas.com.whatchaap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;

public class Login extends AppCompatActivity implements View.OnClickListener {

    AppCompatEditText passwd, username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserDetails ud = new UserDetails(this);
        if(ud.isUserSet() == 1) {
           // ud.setDetails(u, p, this);
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
        username = (AppCompatEditText) findViewById(R.id.username);
        passwd  = (AppCompatEditText) findViewById(R.id.pass);
        AppCompatButton log = (AppCompatButton) findViewById(R.id.login);

        log.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String u = username.getText().toString().trim();
        String p = passwd.getText().toString().trim();
        Log.d(u, p);

        if((!u.equals("") && !p.equals(""))) {
            UserDetails ud = new UserDetails(this);
            ud.setDetails(u, p , this);
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();

        }
    }
}
