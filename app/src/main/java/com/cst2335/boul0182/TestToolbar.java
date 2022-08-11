package com.cst2335.boul0182;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class TestToolbar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);

        Toolbar toolBar = (Toolbar)findViewById(R.id.toolbar);
       // setSupportActionBar(toolBar);

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolBar, R.string.Open, R.string.Close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected( MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.chatPage:
                Intent goToChatPage = new Intent(this, ChatRoomActivity.class);
                startActivity(goToChatPage);
                break;
            case R.id.backToProfile:
                Intent goToProfile = new Intent(this, ProfileActivity.class);
                startActivity(goToProfile);
                break;
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getTitle().equals("Choice1")){
            Toast.makeText(this, "You clicked item 1", Toast.LENGTH_LONG).show();
        } else if (item.getTitle().equals("Choice2")){
            Toast.makeText(this, "You clicked item 2", Toast.LENGTH_LONG).show();
        } else if (item.getTitle().equals("Choice3")){
            Toast.makeText(this, "You clicked item 3", Toast.LENGTH_LONG).show();
        } else if (item.getTitle().equals("Choice4")){
            Toast.makeText(this, "You clicked on the overflow menu", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}