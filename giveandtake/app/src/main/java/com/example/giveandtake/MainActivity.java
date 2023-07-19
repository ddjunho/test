package com.example.giveandtake;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ChatFragment chatFragment;
    private MenuFragment menuFragment;
    private PlaceFragment placeFragment;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.chat_button:
                        setFragment(chatFragment);
                        return true;
                    case R.id.lesson_button:
                        setFragment(placeFragment);
                        return true;
                    case R.id.home_button:
                        // Show the main content in the MainActivity layout
                        setFragment(null);
                        return true;
                    case R.id.menu_button:
                        setFragment(menuFragment);
                        return true;
                }
                return false;
            }
        });


        chatFragment = new ChatFragment();
        menuFragment = new MenuFragment();
        placeFragment = new PlaceFragment();
        setFragment(null); // 초기에는 홈 프래그먼트는 사용하지 않으므로 null 전달
    }

    private void setFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment != null) {
            fragmentTransaction.replace(R.id.content_main, fragment);
        } else {
            // 홈 프래그먼트는 별도의 프래그먼트로 사용하지 않으므로 기본 콘텐츠로 대체
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        fragmentTransaction.commit();
    }
}
