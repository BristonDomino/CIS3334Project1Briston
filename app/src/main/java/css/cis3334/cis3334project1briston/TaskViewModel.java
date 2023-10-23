package css.cis3334.cis3334project1briston;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class TaskViewModel extends AndroidViewModel
{
    private TaskRepository repository;

    private LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application)
    {
        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
    }

    /**
     * Expose the method to insert a task to the UI
     */
    public void insert(Task task)
    {
        repository.insert(task);
    }

    /**
     * Expose the method to update a task to the UI
     */
    public void update(Task task)
    {
        repository.update(task);
    }

    /**
     * Expose the method to delete a task to the UI
     */
    public void delete(Task task)
    {
        repository.delete(task);
    }

    /**
     * Expose the method to retrieve all tasks to the UI
     */
    public LiveData<List<Task>> getAllTasks()
    {
        return allTasks;
    }

}
