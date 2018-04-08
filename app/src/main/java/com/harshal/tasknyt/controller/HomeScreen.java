package com.harshal.tasknyt.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.harshal.tasknyt.Interfaces.RetrofitObjectAPI;
import com.harshal.tasknyt.Model.Books;
import com.harshal.tasknyt.Model.Lists;
import com.harshal.tasknyt.Model.MainBookModel;
import com.harshal.tasknyt.R;
import com.harshal.tasknyt.Util.CommonCode;
import com.harshal.tasknyt.Util.CommonIOManager;
import com.harshal.tasknyt.Util.DividerItemDecoration;
import com.harshal.tasknyt.Util.ImageLoader;
import com.harshal.tasknyt.Util.Logger;
import com.harshal.tasknyt.Util.RecyclerItemClickListener;
import com.harshal.tasknyt.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreen extends AppCompatActivity {
    private Boolean exit = false;
    private ActivityMainBinding activityMainBinding;
    private HashMap<Integer,ArrayList<Books>> bookMap = new HashMap<>();
    private List<Lists> listData;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        getSupportActionBar().setTitle("Books Categories");

        pd = new ProgressDialog(this);

        getData();

        activityMainBinding.booksRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(HomeScreen.this,BookListScreen.class);
                intent.putParcelableArrayListExtra("book_list",bookMap.get(listData.get(position).getList_id()));
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }));
    }

    void getData()
    {
        if(CommonCode.getInstance(this).checkInternet())
        {
            pd.setMessage("Loading....");
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setIndeterminate(true);
            pd.show();

            RetrofitObjectAPI service = CommonIOManager.getRetrofitManager().create(RetrofitObjectAPI.class);

            Call<MainBookModel> call = service.getBooksDetails("7745fc0778b44a61bc7287765ca7aed0");

            call.enqueue(new Callback<MainBookModel>() {

                @Override
                public void onResponse(Call<MainBookModel> call, Response<MainBookModel> response) {
                    pd.dismiss();
                    try {
                        listData = new ArrayList<>();
                        listData = response.body().results.getLists();
                        for (Lists lists:response.body().results.getLists())
                        {
                            ArrayList<Books> bookData = new ArrayList<>();
                            bookData.addAll(lists.getBooks());
                            bookMap.put(lists.getList_id(),bookData);
                        }
                        setAdapter();

                    } catch (Exception e) {
                        Logger.getInstance().Log("Error Occured");
                        CommonCode.getInstance(HomeScreen.this).showToastMessage("Some Error Occured");
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<MainBookModel> call, Throwable t) {
                    pd.dismiss();
                    Logger.getInstance().Log("Api Call Failure");
                    CommonCode.getInstance(HomeScreen.this).showToastMessage("Api Call Failure");
                }
            });
        }
        else
        {
            CommonCode.getInstance(this).showToastMessage(R.string.no_internet_connection);
        }
    }

    private void setAdapter()
    {
        BookAdapter bookAdapter = new BookAdapter();
        LinearLayoutManager mLinearLayoutmanager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        activityMainBinding.booksRecyclerView.setLayoutManager(mLinearLayoutmanager);
        activityMainBinding.booksRecyclerView.addItemDecoration(new DividerItemDecoration(this,1));
        activityMainBinding.booksRecyclerView.setHasFixedSize(true);
        activityMainBinding.booksRecyclerView.setItemAnimator(new DefaultItemAnimator());
        activityMainBinding.booksRecyclerView.setAdapter(bookAdapter);
    }

    private class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>
    {
        public class BookViewHolder extends RecyclerView.ViewHolder
        {
            ImageView bookImgView;
            TextView bookDisplayNameTextView;
            public BookViewHolder(View itemView) {
                super(itemView);
                bookImgView = itemView.findViewById(R.id.bookImgView);
                bookDisplayNameTextView = itemView.findViewById(R.id.bookDisplayNameTextView);
            }
        }

        @Override
        public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_view,parent,false);
            return new BookViewHolder(view);
        }

        @Override
        public void onBindViewHolder(BookViewHolder holder, int position) {
            Lists data = listData.get(position);
            ImageLoader.getInstance(HomeScreen.this).displayImage(data.getList_image(),holder.bookImgView);
            holder.bookDisplayNameTextView.setText(data.getDisplay_name());
        }

        @Override
        public int getItemCount() {
            return listData.size();
        }
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish();// finish activity
        } else {
            CommonCode.getInstance(this).showToastMessage("Press Back again to Exit.");
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }
}


