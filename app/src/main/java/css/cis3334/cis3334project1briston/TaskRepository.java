package css.cis3334.cis3334project1briston;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

/**
 *
 */
public class TaskRepository
{
    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;


    /**
     * @param application
     */
    public TaskRepository(Application application)
    {
        AppDatabase database = Room.databaseBuilder(application, AppDatabase.class, "task_database")
                .allowMainThreadQueries()
                .build();
        taskDao = database.taskDao();
        allTasks = taskDao.getAllTasks();
    }

    /* Methods to insert, update, delete, and retrieve tasks **/

    /**
     * method to insert a task
     */
    public void insert(Task task)
    {
        taskDao.insert(task);
    }

    /**
     * Method to update a task
     */
    public void update(Task task)
    {
        taskDao.update(task);
    }

    /**
     * Method to delete a task
     */
    public void delete(Task task)
    {
        taskDao.delete(task);
    }

    /**
     * Method to retrieve all task
     */
    public LiveData<List<Task>> getAllTasks()
    {
        return allTasks;
    }

}
