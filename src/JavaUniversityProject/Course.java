package JavaUniversityProject;

import java.util.Hashtable;
import java.util.Random;

public class Course {	
	
	public int courseGrade;
	public CourseEnum courseName;	
	public Hashtable<CourseEnum, Integer> gradedCourses;
	
	public Course()
	{
		this.gradedCourses = new Hashtable<CourseEnum, Integer>();
		this.gradedCourses=	getGradedCourses(this.gradedCourses);	//pass empty hash-table and get a filled on return
	}
	
	public int setGrade() {
		//get some random grade between 30 and 100
		this.courseGrade = setRandomGradeInRange(30, 100); 
		return this.courseGrade;
	}

	
	public Hashtable<CourseEnum, Integer> getGradedCourses(Hashtable<CourseEnum, Integer> gradedCourses) {		
		//set the grade for every courses 
		this.courseGrade = setRandomGradeInRange(30, 100); 
		gradedCourses.put(CourseEnum.CSHARP, setGrade());
		gradedCourses.put(CourseEnum.JAVA, setGrade());
		gradedCourses.put(CourseEnum.PHP, setGrade());
		gradedCourses.put(CourseEnum.SQL, setGrade());
		return gradedCourses;
	}
	
	
	private static int setRandomGradeInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

}
