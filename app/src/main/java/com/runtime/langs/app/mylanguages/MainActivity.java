package com.runtime.langs.app.mylanguages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.runtime.langs.app.mylanguages.service.ApiService;
import com.runtime.langs.app.mylanguages.service.LangPayload;
import com.runtime.langs.app.mylanguages.service.ContactsVO;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ContactsListAdapter adapter;
    private RecyclerView list;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = (RecyclerView) findViewById(R.id.list);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(MainActivity.this);
        list.setLayoutManager(lm);
        list.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));

        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchOnlineData();
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onResume() {
        super.onResume();

        fetchOnlineData();
    }

    private void fetchOnlineData() {
        ApiService
                .getInstance()
                .getLanguages()
                .subscribe(result -> {
                    Log.i(TAG, "Payload" + result);
                    updatePayload(result);
                    swipeRefresh.setRefreshing(false);
                    Snackbar.make(swipeRefresh, R.string.data_loaded, Snackbar.LENGTH_SHORT)
                            .show();
                }, err -> {
                    swipeRefresh.setRefreshing(false);
                });
    }

    private void updatePayload(LangPayload payloads) {

        if(adapter == null) {
            adapter = new ContactsListAdapter(MainActivity.this, payloads) {
                @Override
                public void onItemSelect(ContactsVO contactsVO) {
                    super.onItemSelect(contactsVO);

                    launchDetails(contactsVO);
                }
            };
        }

        list.setAdapter(adapter);
    }

    private void launchDetails(ContactsVO contactsVO) {
        Intent intent = new Intent(getBaseContext(), DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_LANGVO, contactsVO);
        startActivity(intent);
    }
}
