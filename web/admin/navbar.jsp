<%@page import="Aplicacao.Usuario"%>
      
<% Usuario ulNav = (Usuario) session.getAttribute("usuarioLogado"); %>


<nav class="col-md-2 d-none d-md-block bg-light sidebar">
  <div class="sidebar-sticky">
    <ul class="nav flex-column">
      <li class="nav-item">
        <a class="nav-link active" href="/admin/index.jsp">
          <span data-feather="home"></span>
          Dashboard <span class="sr-only">(current)</span>
        </a>
      </li>
      <% if(ulNav.getPapel() == 0 ) { %>
      <li class="nav-item">
        <a class="nav-link" href="/admin/usuarios">
          <span data-feather="users"></span>
          Usuários
        </a>
      </li>
      <% } %>
    </ul>
    
    <% if(ulNav.getPapel() == 0 || ulNav.getPapel() == 1 ) { %>
    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
      <span>Postagens</span>
    </h6>
    <ul class="nav flex-column mb-2">
      <li class="nav-item">
        <a class="nav-link" href="/admin/artigos/novo">
          <span data-feather="plus-circle"></span>
          Nova Postagem
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/admin/artigos/meus">
          <span data-feather="file"></span>
          Minhas Postagens
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/admin/artigos/aprovacoes">
          <span data-feather="file-text"></span>
          Aprovações
        </a>
      </li>
    </ul>
    <% } %>    
  </div>
</nav>