package voluntarix.screens;


import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.os.Bundle;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginActivity loginActivity = new LoginActivity(); //no hace nada creo? 
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

    }
    HomeFragment homeFragment = new HomeFragment();
    SearchFragment searchFragment = new SearchFragment();
    MessagesFragment messagesFragment = new MessagesFragment();
    ProfileFragment profileFragment = new ProfileFragment();





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.home, homeFragment).commit();
                return true;

            case R.id.search:
                getSupportFragmentManager().beginTransaction().replace(R.id.search, searchFragment).commit();
                return true;
            case R.id.messages:
                getSupportFragmentManager().beginTransaction().replace(R.id.messages, messagesFragment).commit();
                return true;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.profile, profileFragment).commit();
                return true;
        }
        return false;
    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragment).commit();

    }
}