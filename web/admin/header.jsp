<%@page import="Aplicacao.Usuario"%>
      
<% Usuario ulHeader = (Usuario) session.getAttribute("usuarioLogado"); %>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
  <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Blog Dev</a>
  <span class="navbar-text">
    Bem vindo, <%= ulHeader.getNome() %>
  </span>
  <ul class="navbar-nav px-3">
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="/index.html">Sair</a>
    </li>
  </ul>
</nav>