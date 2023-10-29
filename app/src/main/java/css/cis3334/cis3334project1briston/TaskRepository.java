package css.cis3334.cis3334project1briston;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Repository class that abstracts access to the data sources.
 * It manages query threads and allows multiple repositories to use the databases.
 */
public class TaskRepository
{
    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();



    /**
     * Constructor for TaskRepository
     *
     * @param application Application instance to build the database.
     */
    public TaskRepository(Application application)
    {
        AppDatabase database = AppDatabase.getInstance(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getAllTasks();
    }

    /* Methods to insert, update, delete, and retrieve tasks **/

    /**
     * Insert a new Task into the database.
     *
     * @param task Task to be inserted.
     */
    public void insert(Task task)
    {
        executor.execute(() -> taskDao.insert(task));

    }

    /**
     * Update an existing task in the database.
     *
     * @param task Task to be updated.
     */
    public void update(Task task)
    {
        executor.execute(() -> taskDao.update(task));

    }

    /**
     * Delete a task from the database.
     *
     * @param task Task to be deleted.
     */
    public void delete(Task task)
    {
        executor.execute(() -> taskDao.delete(task));

    }

    /**
     * Retrieve all tasks from the database.
     *
     * @return List of all tasks.
     */
    public LiveData<List<Task>> getAllTasks()
    {
        return allTasks;
    }

}
