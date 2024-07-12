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
import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TeacherDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExceptionAction extends Action{
    
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");
        

        int count;
        String classNo;
        String [] studentNo;
        String subjectcd = "";
        String [] point;
        List<Test> tests = new ArrayList<>();

        TestDao tDao = new TestDao();

        Map<String, String> errors = new HashMap<>();



        studentNo = req.getParameterValues("no");
        classNo = req.getParameter("cno");
        point = (req.getParameterValues("point"));
        subjectcd = req.getParameter("subject");
        count = Integer.parseInt(req.getParameter("count"));
        
        // List<Integer> newpoint = new ArrayList<>();
        // for(String i : point){
        //     newpoint.add(Integer.parseInt(i));
        // }
        // if(newpoint.contains())

        for(int i = 0; i < studentNo.length; i++){
            Test test = new Test();
            Student stu = new Student();
            Subject sbj = new Subject();
            School schl = new School();
            stu.setNo(studentNo[i]);
            stu.setClassNum(classNo);
            sbj.setCd(subjectcd);
            schl.setCd(teacher.getSchool().getCd());
            test.setStudent(stu);
            test.setNo(count);
            test.setPoint(Integer.parseInt(point[i]));

            test.setSubject(sbj);
            test.setSchool(schl);
            tests.add(test);
            // System.out.println(studentNo[i]);
            // System.out.println(point[i]);
            // System.out.println(subject);

        }



        boolean flag = tDao.save(tests);


        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
        
    }
    
}
