<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">

  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Liste Espaces</title>

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/business-casual.min.css" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp" %>
 
  <br>
  <br>
<div class="container"> 
     <div class="row">  
 
     <c:forEach var="esp" items="${esps}">
     
       <div class="text-center bg-faded col-sm-3 ">
         <br>
          <h2 class="section-heading mb-4">
            <span class="section-heading-upper">   
            
            <br> 
             <c:if test = "${!esp.appels.isEmpty()}">
              <c:out value="${ esp.appels[0]}"/>
              </c:if> 
            </span>
        
          </h2>
           <c:if test = "${!esp.images.isEmpty()}">
            <img class=" rounded" src="${esp.images[0]}" alt="">
            </c:if>
            <c:if test = "${esp.images.isEmpty()}">
            </c:if>
          <div class="intro-button mx-auto">
            <a class="btn btn-primary btn-xl" href="Espace?idEsp=${esp.idEltPatri}">Voir Plus</a>
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