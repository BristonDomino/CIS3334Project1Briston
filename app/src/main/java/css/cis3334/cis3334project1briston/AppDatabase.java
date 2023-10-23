package css.cis3334.cis3334project1briston;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    /**
     * @return
     */
    public abstract TaskDao taskDao();

    private static AppDatabase INSTANCE;

    /**
     * @param context
     * @return
     */
    public static synchronized AppDatabase getInstance(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "task_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTANCE;
    }


}
