package css.cis3334.cis3334project1briston;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 *
 */
@Dao
public interface TaskDao
{
    /**
     * @param task
     */
    @Insert
    void insert(Task task);

    /**
     * @param task
     */
    @Update
    void update(Task task);

    /**
     * @param task
     */
    @Delete
    void delete(Task task);

    /**
     * @return
     */
    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAllTasks();

}
