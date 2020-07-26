package com.litepal.litepalsample.db;

import com.litepal.litepalsample.model.Student;

public class StudentDao extends BaseDBDao<Student> {
    public StudentDao(String daName, int version, String tableName) {
        super(daName, version, tableName);
    }
}
