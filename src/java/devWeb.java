import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "devWeb", urlPatterns = {"/devWeb"})
public class devWeb extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // pegando os parâmetros do request
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");

        if (cpf.isEmpty() || senha.isEmpty()) {
            // voltando para página inicial
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resutado</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Dados nao entrados</h1>");
            out.println("<a href = 'index.html'>Voltar a página inicial</a>");
            out.println("</body>");
            out.println("</html>");
            
        } else {
            // gerando a resposta
            response.sendRedirect("admin/index.html");
            
        }

    }
}
