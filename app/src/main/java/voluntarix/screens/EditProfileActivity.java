package voluntarix.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProfileActivity extends AppCompatActivity{

    private EditText name, last_name, location, tags, description;
    private Button update, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        name = (EditText) findViewById(R.id.etName);
        last_name = (EditText) findViewById(R.id.etLastName);
        location = (EditText) findViewById(R.id.etLocation);
        tags = (EditText) findViewById(R.id.etTags);
        description = (EditText) findViewById(R.id.etDescription);

        update = (Button) findViewById(R.id.bUpdateProfile);
        cancel = (Button) findViewById(R.id.bCancel);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_str, last_name_str, location_str, tags_str, description_str;
                Intent new_activity;

                UserLocalStore prefs = MainActivity.preferences;

                name_str = name.getText().toString();
                if (name_str != "") {
                    prefs.updateUserData(UserLocalStore.NAME, name_str);
                }
                last_name_str = last_name.getText().toString();
                if (last_name_str != "") {
                    prefs.updateUserData(UserLocalStore.LAST_NAME, last_name_str);
                }
                location_str = location.getText().toString();
                if (location_str != "") {
                    prefs.updateUserData(UserLocalStore.LOCATION, location_str);
                }
                tags_str = tags.getText().toString();
                if (tags_str != "") {
                    prefs.updateUserData(UserLocalStore.TAGS, name_str);
                }
                description_str = description.getText().toString();
                if (description_str != "") {
                    prefs.updateUserData(UserLocalStore.DESCRIPTION, description_str);
                }

                new_activity = new Intent(EditProfileActivity.this, MainActivity.class);
                startActivity(new_activity);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent new_activity = new Intent(EditProfileActivity.this, MainActivity.class);
                startActivity(new_activity);
            }
        });

    }
}