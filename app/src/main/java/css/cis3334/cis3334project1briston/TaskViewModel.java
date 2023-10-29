package css.cis3334.cis3334project1briston;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * ViewModel class that provides a layer of abstraction between UI and the data source.
 * It manages and prepares the data for the UI components and handles configuration changes.
 */
public class TaskViewModel extends AndroidViewModel
{
    private TaskRepository repository;

    private LiveData<List<Task>> allTasks;

    /**
     * Constructor for TaskViewModel. Initializes the repository and fetches all tasks.
     *
     * @param application The application context.
     */
    public TaskViewModel(@NonNull Application application)
    {
        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
    }

    /**
     * Inserts a task into the database.
     *
     * @param task The task to be inserted.
     */
    public void insert(Task task)
    {
        repository.insert(task);
    }

    /**
     * Updates an existing task in the database.
     *
     * @param task The task to be updated.
     */
    public void update(Task task)
    {
        repository.update(task);
    }

    /**
     * Deletes a task from the database.
     *
     * @param task The task to be deleted.
     */
    public void delete(Task task)
    {
        repository.delete(task);
    }

    /**
     * Retrieves all task from the database.
     *
     * @return LiveData list of all tasks.
     */
    public LiveData<List<Task>> getAllTasks()
    {
        return allTasks;
    }

}
