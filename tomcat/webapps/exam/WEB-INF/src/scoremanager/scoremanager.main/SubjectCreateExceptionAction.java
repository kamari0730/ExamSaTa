package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;
import java.util.*;

public class SubjectCreateExceptionAction extends Action{

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");
        SubjectDao sDao = new SubjectDao();// 科目Dao
        Map<String, String> errors = new HashMap<>();// エラーメッセージ

        String cd = req.getParameter("cd");
        String name = req.getParameter("name");
        errors.get(cd);

        Subject subjects = sDao.get(cd, teacher.getSchool());

        //  値が三文字でないならば画面遷移せずにエラーを表示する
        if (cd.length() != 3) {
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);
            // System.out.println("三文字で入力");
            errors.put("errors", "科目コードは3文字で入力してください。");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);

        //  値が重複したならば画面遷移せずにエラーを表示する
        } else if (subjects != null) {
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);
            // System.out.println("値が重複");
            errors.put("errors", "科目コードが重複しています。");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);

        // 問題がない場合はDaoを経由してDBに保存、doneに飛ぶ
        } else {
            // System.out.println("異常なし");
            Subject sub = new Subject();
            sub.setCd(cd);
            sub.setName(name);
            sub.setSchool(((Teacher)session.getAttribute("user")).getSchool());
            boolean flag = sDao.save(sub);// DBに保存
            req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
        }
    }
}
