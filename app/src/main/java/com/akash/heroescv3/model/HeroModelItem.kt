package com.akash.heroescv3.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable


data class HeroModelItem(
//    val appearance: Appearance,
//    val biography: Biography,
//    val connections: Connections,
    val id: Int,
    val images: Images,
    val name: String,
//    val powerstats: Powerstats,
    val slug: String,
//    val work: Work
) : Serializable {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}