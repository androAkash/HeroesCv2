package com.akash.heroescv3.db

import androidx.room.TypeConverter
import com.akash.heroescv3.model.Images

class Converters {

    @TypeConverter
    fun fromImage(images : Images) : String{
        return images.lg
    }

    @TypeConverter
    fun toImage(lg : String) : Images{
        return Images(lg)
    }


}