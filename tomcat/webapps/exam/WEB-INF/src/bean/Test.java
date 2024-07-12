package bean;

import java.io.Serializable;
public class Test extends User implements Serializable{
    
    private String stundentNo;
    private String subjectCd;
    private String schoolCd;
    private int no;
    private int point;
    private String classNum;
    private School school;
    private Student student;
    private Subject subject;

    public String getStudentNo(){
        return stundentNo;
    }

    public void setStudentNo(String studentNo){
        this.stundentNo = studentNo;
    }
    public String getSubjectCd(){
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd){
        this.subjectCd = subjectCd;
    }
    public String getSchoolCd(){
        return schoolCd;
    }

    public void setSchoolCd(String schoolCd){
        this.schoolCd = schoolCd;
    }
    public int getNo(){
        return no;
    }

    public void setNo(int no){
        this.no = no;
    }

    public int getPoint(){
        return point;
    }

    public void setPoint(int point){
        this.point = point;
    }

    public String getClassNum(){
        return classNum;
    }

    public void setClassNum(String classNum){
        this.classNum = classNum;
    }

    public School getSchool(){
        return school;
    }

    public void setSchool(School school){
        this.school = school;
    }

    public Student getStudent(){
        return student;
    }

    public void setStudent(Student student){
        this.student = student;
    }

    public Subject getSubject(){
        return subject;
    }

    public void setSubject(Subject subject){
        this.subject = subject;
    }

}
