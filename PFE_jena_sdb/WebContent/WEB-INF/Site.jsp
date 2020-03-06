<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Turath</title>

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.1/dist/leaflet.css" integrity="sha512-Rksm5RenBEKSKFjgI3a41vrjkw4EVPlJ3+OiI65vTjIdo9brlAacEuKOiQ5OFh7cOI1bkDwLqdLw3Zg0cRJAAQ==" crossorigin="" />
        <style type="text/css">
            #map{ /* la carte DOIT avoir une hauteur sinon elle n'apparaît pas */
                height:400px;
            }
        </style>

  <!-- Custom fonts for this template -->
  <link href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
  <!-- Custom styles for this template -->
  <link href="css/business-casual.min.css" rel="stylesheet">

</head>

<body>





  <!-- Navigation -->
  <%@include file="header.jsp" %>
 <br>

        
     
       <div class="col-xl-9 col-lg-10 mx-auto">
            <div class="bg-faded p-5">
              <h2 class="section-heading mb-4">
              <span class="section-heading-lower">${ site.appels[0]} </span>
         </h2>
         <h2 class="section-heading mb-4">     
                <span class="section-heading-upper">Description</span>
              </h2>
              <img src= "${site.images[0]}" class="float-right">
              <p>${site.descEltPatri}</p>
              <p>  </p>
              <p>  </p>
              <p>  </p>
             <!--  <p class="mb-0">We guarantee that you will fall in <em>lust</em> with our decadent blends the moment you walk inside until you finish your last sip. Join us for your daily routine, an outing with friends, or simply just to enjoy some alone time.</p>
             -->
             
              <h2 class="section-heading mb-4">
	                <span class="section-heading-upper">Appelations</span>
	              </h2>
	               
                   <c:forEach var="appel" items="${site.appels}">
	              <ul>
	              <li><p>${appel}</p></li>
	              </ul>
	              </c:forEach>
	              <p class="mb-0"></p>
	              
	             
               
             
              </div>
             
            </div> 
           <div class="col-xl-9 col-lg-10 mx-auto"> 
            <div class="row">   
           <div class="col-md-6 section-md-t3" style="padding-right: 0px;">
           
            <div class="bg-faded p-5" style="font-size: 16.75px;">
              <h2 class="section-heading mb-4">  
	                <span class="section-heading-upper">Historique</span>
	              </h2>
	              <p>Ce site a été construit en: ${site.dateConstruction} </p>
	              <p class="mb-0">La période de construction : ${site.périodeConstruction} </p>
	              <p>            </p>
 <p class="mb-0"> </p>
	            
	             </div>
	              
	           
              </div> 
            
            <div class="col-md-6 section-md-t3" style="padding-left: 0px;">
            
	              <div class="bg-faded p-5" style="font-size: 16.75px;">
              <h2 class="section-heading mb-4">
                
                <span class="section-heading-upper">Les caractéristiques du site: </span>
              </h2>
              <ul>
              <li>
              Altitude: ${site.altitude}
              </li>
             
              <li>
              Longitude: ${site.longitude}
              </li>
            <li>
              Superficie: ${site.surfaceSite}
              </li>
              </ul>
              
             </div>
            </div>
           
          </div>
       </div>
          
           <div class="col-xl-9 col-lg-10 mx-auto"> 
            <div class="row">    
              
        <div class="col-md-6 section-md-t3" style="padding-right: 0px;">
           
            
            </div>
         <div class="col-md-6 section-md-t3" style="padding-left: 0px;">
            
      </div>
      </div>
      
    
            
            <div class="bg-faded rounded p-5">
              <h2 class="section-heading mb-4">
                
                <span class="section-heading-upper">Autres photos</span>
              </h2>
              
				<div id="demo" class="carousel slide" data-ride="carousel" style="padding-right: 0px;padding-top: 0px;padding-bottom: 0px;width: 400;padding-left: 260px;">
					  <ul class="carousel-indicators">
					    <li data-target="#demo" data-slide-to="0" class="active"></li>
					    <li data-target="#demo" data-slide-to="1"></li>
					    <li data-target="#demo" data-slide-to="2"></li>
					  </ul>
					  <div class="carousel-inner">
					            
                   <c:forEach var="image" items="${site.images}">
					    <div class="carousel-item active">
					      <img src="${image}" alt="Los Angeles" width="300" height="200">
					      <div class="carousel-caption">
					        
					      </div>   
					    </div>
					   </c:forEach>
					  </div>
					  <a class="carousel-control-prev" href="#demo" data-slide="prev">
					    <span class="carousel-control-prev-icon"></span>
					  </a>
					  <a class="carousel-control-next" href="#demo" data-slide="next">
					    <span class="carousel-control-next-icon"></span>
					  </a>
					</div>
              
             </div>
   
           </div>        
  <br>

<%@include file="footer.jsp" %>
 

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
   <script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet.js" integrity="sha512-/Nsx9X4HebavoBvEBuyp3I7od5tA0UzAxs+j83KgC8PU0kgB4XiK4Lfe4y4cgBtaRJQEIFCW+oC506aPT2L1zw==" crossorigin=""></script>


</body>

</html>
    