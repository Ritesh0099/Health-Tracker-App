package com.ritesh.healthtrackerapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nav = findViewById(R.id.bottom_navigation);
        loadFragment(new HomeFragment());

        nav.setOnItemSelectedListener(item -> {
            Fragment selected = null;

            int id = item.getItemId();
            if (id == R.id.nav_home) {
                selected = new HomeFragment();
                Toast.makeText(this, "Home selected", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_log) {
                selected = new LogFragment();
                Toast.makeText(this, "Log selected", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_profile) {
                selected = new ProfileFragment();
                Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show();
            }

            if (selected != null) {
                loadFragment(selected);
                return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
