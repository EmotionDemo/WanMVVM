package com.lfh.wanmvvm.logic.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lfh.wanmvvm.R


abstract class BaseFragment : Fragment() {
    protected lateinit var mView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        initBinding(inflater,container)
        return inflater.inflate(R.layout.fragment_base, container, false)
    }


    abstract fun initViewModel()

    abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?)

}