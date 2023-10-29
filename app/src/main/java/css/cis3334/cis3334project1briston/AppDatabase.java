package css.cis3334.cis3334project1briston;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * AppDatabase class that serves as the database for the application.
 * Utilizes Room persistence library for SQLite database management.
 *
 * @author Briston
 */
@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    /**
     * Abstract method that returns an instance of TaskDao
     *
     * @return TaskDao object to perform database operations on Task.
     */
    public abstract TaskDao taskDao();

    /**
     *  Singleton instance of AppDatabase.
     */
    private static AppDatabase INSTANCE;

    /**
     * Returns the singleton instance of AppDatabase.
     * If the instance is not already created, a new one will be built.
     *
     * @param context Application context needed to build the database.
     * @return Singleton instance of AppDatabase
     */
    static synchronized AppDatabase getInstance(Context context)
    {
        if (INSTANCE == null)
        {
            Log.d("CIS 3334","Database test");
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "task_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }


}
