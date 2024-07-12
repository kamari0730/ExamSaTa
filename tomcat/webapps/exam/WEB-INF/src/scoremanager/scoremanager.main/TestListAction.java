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
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TeacherDao;
import tool.Action;

public class TestListAction extends Action{
    
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");

        String entYearStr = "";
        String classNum = "";
        int entYear = 0;
        String subject = "";
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        StudentDao sDao = new StudentDao();
        SubjectDao sbDao = new SubjectDao();
        ClassNumDao cNumDao = new ClassNumDao();
        Map<String, String> errors = new HashMap<>();

        entYearStr = req.getParameter("f1");
        classNum = req.getParameter("f2");
        subject = req.getParameter("f3");

        List<String> list = cNumDao.filter(teacher.getSchool());
        Subject subject_set = sbDao.get(subject, teacher.getSchool());
        
        if(entYearStr != null){
            entYear = Integer.parseInt(entYearStr);
        }

        List<Integer> entYearSet = new ArrayList<>();
        
        for(int i = year - 10; i < year + 1; i++){
            entYearSet.add(i);
        }

        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subject);

        req.setAttribute("subject_set", subject_set);
        req.setAttribute("class_num_set", list);
        req.setAttribute("ent_year_set", entYearSet);

        req.getRequestDispatcher("test_list.jsp").forward(req, res);

    }
}
