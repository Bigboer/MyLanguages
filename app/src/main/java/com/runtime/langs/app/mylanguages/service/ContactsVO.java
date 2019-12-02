package com.runtime.langs.app.mylanguages.service;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactsVO implements Parcelable {

    public String Name;
    public String Abstract;
    public String Description;
    public String Link;

    protected ContactsVO(Parcel in) {
        Name = in.readString();
        Abstract = in.readString();
        Description = in.readString();
        Link = in.readString();
    }

    public static final Creator<ContactsVO> CREATOR = new Creator<ContactsVO>() {
        @Override
        public ContactsVO createFromParcel(Parcel in) {
            return new ContactsVO(in);
        }

        @Override
        public ContactsVO[] newArray(int size) {
            return new ContactsVO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Name);
        parcel.writeString(Abstract);
        parcel.writeString(Description);
        parcel.writeString(Link);
    }
}
