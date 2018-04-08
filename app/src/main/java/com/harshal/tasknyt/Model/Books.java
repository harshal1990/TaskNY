package com.harshal.tasknyt.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Harshal on 07-Apr-18.
 */

public class Books implements Parcelable {
    public String author;
    public String book_image;
    public String created_date;
    public String description;
    public int price;
    public String publisher;
    public int rank;
    public String title;
    public String updated_date;
    public List<Buy_Links> buy_links;

    protected Books(Parcel in) {
        author = in.readString();
        book_image = in.readString();
        created_date = in.readString();
        description = in.readString();
        price = in.readInt();
        publisher = in.readString();
        rank = in.readInt();
        title = in.readString();
        updated_date = in.readString();
    }

    public static final Creator<Books> CREATOR = new Creator<Books>() {
        @Override
        public Books createFromParcel(Parcel in) {
            return new Books(in);
        }

        @Override
        public Books[] newArray(int size) {
            return new Books[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBook_image() {
        return book_image;
    }

    public void setBook_image(String book_image) {
        this.book_image = book_image;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public List<Buy_Links> getBuy_links() {
        return buy_links;
    }

    public void setBuy_links(List<Buy_Links> buy_links) {
        this.buy_links = buy_links;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(author);
        parcel.writeString(book_image);
        parcel.writeString(created_date);
        parcel.writeString(description);
        parcel.writeInt(price);
        parcel.writeString(publisher);
        parcel.writeInt(rank);
        parcel.writeString(title);
        parcel.writeString(updated_date);
    }
}
