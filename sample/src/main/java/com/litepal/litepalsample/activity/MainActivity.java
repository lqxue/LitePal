package com.litepal.litepalsample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.litepal.litepalsample.R;
import com.litepal.litepalsample.db.StudentDao;
import com.litepal.litepalsample.model.Student;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        findViewById(R.id.btn_save_student).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDao studentDao = new StudentDao(Student.class.getSimpleName(), 4, Student.class.getName());
                Student student = new Student();
                student.setAge("18");
                try {
                    studentDao.insert(student);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}