<<<<<<< HEAD
package com.ych.dao.student;

import com.ych.dao.BaseDao;
import com.ych.entity.Student;

public interface StudentDao extends BaseDao<Student>{
	public Student findByStudent(String studentId);
}
=======
<<<<<<< HEAD
package com.ych.dao.student;

import java.util.List;

import com.ych.dao.BaseDao;
import com.ych.entity.Student;

public interface StudentDao extends BaseDao<Student>{
	public List<Student> findByStudent(int studentId);
}
=======
package com.ych.dao.student;

import java.util.List;

import com.ych.dao.BaseDao;
import com.ych.entity.Student;

public interface StudentDao extends BaseDao<Student>{
	public List<Student> findByStudent(int studentId);
}
>>>>>>> eb63eded9899513af9538a187ddf701ead10b135
>>>>>>> 2d47266740bc805e9dd402192a7321ac5ee26525
