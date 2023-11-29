package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(BusSchedule::class), version = 1)
abstract class ScheduleDatabase : RoomDatabase() {

    abstract fun busScheduleDao(): BusScheduleDao

    companion object {
        @Volatile
        private var Instance: ScheduleDatabase? = null

        fun getDatabase(context: Context): ScheduleDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context = context, ScheduleDatabase::class.java, "schedule_database")
                    .createFromAsset("database/bus_schedule.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }

}