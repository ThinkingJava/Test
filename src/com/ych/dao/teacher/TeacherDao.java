package com.ych.dao.teacher;

import java.util.List;

import com.ych.dao.BaseDao;
import com.ych.entity.Teacher;

public interface TeacherDao extends BaseDao<Teacher> {
   public Teacher findByTeacher(int teacherId);
}
