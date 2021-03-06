package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.Article;
import dao.ArticleDAO;
import dao.DAOFactory;

public class ArticleInfoShowAction extends HttpServlet {


	public ArticleInfoShowAction() {
		super();
	}


	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "error";

		if(request.getParameter("articleid")!=null){
			int articleid =Integer.parseInt(request.getParameter("articleid"));
			ArticleDAO articledao = DAOFactory.getArticleDAOInstance();
			Gson gson = new Gson();
			try {
				if(articledao.queryById(articleid)!=null){
					Article article =articledao.queryById(articleid);
					String article_json =gson.toJson(article);
					result=article_json;
				}else{
					result="error";
				}
				out.write(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request,response);
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
