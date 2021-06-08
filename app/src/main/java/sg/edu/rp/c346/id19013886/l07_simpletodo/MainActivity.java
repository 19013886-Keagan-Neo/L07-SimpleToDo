package sg.edu.rp.c346.id19013886.l07_simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etToDo;
    Button btnAdd, btnClear, btnDelete;
    ListView lvTask;

    Spinner spnAddRemove;

    ArrayList<String> alTask;
    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etToDo = findViewById(R.id.editTextToDo);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnClear = findViewById(R.id.btnClear);
        lvTask = findViewById(R.id.listViewTask);
        spnAddRemove = findViewById(R.id.spinnerAddRemove);

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
                alTask.clear();
                aaTask.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etToDo.getText().toString().isEmpty()){
                    int pos = Integer.parseInt(etToDo.getText().toString());
                    if (aaTask.getCount() > pos) {
                        alTask.remove(pos);
                    } else if (aaTask.getCount() == 0) {
                        Toast.makeText(MainActivity.this, "You don't have any task to remove",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(MainActivity.this, "Index " + pos + " have been deleted!",
                            Toast.LENGTH_LONG).show();

                    aaTask.notifyDataSetChanged();
                    etToDo.setText("");

                } else {
                    Toast.makeText(MainActivity.this, "Index is empty! Please enter something!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        // Add button
                        etToDo.setHint(R.string.addHint);
                        etToDo.setInputType(InputType.TYPE_CLASS_TEXT);
                        etToDo.setText("");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;

                    case 1:
                        // Delete button
                        etToDo.setHint(R.string.removeHint);
                        etToDo.setInputType(InputType.TYPE_CLASS_NUMBER);
                        etToDo.setText("");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}