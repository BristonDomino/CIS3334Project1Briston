package css.cis3334.cis3334project1briston;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        /* Set up RecyclerView, Adapters go down here **/


    }
}