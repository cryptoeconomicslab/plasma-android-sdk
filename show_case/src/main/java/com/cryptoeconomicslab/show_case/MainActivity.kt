package com.cryptoeconomicslab.show_case

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.cryptoeconomicslab.show_case.fragment.DBFragment
import com.cryptoeconomicslab.show_case.fragment.HelloWorldFragment
import com.google.android.material.navigation.NavigationView

/**
 * Main Activity for show case demo
 */
class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)

        navigationView = findViewById<NavigationView>(R.id.nav_view).apply {
            setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_hello_world -> {
                        supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.main_container,
                                HelloWorldFragment()
                            )
                            .commit()
                    }

                    R.id.nav_db_sample -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_container, DBFragment())
                            .commit()
                    }
                }

                drawerLayout.closeDrawer(GravityCompat.START)

                true
            }
        }
    }
}
