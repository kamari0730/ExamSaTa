package scoremanager.main;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import bean.Student;
import bean.Teacher;
import bean.School;
import dao.StudentDao;
import dao.ClassNumDao;
import tool.Action;
import java.util.*;
 
public class StudentUpdateAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
 
        String no = req.getParameter("no");// 学生番号の取得
 
        StudentDao sDao = new StudentDao();// インスタンス化
        Student stu = sDao.get(no);// 変更対象の学生詳細データを取得
 
        // クラス一覧の取得
        HttpSession session = req.getSession();// セッション
        Teacher teacher = (Teacher)session.getAttribute("user");
 
        School school = teacher.getSchool();
       
        ClassNumDao cNumDao = new ClassNumDao();
 
        List<String> class_list = cNumDao.filter(teacher.getSchool());
 
        req.setAttribute("student", stu);
        req.setAttribute("school", school);
 
        req.getRequestDispatcher("student_update.jsp").forward(req, res);
    }
}