<<<<<<< HEAD
package com.ych.dao.attend;

import java.util.List;

import com.ych.dao.BaseDao;
import com.ych.entity.Attend;

public interface AttendDao extends BaseDao<Attend>{
	public List<Attend> findByAttend(int attendId);
	public List<Attend> findByAttendCourse(String courseid);
	public List<Attend> findByAttendCourseToYesterday(String courseid);
	public int getCountByWhereToDay(String courseid);
	public int getCountByWhereYesterDay(String courseid);
	public boolean findIsExitAttend(String studentId,String courseId);
}
=======
<<<<<<< HEAD
package com.ych.dao.attend;

import java.util.List;

import com.ych.dao.BaseDao;
import com.ych.entity.Attend;

public interface AttendDao extends BaseDao<Attend>{
	public List<Attend> findByAttend(int attendId);
}
=======
package com.ych.dao.attend;

import java.util.List;

import com.ych.dao.BaseDao;
import com.ych.entity.Attend;

public interface AttendDao extends BaseDao<Attend>{
	public List<Attend> findByAttend(int attendId);
}
>>>>>>> eb63eded9899513af9538a187ddf701ead10b135
>>>>>>> 2d47266740bc805e9dd402192a7321ac5ee26525
