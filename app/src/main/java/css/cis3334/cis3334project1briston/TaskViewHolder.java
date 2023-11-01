package css.cis3334.cis3334project1briston;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;


/**
 * ViewHolder for Task items in the RecyclerView. It defines the interaction of
 * UI elements within the item such as EditText, CheckBox, and Button.
 */
public class TaskViewHolder extends RecyclerView.ViewHolder
{
     EditText editTextViewTaskName;

     TaskViewModel taskViewModel;

     // private TaskListAdapter adapter;

     Task currentTask;

     CheckBox checkBoxCompleted;

     // save button
     Button doneButton;


    /**
     * Constructor for TaskViewHolder. Initializes UI elements and setup listeners.
     *
     * @param itemView The current item view.
     */
    public TaskViewHolder(@NonNull View itemView)
    {
        super(itemView);
        editTextViewTaskName = itemView.findViewById(R.id.task_text);
        checkBoxCompleted = itemView.findViewById(R.id.task_checkbox);
        doneButton = itemView.findViewById(R.id.doneButton);

        // Initialize the taskViewModel
        taskViewModel = new ViewModelProvider((AppCompatActivity) itemView.getContext()).get(TaskViewModel.class);

        // Listener for EditText
        editTextViewTaskName.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus)
            {
                currentTask.setTaskName(editTextViewTaskName.getText().toString());
                taskViewModel.update(currentTask);

            }
        });

        // Listener For CheckBox
        checkBoxCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
            currentTask.setCompleted(isChecked);
            taskViewModel.update(currentTask);
        });

        // not working
//        Log.d("CIS 3334", " before keyboard");
//        editTextViewTaskName.setOnKeyListener((v, keyCode, event) ->
//        {
//            Log.d("CIS 3334", "keyboard");
//            if (keyCode == EditorInfo.IME_ACTION_DONE || (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
//            {
//
//                Log.d("CIS 3334", "keyboard");
//
//                // Hide the keyboard
//                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//
//                return true;  // consume the event
//            }
//            return false;
//        });

        // code for done button
        doneButton.setOnClickListener(view -> {
            if (currentTask != null)
            {
                currentTask.setTaskName(editTextViewTaskName.getText().toString());
                taskViewModel.update(currentTask);

                // Hide the keyboard
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                editTextViewTaskName.clearFocus();
            }
            else
            {
                Log.d("TaskViewHolder", "currentTask is null");
            }
        });
    }

    /**
     * Checks if the task is marked as completed.
     *
     * @return True if the task is checked as completed, otherwise false
     */
    public boolean isTaskChecked()
    {
        return checkBoxCompleted.isChecked();
    }


}
