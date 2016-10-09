package com.ych.dao.teacher;

import com.ych.dao.BaseDao;
import com.ych.entity.Teacher;

public interface TeacherDao extends BaseDao<Teacher> {

   public Teacher findByTeacher(String teacherId);
}
