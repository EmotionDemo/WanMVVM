package com.lfh.wanmvvm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.lfh.wanmvvm.R
import com.lfh.wanmvvm.databinding.FragmentMainBinding
import com.lfh.wanmvvm.logic.base.BaseFragment


class MainFragment : BaseFragment() {


    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_main, container, false)
        return mView
    }

    override fun initViewModel()  {

    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false)

    }


}