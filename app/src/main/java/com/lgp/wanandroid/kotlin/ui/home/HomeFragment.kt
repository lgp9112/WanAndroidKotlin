package com.lgp.wanandroid.kotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView
import com.lgp.wanandroid.kotlin.R
import com.lgp.wanandroid.kotlin.databinding.FragmentHomeBinding
import com.lgp.wanandroid.kotlin.di.InjectorUtil
import com.lgp.wanandroid.kotlin.ui.adapter.HomeAdapter
import com.lgp.wanandroid.kotlin.ui.adapter.ImageTitleAdapter
import com.lgp.wanandroid.kotlin.viewmodel.HomeViewModel
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.config.BannerConfig
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.util.BannerUtils


/**
 * 创建者     罗国鹏
 * 创建时间   2020/12/24 13:45
 * 描述
 * 更新者     $
 * 更新时间   $
 * 更新描述
 */
class HomeFragment : Fragment() {

    private val homeViewModel by viewModels<HomeViewModel> { InjectorUtil.getHomeViewModelFactory() }
    private val homeAdapter by lazy { HomeAdapter() }
    private val bannerView by lazy { com.youth.banner.Banner<Any, BannerAdapter<*, *>>(context) }

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        ) as FragmentHomeBinding
        return binding.apply { lifecycleOwner = this@HomeFragment }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startObserve()
        initView()
        initData()
    }

    private fun startObserve() {
        homeViewModel.apply {
            uiState.observe(viewLifecycleOwner, Observer {
                it.showSuccess?.let { list ->
                    if (it.isRefresh) {
                        homeAdapter.setList(list)
                    } else {
                        homeAdapter.addData(list)
                    }
                    homeAdapter.loadMoreModule.loadMoreComplete()
                }
            })

            banner.observe(viewLifecycleOwner, {
                bannerView.setDatas(it)
            })
        }
    }

    private fun initView() {
        binding.viewModel = homeViewModel
        binding.adapter = homeAdapter

        //SwipeRefreshLayout
//        binding.homeRefreshLayout.apply {
//            //已通过xml配置
//            //setOnRefreshListener { homeViewModel.requestHomeData() }
//        }

        //RecyclerView
        binding.homeRecycleView.apply {
            layoutManager = LinearLayoutManager(activity)
            //已通过xml配置
            //adapter=homeAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        //homeAdapter
        homeAdapter.apply {
            //点击事件
            addChildClickViewIds(R.id.iv_like)
            setOnItemChildClickListener { adapter, view, position -> ToastUtils.showShort("子view点击 position==$position") }
            setOnItemClickListener { adapter, view, position -> ToastUtils.showShort("item点击 position==$position") }

            addHeaderView(bannerView)
            loadMoreModule.apply {
                isAutoLoadMore=true
                loadMoreView=SimpleLoadMoreView()
                setOnLoadMoreListener {
                    LogUtils.eTag("111", "HomeFragment.setOnLoadMoreListener")
                    homeViewModel.getHomeArticleList(false)
                }
            }
        }

        bannerView.apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ConvertUtils.dp2px(
                    200.0f
                )
            )
            adapter = ImageTitleAdapter(null)
            indicator = CircleIndicator(context)
            setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
            setIndicatorMargins(
                IndicatorConfig.Margins(
                    0, 0,
                    BannerConfig.INDICATOR_MARGIN, BannerUtils.dp2px(12f).toInt()
                )
            )
            //HomeFragment生命周期监听
            addBannerLifecycleObserver(this@HomeFragment)
        }
    }

    private fun initData() {
        homeViewModel.requestBanner()
        homeViewModel.requestHomeData()
    }
}