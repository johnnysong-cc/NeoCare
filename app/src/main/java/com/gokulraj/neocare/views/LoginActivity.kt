package com.gokulraj.neocare.views

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.gokulraj.neocare.R
import com.gokulraj.neocare.database.User
import com.gokulraj.neocare.databinding.ActivityLoginBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        /*
        binding.requestButton.setOnClickListener {
            // Handle the emergency services request here
            // You can start an emergency service activity, make a call, or perform any other action as needed.
            // For this example, we'll open the phone dialer to call an emergency number (e.g., 911).
            val phoneNumber = "tel:911"
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))
            startActivity(intent)
        }

         */

        binding.requestImageView.setOnClickListener {
            // Handle the emergency services request here
            // You can start an emergency service activity, make a call, or perform any other action as needed.
            // For this example, we'll open the phone dialer to call an emergency number (e.g., 911).
            val phoneNumber = "tel:911"
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))
            startActivity(intent)
        }


        binding.btnLogin.setOnClickListener{
            val email = binding.emailAddressEt.text.toString()
            val password =  binding.passwordEt.text.toString()

            if (validateEmail() && validatePassword()){
                loginUser(email, password)
            } else {
                Toast.makeText(this@LoginActivity, "All fields are mandatory.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.registerRedirect.setOnClickListener{
            startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
            finish()
        }
    }

    private fun validateEmail(): Boolean {
        var errorMsg: String? = null
        val value = binding.emailAddressEt.text.toString()

        if(value.isEmpty()) {
            errorMsg = "Email is required."
        } else if(!Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            errorMsg = "Email address is invalid."
        }

        if (errorMsg != null){
            binding.emailAddressTil.apply {
                isErrorEnabled = true
                error = errorMsg
            }
        }

        return errorMsg == null
    }

    private fun validatePassword(): Boolean {
        var errorMsg: String? = null
        val value = binding.passwordEt.text.toString()

        if(value.isEmpty()) {
            errorMsg = "Password is required."
        } else if(value.length < 5){
            errorMsg = "Password must be at least 5 characters long."
        }

        if (errorMsg != null){
            binding.passwordTil.apply {
                isErrorEnabled = true
                error = errorMsg
            }
        }

        return errorMsg == null
    }

    private fun loginUser(email: String, password: String){
        databaseReference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val userData = userSnapshot.getValue(User::class.java)

                        if (userData != null && userData.password == password){
                            Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                            return
                        }
                    }
                }
                Toast.makeText(this@LoginActivity, "Login Failed! Please check your credentials.", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity, "Database Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Exit App")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") { _, _ ->
                // If the user clicks "Yes", exit the app
                finishAffinity()
                exitProcess(0)
            }
            .setNegativeButton("No", null)
            .show()

    }
}