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

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/blog/">
    <script src="assets/js/vendor/jquery-slim.min.js"></script>
    <script src="assets/js/vendor/popper.min.js"></script>
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="assets/js/bootstrap/bootstrap.min.js"></script>

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
  <%@include file="header.jsp" %>

<% 
    List<Artigo> list = (List<Artigo>) request.getAttribute("listaArtigos");
%>
<main role="main" class="container pt-3">
  <div class="row">
    <div class="col-md-8 blog-main">

    <% for(Artigo a: list) { %>
      <div class="blog-post">
        <h2 class="blog-post-title"><a href="post-detail/index.html?id=<%=a.getId() %>"><%=a.getTitulo()%></a></h2>
        <p class="blog-post-meta"><%=a.getCategoria().getDescricaoFormat() %> - <%=a.getUsuario().getNome()%></p>

        <%=a.getConteudo() %>
      </div><!-- /.blog-post -->

      <% } %>

      <nav class="blog-pagination">
        <a class="btn btn-outline-primary" href="#">Anterior</a>
        <a class="btn btn-outline-secondary disabled" href="#" tabindex="-1" aria-disabled="true">Próximo</a>
      </nav>

    </div><!-- /.blog-main -->

    <aside class="col-md-4 blog-sidebar">
      <div class="p-4 mb-3 bg-light rounded">
        <h4 class="font-italic">Sobre nós</h4>
        <p class="mb-0">Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>
      </div>

      <div class="p-4">
        <h4 class="font-italic">Arquivo</h4>
        <ol class="list-unstyled mb-0">
          <li><a href="#">March 2020</a></li>
          <li><a href="#">February 2020</a></li>
          <li><a href="#">January 2020</a></li>
          <li><a href="#">December 2019</a></li>
          <li><a href="#">November 2019</a></li>
          <li><a href="#">October 2019</a></li>
          <li><a href="#">September 2019</a></li>
          <li><a href="#">August 2019</a></li>
          <li><a href="#">July 2019</a></li>
          <li><a href="#">June 2019</a></li>
          <li><a href="#">May 2019</a></li>
          <li><a href="#">April 2019</a></li>
        </ol>
      </div>
    </aside><!-- /.blog-sidebar -->

  </div><!-- /.row -->

</main><!-- /.container -->

<footer class="blog-footer">
  <p>Blog template built for <a href="https://getbootstrap.com/">Bootstrap</a> by <a href="https://twitter.com/mdo">@mdo</a>.</p>
  <p>
    <a href="#">Back to top</a>
  </p>
</footer>
</body>
</html>
