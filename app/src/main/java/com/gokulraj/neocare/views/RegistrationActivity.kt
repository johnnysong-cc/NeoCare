package com.gokulraj.neocare.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.gokulraj.neocare.R
import com.gokulraj.neocare.databinding.ActivityRegistrationBinding
//import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity(), View.OnFocusChangeListener {

    private lateinit var mBinding: ActivityRegistrationBinding
    //private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegistrationBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        //firebaseAuth = FirebaseAuth.getInstance()

        mBinding.fullNameEt.onFocusChangeListener = this
        mBinding.healthCardNumberEt.onFocusChangeListener = this
        mBinding.emailAddressEt.onFocusChangeListener = this
        mBinding.passwordEt.onFocusChangeListener = this
        mBinding.termsCb.onFocusChangeListener = this

        mBinding.btnRegister.setOnClickListener {
            val fullName = mBinding.fullNameEt.text.toString()
            val healthCard = mBinding.healthCardNumberEt.text.toString()
            val email = mBinding.emailAddressEt.text.toString()
            val password =  mBinding.passwordEt.text.toString()
            val terms = mBinding.termsCb.isSelected.toString()


        }
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