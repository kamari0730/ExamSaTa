package kouka;

import tool.Page;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/kouka/Question4"})
public class Question4 extends HttpServlet{
    public void doGet(
        HttpServletRequest request, HttpServletResponse response
    )throws ServletException, IOException{
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        request.setCharacterEncoding("UTF-8");
        
        String user = request.getParameter("user");
        System.out.println("お名前: " + user);

        Page.header(out);
        request.getRequestDispatcher("Question4output.jsp").include(request,response);
        out.println("<p>入力されたお名前は・・・</p>");
        out.println("<p>" + user + "</p>");
        out.println("<p>ですね!</p>");
        Page.footer(out);
    }
}
