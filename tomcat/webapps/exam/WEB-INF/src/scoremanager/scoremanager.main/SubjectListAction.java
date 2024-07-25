package scoremanager.main;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import dao.SchoolDao;
import java.util.*;

import tool.Action;

// 一覧表示
public class SubjectListAction extends Action{
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();// セッション
        Teacher teacher = (Teacher)session.getAttribute("user");

        SubjectDao sDao = new SubjectDao();// 科目Dao

        // DBからデータ取得
        // ログインユーザーの学校コードをもとに科目コードの一覧を取得

        List<Subject> subjects = sDao.filter(teacher.getSchool());

        req.setAttribute("subjects", subjects);

        req.getRequestDispatcher("subject_list.jsp").forward(req, res);
    }
}
