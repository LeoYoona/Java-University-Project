package JavaUniversityProject;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import java.util.Random;

public class Course {
	
	List<Course> course;
	public int Java ,SQL ,Php,Csharp;
	ArrayList<String> courseList;
//	public Course(){
//		this.Java = CourseEnum.JAVA.getCode();
//		this.SQL = CourseEnum.SQL.getCode();
//		this.Php = CourseEnum.PHP.getCode();
//		this.Csharp = CourseEnum.CSHARP.getCode();		
//	}
	

	public ArrayList<String> setCourseGrades(int grade)
	{
	    int i =9;
		courseList.add(CourseEnum.JAVA.getName());
		courseList.add(CourseEnum.SQL.getName());
		courseList.add(CourseEnum.PHP.getName());
		courseList.add(CourseEnum.CSHARP.getName());
		return courseList;
	}
	
	public ArrayList<String> setJavaGrades(int grade)
	{
	
	}
	
	public int GetRandomGrade() {
		double rand = Math.random();
		int rndGrade=(int)(rand* 45+56)
	}
	
	public void getCourseEnum()
	{
		
	}

}
