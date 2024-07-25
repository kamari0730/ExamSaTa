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
import bean.Subject;
import bean.TestListSubject;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import dao.ClassNumDao;
import tool.Action;

public class TestListSubjectExceptionAction extends Action{

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");

        String entYearStr = "";
        String classNum = "";
        String subject = "";
        int entYear = 0;
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        List<TestListSubject> listsb = null;
        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao sbDao = new SubjectDao();
        TestListSubjectDao tlsbDao = new TestListSubjectDao();
        Map<String, String> errors = new HashMap<>();

        entYearStr = req.getParameter("f1");
        classNum = req.getParameter("f2");
        subject = req.getParameter("f3");

        List<String> list = cNumDao.filter(teacher.getSchool());
        List<Integer> entYearSet = new ArrayList<>();
        List<Subject> subjects = sbDao.filter(teacher.getSchool());
        Subject subject_set = sbDao.get(subject, teacher.getSchool());

        for(int i = year - 10; i < year + 1; i++){
            entYearSet.add(i);
        }

        req.setAttribute("f1", entYearStr);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subject_set);
        req.setAttribute("subject_set", subjects);
        req.setAttribute("class_num_set", list);
        req.setAttribute("ent_year_set", entYearSet);
        if(entYearStr != null){
            entYear = Integer.parseInt(entYearStr);
            if(entYear != 0 && !classNum.equals("0") && !subject.equals("0")){
               listsb = tlsbDao.filter(entYear, classNum, subject_set, teacher.getSchool());
            }
            else{
                errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("test_list.jsp").forward(req, res);
            }
        }

    
        req.setAttribute("students", listsb);


        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);

    }
}
