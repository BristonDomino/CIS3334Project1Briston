package css.cis3334.cis3334project1briston;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity class that handles the main user interface and interaction of the app.
 * This activity displays a list of task and provides options to add and remove task.
 *
 * @author Briston
 */
public class MainActivity extends AppCompatActivity
{

    /**
     * ViewModel for task operations.
     */
    private TaskViewModel taskViewModel;

    /**
     * Adapter for displaying the list of tasks in the RecyclerView
     */
    private TaskListAdapter adapter;

    /**
     * Called when activity is starting.
     * Initializes the UI components, sets up the RecyclerView, and sets up button click listeners.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Hides keyboard when user touches outside the input area
        findViewById(R.id.mainLayout).setOnTouchListener((v, event) ->
        {
            if (getCurrentFocus() != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                getCurrentFocus().clearFocus();
            }
            return false;
        });


        // Initialize ViewModel for tasks
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        // setup RecyclerView adapter
        adapter = new TaskListAdapter(new TaskListAdapter.TaskDiff(), taskViewModel);
        recyclerView.setAdapter(adapter);

        // Observe and update the list of tasks from ViewModel
        taskViewModel.getAllTasks().observe(this, tasks -> adapter.submitList(tasks));

        // Initialize UI buttons for interactions
        Button addTaskButton = findViewById(R.id.addTaskButton);
        Button removeTaskButton = findViewById(R.id.removeTaskButton);

        // Listener for when the user clicks on add task button and displays Task added using a Toast message
        addTaskButton.setOnClickListener(view ->
        {
            Task newTask = new Task("");
            taskViewModel.insert(newTask);
            Toast.makeText(this, "Task added!", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        });

        // Listener for when removing the task after the user clicks on Remove task and displays a Toast message saying Task Removed!
        removeTaskButton.setOnClickListener(view ->
        {
            List<Task> tasksToRemove = new ArrayList<>();
            for (int i = 0; i < recyclerView.getChildCount(); i++)
            {
                View child = recyclerView.getChildAt(i);
                TaskViewHolder viewHolder = (TaskViewHolder) recyclerView.getChildViewHolder(child);

                if (viewHolder.isTaskChecked())
                {
                    Task taskToRemove = adapter.getCurrentList().get(i);
                    tasksToRemove.add(taskToRemove);
                }
            }
            for (Task task : tasksToRemove)
            {
                taskViewModel.delete(task);
            }

            // Get the currently focused view, which is EditText
            View focusedView = getCurrentFocus();
            if (focusedView != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
            }

            Toast.makeText(this, "Task removed!", Toast.LENGTH_SHORT).show();
        });
    }
}
