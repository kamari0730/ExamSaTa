package tool;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns={"/kadai/chap10/*"})
public class EncodingFilter_kadai_chap10 implements Filter{
    
    public void doFilter(
        ServletRequest request, ServletResponse response,FilterChain chain
    )throws IOException, ServletException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        // System.out.println("フィルタの前処理");

        chain.doFilter(request, response);
        System.out.println("フィルタmondai8の処理");
    }

    public void init(FilterConfig FilterConfig){
        System.out.println("フィルターMondai8が生成されました。");
    }

    public void destroy(){
        System.out.println("フィルターMondai8が破棄されました。");
    }
}
