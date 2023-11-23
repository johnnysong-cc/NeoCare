package com.gokulraj.neocare.views

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.R
import com.gokulraj.neocare.database.User
import com.gokulraj.neocare.database.EmergencyTechnicianEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.system.exitProcess

class EmtRegistrationLoginActivity : AppCompatActivity(), View.OnFocusChangeListener {

    private lateinit var mBinding: ActivityEmtRegistrationLoginBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private val userType: String = "EMT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityEmtRegistrationLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        mBinding.emailAddressEt.onFocusChangeListener = this
        mBinding.passwordEt.onFocusChangeListener = this

        mBinding.btnLogin.setOnClickListener {
            val email = mBinding.emailAddressEt.text.toString()
            val password = mBinding.passwordEt.text.toString()

            if (validateEmail() && validatePassword()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this@EmtRegistrationLoginActivity, "All fields are mandatory.", Toast.LENGTH_SHORT).show()
            }
        }

        mBinding.btnRegister.setOnClickListener {
            // Handle EMT registration here
            // To create a separate registration process for EMTs
            // and store the registration details in the Firebase Realtime Database


            val fullName = mBinding.fullNameEt.text.toString()
            val email = mBinding.emailAddressEt.text.toString()
            val password = mBinding.passwordEt.text.toString()

            // Implement EMT registration logic
            registerUser(fullName, email, password)
            // After successful registration, redirect EMTs to the main app screen
        }
    }

    private fun loginUser(email: String, password: String) {
        // Implement the login logic specific to EMTs
        // To check EMT credentials and navigate to the EMT-specific dashboard
        // Similar to the registration process, you can validate EMT credentials
        // and handle the login process.


        databaseReference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object :
            ValueEventListener {
           override fun onDataChange(snapshot: DataSnapshot) {
               if (snapshot.exists()) {
                   for (userSnapshot in snapshot.children) {
                       val userData = userSnapshot.getValue(User::class.java)
                       if (userData != null && userData.password == password) {
                           val userType = userData.userType
                           if (userType == "EMT") {
                               // EMT login successful, navigate to EMT dashboard
                               startActivity(Intent(this@EmtRegistrationLoginActivity, EmtDashboardActivity::class.java))
                               finish()
                           }
                       }
                   }
               }
               Toast.makeText(this@EmtRegistrationLoginActivity, "Login Failed! Please check your credentials.", Toast.LENGTH_SHORT).show()
           }

           override fun onCancelled(error: DatabaseError) {
               Toast.makeText(this@EmtRegistrationLoginActivity, "Database Error: ${error.message}", Toast.LENGTH_SHORT).show()
           }
        })
    }

    private fun registerUser(fullName: String, email: String, password: String) {
        val user = User(fullName, email, password, userType)
        databaseReference.child(email.replace(".", "")).setValue(user)
        startActivity(Intent(this@EmtRegistrationLoginActivity, EmtDashboardActivity::class.java))
        finish()
    }

    private fun validateEmail(): Boolean {
        // Implement email validation logic specific to EMT registration
        // To check if the email format is valid, if it's already registered, etc.
        // Return true if email is valid, false otherwise.
        val email = mBinding.emailAddressEt.text.toString().trim()
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validatePassword(): Boolean {
        // Implement password validation logic specific to EMT registration
        // To check the password strength, length, etc.
        // Return true if the password is valid, false otherwise.
        val password = mBinding.passwordEt.text.toString().trim()
        return password.isNotEmpty() && password.length >= 6
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        // Implement the onFocusChange logic for EMT registration fields
        // To validate fields as the user interacts with them.
    }

    override fun onBackPressed() {
        // Handle the back button behavior for EMT registration/login
        // To navigate back to the login screen or exit the app.
    }
}

