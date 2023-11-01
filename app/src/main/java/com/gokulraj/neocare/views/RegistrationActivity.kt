package com.gokulraj.neocare.views

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.gokulraj.neocare.R
import com.gokulraj.neocare.database.User
import com.gokulraj.neocare.databinding.ActivityRegistrationBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.core.Context

class RegistrationActivity : AppCompatActivity(), View.OnFocusChangeListener {

    private lateinit var mBinding: ActivityRegistrationBinding
    //private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private var userType: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegistrationBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        //firebaseAuth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        mBinding.radioButtonPatient.onFocusChangeListener=this
        mBinding.radioButtonHealthcareProfessional.onFocusChangeListener=this
        mBinding.fullNameEt.onFocusChangeListener = this
        mBinding.healthCardNumberEt.onFocusChangeListener = this
        mBinding.emailAddressEt.onFocusChangeListener = this
        mBinding.passwordEt.onFocusChangeListener = this
        mBinding.termsCb.onFocusChangeListener = this

        mBinding.radioButtonPatient.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                userType = "Patient"
            }
        }

        mBinding.radioButtonHealthcareProfessional.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                userType = "Healthcare Professional"
            }
        }


        mBinding.btnRegister.setOnClickListener {
            val fullName = mBinding.fullNameEt.text.toString()
            val healthCard = mBinding.healthCardNumberEt.text.toString()
            val email = mBinding.emailAddressEt.text.toString()
            val password =  mBinding.passwordEt.text.toString()
            val isTermsAccepted = mBinding.termsCb.isSelected


            if (validateFullName() && validateHealthCardNumber() && validateEmail() && validatePassword()){
                signUpUser(fullName, healthCard, email, password, isTermsAccepted)
            }
        }

        mBinding.loginRedirect.setOnClickListener {
            startActivity(Intent(this@RegistrationActivity, LoginActivity::class.java))
            finish()
        }
    }

    private fun signUpUser(fullName: String, healthCard: String, email: String, password: String, isTermsAccepted: Boolean){
        databaseReference.orderByChild("fullName").equalTo(fullName).addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (!dataSnapshot.exists()){
                    val id = databaseReference.push().key
                    val UserData = User(id, userType,fullName, healthCard, email, password, isTermsAccepted)
                    databaseReference.child(id!!).setValue(UserData)

                    Toast.makeText(this@RegistrationActivity, "Signup Successful!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@RegistrationActivity, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@RegistrationActivity, "User already exists", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@RegistrationActivity, "Database Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun validateFullName(): Boolean {
        var errorMsg: String? = null
        val value: String = mBinding.fullNameEt.text.toString()

        if(value.isEmpty()){
            errorMsg = "Full name is required."
        }

        if (errorMsg != null){
            mBinding.fullNameTil.apply {
                isErrorEnabled = true
                error = errorMsg
            }
        }

        return errorMsg == null
    }

    private fun validateHealthCardNumber(): Boolean {
        var errorMsg: String? = null
        val value = mBinding.healthCardNumberEt.text.toString()

        if(value.isEmpty()){
            errorMsg = "Health card number is required."
        } else if(value.length < 12){
            errorMsg = "Health card number is in the wrong format."
        }

        if (errorMsg != null){
            mBinding.healthCardNumberTil.apply {
                isErrorEnabled = true
                error = errorMsg
            }
        }

        return errorMsg == null
    }

    private fun validateEmail(): Boolean {
        var errorMsg: String? = null
        val value = mBinding.emailAddressEt.text.toString()

        if(value.isEmpty()) {
            errorMsg = "Email is required."
        } else if(!Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            errorMsg = "Email address is invalid."
        }

        if (errorMsg != null){
            mBinding.emailAddressTil.apply {
                isErrorEnabled = true
                error = errorMsg
            }
        }

        return errorMsg == null
    }

    private fun validatePassword(): Boolean {
        var errorMsg: String? = null
        val value = mBinding.passwordEt.text.toString()

        if(value.isEmpty()) {
            errorMsg = "Password is required."
        } else if(value.length < 5){
            errorMsg = "Password must be at least 5 characters long."
        }

        if (errorMsg != null){
            mBinding.passwordTil.apply {
                isErrorEnabled = true
                error = errorMsg
            }
        }

        return errorMsg == null
    }

    private fun validateTerms(): Boolean{
        var errorMsg: String? = null
        val value = mBinding.termsCb.isSelected

        if (!value){
            errorMsg = "Please accept the terms and conditions to continue."
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
        }

        if (errorMsg != null){
            mBinding.termsTil.apply {
                isErrorEnabled = true
                error = errorMsg
            }
        }

        return errorMsg == null
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if(view != null){
            when(view.id){
                R.id.fullNameEt -> {
                    if(hasFocus){
                        if(mBinding.fullNameTil.isErrorEnabled){
                            mBinding.fullNameTil.isErrorEnabled = false
                        }
                    } else {
                        validateFullName()
                    }
                }
                R.id.healthCardNumberEt -> {
                    if(hasFocus){
                        if(mBinding.healthCardNumberTil.isErrorEnabled){
                            mBinding.healthCardNumberTil.isErrorEnabled = false
                        }
                    } else {
                        validateHealthCardNumber()
                    }
                }
                R.id.emailAddressEt -> {
                    if(hasFocus){
                        if(mBinding.emailAddressTil.isErrorEnabled){
                            mBinding.emailAddressTil.isErrorEnabled = false
                        }
                    } else {
                        validateEmail()
                    }
                }
                R.id.passwordEt -> {
                    if(hasFocus){
                        if(mBinding.passwordTil.isErrorEnabled){
                            mBinding.passwordTil.isErrorEnabled = false
                        }
                    } else {
                        if(validatePassword()){
                            if(mBinding.passwordTil.isErrorEnabled){
                                mBinding.passwordTil.isErrorEnabled = false
                            }
                            mBinding.passwordTil.setStartIconDrawable(R.drawable.checkmark)
                        }
                    }
                }
                R.id.termsCb -> {
                    if(hasFocus){
                        if(mBinding.termsTil.isErrorEnabled){
                            mBinding.termsTil.isErrorEnabled = false
                        }
                    } else {
                        validateTerms()
                    }
                }
            }
        }
    }
}