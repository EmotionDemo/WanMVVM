package com.lfh.wanmvvm.logic.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class BaseFragment<Binding : ViewDataBinding> : Fragment() {
    protected lateinit var mView: View
    protected lateinit var binding: Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getResourceId()?.let {
            binding = DataBindingUtil.inflate(inflater, it, container, false)

            return binding.root
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragmentViewModel()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)
    }
    abstract fun initViewModel()

    abstract fun getResourceId(): Int

    abstract fun initFragmentViewModel()

    abstract fun init(savedInstanceState: Bundle?)

}