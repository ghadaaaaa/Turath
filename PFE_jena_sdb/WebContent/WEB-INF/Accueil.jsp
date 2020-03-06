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

  <!-- Custom fonts for this template -->
  <link href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/business-casual.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%= request.getContextPath() %>/leaflet/leaflet.css" />
<script src="<%= request.getContextPath() %>/leaflet/leaflet.js"></script>
   <style> 
   #mapid { height: 600px; 
            weight: 100px;}
   </style>
</head>

<body>



  <h1 class="site-heading text-center text-white d-none d-lg-block">
     <img src="img/Turath.png" alt="">
    <span class="site-heading-upper text-primary mb-3">Une plateforme pour </span>
    <span class="site-heading-upper text-primary mb-3">Le patrimoine architectural algérien</span>
  </h1>

  <!-- Navigation -->
<%@include file="header.jsp" %>
 
      
      <div class=" page-section text-center ">
        <div class="col-xl-6xl-auto text-center">
         <form method="Post" action="MotCle">
          <div class=" form-group rounded text-center  mx-auto ">
            <label></label>
                <input class="rounded champ text-center" id="keyWord" name="keyWord" placeholder="Recherche par mot clé" >
           
            <button type="submit" class="btn btn-primary btn-xl">Chercher</button>
             </div>
        
              </form>
     
       </div>
        </div>
   

          <div class="row">

         <div class="text-center bg-faded col-sm-3 ">
         <br>
          <h2 class="section-heading mb-4">
            <span class="section-heading-upper">Les maisons<br> traditionnelles</span>
        
          </h2>
            <img class=" rounded" src="img/Hassan_Bacha.jpg" alt="">
          <div class="intro-button mx-auto">
            <a class="btn btn-primary btn-xl" href="Maisons">Voir Plus</a>
          </div>
           <br>
        </div>
 
        
         <div class="text-center bg-faded col-sm-3 ">
         <br>
          <h2 class="section-heading mb-4">
            <span class="section-heading-upper"><br> Les espaces</span>
        
          </h2>
            <img class=" rounded" src="img/Casbah.jpg" alt="">
          <div class="intro-button mx-auto">
            <a class="btn btn-primary btn-xl" href="Espaces">Voir Plus</a>
          </div>
           <br>
        </div>
   
       
         <div class="text-center bg-faded col-sm-3 ">
         <br>
          <h2 class="section-heading mb-4">
            <span class="section-heading-upper">Les sites<br> archéologiques</span>
        
          </h2>
            <img class=" rounded" src="img/Timgad.jpeg" alt="">
          <div class="intro-button mx-auto">
            <a class="btn btn-primary btn-xl" href="Sites">Voir Plus</a>
          </div>
           <br>
        </div>
    
         <div class="text-center bg-faded col-sm-3 ">
         <br>
          <h2 class="section-heading mb-4">
            <span class="section-heading-upper">Les monuments<br> historiques</span>
        
          </h2>
            <img class=" rounded" src="img/Martyrs.jpg" alt="">
          <div class="intro-button mx-auto">
            <a class="btn btn-primary btn-xl" href="Monuments">Voir Plus</a>
          </div>
           <br>
        </div>
       
 </div>

  
  <section class="page-section cta text-center">

     <div class="container text-center">
     
            <div class="maping">
      <div class="container ">
      <div class="col-xl-4mx-auto">
  
            <div class="bg-faded rounded">
            
			    <div id="mapid" style="width:1100px; height:1000px" ></div>
			    <script type="text/javascript">
			    var mymap = L.map('mapid').setView([28.768,2.5],5.6);
			    
			    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png',{
			
			        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a>',
			        maxZoom: 20,
			        id: 'mapbox/streets-v11',
			        tileSize: 512,
			        zoomOffset: -1,
			        accessToken: 'your.mapbox.access.token'
			    }).addTo(mymap);
			    var nomsMa=[]; var nomsMo=[]; var nomsSites=[]; var nomsEsp=[];
			    var longitudesMa=[]; var longitudesMo=[]; var longitudesSites=[]; var longitudesEsp=[];
			    var altitudesMa=[]; var altitudesMo=[]; var altitudesSites=[]; var altitudesEsp=[];
			    var i=0; var j=0; var k=0; var l=0;
			    </script>
			  
	
			<c:forEach var="mai" items="${mais}">
			<script>
			nomsMa[i] = "<c:out value='${mai.appels[0]}'/>";
		    altitudesMa[i] = "<c:out value='${mai.altitude}'/>";
		    longitudesMa[i] = "<c:out value='${mai.longitude}'/>";
			i++;
			</script>
			</c:forEach>
			
			<c:forEach var="site" items="${sites}">
			<script>
			nomsSites[j] = "<c:out value='${site.appels[0]}'/>";
		    altitudesSites[j] = "<c:out value='${site.altitude}'/>";
		    longitudesSites[j] = "<c:out value='${site.longitude}'/>";
			j++;
			</script>
			</c:forEach>
			
			<c:forEach var="mon" items="${mons}">
			<script>
			nomsMo[k] = "<c:out value='${mon.appels[0]}'/>";
		    altitudesMo[k] = "<c:out value='${mon.altitude}'/>";
		    longitudesMo[k] = "<c:out value='${mon.longitude}'/>";
			k++;
			</script>
			</c:forEach>
			
			<c:forEach var="esp" items="${esps}">
			<script>
			nomsEsp[l] = "<c:out value='${esp.appels[0]}'/>";
		    altitudesEsp[l] = "<c:out value='${esp.altitude}'/>";
		    longitudesEsp[l] = "<c:out value='${esp.longitude}'/>";
			l++;
			</script>
			</c:forEach>
		
			    <script>
			   var markersMa = []; 
			   var  markersSites =[]; 
			   var markersMo =[]; 
			   var markersEsp =[];
									/*********MAISONS**********/
				   for (var z=0; z<nomsMa.length; z++) {    
				    var marker= new L.marker([altitudesMa[z],longitudesMa[z]]).addTo(mymap);
				    marker.bindPopup(nomsMa[z]).openPopup();
				    markersMa.push(marker);
				   }
			
				/* for (var  y=0; y < markersMa.length; y++) {
					  let new_y = y;
					  markersMa[y].on('click', function()
					 { console.log("here", markersMa[new_y]._popup._content);
				       window.location.href="MaisonMap?appelMa="+markersMa[new_y]._popup._content;
				     }
				 )};*/
			                        /*********SITES**********/
			                        
			        for (var b=0; b<nomsSites.length; b++) {    
				    var marker= new L.marker([altitudesSites[b],longitudesSites[b]]).addTo(mymap);
				    marker.bindPopup(nomsSites[b]);
				    markersSites.push(marker);
				   }
			                        
			  /*      for ( var c=0; c < markersSites.length; c++) {
						  let new_c = c;
						  markersSites[c].on('click', function()
						 { console.log("here", markersSites[new_c]._popup._content);
					     window.location.href="SiteMap?appelSite="+markersSites[new_c]._popup._content;
					     }
					 )};*/
					 
                     /*********Mouments**********/
                     
				        for (var d=0; d<nomsMo.length; d++) {    
					    var marker= new L.marker([altitudesMo[d],longitudesMo[d]]).addTo(mymap);
					    marker.bindPopup(nomsMo[d]).openPopup();
					    markersMo.push(marker);
					   }
				                        
				 /*       for ( var e=0; e < markersMo.length; e++) {
							  let new_e = e;
							  markersMo[e].on('click', function()
							 { console.log("here", markersMo[new_e]._popup._content);
						       window.location.href="MonumentMap?appelMo="+markersMo[new_e]._popup._content;
						     }
						 )};*/
				   /*********Espaces**********/
				   
				      for (var f=0; f<nomsEsp.length; f++) {    
					    var marker= new L.marker([altitudesEsp[f],longitudesEsp[f]]).addTo(mymap);
					    marker.bindPopup(nomsEsp[f]).openPopup();
					    markersEsp.push(marker);
					   }
				                        
				    /*    for ( var g=0; g < markersEsp.length; g++) {
							  let new_g = g;
							  markersEsp[g].on('click', function()
							 { console.log("here", markersEsp[new_g]._popup._content);
						       window.location.href="EspaceMap?appelEsp="+markersEsp[new_g]._popup._content;
						     }
						 )};*/
						 
			    </script>
			      
			  
			   
			 
			   
			
       </div>
         </div>
        </div>
      </div>

    </div>
    

  </section>
  


  


<%@include file="footer.jsp" %>
 

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
    