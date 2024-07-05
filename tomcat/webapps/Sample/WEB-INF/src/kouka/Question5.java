package kouka;

import tool.Page;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(urlPatterns={"/kouka/Question5"})
public class Question5 extends HttpServlet{
    
    public void doGet(
        HttpServletRequest request, HttpServletResponse response
    )throws ServletException, IOException{
        PrintWriter out = response.getWriter();
        Page.header(out);
        try{
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource)ic.lookup("java:/comp/env/jdbc/kouka");
            Connection con = ds.getConnection();

            PreparedStatement st = con.prepareStatement("select * from User");
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                out.println(rs.getInt("UserId"));
                out.println("|");
                out.println(rs.getString("UserName"));
                out.println("|");
                out.println(rs.getString("MailAddress"));
                out.println("<br>");
            }

            PreparedStatement st1 = con.prepareStatement("select * from Student");
            ResultSet rs1 = st1.executeQuery();

            while(rs1.next()){
                out.println(rs1.getInt("StudentId"));
                out.println("|");
                out.println(rs1.getString("StudentName"));
                out.println("|");
                out.println(rs1.getString("Course"));
                out.println("<br>");
            }

            st.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace(out);
        }
        Page.footer(out);
    }
}