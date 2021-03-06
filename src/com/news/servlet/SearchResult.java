package com.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.news.search.NewsSearcher;
import com.news.search.SearchBean;

public class SearchResult extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 处理get请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet方法执行");
		// 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
		try {
            String queryStr = new String(request.getParameter("q").getBytes("ISO-8859-1"),"utf-8");
            System.out.println(queryStr);
            NewsSearcher newsSearcher = new NewsSearcher();
            List<SearchBean> result = newsSearcher.getResult(queryStr);
			int i = 0;
			for(SearchBean bean : result) {
				if(i == 10)
					break;
				System.out.println("bean.title: " + bean.getTitle() + " bean.snippet: "+ bean.getSnippet() + " bean.content: " + 
									bean.getContent() + " bean.keyword: " + bean.getKeyword());
				i++;
			}
			System.out.println("searchBean.result.size: " + result.size());
//			PrintWriter out = response.getWriter();
//			JSONArray jsonArray2 = JSONArray.fromObject( result );//转化成json对象
//            //JSONObject jo=JSONObject.fromObject(m);//转化Map对象
//			out.print(jsonArray2);//返给ajax请求
//			System.out.println(jsonArray2);
//            //out.print(jo);//返给ajax请求
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 处理POST请求
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
