<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">

  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Region</title>

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/business-casual.min.css" rel="stylesheet">
</head>
<body>

  <nav class="navbar navbar-expand-lg navbar-dark py-lg-4" id="mainNav">
    <div class="container">
 
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" 
      aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav mx-auto">
          <li class="nav-item active px-lg-4">
            <a class="nav-link text-uppercase text-expanded" href="#">Accueil
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item px-lg-4">
            <a class="nav-link text-uppercase text-expanded" href="Region">Les Espaces </a>
           
          </li>
          <li class="nav-item px-lg-4">
            <a class="nav-link text-uppercase text-expanded" href="#">Les Maisons traditionnelles</a>
          </li>
          <li class="nav-item px-lg-4">
            <a class="nav-link text-uppercase text-expanded" href="#">Les Monuments historiques</a>
          </li>
          <li class="nav-item px-lg-4">
            <a class="nav-link text-uppercase text-expanded" href="#">Les Sites archéologiques</a>
          </li>
           <li class="nav-item px-lg-4">
            <a class="nav-link text-uppercase text-expanded" href="#">Contact</a>
          </li>
          
          
        </ul>
      </div>
    </div>
  
  </nav> 
  
<br>
<br>
  <div class="container"> 
     <div class="row">  
 
     <c:forEach var="mon" items="${mons}">
     
       <div class="text-center bg-faded col-sm-3 ">
         <br>
          <h2 class="section-heading mb-4">
            <span class="section-heading-upper">    
            <c:out value="Monument historique:"/> 
            <br> 
              <c:out value="${ mon.appels[0]}"/> 
            </span>
        
          </h2>
            <img class=" rounded" src="${mon.images[0]}" alt="">
          <div class="intro-button mx-auto">
            <a class="btn btn-primary btn-xl" href="#">Voir Plus</a>
          </div>
           <br>
        </div>
     </c:forEach> 
     
      <c:forEach var="mai" items="${mais}">
     
       <div class="text-center bg-faded col-sm-3 ">
         <br>
          <h2 class="section-heading mb-4">
            <span class="section-heading-upper">    
            <c:out value="Maison traditionnelle:"/> 
            <br> 
              <c:out value="${ mai.appels[0]}"/> 
            </span>
        
          </h2>
            <img class=" rounded" src="${mai.images[0]}" alt="">
          <div class="intro-button mx-auto">
            <a class="btn btn-primary btn-xl" href="#">Voir Plus</a>
          </div>
           <br>
        </div>
     </c:forEach> 
     
   
     
     
      <c:forEach var="esp" items="${espaces}">
     
       <div class="text-center bg-faded col-sm-3 ">
         <br>
          <h2 class="section-heading mb-4">
            <span class="section-heading-upper">    
            <c:out value="Espace:"/> 
            <br> 
              <c:out value="${ esp.appels[0]}"/> 
            </span>
        
          </h2>
            <img class=" rounded" src="${esp.images[0]}" alt="">
          <div class="intro-button mx-auto">
            <a class="btn btn-primary btn-xl" href="#">Voir Plus</a>
          </div>
           <br>
        </div>
     </c:forEach> 
     
     
       <c:forEach var="si" items="${sites}">
     
       <div class="text-center bg-faded col-sm-3 ">
         <br>
          <h2 class="section-heading mb-4">
            <span class="section-heading-upper">    
            <c:out value="Site archéologique:"/> 
            <br> 
              <c:out value="${ si.appels[0]}"/> 
            </span>
        
          </h2>
            <img class=" rounded" src="${si.images[0]}" alt="">
          <div class="intro-button mx-auto">
            <a class="btn btn-primary btn-xl" href="#">Voir Plus</a>
          </div>
           <br>
        </div>
     </c:forEach> 
</div>
</div>


<%@include file="footer.jsp" %>
       <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script> 


</body>

</html>