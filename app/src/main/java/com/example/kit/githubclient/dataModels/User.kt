package com.example.kit.githubclient.dataModels

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by jasiekpor on 22.01.2016.
 */

class User(@SerializedName("login") override val name: String, val avatar_url: String) : ItemModel, Parcelable{
    companion object {
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User {
                return User(source)
            }

            override fun newArray(size: Int): Array<User?> {
                return arrayOfNulls(size)
            }
        }
    }
    constructor(source: Parcel) : this(source.readString(),source.readString())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(avatar_url)
    }

    override fun describeContents(): Int {
        return 0
    }

}
