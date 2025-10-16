package com.shenzhen.housing;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements 
        BottomNavigationView.OnNavigationItemSelectedListener,
        NavigationView.OnNavigationItemSelectedListener {

    private MaterialToolbar toolbar;
    private BottomNavigationView bottomNavigation;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupToolbar();
        setupBottomNavigation();
        setupDrawer();
        setupClickListeners();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24);
        }
    }

    private void setupBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    private void setupDrawer() {
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    private void setupClickListeners() {
        // 快速查询按钮
        MaterialButton btnQuickSearch = findViewById(R.id.btnQuickSearch);
        if (btnQuickSearch != null) {
            btnQuickSearch.setOnClickListener(v -> {
                Toast.makeText(this, "快速查询功能开发中...", Toast.LENGTH_SHORT).show();
            });
        }

        // 政策查询按钮
        MaterialButton btnPolicyQuery = findViewById(R.id.btnPolicyQuery);
        if (btnPolicyQuery != null) {
            btnPolicyQuery.setOnClickListener(v -> {
                Toast.makeText(this, "政策查询功能开发中...", Toast.LENGTH_SHORT).show();
            });
        }

        // 申请指南按钮
        MaterialButton btnApplicationGuide = findViewById(R.id.btnApplicationGuide);
        if (btnApplicationGuide != null) {
            btnApplicationGuide.setOnClickListener(v -> {
                Toast.makeText(this, "申请指南功能开发中...", Toast.LENGTH_SHORT).show();
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout != null) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        
        if (itemId == R.id.nav_home) {
            Toast.makeText(this, "首页", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.nav_search) {
            Toast.makeText(this, "查询", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.nav_policy) {
            Toast.makeText(this, "政策", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.nav_profile) {
            Toast.makeText(this, "我的", Toast.LENGTH_SHORT).show();
        }
        
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        
        return true;
    }
}
