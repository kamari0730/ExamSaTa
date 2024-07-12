package bean;
 
import java.io.Serializable;
 
public class TestListStudent implements Serializable{
   
    private String subjectCd;// 科目コード
 
    private String subjectName;// 科目名

    private int num;

    private int point;
 
    public String getSubjectCd() {
        return subjectCd;
    }
 
    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }
 
    public String getSubjectName() {
        return subjectName;
    }
 
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getNum(){
        return num;
    }

    public void setNum(int num){
        this.num = num;
    }

    public int getPoint(){
        return point;
    }

    public void setPoint(int point){
        this.point = point;
    }
}
 