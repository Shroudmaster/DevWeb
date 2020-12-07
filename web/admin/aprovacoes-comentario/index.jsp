<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Adm Panel</title>

    <!-- Bootstrap core CSS -->
    <link href="../../assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="aprovacoes-comentarios.css" rel="stylesheet">
  </head>

  <body>
    <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Blog Dev</a>
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
          <a class="nav-link" href="../../index.html">Sair</a>
        </li>
      </ul>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
          <div class="sidebar-sticky">
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link active" href="../index.html">
                  <span data-feather="home"></span>
                  Dashboard <span class="sr-only">(current)</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="../usuarios/index.html">
                  <span data-feather="users"></span>
                  Usuários
                </a>
              </li>              
            </ul>

            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
              <span>Postagens</span>
            </h6>
            <ul class="nav flex-column mb-2">
              <li class="nav-item">
                <a class="nav-link" href="../novo-post/index.html">
                  <span data-feather="plus-circle"></span>
                  Nova Postagem
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="../meus-posts/index.html">
                  <span data-feather="file"></span>
                  Minhas Postagens
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="../aprovacoes-post/index.html">
                  <span data-feather="file-text"></span>
                  Aprovações
                </a>
              </li>
            </ul>
            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
              <span>Comentários</span>
            </h6>
            <ul class="nav flex-column mb-2">
              <li class="nav-item">
                <a class="nav-link" href="../aprovacoes-comentario/index.html">
                  <span data-feather="file-text"></span>
                  Aprovações
                </a>
              </li>
            </ul>
          </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
          <form action="#" method="POST">
            <h2>Aprovações de Comentários</h2>
            <div class="table-responsive">
              <table class="table table-striped table-sm">
                <thead>
                  <tr>
                    <th>Post</th>
                    <th>Conteúdo</th>
                    <th>Link</th>
                    <th>Autorizado</th>
                    <th>Outras Opções</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>Como fazer posts interessantes</td>
                    <td>Muito legal, dicas muito boas!!</td>
                    <td><a href="#" class="pl-2"><span data-feather="share"></span></a></td>
                    <td>
                      <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" name="authorized2" id="authorized2" value="TRUE" checked>
                        <label class="form-check-label" for="autorized2">Autorizado</label>
                      </div>
                    </td>
                    <td>                      
                      <a href="#" class="pl-2"><span data-toggle="tooltip" data-placement="bottom" title="Editar" data-feather="edit"></span></a>
                      <a href="#" class="pl-2"><span data-toggle="tooltip" data-placement="bottom" title="Excluir" data-feather="trash"></span></a>
                    </td>
                  </tr>
                  <tr>
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
