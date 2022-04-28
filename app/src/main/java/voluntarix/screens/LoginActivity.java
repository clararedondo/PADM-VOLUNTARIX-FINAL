package voluntarix.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button bLogin;
    EditText etUsername, etPassword;
    TextView tvRegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                User user = new User(etUsername.getText().toString(), etPassword.getText().toString()); //Comprobar login en servidor, mostrar error si devuelve fallo
                Intent result = new Intent().putExtra(MainActivity.USER_MESSAGE_KEY, user);
                setResult(RESULT_OK, result);
                finish();
                break;

            case R.id.tvRegisterLink:
                startActivity(new Intent(this,RegisterActivity.class));

                break;
        }
    }
}