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

    <link rel="stylesheet" href="/assets/modules/quill/dist/css/quill.css">
    <link rel="stylesheet" href="/assets/modules/quill/dist/css/quill.snow.css">
    <link rel="stylesheet" href="/assets/modules/quill/dist/css/quill.bubble.css">
    <!-- Bootstrap core CSS -->
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/admin/novo-post/novo-post.css" rel="stylesheet">

  </head>
  
  <%
      List<Categoria> listCategoria = (List<Categoria>) request.getAttribute("listaCategorias");
      Artigo artigoEdit = (Artigo) request.getAttribute("artigoEdit");
      boolean edit = true;
      if(Objects.isNull(artigoEdit)) {
          artigoEdit = new Artigo();
          edit = false;
      }
  %>

  <body>
    <%@include file="../header.jsp" %>
    
    <div class="container-fluid">
      <div class="row">
        <%@include file="../navbar.jsp" %>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <% if(!edit) { %>
                <h2>Novo Artigo</h2>
            <% } else { %>
                <h2>Edição de Artigo</h2>
            <% } %>
            <h4 class="error py-2">
                <%= Objects.nonNull(request.getAttribute("error"))? "Erro: " + request.getAttribute("error"): ""  %>
            </h4>
            <div>
              <div class="editor-full">
                <div id="document-full" class="ql-scroll-y" style="height: 300px;">
                    <%=edit? artigoEdit.getConteudo(): "" %>
                </div>
              </div>
            </div>
            <form id="post-form" action="/admin/artigos/novo" method="POST">
                <% if(edit) { %>
                    <input type="hidden" name="id" value="<%=artigoEdit.getId() %>">
                <% } %>
                <input type="text" id="titulo" class="form-control my-2" placeholder="Titulo" name = "titulo" <%=edit? "value=\"" + artigoEdit.getTitulo() + "\"": "" %>>
                <select id="categoria" class="form-control mt-3 categoria-select" name = "categoria" >
                    <% for(Categoria c: listCategoria) { %>
                        <option value="<%= c.getId() %>" <%=c.getId() == artigoEdit.getIdCategoria()? "selected": "" %> ><%= c.getDescricao().toUpperCase() %></option>
                    <% } %>
                </select>
                <textarea name="text" style="display:none" id="hiddenArea"></textarea>
                <button id="send-post" class="btn float-right btn-primary mt-3 px-5 py-2">Salvar</button>
            </form>
        </main>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="/assets/js/vendor/jquery-slim.min.js"></script>
    <script src="/assets/js/vendor/popper.min.js"></script>
    <script src="/assets/js/bootstrap/bootstrap.min.js"></script>
    <script src="/assets/modules/quill/dist/sprite.svg.js"></script>
    <script src="/assets/modules/quill/dist/bootstrap-quill.js"></script>
    <script src="/admin/novo-post/novo-post.js"></script>

    <!-- Icons -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
      feather.replace()
    </script>    
  </body>
</html>
