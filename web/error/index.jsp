<%@page import="java.util.Objects"%>
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
    <link href="/error/blog.css" rel="stylesheet">
  </head>
  <body>
    <div class="container">
  <%@include file="../header.jsp" %>

<main role="main" class="container pt-3">
  <div class="row">
    <div class="col-md-12 blog-main">
        <%
            String error = (String) request.getAttribute("error");
        %>

      <div class="blog-post">
        <%
            if(Objects.isNull(error)) {
        %>
            <h3 class="m-auto error">Houve um erro não identificado. Por favor tente voltar ao início.</h3>
        <%
            } else {
        %>
            <h3 class="m-auto error">ERRO: <%=error  %></h3>
        <%
            }
        %>
      </div><!-- /.blog-post -->

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
</html>
