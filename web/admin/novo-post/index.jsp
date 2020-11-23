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

    <link rel="stylesheet" href="../../assets/modules/quill/dist/css/quill.css">
    <link rel="stylesheet" href="../../assets/modules/quill/dist/css/quill.snow.css">
    <link rel="stylesheet" href="../../assets/modules/quill/dist/css/quill.bubble.css">
    <!-- Bootstrap core CSS -->
    <link href="../../assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="novo-post.css" rel="stylesheet">

  </head>
  
  <%
      List<Categoria> listCategoria = (List<Categoria>) request.getAttribute("listaCategorias");
  %>

  <body>
    <%@include file="../header.jsp" %>
    
    <div class="container-fluid">
      <div class="row">
        <%@include file="../navbar.jsp" %>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <h2>Novo Post</h2>
            <div>
              <div class="editor-full">
                <div id="document-full" class="ql-scroll-y" style="height: 300px;">
                </div>
              </div>
            </div>
            <form id="post-form" action="/admin/novo-post/" method="POST">
                <input type="text" id="titulo" class="form-control my-2" placeholder="Titulo" name = "titulo">
                <select id="categoria" class="form-control mt-3 categoria-select" name = "categoria" >
                    <% for(Categoria c: listCategoria) { %>
                        <option value="<%= c.getId() %>"><%= c.getDescricao().toUpperCase() %></option>
                    <% } %>
                </select>
                <textarea name="text" style="display:none" id="hiddenArea"></textarea>
                <button id="send-post" class="btn float-right btn-primary mt-3 px-5 py-2">Salvar</button>
            </form>
            <h6 class="error pt-2">
                <%= request.getAttribute("erro") != null? request.getAttribute("erro"): ""  %>
            </h6>
        </main>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../assets/js/vendor/popper.min.js"></script>
    <script src="../../assets/js/bootstrap/bootstrap.min.js"></script>
    <script src="../../assets/modules/quill/dist/sprite.svg.js"></script>
    <script src="../../assets/modules/quill/dist/bootstrap-quill.js"></script>
    <script src="novo-post.js"></script>

    <!-- Icons -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
      feather.replace()
    </script>    
  </body>
</html>
