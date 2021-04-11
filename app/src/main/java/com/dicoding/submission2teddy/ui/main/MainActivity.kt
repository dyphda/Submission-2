package com.dicoding.submission2teddy.ui.main

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.viewbinding.ViewBinding
import cn.pedant.SweetAlert.SweetAlertDialog
import com.dicoding.submission2teddy.R
import com.dicoding.submission2teddy.data.model.ViewPlaceholder
import com.dicoding.submission2teddy.databinding.ActivityMainBinding
import com.dicoding.submission2teddy.databinding.FragmentHomeBinding
import com.dicoding.submission2teddy.databinding.FragmentProfileBinding
import com.dicoding.submission2teddy.databinding.FragmentProfileFollowBinding
import com.dicoding.submission2teddy.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    lateinit var mDialog: SweetAlertDialog
    override var getLayoutId: Int = R.layout.activity_main
    override var getViewModel: Class<MainViewModel> = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set mDialog
        mDialog = SweetAlertDialog(this).apply {
            setCancelable(false)
        }
        setToolbar()
        setUpNavigation()
    }

    // Function : for set navigation controller
    private fun setUpNavigation() {
        val navController = findNavController(R.id.main_nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    // Function : for set toolbar
    private fun setToolbar() {
        mViewBinding.apply {
            setSupportActionBar(mainToolbar)
            mainToolbarTittle.text = getString(R.string.app_name)
        }

        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    // Function : for change tittle toolbar
    fun changeToolbarTitle(title: String) {
        mViewBinding.mainToolbarTittle.text = title
    }

    // Function : for set content placeholder view
    fun setContentPlaceholder(condition: Int, binding: ViewBinding) {
        val data = ViewPlaceholder()

        data.apply {
            when (condition) {
                1 -> {
                    // View All when data is success load
                    show = false
                }
                2 -> {
                    // View All when data is error load
                    show = true
                    image = R.drawable.ic_search_error
                    tittle = R.string.placeholder_error_tittle
                    message = R.string.placeholder_error_message
                }
                3 -> {
                    // View Home when waiting to search
                    show = true
                    image = R.drawable.ic_find_inpage
                    tittle = R.string.placeholder_waiting_recent_tittle
                    message = R.string.placeholder_waiting_recent_message
                }
                4 -> {
                    // View Home when data user is not found
                    show = true
                    image = R.drawable.ic_not_found
                    tittle = R.string.placeholder_error_tittle
                    message = R.string.placeholder_not_found_message
                }
                5 -> {
                    // View Profile when data followers is null
                    show = true
                    image = R.drawable.ic_not_found
                    tittle = R.string.placeholder_error_tittle
                    message = R.string.placeholder_not_found_followers_message
                }
                6 -> {
                    // View Profile when data following is null
                    show = true
                    image = R.drawable.ic_not_found
                    tittle = R.string.placeholder_error_tittle
                    message = R.string.placeholder_not_found_following_message
                }
            }
        }

        when (binding) {
            is FragmentHomeBinding -> {
                binding.placeholder = data
            }
            is FragmentProfileBinding -> {
                binding.placeholder = data
            }
            is FragmentProfileFollowBinding -> {
                binding.placeholder = data
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean =
            findNavController(R.id.main_nav_host_fragment).navigateUp()

    override fun onBackPressed() {
        supportFragmentManager.apply {
            if (backStackEntryCount > 0)
                popBackStack()
            else
                super.onBackPressed()
        }
    }
}
