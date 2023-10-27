package css.cis3334.cis3334project1briston;

import static androidx.core.app.ActivityCompat.recreate;
import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 *
 */
public class TaskListAdapter extends ListAdapter<Task, TaskViewHolder>
{
    private final TaskViewModel taskViewModel;

    /**
     *
     * @param diffCallback
     */
    public TaskListAdapter(@NonNull DiffUtil.ItemCallback<Task> diffCallback, TaskViewModel taskViewModel)
    {
        super(diffCallback);
        this.taskViewModel = taskViewModel;
    }

    /**
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return
     */
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_task, parent, false);
        return new TaskViewHolder(itemView);
    }

    /**
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
        {
            showEditTaskDialog(view.getContext(), currentTask);
        });

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
     * @param context
     * @param task
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
     *
     */
    static class TaskDiff extends DiffUtil.ItemCallback<Task>
    {
        /**
         * @param oldItem The item in the old list.
         * @param newItem The item in the new list.
         * @return
         */
        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem)
        {
            return oldItem.getId() == newItem.getId();
        }

        /**
         * @param oldItem The item in the old list.
         * @param newItem The item in the new list.
         * @return
         */
        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem)
        {
            return oldItem.equals(newItem);
        }
    }

}



