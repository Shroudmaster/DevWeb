<%@page import="Aplicacao.Comentario"%>
<%@page import="java.util.List"%>
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
    <link href="/admin/aprovacoes-comentario/aprovacoes-comentarios.css" rel="stylesheet">
  </head>

  <%
      List<Comentario> listComentario = (List<Comentario>) request.getAttribute("listaComentarios");
  %>

  <body>
    <%@include file="../header.jsp" %>
    
    <div class="container-fluid">
      <div class="row">
        <%@include file="../navbar.jsp" %>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
          <form action="#" method="POST">
            <h2>Aprovações de Comentários</h2>
            <div class="table-responsive">
              <table class="table table-striped table-sm">
                <thead>
                  <tr>
                    <th>Artigo</th>
                    <th>Usuario</th>
                    <th>Conteúdo</th>
                    <th>Autorizado</th>
                    <th>Outras OpÃ§Ãµes</th>
                  </tr>
                </thead>
                <tbody>
                  <% for(int i=0; i<listComentario.size(); i++) { %>
                  <% Comentario c = listComentario.get(i); %>
                  <tr>
                    <td><%=c.getArtigo().getTitulo() %></td>
                    <td><%=c.getUsuario().getNome() %></td>
                    <td><%=c.getComentario() %></td>
                    <td>
                      <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" name="aprovado<%=i %>" id="aprovado<%=i %>" value="S" <%= c.getAprovado().equals("S")? "checked": "" %>>
                        <label class="form-check-label" for="aprovado<%=i %>">Aprovado</label>
                      </div>
                    </td>
                    <td>                      
                      <a href="#" class="pl-2"><span data-toggle="tooltip" data-placement="bottom" title="Editar" data-feather="edit"></span></a>
                      <a href="#" class="pl-2"><span data-toggle="tooltip" data-placement="bottom" title="Excluir" data-feather="trash"></span></a>
                    </td>
                  </tr>
                  <tr>
                  <% } %>
                </tbody>
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
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="../../assets/js/bootstrap/bootstrap.bundle.min.js"></script>

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
