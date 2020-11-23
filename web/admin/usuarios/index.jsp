<%@page import="java.util.List"%>
<%@page import="Aplicacao.Usuario"%>
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
  <link href="/admin/usuarios/usuarios.css" rel="stylesheet">
</head>

<body>
  <%@include file="../header.jsp" %>
    
  <%
      List<Usuario> listUsuarios = (List<Usuario>) request.getAttribute("listaUsuarios");
  %>

  <div class="container-fluid">
    <div class="row">
        <%@include file="../navbar.jsp" %>

      <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
        <form action="" method="POST">
          <h2>Usuários</h2>
          <div class="table-responsive">
            <table class="table table-striped table-sm">
              <thead>
                <tr>
                  <th>Nome</th>
                  <th>E-Mail</th>
                  <th>CPF</th>
                  <th>Papel</th>
                  <th>Autorizado</th>
                </tr>
              </thead>
              <tbody>
                  <% for(int i=0; i< listUsuarios.size(); i++) { %>
                  <% Usuario u = listUsuarios.get(i); %>
                <tr>
                    <td><%= u.getNome() %></td>
                  <td><%= u.getEmail() %></td>
                  <td><%= u.getCpf()%></td>
                  <td>
                    <div class="form-check form-check-inline">
                      <input class="form-check-input" type="radio" name="papelradio<%=i%>" id="radio1<%=i%>" value="0" <%= u.getPapel() == 0? "checked": "" %>>
                      <label class="form-check-label" for="radio1<%=i%>">Administrador</label>
                    </div>
                    <div class="form-check form-check-inline">
                      <input class="form-check-input" type="radio" name="papelradio<%=i%>" id="radio2<%=i%>" value="1" <%= u.getPapel() == 1? "checked": "" %>>
                      <label class="form-check-label" for="radio2<%=i%>">Autor</label>
                    </div>
                    <div class="form-check form-check-inline">
                      <input class="form-check-input" type="radio" name="papelradio<%=i%>" id="radio3<%=i%>" value="2" <%= u.getPapel() == 2? "checked": "" %>>
                      <label class="form-check-label" for="radio3<%=i%>">Comentarista</label>
                    </div>
                  </td>
                  <td>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" name="aprovado<%=i%>" id="authorized<%=i%>" value="S" <%= u.getAprovado().equals("S")? "checked": "" %>>
                      <label class="form-check-label" for="autorized<%=i%>">Autorizado</label>
                    </div>
                  </td>
                </tr>
                <input type="hidden" name="id[]" value="<%= u.getId() %>">
                <% } %>
              </tbody>
            </table>
          </div>
          <button type="submit" class="btn float-right btn-primary">Salvar</button>
        </form>
        <h6 class="error pt-2">
            <%= request.getAttribute("erroAdminUsuarios") != null? request.getAttribute("erroAdminUsuarios"): ""  %>
        </h6>
      </main>
    </div>
  </div>

  <!-- Bootstrap core JavaScript
    ================================================== -->
  <!-- Placed at the end of the document so the pages load faster -->
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
    integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
    crossorigin="anonymous"></script>
  <script src="/assets/js/bootstrap/bootstrap.bundle.min.js"></script>

  <!-- Icons -->
  <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
  <script>
    feather.replace()
  </script>
</body>

</html>