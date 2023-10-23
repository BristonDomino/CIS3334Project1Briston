package css.cis3334.cis3334project1briston;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String taskName;
    private boolean completed;

    /* Getters, setters and maybe other methods go down here. **/

    // Constructors
    public Task(String taskName, boolean completed)
    {
        this.taskName = taskName;
        this.completed = completed;
    }

    /* Getters **/

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

}
