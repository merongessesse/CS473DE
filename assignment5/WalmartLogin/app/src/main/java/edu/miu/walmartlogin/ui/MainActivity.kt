package edu.miu.walmartlogin.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.miu.walmartlogin.data.User
import edu.miu.walmartlogin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val users = mutableListOf(
        User("Meron", "Gessesse", "meri@gmail.com", "password1"),
        User("Dagim", "Gessesse", "dagi@gmail.com", "password2"),
        User("Saron", "Gessesse", "sari@gmail.com", "password3"),
        User("Biniyam", "Haile", "bini@gmail.com", "password4"),
        User("Sosina", "Haile", "sosi@gmail.com", "password5")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user: User? = intent.getSerializableExtra("user") as User?
        user?.let { users.add(it) }
    }

    fun onSign(view: View) {
        val email = binding.etEmail.text.toString().trim()
        val pass = binding.etPassword.text.toString().trim()
        if(email.isEmpty()){
            Toast.makeText(applicationContext,"Please enter your email", Toast.LENGTH_LONG).show()
            return
        }
        if(pass.isEmpty()){
            Toast.makeText(applicationContext,"Please enter your password", Toast.LENGTH_LONG).show()
            return
        }
        var isUserFound = false
        for(user in users){
            if(user.username == email && user.password == pass){
                isUserFound = true
                openShoppingCategoryActivity(user)
            }
        }
        if(!isUserFound){
            Toast.makeText(applicationContext,"Username and password not found.", Toast.LENGTH_LONG).show()
        }
    }

    private fun openShoppingCategoryActivity(user: User){
        val intent = Intent(this, ShoppingCategoryActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    fun onSignUp(view: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun onForgetPasswordClick(view: View) {
        val email = binding.etEmail.text.toString().trim()
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, email)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Forgot Password from MDP Course")
        intent.putExtra(Intent.EXTRA_TEXT, "Forgot Password from MDP Course, " +
                "for resetting use this code: 123456.");
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

}