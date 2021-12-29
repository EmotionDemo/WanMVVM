package com.lfh.wanmvvm.logic.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


abstract class BaseFragment<Binding : ViewDataBinding> : Fragment() {
    protected lateinit var mView: View
    protected lateinit var binding: Binding
    private var activityProvider: ViewModelProvider? = null
    private var fragmentProvider: ViewModelProvider? = null
    private lateinit var mActivity: AppCompatActivity
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
        mActivity = context as AppCompatActivity
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)
        observe()
    }

    abstract fun initViewModel()

    abstract fun getResourceId(): Int

    /**
     * 初始化Viewmodel
     */
    abstract fun initFragmentViewModel()

    /**
     * 初始化操作，放在viewCreated下
     */
    abstract fun init(savedInstanceState: Bundle?)

    /**
     * 设置标题
     */
    protected fun initTitle(tvTitle: TextView?, title: String) {
        tvTitle?.text = title
    }

    /**
     * 通过activity获得viewmodel，跟随Activity生命周期
     */
    protected fun <T : ViewModel?> getActivityViewModel(viewmodel: Class<T>): T {
        if (activityProvider == null) {
            activityProvider = ViewModelProvider(mActivity)
        }
        return activityProvider!!.get(viewmodel)
    }


    /**
     * 根据fragment获取viewmodel
     */
    protected fun <T : ViewModel?> getFragmentViewModel(viewModel: Class<T>): T {

        if (fragmentProvider == null) {
            fragmentProvider = ViewModelProvider(this)
        }
        return fragmentProvider!!.get(viewModel)
    }

    open fun observe(){}

}