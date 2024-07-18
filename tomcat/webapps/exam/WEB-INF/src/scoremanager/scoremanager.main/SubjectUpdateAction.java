package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.School;
import dao.SubjectDao; 
import dao.ClassNumDao;
import tool.Action;
import java.util.*;

public class SubjectUpdateAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // クラス一覧の取得
        HttpSession session = req.getSession();// セッション
        Teacher teacher = (Teacher)session.getAttribute("user");
        Map<String, String> errors = new HashMap<>();// エラーメッセージ

        School school = teacher.getSchool();// 学校の取得


        String cd = req.getParameter("cd");// 科目コードの取得

        SubjectDao sDao = new SubjectDao();// インスタンス化
        
        Subject sub = sDao.get(cd, school);// 変更対象の学生詳細データを取得
        Subject subjects = sDao.get(cd, teacher.getSchool());

        req.setAttribute("cd", sub.getCd());
        req.setAttribute("name", sub.getName());

        // if (subjects == null) {
        //     req.setAttribute("cd", sub.getCd());
        //     req.setAttribute("name", sub.getName());
        //     System.out.println("コードがない");
        //     errors.put("errors", "科目が存在しません。");
        //     req.setAttribute("errors", errors);
        //     req.getRequestDispatcher("subject_update.jsp").forward(req, res);
        // }

        req.getRequestDispatcher("subject_update.jsp").forward(req, res);
    }
}
