package css.cis3334.cis3334project1briston;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * TaskDao defines the data access methods for the Task database operations.
 * It provides methods to insert, update, delete, and retrieve tasks.
 *
 * @author Briston
 */
@Dao
public interface TaskDao
{
    /**
     * Inserts a new task into the database.
     *
     * @param task The task object to be inserted.
     */
    @Insert
    void insert(Task task);

    /**
     * Updates an existing task in the database.
     *
     * @param task The task object to be updated.
     */
    @Update
    void update(Task task);

    /**
     * Deletes a task from the database.
     *
     * @param task The task object to be deleted.
     */
    @Delete
    void delete(Task task);

    /**
     * Retrieves all tasks from the database.
     *
     * @return LiveData containing a list of all tasks.
     */
    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAllTasks();

}
