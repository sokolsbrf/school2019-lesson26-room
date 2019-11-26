package ru.dimasokol.school.newnotebook.room;

import androidx.room.TypeConverter;

import java.util.Date;

public final class Converters {

    @TypeConverter
    public static long fromDate(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public static Date toDate(long time) {
        return new Date(time);
    }

}
