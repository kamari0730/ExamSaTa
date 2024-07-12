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
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TeacherDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action{
    
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");

        String entYearStr = "";
        String classNum = "";
        String subject = "";
        String testNum = "";
        int entYear = 0;
        int num = 0;
        List<Student> students = null;
        List<Test> tests = null;
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        StudentDao sDao = new StudentDao();
        SubjectDao sbDao = new SubjectDao();
        ClassNumDao cNumDao = new ClassNumDao();
        TestDao tDao = new TestDao();

        Map<String, String> errors = new HashMap<>();

        entYearStr = req.getParameter("f1");
        classNum = req.getParameter("f2");
        subject = req.getParameter("f3");
        testNum = req.getParameter("f4");


        List<String> list = cNumDao.filter(teacher.getSchool());
        List<Subject> subjects = sbDao.filter(teacher.getSchool());
        
        if(entYearStr != null && classNum != null && subject != null && testNum != null){
            entYear = Integer.parseInt(entYearStr);
            num = Integer.parseInt(testNum);
            if(entYear != 0 && !classNum.equals("0") && !subject.equals("0") && num != 0){

                Subject subject_set = sbDao.get(subject, teacher.getSchool());
                
                tests = tDao.filter(entYear, classNum, subject_set, num, teacher.getSchool());
                req.setAttribute("f1", entYear);
                req.setAttribute("f2", classNum);
                req.setAttribute("f3", subject_set);
                req.setAttribute("f4", testNum);

            }
        }
        else{
            errors.put("filter", "入学年度とクラスと科目と回数を選択してください");
            req.setAttribute("errors", errors);

        }

        List<Integer> entYearSet = new ArrayList<>();
        List<Integer> testNums = new ArrayList<>();
        

        
        
        for(int i = year - 10; i < year + 1; i++){
            entYearSet.add(i);
        }

        for(int i = 1; i <= 2; i++){
            testNums.add(i);
        }

        

        req.setAttribute("students", students);
        req.setAttribute("class_num_set", list);
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("subjects", subjects);
        req.setAttribute("test_num_set", testNums);
        req.setAttribute("tests",tests);

        req.getRequestDispatcher("test_regist.jsp").forward(req, res);

    }
}
