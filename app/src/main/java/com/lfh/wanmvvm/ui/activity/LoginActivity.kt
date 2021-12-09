package com.lfh.wanmvvm.ui.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lfh.wanmvvm.R
import com.lfh.wanmvvm.logic.model.RegisterModel
import com.lfh.wanmvvm.logic.viewmodel.LoginViewModel

class LoginActivity : BaseActivity() {

    val TAG: String = "LoginActivity"
    val loginModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }
    private var etUserName: EditText? = null
    private var etPwd: EditText? = null
    private var btnLogin: Button? = null
    private var btnRegister: Button? = null
    private var loginProBar: ProgressBar? = null
    private var mContext: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViews()
        mContext = this
        //注册回调
        loginModel.getRegisterLiveData()?.observe(this, object : Observer<RegisterModel> {
            override fun onChanged(registerModel: RegisterModel?) {
                Toast.makeText(mContext, "1111", Toast.LENGTH_SHORT).show()
                Log.d(TAG, registerModel?.errorCode.toString())
            }
        })
    }

    /**
     * 初始化views
     */
    fun initViews() {
        etUserName = findViewById(R.id.etUserName)
        etPwd = findViewById(R.id.etPwd)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)
        loginProBar = findViewById(R.id.loginProBar)
    }

    override fun onResume() {
        super.onResume()
        btnRegister!!.setOnClickListener {
            val username = etUserName!!.text.toString()
            val password = etPwd!!.text.toString()
            Toast.makeText(mContext, "22222", Toast.LENGTH_SHORT).show()
            with(loginModel) {
                Toast.makeText(mContext, "33333", Toast.LENGTH_SHORT).show()
                register(username, password, password)
            }
        }
    }

}