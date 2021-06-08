package sg.edu.rp.c346.id19013886.l07_simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etToDo;
    Button btnAdd, btnClear;
    ListView lvTask;

    ArrayList<String> alTask;
    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etToDo = findViewById(R.id.editTextToDo);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        lvTask = findViewById(R.id.listViewTask);

        alTask = new ArrayList<>();

        aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTask);

        lvTask.setAdapter(aaTask);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etToDo.getText().toString();
                if (!etToDo.getText().toString().isEmpty()) {
                    alTask.add(task);
                }
                aaTask.notifyDataSetChanged();
                etToDo.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etToDo.getText().toString();
                alTask.clear();
                aaTask.notifyDataSetChanged();
            }
        });


    }
}