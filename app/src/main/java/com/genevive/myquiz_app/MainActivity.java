package com.genevive.myquiz_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;



import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    EditText question, answer, edtId;
    Button add, update, delete;

    ListView newquestions;

    List<Quiz> data = new ArrayList<>();

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);


        question = findViewById(R.id.Type_Question);
        answer = findViewById(R.id.Type_Answer);
        edtId = findViewById(R.id.TypeID);
        add = findViewById(R.id.add_ID);
        update = findViewById(R.id.update_ID);
        delete = findViewById(R.id.delete_ID);
        newquestions = (ListView) findViewById(R.id.list);



        refreshData();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quiz quiz = new Quiz(Integer.parseInt(edtId.getText().toString()), question.getText().toString(), answer.getText().toString());
                databaseHelper.addData(quiz);
                refreshData();
                Toast.makeText(getApplicationContext(), "Data added successfully", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quiz quiz = new Quiz(Integer.parseInt(edtId.getText().toString()), question.getText().toString(), answer.getText().toString());
                databaseHelper.updateData(quiz);
                refreshData();
                Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quiz quiz = new Quiz(Integer.parseInt(edtId.getText().toString()), question.getText().toString(), answer.getText().toString());
                databaseHelper.deleteData(quiz);
                refreshData();
                Toast.makeText(getApplicationContext(), "Data deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });



    }


    private void refreshData(){
        data = databaseHelper.getAllQuiz();
        QuizAdapter quizAdapter = new QuizAdapter(MainActivity.this, data, edtId, question, answer);
        newquestions.setAdapter(quizAdapter);
    }

}
