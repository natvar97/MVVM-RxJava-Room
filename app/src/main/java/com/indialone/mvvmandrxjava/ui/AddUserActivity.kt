package com.indialone.mvvmandrxjava.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.indialone.mvvmandrxjava.R
import com.indialone.mvvmandrxjava.databinding.ActivityAddUserBinding
import com.indialone.mvvmandrxjava.roomdatabase.User
import com.indialone.mvvmandrxjava.utils.Constants
import com.indialone.mvvmandrxjava.utils.MyApplication
import com.indialone.mvvmandrxjava.viewmodel.UserViewModel
import com.indialone.mvvmandrxjava.viewmodel.ViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AddUserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var userViewModel: UserViewModel
    private lateinit var mBinding: ActivityAddUserBinding
    private var compositeDisposable = CompositeDisposable()
    private var mEditUser: User? = null
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        userViewModel =
            ViewModelProvider(
                this,
                ViewModelFactory(
                    (application as MyApplication).repository,
                    (application as MyApplication).newsRepository
                )
            )
                .get(UserViewModel::class.java)

        if (intent.hasExtra(Constants.USER_DATA)) {
            mEditUser = intent.getParcelableExtra(Constants.USER_DATA)
            Log.e("edit_user", "$mEditUser")
        }

        mEditUser?.let {
            mBinding.etUsername.setText(mEditUser!!.username)
            mBinding.etEmail.setText(mEditUser!!.email)
            mBinding.etFavourite.setText(mEditUser!!.favourite)
            mBinding.etHobby.setText(mEditUser!!.hobby)
            mBinding.etCountry.setText(mEditUser!!.country)
            mBinding.btnSave.text = Constants.UPDATE
        }


        mBinding.btnSave.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        v?.id?.let {
            when (v.id) {
                R.id.btn_save -> {
                    if (checkValidations()) {
                        Toast.makeText(this, "All fields required!!", Toast.LENGTH_SHORT).show()
                        return
                    }
                    val username = mBinding.etUsername.text.toString()
                    val email = mBinding.etEmail.text.toString()
                    val favourite = mBinding.etFavourite.text.toString()
                    val hobby = mBinding.etHobby.text.toString()
                    val country = mBinding.etCountry.text.toString()

                    mEditUser?.let {
                        userId = it.id
                    }

                    val user = User(
                        username,
                        email,
                        favourite,
                        hobby,
                        country,
                        userId
                    )

                    if (userId == 0) {

                        compositeDisposable.add(
                            userViewModel.insert(user)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe()
                        )
                    } else {
                        compositeDisposable.add(
                            userViewModel.update(user)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe()
                        )
                    }
                    startActivity(Intent(this@AddUserActivity, MainActivity::class.java))
                    finish()
                }
                else -> {

                }
            }
        }
    }

    private fun checkValidations(): Boolean {
        return TextUtils.isEmpty(mBinding.etUsername.text.toString().trim { it <= ' ' })
                || TextUtils.isEmpty(mBinding.etEmail.text.toString().trim { it <= ' ' })
                || TextUtils.isEmpty(mBinding.etFavourite.text.toString().trim { it <= ' ' })
                || TextUtils.isEmpty(mBinding.etHobby.text.toString().trim { it <= ' ' })
                || TextUtils.isEmpty(mBinding.etCountry.text.toString().trim { it <= ' ' })
    }
}