package com.ouyben.emptylayout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ouyben.empty.EmptyLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView mTv;
    private EmptyLayout mEmpty;
    private RelativeLayout mContentMain;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_no_net) {//无网络
            mEmpty.showNoNetwork();
            return true;
        } else if (id == R.id.action_error) {
            mEmpty.showError();
            return true;
        } else if (id == R.id.action_load) {
            mEmpty.showLoading();
            return true;
        } else if (id == R.id.action_no_data) {
            mEmpty.showEmpty();
            return true;
        } else if (id == R.id.action_sucess) {
            mEmpty.showSuccess();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTv = (TextView) findViewById(R.id.tv);
        mEmpty = (EmptyLayout) findViewById(R.id.empty);
        mContentMain = (RelativeLayout) findViewById(R.id.content_main);
        mFab = (FloatingActionButton) findViewById(R.id.fab);

        mFab.setOnClickListener(this);

        mToolbar.setTitle("状态显示");
        setSupportActionBar(mToolbar);
        mEmpty.bindView(mTv);

        mEmpty.setOnRetryLisenter(new EmptyLayout.OnRetryLisenter() {
            @Override
            public void onRetry() {
                mEmpty.showSuccess();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                mEmpty.showSuccess();
                break;
        }
    }
}
