package bean;

import java.io.Serializable;
import java.util.HashMap;

public class TestListSubject implements Serializable{

    private String studentNo;
    private String studentName;
    private int entYear;
    private String classNum;
    private Map<Integer, Integer> points;
    
    public String getStudentNo(){
        return studentNo;
    }

    public void setStudentNo(String studentNo){
        this.studentNo = studentNo;
    }

    public String getStudentName(){
        return studentName;
    }

    public void setStudentName(String studentName){
        this.studentName = studentName;
    }

    public int getEntYear(){
        return entYear;
    }

    public void setEntYear(int entYear){
        this.entYear = entYear;
    }

    public String getClassNum(){
        return classNum;
    }

    public void setClassNum(String classNum){
        this.classNum = classNum;
    }

    public Map<Integer, Integer> getPoints(){
        return points;
    }

    public void setPoints(Map<Integer, Integer> points){
        this.points = points;
    }
    
    public String getPoint(int key){
        return points.get(key);
    }

    public void putPoint(int key int value){
        this.points.put(key, value);
    }
    
}
