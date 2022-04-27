package voluntarix.screens;


import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    public static final int LOG_IN_REQUEST = 1;

    public static final String USER_MESSAGE_KEY = "User";

    BottomNavigationView bottomNavigationView;
    UserLocalStore preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = new UserLocalStore(this);

        Intent login = new Intent(this, LoginActivity.class);
        startActivityForResult(login, LOG_IN_REQUEST);

        NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Fragment selected_fragment = null;

                switch (item.getItemId()) {
                    case R.id.home:
                        selected_fragment = new HomeFragment();
                        break;
                    case R.id.search:
                        selected_fragment = new SearchFragment();
                        break;
                    case R.id.messages:
                        selected_fragment = new MessagesFragment();
                        break;
                    case R.id.profile:
                        selected_fragment = new ProfileFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, selected_fragment).commit();
                return true;
            }
        };

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(navListener);
        bottomNavigationView.setSelectedItemId(R.id.home);

    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOG_IN_REQUEST:
                if (resultCode == RESULT_OK) {
                    preferences.storeUserData((User) data.getSerializableExtra(USER_MESSAGE_KEY));
                    preferences.setUserLoggedIn(true);
                }
                break;
        }
    }
}