package com.harshal.tasknyt.controller;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.harshal.tasknyt.Model.Books;
import com.harshal.tasknyt.R;
import com.harshal.tasknyt.Util.ImageLoader;
import com.harshal.tasknyt.Util.Logger;
import com.harshal.tasknyt.databinding.ActivityBookListScreenBinding;

import java.util.ArrayList;

public class BookListScreen extends AppCompatActivity {
    private ActivityBookListScreenBinding activityBookListScreenBinding;
    private ArrayList<Books> bookList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBookListScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_book_list_screen);

        getSupportActionBar().setTitle("Books");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if(intent.getExtras() != null)
        {
            bookList = intent.getParcelableArrayListExtra("book_list");
        }

        Logger.getInstance().Log(""+bookList.size());

        setBookViewPagerAdapter();
    }

    private void setBookViewPagerAdapter()
    {
        SliderFragmentAdapter sAdapter = new SliderFragmentAdapter();
        activityBookListScreenBinding.booksViewPager.setAdapter(sAdapter);
        activityBookListScreenBinding.viewPagerIndicator.setViewPager(activityBookListScreenBinding.booksViewPager);
        activityBookListScreenBinding.booksViewPager.setOffscreenPageLimit(bookList.size());
    }

    /**
     * @author Harshal Created on 19-05-2017.
     * Adapter class to display banner images
     */
    private class SliderFragmentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return bookList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup parent, final int position) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider,parent,false);

            ImageView imageView = itemView.findViewById(R.id.slider_image);
            ImageView img = imageView;

            ImageLoader.getInstance(BookListScreen.this).displayImage(
                    bookList.get(position).getBook_image(), img);

            parent.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//	        container.removeView((LinearLayout) object);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
