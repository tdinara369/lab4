package com.example.lab4news.News;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.lab4news.JSON.Headline;
import com.example.lab4news.JSON.Multimedium;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Динара on 13.10.2017.
 */

public class News implements Parcelable
{
    //@PrimaryKey(autoGenerate = true)
    //@ColumnInfo(name = "id")
    @SerializedName("articleID")
    @Expose
    public  int id;

    @SerializedName("headline")
    @Expose
    //@ColumnInfo(name = "title")
    public Headline title;

    @SerializedName("pub_date")
    @Expose
    // @ColumnInfo(name = "date")
    public String date;


    @SerializedName("snippet")
    @Expose
    //@ColumnInfo(name = "desc")
    public String desc;

    @SerializedName("multimedia")
    @Expose
    public List<Multimedium> imageURL = null;

    @SerializedName("section_name")
    @Expose
    public String category;



    public News(int id, Headline title, String date, String desc, List<Multimedium> imageURL, String category) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.desc = desc;
        this.imageURL = imageURL;
        this.category = category;
    }


    protected News(Parcel in) {
        id = in.readInt();
        title = in.readParcelable(Headline.class.getClassLoader());
        date = in.readString();
        desc = in.readString();
        imageURL = in.readArrayList(Multimedium.class.getClassLoader());
        category = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public Headline getTitle() { return title; }

    public String getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }


    public void setTitle(Headline title) {
        this.title = title;
    }

    public void setId(Integer id) {
        this.id= id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDate(String date) {
        this.date= date;
    }

    public List<Multimedium> getMultimedium() { return imageURL; }

    @Override
    public String toString() {
        return title + " " + date + "\n";
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeParcelable(title, i);
        parcel.writeString(date);
        parcel.writeString(desc);
        parcel.writeTypedList(imageURL);
        parcel.writeString(category);
    }
}

