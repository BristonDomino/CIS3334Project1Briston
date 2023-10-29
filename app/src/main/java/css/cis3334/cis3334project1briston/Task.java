package css.cis3334.cis3334project1briston;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

/**
 * Task represents a task object that contains information about a particular task.
 * Each task has an ID, a name, and a completion status.
 *
 * @author Briston
 */
@Entity
public class Task
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String taskName;
    private boolean completed;

    /**
     * Constructor to create a new Task object with a specified name.
     * The completion status is initialized to false.
     * @param taskName The name of the Task.
     */
    @Ignore
    public Task(String taskName)
    {
        this.taskName = taskName;
        this.completed = false;
    }

    /* Getters **/

    /**
     * Default constructor.
     */
    public Task()
    {

    }

    /**
     * Retrieves the ID of the Task.
     *
     * @return The task ID
     */
    public int getId()
    {
        return id;
    }

    /**
     * Retrieves the name of the task.
     *
     * @return The task name.
     */
    public String getTaskName()
    {
        return taskName;
    }

    /**
     * Checks if the task is completed or not.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean isCompleted()
    {
        return completed;
    }

    /* Setters **/

    /**
     * Sets teh ID for the task.
     *
     * @param id The task ID to set.
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Sets the name for the task.
     *
     * @param taskName The task name to set.
     */
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    /**
     * Sets the completion status for the task.
     *
     * @param completed The completion status to set.
     */
    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }

    /**
     * Compares the current Task object to another object.
     * The tasks are considered equal if their IDs are the same.
     *
     * @param obj The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        Task task = (Task) obj;

        return id == task.id;
    }

    /**
     * Generates a hash code for the Task object based on its ID.
     *
     * @return The hash code for the Task object.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }

}
