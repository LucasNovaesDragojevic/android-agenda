package br.com.agenda.db.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class CalendarConverter {

    @TypeConverter
    public Long toLong(Calendar calendar) {
        if (calendar != null)
            return calendar.getTimeInMillis();
        return null;
    }

    @TypeConverter
    public Calendar toCalendar(Long value) {
        final Calendar instance = Calendar.getInstance();
        if (value != null)
            instance.setTimeInMillis(value);
        return instance;
    }
}
