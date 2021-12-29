package com.lfh.wanmvvm.logic.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.lfh.wanmvvm.R
import com.lfh.wanmvvm.databinding.FragmentHomeBinding
import com.lfh.wanmvvm.logic.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getResourceId()?.let {
            binding = DataBindingUtil.inflate(inflater, it, container, false)
            return binding.root
        }
    }



    override fun initViewModel() {

    }

    override fun getResourceId(): Int = R.layout.fragment_home

    override fun initFragmentViewModel() {

    }

    override fun init(savedInstanceState: Bundle?) {
        initTitle(binding.rlHeader.tvFgTitle,"主页")
    }


}