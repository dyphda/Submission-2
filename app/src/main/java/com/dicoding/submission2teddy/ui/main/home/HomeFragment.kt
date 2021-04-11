package com.dicoding.submission2teddy.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import com.dicoding.submission2teddy.R
import com.dicoding.submission2teddy.data.adapter.UserSearchAdapter
import com.dicoding.submission2teddy.data.model.Status
import com.dicoding.submission2teddy.data.model.UserSearch
import com.dicoding.submission2teddy.data.util.*
import com.dicoding.submission2teddy.databinding.FragmentHomeBinding
import com.dicoding.submission2teddy.ui.base.BaseFragment
import com.dicoding.submission2teddy.ui.main.MainActivity

// Home fragment implements dagger fragment
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    UserSearchAdapter.Listener {

    private lateinit var mDialog: SweetAlertDialog

    private var rvUserSearchAdapter = UserSearchAdapter(this)

    override var getLayoutId: Int = R.layout.fragment_home
    override var getViewModel: Class<HomeViewModel> = HomeViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Search User")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set condition PlaceholderView
        setContentPlaceholder(if (rvUserSearchAdapter.itemCount <= 0) 3 else 1)

        // Set mDialog to get dialog from MainActivity
        mDialog = (activity as MainActivity).mDialog

        // Configure ViewBinding
        mViewBinding.apply {

            // Inflate options menu & set optionsClickListener
            homeToolbar.apply {
                inflateMenu(R.menu.main_menu)
                setOnMenuItemClickListener {
                    if (it.itemId == R.id.action_home_change_language) {
                        startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                    }

                    false
                }
            }

            // Set adapter user RecyclerView
            homeRvUser.apply {
                adapter = rvUserSearchAdapter
            }

            // Configure handle search EditText
            homeEtSearch.apply {
                setOnEditorActionListener { _, actionId, _ ->
                    var handled = false

                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        searchUser()
                        handled = true
                    }
                    handled
                }
            }

            // Set button search clickListener
            homeBtnSearch.setOnClickListener {
                searchUser()
            }
        }
    }

    // Function : for search user
    private fun searchUser() {
        mViewBinding.apply {
            if (!homeEtSearch.text.isBlank()) {
                homeRvUser.requestFocus()

                homeProgressBar.show()
                context?.hideKeyboard(requireView())

                observeUserSearch(homeEtSearch.text.toString())
            }
        }
    }

    // Function : for observe data user search from api
    private fun observeUserSearch(keyword: String) {
        mViewModel.getUserSearch(keyword)
            .observe(viewLifecycleOwner, Observer {
                it?.let { status ->
                    when (status.status) {
                        Status.StatusType.SUCCESS -> {

                            // Hide ProgressBar and set list data in RecyclerViewAdapter
                            it.data?.let { data ->
                                mViewBinding.homeProgressBar.hide()

                                if (data.total_count > 0) {
                                    // When data is not empty
                                    setContentPlaceholder(1)
                                    rvUserSearchAdapter.setList(data.items)
                                } else {
                                    // When data is empty
                                    setContentPlaceholder(4)
                                }
                            }
                        }
                        Status.StatusType.ERROR -> {

                            // Hide ProgressBar and show warning dialog
                            mViewBinding.homeProgressBar.hide()

                            showDialogWarning(mDialog, status.message ?: "Error", null)
                            setContentPlaceholder(2)
                        }
                    }
                }
            })
    }

    override fun onUserClickListener(view: View, data: UserSearch) {
        val action = HomeFragmentDirections.actionHomeFragmentToUserDetailFragment(data.login)
        view.changeNavigation(action)
    }
}