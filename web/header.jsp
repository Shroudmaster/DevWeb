<%@page import="java.util.List"%>
<%@page import="Aplicacao.Categoria"%>

<% List<Categoria> categorias = (List<Categoria>) session.getAttribute("categoriasMenu"); %>
<header class="blog-header py-3">
    <div class="row flex-nowrap justify-content-beetween align-items-center">
        <div class="col-4 pt-1">

        </div>
        <div class="col-4 text-center">
          <a class="blog-header-logo text-dark" href="#">Dev Blog Web</a>
        </div>
        <div class="col-4 d-flex justify-content-end align-items-center">
          <a class="btn btn-sm btn-outline-secondary" href="/sign-in/index.jsp">Entrar</a>
        </div>
    </div>
</header>

<div class="nav-scroller py-1 mb-2">
    <nav class="nav d-flex justify-content-around">
        <% for(Categoria c: categorias) { %>
            <a class="p-2 text-muted" href="#"><%=c.getDescricaoFormat()%></a>
        <% } %>
    </nav>
</div>