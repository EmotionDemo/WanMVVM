package com.lfh.wanmvvm.logic.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfh.wanmvvm.R
import com.lfh.wanmvvm.databinding.FragmentHomeBinding
import com.lfh.wanmvvm.logic.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        return v
    }

    override fun initViewModel() {
    }

    override fun getResourceId(): Int {
        return 0;
    }

    override fun initFragmentViewModel() {
        TODO("Not yet implemented")
    }

    override fun init(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }


}