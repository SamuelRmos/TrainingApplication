package com.example.starwarscharacters.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.starwarscharacters.base.BaseFragment
import com.example.starwarscharacters.base.LiveDataWrapper
import com.example.starwarscharacters.databinding.FragmentMainBinding
import com.example.starwarscharacters.extensions.hide
import com.example.starwarscharacters.extensions.show
import com.example.starwarscharacters.model.People
import com.example.starwarscharacters.repository.MainRepository
import com.example.starwarscharacters.view.adapter.MainRecyclerViewAdapter
import com.example.starwarscharacters.view.viewmodel.MainViewModel
import com.example.starwarscharacters.view.viewmodel.ViewModelFactory
import kotlinx.coroutines.Dispatchers

class MainFragment : Fragment() {

    companion object {
        fun getMainFragment() = MainFragment()
    }

    private val mViewModelFactory = ViewModelFactory(Dispatchers.Main, Dispatchers.IO)
    private lateinit var mMainRecyclerViewAdapter: MainRecyclerViewAdapter
    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, mViewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mMainRecyclerViewAdapter = MainRecyclerViewAdapter(activity!!, arrayListOf())
        binding = FragmentMainBinding.inflate(
            inflater,
            container,
            false
        )
        binding.recyclerView.adapter = mMainRecyclerViewAdapter
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.viewModel.mPeopleResponse.observe(viewLifecycleOwner, mDataObserver)
        this.viewModel.mLoadingLiveData.observe(viewLifecycleOwner, this.loadingObserver)
        this.viewModel.requestData()
    }

    private val mDataObserver = Observer<LiveDataWrapper<People>> { result ->
        when (result?.responseStatus) {
            LiveDataWrapper.ResponseStatus.LOADING -> {

            }
            LiveDataWrapper.ResponseStatus.ERROR -> {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
            LiveDataWrapper.ResponseStatus.SUCCESS -> {
                processData(result.response!!)
            }
        }
    }

    private fun processData(listItems: MutableList<People>) {
        val refresh = Handler(Looper.getMainLooper())
        refresh.post {
            mMainRecyclerViewAdapter.updateListItems(listItems)
        }
    }

    private val loadingObserver = Observer<Boolean> { visible ->
        // Show hide progress bar
        if (visible) {
            binding.progressBar.show()
        } else {
            binding.progressBar.hide()
        }
    }
}