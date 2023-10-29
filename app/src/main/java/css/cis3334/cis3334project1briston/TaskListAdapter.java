package css.cis3334.cis3334project1briston;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

/**
 * Adapter class for the Task list.
 * Manages data between the database and the UI.
 */
public class TaskListAdapter extends ListAdapter<Task, TaskViewHolder>
{
    private final TaskViewModel taskViewModel;

    /**
     * Constructor for the TaskListAdapter.
     *
     * @param diffCallback  Utility tool to calculate the difference between two lists.
     * @param taskViewModel The ViewModel containing data for the UI.
     */
    public TaskListAdapter(@NonNull DiffUtil.ItemCallback<Task> diffCallback, TaskViewModel taskViewModel)
    {
        super(diffCallback);
        this.taskViewModel = taskViewModel;
    }

    /**
     * Called when RecyclerView needs a new {@link TaskViewHolder}.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return          New ViewHolder.
     */
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_task, parent, false);
        return new TaskViewHolder(itemView);
    }

    /**
     * Binds the data to the {@link TaskViewHolder}.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position)
    {
        Task currentTask = getItem(position);
        holder.currentTask = currentTask;
        holder.editTextViewTaskName.setText(currentTask.getTaskName());
        holder.checkBoxCompleted.setChecked(currentTask.isCompleted());

        holder.editTextViewTaskName.setOnClickListener(view ->
                showEditTaskDialog(view.getContext(), currentTask));

        holder.editTextViewTaskName.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE)
            {
                v.clearFocus();
                return true;
            }
            return false;
        });

        holder.editTextViewTaskName.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String updatedTaskName = holder.editTextViewTaskName.getText().toString();
                Task updatedTask = new Task(updatedTaskName);
                updatedTask.setId(currentTask.getId());
                taskViewModel.update(updatedTask);
                v.clearFocus();
                return true;
            }
            return false;
        });
    }

    /**
     * Display a dialog to edit a tasks.
     *
     * @param context Context in which the dialog is shown.
     * @param task    Task to be edited.
     */
    private void showEditTaskDialog(Context context, Task task)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit task");

        EditText input = new EditText(context);
        input.setText(task.getTaskName());
        builder.setView(input);


        builder.setPositiveButton("OK", (dialog, which) -> {
            String newTaskName = input.getText().toString();
            task.setTaskName(newTaskName);
            taskViewModel.update(task);

            // Refresh the activity so the changes get applied
            Intent refresh = new Intent(context, MainActivity.class);
            context.startActivity(refresh);
            ((Activity) context).finish();

            Log.d("cis3334", "after ok");
        }).setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());


        //builder.setOnDismissListener(dialog -> {
//            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
//        });

       builder.show();


    }

    /**
     * Utility class to calculate the difference between two lists.
     */
    static class TaskDiff extends DiffUtil.ItemCallback<Task>
    {
        /**
         * Check if two tasks are the same.
         *
         * @param oldItem The item in the old list.
         * @param newItem The item in the new list.
         * @return         True if items are the same, false otherwise.
         */
        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem)
        {
            return oldItem.getId() == newItem.getId();
        }

        /**
         * Check if the contents of two tasks are the same
         *
         * @param oldItem The item in the old list.
         * @param newItem The item in the new list.
         * @return         True if contents are the same, false otherwise.
         */
        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem)
        {
            return oldItem.equals(newItem);
        }
    }

}



