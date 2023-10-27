package css.cis3334.cis3334project1briston;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

/**
 *
 */
@Entity
public class Task
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String taskName;
    private boolean completed;

    /* Getters, setters and maybe other methods go down here. **/

    /**
     * Constructor
     * @param taskName
     */
    public Task(String taskName)
    {
        this.taskName = taskName;
        this.completed = completed = false;
    }

    /* Getters **/

    /**
     *
     */
    public Task()
    {

    }

    /**
     * @return
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return
     */
    public String getTaskName()
    {
        return taskName;
    }

    /**
     * @return
     */
    public boolean isCompleted()
    {
        return completed;
    }

    /* Setters **/

    /**
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @param taskName
     */
    public void setTaskName(String taskName)
    {

        this.taskName = taskName;
    }

    /**
     * @param completed
     */
    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }

    /**
     * @param obj
     * @return
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
     * @return
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }

}
