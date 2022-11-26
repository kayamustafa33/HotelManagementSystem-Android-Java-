package com.mustafa.hotelmanagementsystem.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.mustafa.hotelmanagementsystem.Fragments.AllCustomersFragment;
import com.mustafa.hotelmanagementsystem.Fragments.AllReservaionsFragment;
import com.mustafa.hotelmanagementsystem.Fragments.HomeFragment;
import com.mustafa.hotelmanagementsystem.Fragments.ProfileFragment;
import com.mustafa.hotelmanagementsystem.Fragments.RegisterCustomerFragment;
import com.mustafa.hotelmanagementsystem.Fragments.ReservationsFragment;
import com.mustafa.hotelmanagementsystem.Fragments.RoomsFragment;
import com.mustafa.hotelmanagementsystem.R;
import com.mustafa.hotelmanagementsystem.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    MaterialToolbar toolbar;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    SQLiteDatabase sqLiteDatabase;
    TextView companyName, userEmail;
    String currentNompanyName,currentUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        try {
            sqLiteDatabase = this.openOrCreateDatabase("HotelSystem",MODE_PRIVATE,null);
        }catch (SQLException e){
            e.printStackTrace();
        }



        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(item -> {
            binding.drawerLayout.openDrawer(GravityCompat.START);
        });

        binding.navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.profile:
                    binding.drawerLayout.close();
                    ReplaceFragment(new ProfileFragment());
                    break;
                case R.id.allReservations:
                    binding.drawerLayout.close();
                    ReplaceFragment(new AllReservaionsFragment());
                    break;
                case R.id.exit:
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    loginPrefsEditor.putString("username", "");
                    loginPrefsEditor.putString("password", "");
                    loginPrefsEditor.commit();
                    finish();
                    break;
            }
            return false;
        });

        ReplaceFragment(new HomeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.home:
                    ReplaceFragment(new HomeFragment());
                    break;
                case R.id.rooms:
                    ReplaceFragment(new RoomsFragment());
                    break;
                case R.id.registerCustomer:
                    ReplaceFragment(new RegisterCustomerFragment());
                    break;
                case R.id.allCustomers:
                    ReplaceFragment(new AllCustomersFragment());
                    break;
                case R.id.rezervations:
                    ReplaceFragment(new ReservationsFragment());
                    break;
            }

            return true;
        });

        updateNavHeader();
    }

    public void ReplaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    public void updateNavHeader(){
        NavigationView navigationView = findViewById(R.id.navigationView);
        View viewHeader = navigationView.getHeaderView(0);

        companyName = viewHeader.findViewById(R.id.companyNameTextHeader);
        userEmail = viewHeader.findViewById(R.id.userEmailTextHeader);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentNompanyName = extras.getString("companyName");
            currentUserEmail = extras.getString("userEmail");

        }

        if(currentNompanyName != null && currentUserEmail != null){
            companyName.setText(currentNompanyName);
            userEmail.setText(currentUserEmail);
        }
    }
}