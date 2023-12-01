package com.gokulraj.neocare.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gokulraj.neocare.databinding.ActivityForgetpasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

/**
 *
 * Apply to all entities
 *
 * --------------------------
 * potential steps to address this issue:
 *
 * Enable reCAPTCHA in Firebase Console:
 *
 * Go to your Firebase Console.
 * Select your project.
 * Navigate to the Authentication section.
 * Under the Sign-in method tab, find the section about password reset.
 * Make sure that the reCAPTCHA setting is correctly configured. If you are using the Firebase Authentication UI, it should handle reCAPTCHA automatically.
 * Update Firebase Dependencies:
 *
 * Ensure that your project has the latest Firebase Authentication SDK. Outdated SDKs may lead to unexpected issues.
 * Testing on a Real Device:
 *
 * Sometimes, Firebase reCAPTCHA verification can have issues when tested on emulators. Try running your app on a real device.
 * Implementing Custom reCAPTCHA:
 *
 * If you are not using Firebase's built-in UI for authentication and implementing your own custom authentication flow, you might need to handle reCAPTCHA verification manually. This involves embedding a reCAPTCHA verifier in your password reset flow.
 * Check Network Restrictions:
 *
 * Some network configurations or VPNs might interfere with reCAPTCHA. Ensure that there's no such interference.
 * Examine Code for Email Sending:
 *
 * Ensure that your code for sending the password reset email is correctly implemented as per Firebase documentation.
 * */
class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgetpasswordBinding
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val nodesToCheck = listOf("emergency_technicians", "users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetpasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnResetPassword.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            if (email.isNotEmpty()) {
                checkEmailAcrossNodes(email) { nodeFound ->
                    if (nodeFound != null) {
                        resetPassword(email)
                    } else {
                        Toast.makeText(this, "Email not found", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBackToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }


    }

    private fun checkEmailAcrossNodes(email: String, callback: (String?) -> Unit) {
        checkEmailInNode(email, nodesToCheck.iterator(), callback)
    }

    private fun checkEmailInNode(email: String, nodeIterator: Iterator<String>, callback: (String?) -> Unit) {
        if (nodeIterator.hasNext()) {
            val node = nodeIterator.next()
            val query = firebaseDatabase.reference.child(node).orderByChild("email").equalTo(email)
            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        callback(node)
                    } else {
                        checkEmailInNode(email, nodeIterator, callback)  // Check next node
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@ForgetPasswordActivity, "Error checking email: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            callback(null)  // Email not found in any node
        }
    }

    private fun resetPassword(email: String) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Reset link sent to your email", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Failed to send reset email", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onBackPressed() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
