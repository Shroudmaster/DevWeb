<%@page import="java.util.Objects"%>
<%@page import="Aplicacao.Comentario"%>
<%@page import="Aplicacao.Usuario"%>
<%@page import="Aplicacao.Artigo"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>Blog Template Â· Bootstrap</title>

    <link rel="stylesheet" href="../assets/modules/quill/dist/css/quill.css">
    <link rel="stylesheet" href="../assets/modules/quill/dist/css/quill.snow.css">
    <link rel="stylesheet" href="../assets/modules/quill/dist/css/quill.bubble.css">
    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/blog/">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <!-- Bootstrap core CSS -->
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="../assets/js/bootstrap/bootstrap.min.js"></script>
    <script src="../assets/modules/quill/dist/sprite.svg.js"></script>
    <script src="../assets/modules/quill/dist/bootstrap-quill.js"></script>


    <script src="/post-detail/post-details.js"></script>

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
    <!-- Custom styles for this template -->
    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="blog.css" rel="stylesheet">
  </head>
  <body>
    <div class="container">
  <%@include file="../header.jsp" %>
  
<% 
    Artigo artigo = (Artigo) request.getAttribute("artigo");
    Usuario ul = (Usuario) session.getAttribute("usuarioLogado");
%>

<main role="main" class="container pt-3">
  <div class="row">
    <div class="col-md-12 blog-main">

      <div class="blog-post">
        <h2 class="blog-post-title"><%=artigo.getTitulo() %></h2>
        <p class="blog-post-meta"><%=artigo.getCategoria().getDescricaoFormat() %> - Por <%=artigo.getUsuario().getNome() %></p>

        <%=artigo.getConteudo() %>
      </div><!-- /.blog-post -->
      <div class="blog-comments pb-5">
        <% if(!Objects.isNull(ul)) { %>  
        <div class="editor-full">
          <div id="document-full" class="ql-scroll-y" style="height: 300px;">
          </div>
        </div>
        <form id="post-form" action="/post-detail" method="POST">
            <textarea name="text" style="display:none" id="hiddenArea"></textarea>
            <input name="id" type="hidden" value="<%=artigo.getId() %>">
            <input id="idComentario" name="idComentario" type="hidden" value="0">
            <button id="send-post" class="btn float-right btn-primary mt-3 px-5 py-2">Salvar</button>
        </form>
        <% } %>  
        <h2 class="blog-post-title pb-3 pt-3">Comentários</h2>
        <% for(Comentario c: artigo.getComentarios()) { %>
            <div class="blog-post border-bottom">
              <input class="id-comentario" type="hidden" value="<%=c.getId() %>">
              <% if(Objects.nonNull(ul) && (ul.getPapel() == 0 || ul.getId() == c.getIdUsuario())) { %>  
                  <div class="float-right">
                    <a href="#" class="edit-com pl-2"><span data-toggle="tooltip" data-placement="bottom" title="Editar" data-feather="edit"></span></a>
                    <a href="/comentario/delete?id=<%=c.getId() %>&idArtigo=<%=artigo.getId() %>" class="pl-2"><span data-toggle="tooltip" data-placement="bottom" title="Excluir" data-feather="trash"></span></a>
                  </div>
              <% } %> 
              <p class="blog-post-meta">
                  Por <%=c.getUsuario().getNome() %>                   
              </p>                            
              <div class="conteudo-comentario">
                <%=c.getComentario() %>
              </div>
            </div><!-- /.blog-comment -->
        <% } %>
      </div><!-- /.blog-comments -->

    </div><!-- /.blog-main -->

  </div><!-- /.row -->

</main><!-- /.container -->

<footer class="blog-footer">
  <p>Blog template built for <a href="https://getbootstrap.com/">Bootstrap</a> by <a href="https://twitter.com/mdo">@mdo</a>.</p>
  <p>
    <a href="#">Back to top</a>
  </p>
</footer>
</body>
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
  feather.replace()
  $(function () {
    $('[data-toggle="tooltip"]').tooltip()
  })
</script>  
</html>
