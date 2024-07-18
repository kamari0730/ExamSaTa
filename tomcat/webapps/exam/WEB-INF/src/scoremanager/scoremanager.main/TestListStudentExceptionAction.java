package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Teacher;
import bean.Student;
import bean.Subject;
import bean.TestListSubject;
import bean.TestListStudent;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import dao.TestListStudentDao;
import dao.ClassNumDao;
import tool.Action;

public class TestListStudentExceptionAction extends Action{

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");


        String classNum = "";
        String studentId = "";
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        Student stu = new Student();
        List<TestListStudent> liststu = null;
        ClassNumDao cNumDao = new ClassNumDao();
        StudentDao stuDao = new StudentDao();
        SubjectDao sbDao = new SubjectDao();
        TestListStudentDao tlstuDao = new TestListStudentDao();
        Map<String, String> errors = new HashMap<>();

        studentId = req.getParameter("f4");

        List<String> list = cNumDao.filter(teacher.getSchool());
        List<Subject> subjects = sbDao.filter(teacher.getSchool());
        stu = stuDao.get(studentId);
        if(studentId != null){
            liststu = tlstuDao.filter(stu);
        }
        else{
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
            req.setAttribute("errors", errors);
        }
        
        List<Integer> entYearSet = new ArrayList<>();
        for(int i = year - 10; i < year + 1; i++){
            entYearSet.add(i);
        }
        
        req.setAttribute("student", stu);
        req.setAttribute("studentss", liststu);
        req.setAttribute("subject_set", subjects);
        req.setAttribute("class_num_set", list);
        req.setAttribute("ent_year_set", entYearSet);
        if(studentId != null){
            req.getRequestDispatcher("test_list_student.jsp").forward(req, res);

        }
        else{
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
        }
        

    }
}
