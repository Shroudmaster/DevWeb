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
    <link href="/cadastro/cadastro.css" rel="stylesheet">
    
    <script src="/assets/js/vendor/jquery-slim.min.js"></script>
    <script src="/assets/js/bootstrap/bootstrap.bundle.min.js"></script>
  </head>

  <body class="text-center">
    <form class="form-signin" action="/Cadastro" method = "POST">
      <img class="mb-4" src="../assets/img/user_icon.png" alt="" width="108" height="108">
      <label for="inputCPF" class="sr-only">CPF</label>
      <input type="text" id="inputCPF" class="form-control" placeholder="CPF" name = "cpf" autofocus>
      <label for="inputEmail" class="sr-only">Email</label>
      <input type="text" id="inputEmail" class="form-control" placeholder="Email" name = "email" >
      <label for="inputNome" class="sr-only">Nome</label>
      <input type="text" id="inputNome" class="form-control" placeholder="Nome" name = "nome" >
      <label for="inputPapel" class="sr-only">Papel</label>
      <select id="inputPapel" class="form-control" placeholder="Papel" name = "papel" >
          <option value="0">Administrador</option>
          <option value="1">Autor</option>
          <option value="2">Comentarista</option>
      </select>
      <label for="inputPassword" class="sr-only">Senha</label>
      <input type="password" id="inputPassword" class="form-control" name = "senha" placeholder="Senha">
      <button class="btn btn-lg btn-primary btn-block" type="submit">Cadastrar</button>
      <a class="pt-1" href="/sign-in/index.jsp">Entrar</a>
      <h6 class="error pt-2">
            <%= session.getAttribute("erroCadastro") != null? session.getAttribute("erroCadastro"): ""  %>
          </h6>
      
      <p class="mt-5 mb-3 text-muted">&copy; 2020-2020</p>
    </form>
  </body>
</html>
