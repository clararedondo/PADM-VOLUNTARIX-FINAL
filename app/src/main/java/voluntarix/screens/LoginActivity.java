package voluntarix.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button bLogin;
    EditText etUsername, etPassword;
    TextView tvRegisterLink;
    DataBaseAccess db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DataBaseAccess(this);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.bLogin:
                User user = db.log_in(etUsername.getText().toString(), etPassword.getText().toString());
                if (user != null) {
                    Intent result = new Intent().putExtra(MainActivity.USER_MESSAGE_KEY, user);
                    setResult(RESULT_OK, result);
                    finish();
                }
                Toast toast = Toast.makeText(this, "Unknown user", Toast.LENGTH_LONG);
                toast.show();
                break;

            case R.id.tvRegisterLink:
                startActivity(new Intent(this,RegisterActivity.class));

                break;
        }
    }
}