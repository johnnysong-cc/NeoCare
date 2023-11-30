package com.gokulraj.neocare.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.database.EmergencyTechnician
import com.gokulraj.neocare.databinding.ActivityEmtLoginBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EmtLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmtLoginBinding
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.reference.child("emergency_technicians")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmtLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            loginEmt()
        }

        binding.registerLink.setOnClickListener {
            startActivity(Intent(this, EmtRegistrationActivity:: class.java))
            finish()
        }

        binding.forgotPasswordLink.setOnClickListener {
            startActivity(Intent(this, ForgetPasswordActivity::class.java))
            finish()
        }

    }

    private fun loginEmt() {
        // Implement the login logic specific to EMTs
        // To check EMT credentials and navigate to the EMT-specific dashboard
        // Similar to the registration process, you can validate EMT credentials
        // and handle the login process.

        val email = binding.emailAddressEt.text.toString().trim()
        val password = binding.passwordEt.text.toString().trim()

        databaseReference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val userData = userSnapshot.getValue(EmergencyTechnician::class.java)
                        if (userData != null && userData.password == password) {
                            val userType = userData.userType
                            if (userType == "EMT") {
                                // EMT login successful, navigate to EMT dashboard
                                startActivity(Intent(this@EmtLoginActivity, EmtDashboardActivity::class.java))
                                finish()
                            }
                        }
                    }
                }
                Toast.makeText(this@EmtLoginActivity, "Login Failed! Please check your credentials.", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@EmtLoginActivity, "Database Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onBackPressed() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}
