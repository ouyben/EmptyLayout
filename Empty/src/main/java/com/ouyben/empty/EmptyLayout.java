package com.ouyben.empty;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 各种状态控制显示
 */
public class EmptyLayout extends FrameLayout implements View.OnClickListener {

    private Context mContext;
    private View mEmptyView;// 空布局
    private View mBindView;//绑定的布局
    private View mErrorView;// 加载错误
    private View mLoadingView;// 加载布局
    private View mNoNetwork;//无网络布局

    private OnRetryLisenter mLisenter;

    public EmptyLayout(Context context) {
        this(context, null);
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        //居中
        params.gravity = Gravity.CENTER;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EmptyLayout, 0, 0);

        //数据为空时的布局
        int emptyLayout = ta.getResourceId(R.styleable.EmptyLayout_elEmptyLayout, R.layout.layout_empty);
        mEmptyView = View.inflate(context, emptyLayout, null);
        addView(mEmptyView, params);

        //加载中的布局
        int loadingLayout = ta.getResourceId(R.styleable.EmptyLayout_elLoadingLayout, R.layout.layout_loading);
        mLoadingView = View.inflate(context, loadingLayout, null);
        addView(mLoadingView, params);

        //错误时的布局
        int errorLayout = ta.getResourceId(R.styleable.EmptyLayout_elErrorLayout, R.layout.layout_error);
        mErrorView = View.inflate(context, errorLayout, null);
        addView(mErrorView, params);

        int noNetworkLayout = ta.getResourceId(R.styleable.EmptyLayout_elErrorLayout, R.layout.layout_not_network);
        mNoNetwork = View.inflate(context, noNetworkLayout, null);
        addView(mNoNetwork, params);

        mEmptyView.setOnClickListener(this);
        mErrorView.setOnClickListener(this);
        mNoNetwork.setOnClickListener(this);
        //全部隐藏
        setGone();


    }

    /**
     * TODO: 设置空数据布局
     *
     * @param resId
     */
    public void setEmptyView(int resId) {
        setEmptyView(View.inflate(mContext, resId, null));
    }

    /**
     * TODO: 设置空数据布局
     */
    public void setEmptyView(View v) {
        if (indexOfChild(mEmptyView) != -1) {
            removeView(mEmptyView);
        }
        mEmptyView = v;
        addView(mEmptyView);
        setGone();
    }

    /**
     * TODO: 设置加载中布局
     *
     * @param layoutId
     */
    public void setLoadingView(int layoutId) {
        setLoadingView(View.inflate(mContext, layoutId, null));
    }

    /**
     * todo:设置加载中布局
     *
     * @param loadingView
     */
    public void setLoadingView(View loadingView) {
        if (indexOfChild(mLoadingView) != -1) {
            removeView(mLoadingView);
        }
        mLoadingView = loadingView;
        addView(mLoadingView);
        setGone();
    }

    /**
     * TODO: 设置加载错误布局
     *
     * @param layoutId
     */
    public void setErrorView(int layoutId) {
        setLoadingView(View.inflate(mContext, layoutId, null));
    }

    /**
     * TODO: 设置加载错误布局
     *
     * @param errorView
     */
    public void setErrorView(View errorView) {
        if (indexOfChild(mErrorView) != -1) {
            removeView(mErrorView);
        }
        mErrorView = errorView;
        addView(mLoadingView);
        setGone();
    }

    /**
     * TODO: 设置无网络布局
     *
     * @param layoutId
     */
    public void setNoNetworkView(int layoutId) {
        setLoadingView(View.inflate(mContext, layoutId, null));
    }

    /**
     * TODO: 设置无网络布局
     *
     * @param view
     */
    public void setNoNetworkView(View view) {
        if (indexOfChild(mNoNetwork) != -1) {
            removeView(mNoNetwork);
        }
        mNoNetwork = view;
        addView(mNoNetwork);
        setGone();
    }

    public void bindView(View view) {
        mBindView = view;
    }

    public void showEmpty() {
        if (mBindView != null)
            mBindView.setVisibility(View.GONE);
        setGone();
        mEmptyView.setVisibility(View.VISIBLE);
    }

    public void showError() {
        if (mBindView != null)
            mBindView.setVisibility(View.GONE);
        setGone();
        mErrorView.setVisibility(View.VISIBLE);
    }

    public void showLoading() {
        if (mBindView != null)
            mBindView.setVisibility(View.GONE);
        setGone();
        mLoadingView.setVisibility(View.VISIBLE);
    }

    public void showNoNetwork() {
        if (mBindView != null)
            mBindView.setVisibility(View.GONE);
        setGone();
        mNoNetwork.setVisibility(View.VISIBLE);
    }

    /**
     * 全部隐藏
     */
    private void setGone() {
        mEmptyView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mNoNetwork.setVisibility(View.GONE);
    }

    public void showSuccess() {
        if (mBindView != null)
            mBindView.setVisibility(View.VISIBLE);
        setGone();
    }

    public void setOnRetryLisenter(OnRetryLisenter lisenter) {
        mLisenter = lisenter;
    }

    @Override
    public void onClick(View view) {
        if (mLisenter != null) {
            mLisenter.onRetry();
        }
    }

    public interface OnRetryLisenter {
        void onRetry();
    }

}
