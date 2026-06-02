package kadai1;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BbsServlet
 */
@WebServlet("/BbsServlet")
public class BbsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Step4：取得したメッセージを格納するリスト
	List<String> articles = new ArrayList<String>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BbsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの文字コード設定
		request.setCharacterEncoding("utf-8");
		
		// Step3：リクエストパラメータの取得
		String action = request.getParameter("action");
		
		// actionキーによって処理を分岐
		if (action.equals("write")) {
			String name = request.getParameter("name");
			String message = request.getParameter("message");
			
			// 書き込んだ時間を取得
			LocalDateTime now = LocalDateTime.now();
			// 時間の書式を整形
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			String formattedNow = formatter.format(now);
			
			// 「<a href="/jmaster/BbsServlet?action=remove">[削除]</a>」というアンカータグを想像できるようにしよう！
			String removeLink = "<a href=\"/jmaster/BbsServlet?action=remove\">[削除]</a>";
			message = formattedNow + "：" + name + "：" + message + removeLink;
			articles.add(message);
		} else if (action.equals("remove")) {
			articles = new ArrayList<String>();
		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"utf-8\" />");
			out.println("<title>掲示板</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<p>操作エラー</p>");
			out.println("<a href=\"/jmaster/kadai1/bbs.html\">戻る</a>");
			out.println("</body>");
			out.println("</html>");
		}
		
		// Step2：サーブレットからHTMLページの出力
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\" />");
		out.println("<title>掲示板</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("\t<form action=\"/jmaster/BbsServlet\" method=\"post\">");
		out.println("\t\t<p>名前：</p>");
		out.println("\t\t<input type=\"text\" name=\"name\" />");
		out.println("\t\t<p>メッセージ：</p>");
		out.println("\t\t<textarea name=\"message\"></textarea><br />");
		out.println("\t\t<button>書き込み</button>");
		out.println("\t</form>");
		
		// Step3：取得したリクエストパラメータの表示
		for (String article : articles) {
			out.println("<hr />");
			out.println(article);
		}
		
		
		out.println("</body>");
		out.println("</html>");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
