<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
   <a href="${pageContext.request.contextPath}/" class="pull-left" style="padding-right:20px">
   
    <img src="<c:url value='/resources/ttalogo.png'/>" alt="Logo" width=75>
    
   </a>
    
  <div class="collapse navbar-collapse" id="navbarColor02">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/">Gestion des ordres <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/sessionFix">Gesion de session</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/securities">Gestion des valeurs</a>
      </li>
    </ul>
    
  </div>
</nav>