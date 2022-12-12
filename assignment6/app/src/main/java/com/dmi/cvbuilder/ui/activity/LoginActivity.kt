package com.dmi.cvbuilder.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dmi.cvbuilder.domain.User
import com.dmi.cvbuilder.utils.AppUtils
import cvbuilder.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPref: SharedPreferences
    var userList= arrayListOf<User>(
        User("test","test","test@miu.com","password"),
        User("Meron","Gessesse","mgessesse@miu.edu","password"),
         User("Beti","Kebede","bkebede@miu.edu","password"),
         User("Yordi","Tekle","ytekle@miu.edu","password")
    )
    val users= ArrayList<User>(userList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        sharedPref = AppUtils.setPref(this)
        val intent = getIntent()
        val user = intent.getSerializableExtra("user") as? User
        if(user !=null)
           users.add(user)
        loginButton.setOnClickListener{login()}

    }

 private fun login(){
     if(usernameEdt.text.isEmpty() || passwordEdt.text.isEmpty()){
         if(usernameEdt.text.isEmpty()){
             usernameEdt.requestFocus()
         }
         else{
             passwordEdt.requestFocus()
         }
         Toast.makeText(this, "Please enter all required informatiom", Toast.LENGTH_LONG).show()
         return
     }
     val user = users.find { info -> info.getUserName().equals(usernameEdt.text.toString(), true)
             && info.getpassword().equals(passwordEdt.text.toString(),true) }
     if(user !=null){
     val intent = Intent(this, MainActivity::class.java)
         intent.putExtra("username",user.getUserName())
         sharedPref.edit().putString("loggedInUser",user.getUserName()).apply()
       startActivity(intent)
     }
     else{
         Toast.makeText(this,"Invalid username or password", Toast.LENGTH_LONG).show()
     }
 }

}