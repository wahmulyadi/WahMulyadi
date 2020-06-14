package pnj.ac.id.wahmulyadi;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;

import pnj.ac.id.wahmulyadi.ui.gallery.GalleryFragment;
import pnj.ac.id.wahmulyadi.ui.home.HomeFragment;
import pnj.ac.id.wahmulyadi.ui.send.SendFragment;
import pnj.ac.id.wahmulyadi.ui.share.ShareFragment;
import pnj.ac.id.wahmulyadi.ui.slideshow.SlideshowFragment;
import pnj.ac.id.wahmulyadi.ui.tools.ToolsFragment;

public class DashboardActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.containerLayout, new HomeFragment());
        ft.commit();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        Toast.makeText(DashboardActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        ft.replace(R.id.containerLayout, new HomeFragment());
                        ft.commit();
                        break;
                    case R.id.nav_gallery:
                        ft.replace(R.id.containerLayout, new GalleryFragment());
                        ft.commit();
                        break;
                    case R.id.nav_slideshow:
                        ft.replace(R.id.containerLayout, new SlideshowFragment());
                        ft.commit();
                        break;
                    case R.id.nav_send:
                        ft.replace(R.id.containerLayout, new SendFragment());
                        ft.commit();
                        break;
                    case R.id.nav_tools:
                        ft.replace(R.id.containerLayout, new ToolsFragment());
                        ft.commit();
                        break;
                    case R.id.nav_share:
                        ft.replace(R.id.containerLayout, new ShareFragment());
                        ft.commit();
                        break;
                }
                drawer.closeDrawer(Gravity.LEFT);

                return false;

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }
}
