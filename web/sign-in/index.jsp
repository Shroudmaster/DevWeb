<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Entrar</title>

    <!-- Bootstrap core CSS -->
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/sign-in/signin.css" rel="stylesheet">
    
    <script src="/assets/js/vendor/jquery-slim.min.js"></script>
    <script src="/assets/js/bootstrap/bootstrap.bundle.min.js"></script>
    <script src="/assets/js/vendor/jquery.mask.min.js"></script>
  </head>

  <body class="text-center">
    <form class="form-signin" action="/VerificarLogin" method = "POST">
      <img class="mb-4" src="../assets/img/user_icon.png" alt="" width="108" height="108">
      <label for="inputCPF" class="sr-only">CPF</label>
      <input type="text" id="inputCPF" class="form-control" placeholder="CPF" name = "cpf" autofocus>
      <label for="inputPassword" class="sr-only">Senha</label>
      <input type="password" id="inputPassword" class="form-control" name = "senha" placeholder="Senha">
      <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
      <a class="pt-1" href="/cadastro/index.jsp">Cadastrar</a>
      <h6 class="error pt-2">
            <%= session.getAttribute("erroLogin") != null? session.getAttribute("erroLogin"): ""  %>
          </h6>
      
      <p class="mt-5 mb-3 text-muted">&copy; 2020-2020</p>
    </form>
  </body>
  <script>
      $("#inputCPF").mask("000.000.000-00")
  </script>
</html>
