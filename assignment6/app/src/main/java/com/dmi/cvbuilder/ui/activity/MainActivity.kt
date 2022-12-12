package com.dmi.cvbuilder.ui.activity





import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dmi.cvbuilder.utils.AppUtils
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import cvbuilder.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var loggedUser = AppUtils.getPref("loggedInUser")
        Toast.makeText(this,"Welcome $loggedUser",Toast.LENGTH_LONG).show()
//        val theme = sharedPref.getString(getString(R.string.saved_theme), "")
//        if(theme!=null) decideTheme(theme)

        val adapter = MyViewAdapter(supportFragmentManager,lifecycle)
        viewPager.adapter = adapter
        tabs.tabGravity = TabLayout.GRAVITY_FILL
        TabLayoutMediator(tabs,viewPager){tab,position->
            when(position){
                0->{
                    tab.text="Home"
//                    tab.setIcon(R.drawable.home)
                }
                1->{
                    tab.text="About Me"
//                    tab.setIcon(R.drawable.help)
                }
                2->{
                    tab.text="Experience"
//                    tab.setIcon(R.drawable.work)
                }
                3->{
                    tab.text = "Contact"
//                    tab.setIcon(R.drawable.contact)
                }
            }
        }.attach()


    }



}