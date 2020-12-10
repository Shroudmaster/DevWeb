<%@page import="Controllers.admin.ExcluiArtigo"%>
<%@page import="java.util.Objects"%>
<%@page import="Aplicacao.Artigo"%>
<%@page import="java.util.List"%>
<%@page import="Aplicacao.Categoria"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Adm Panel</title>

    <!-- Bootstrap core CSS -->
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/admin/meus-posts/meus-posts.css" rel="stylesheet">
  </head>
  
  <%
      List<Categoria> listCategoria = (List<Categoria>) request.getAttribute("listaCategorias");
      List<Artigo> listArtigo = (List<Artigo>) request.getAttribute("listaArtigos");
      String erro = (String) session.getAttribute(ExcluiArtigo.sessionErrorKey);
      session.setAttribute(ExcluiArtigo.sessionErrorKey, null);
  %>
  
  <%!
      public String getCategoria(Artigo a, List<Categoria> list) {
        for(Categoria c: list) {
            if(c.getId() == a.getIdCategoria())
                return c.getDescricao();
        }
        return "";
      }
  %>

  <body>
    <%@include file="../header.jsp" %>
    
    <div class="container-fluid">
      <div class="row">
        <%@include file="../navbar.jsp" %>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
          <form action="#" method="POST">
            <h2>Minhas Postagens</h2>
            <h4 class="error py-2">
                <%= Objects.nonNull(erro)? "Erro: " + erro: ""  %>
            </h4>
            <div class="table-responsive">
              <table class="table table-striped table-sm">
                <thead>
                  <tr>
                    <th>Titulo</th>
                    <th>Categoria</th>
                    <th>Link</th>
                    <th>Liberar</th>
                    <th>Status</th>
                    <th>Outras Opções</th>
                  </tr>
                </thead>
                <form action="/admin/meus-posts" method="POST">
                <tbody>
                  <% for(int i=0; i<listArtigo.size(); i++) { %>
                  <% Artigo artigo = listArtigo.get(i); %>
                  <tr>
                    <td><%= artigo.getTitulo() %></td>
                    <td><%= getCategoria(artigo, listCategoria) %></td>
                    <td><a href="#" class="pl-2"><span data-feather="share"></span></a></td>
                    <td>
                      <div class="form-check form-check-inline">
                          <input class="form-check-input" type="checkbox" name="liberado<%=i%>" id="liberado<%=i%>" value="S" <%= artigo.getLiberar().equals("S")? "checked": "" %>>
                        <label class="form-check-label" for="autorized<%=i%>">Liberado</label>
                      </div>
                    </td>
                    <td><%= artigo.getAprovado().equals("S")? "Aprovado": "Pendente" %></td>
                    <td>
                      <a href="/admin/artigos/novo?id=<%=artigo.getId() %>" class="pl-2"><span data-toggle="tooltip" data-placement="bottom" title="Editar" data-feather="edit"></span></a>
                      <a href="/admin/artigos/delete?id=<%=artigo.getId() %>" class="pl-2"><span data-toggle="tooltip" data-placement="bottom" title="Excluir" data-feather="trash"></span></a>
                    </td>
                  </tr>
                  <tr>
                  <input type="hidden" name="id[]" value="<%= artigo.getId() %>">
                  <% } %>
                </tbody>
                </form>
              </table>
            </div>
            <button type="submit" class="btn float-right btn-primary">Salvar</button>
          </form>
        </main>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="/assets/js/vendor/jquery-slim.min.js"></script>
    <script src="/assets/js/bootstrap/bootstrap.bundle.min.js"></script>

    <!-- Icons -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
      feather.replace()
      $(function () {
        $('[data-toggle="tooltip"]').tooltip()
      })
    </script>   
  </body>
</html>
