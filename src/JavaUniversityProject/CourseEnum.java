package JavaUniversityProject;

public enum CourseEnum {
	JAVA(1, "Java"),
	PHP(2, "Php"),
	SQL(3, "Sql"),
	CSHARP(4, "CSharp");
	
	private final int code;
	private final String name;
	
	private CourseEnum(int code, String name)
	{
		this.code=code;
		this.name=name;		
	}
	public int getCode() {
		return code;
	}
	public String getName() {
		return name;
	}

}
