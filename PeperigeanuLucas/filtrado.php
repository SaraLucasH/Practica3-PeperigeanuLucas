<?php
	$con = mysqli_connect('localhost:3310','root','','test');
?>
<!DOCTYPE html>
<html>
<head>
<style>		
.nombreHospital{
	font-weight: bold;
	text-align: "center";
	font-size: 20px;
	padding: 10px;
}
.grid-container {
  display: grid;
  grid-template-areas: 
    'header header header header header header header header' 
    'left left middle middle middle1 middle1 right right' 
    'footer footer footer footer footer footer footer footer';
  grid-column-gap: 10px;
  padding-left: 10px;
  
}

/* Style the left column */
.left {
  grid-area: left;
  padding: 10px;
  width:100%;
  height: auto;
}

/* Style the middle column */
.middle1 {
  grid-area: middle1;
  padding: 10px;
  width:100%;
  height: auto;
}

/* Style the middle column */
.middle {
  grid-area: middle;
  padding: 10px;
  width:100%;
  height: auto;
}

/* Style the right column */
.right {
  grid-area: right;
  padding: 10px;
  width:100%;
  height:auto;
}

@media (max-width: 300px),(max-height: 255px){
  .grid-container  {
    grid-template-areas: 
      'header header header header header header' 
      'left left left left left left' 
      'middle middle middle middle middle middle'
	  'middle1 middle1 middle1 middle1 middle1 middle1' 	  
      'right right right right right right' 
      'footer footer footer footer footer footer';
  }
}

</style>

 <meta charset="utf-8">
 <title>Filtrado Colaborativo</title>
 <script type="text/javascript" src="https://www.google.com/jsapi"></script>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
 <script type="text/javascript">
		google.charts.load("current", {packages:["table"]});
		google.setOnLoadCallback(drawTable);

      	function drawTable() {
        	var dataHospital1 = new google.visualization.arrayToDataTable([
			['Id del paciente','C1 nº','C2 nº','C3 nº'],
    			<?php 
				$query = "SELECT paciente_id,r1,r2,r3 FROM (paciente INNER JOIN hecho ON paciente.id = hecho.cliente_id) INNER JOIN filtrado_colaborativo ON paciente.id = filtrado_colaborativo.paciente_id WHERE paciente.id = filtrado_colaborativo.paciente_id AND  hecho.hospital_id=1";
    				$exec = mysqli_query($con,$query);
    				while($row = mysqli_fetch_array($exec)){
						if($row['r1']<0){
							$r1="-";
						}else{
							$r1=$row['r1'];
						}
						if($row['r2']<0){
							$r2="-";
						}else{
							$r2=$row['r2'];
						}
						if($row['r3']<0){
							$r3="-";
						}else{
							$r3=$row['r3'];
						}
    					echo "['".$row['paciente_id']."','".$r1."','".$r2."','".$r3."'],";
    				}
    			?> 
    		 ]);
			 var dataHospital2 = new google.visualization.arrayToDataTable([
			['Id del paciente','C1 nº','C2 nº','C3 nº'],
    			<?php 
				$query = "SELECT paciente_id,r1,r2,r3 FROM (paciente INNER JOIN hecho ON paciente.id = hecho.cliente_id) INNER JOIN filtrado_colaborativo ON paciente.id = filtrado_colaborativo.paciente_id WHERE paciente.id = filtrado_colaborativo.paciente_id AND  hecho.hospital_id=2";
    				$exec = mysqli_query($con,$query);
    				while($row = mysqli_fetch_array($exec)){
						if($row['r1']<0){
							$r1="-";
						}else{
							$r1=$row['r1'];
						}
						if($row['r2']<0){
							$r2="-";
						}else{
							$r2=$row['r2'];
						}
						if($row['r3']<0){
							$r3="-";
						}else{
							$r3=$row['r3'];
						}
    					echo "['".$row['paciente_id']."','".$r1."','".$r2."','".$r3."'],";
    				}
    			?> 
    		 ]);
			 var dataHospital3 = new google.visualization.arrayToDataTable([
			['Id del paciente','C1 nº','C2 nº','C3 nº'],
    			<?php 
				$query = "SELECT paciente_id,r1,r2,r3 FROM (paciente INNER JOIN hecho ON paciente.id = hecho.cliente_id) INNER JOIN filtrado_colaborativo ON paciente.id = filtrado_colaborativo.paciente_id WHERE paciente.id = filtrado_colaborativo.paciente_id AND  hecho.hospital_id=3";
    				$exec = mysqli_query($con,$query);
    				while($row = mysqli_fetch_array($exec)){
						if($row['r1']<0){
							$r1="-";
						}else{
							$r1=$row['r1'];
						}
						if($row['r2']<0){
							$r2="-";
						}else{
							$r2=$row['r2'];
						}
						if($row['r3']<0){
							$r3="-";
						}else{
							$r3=$row['r3'];
						}
    					echo "['".$row['paciente_id']."','".$r1."','".$r2."','".$r3."'],";
    				}
    			?> 
    		 ]);
			 var dataHospital4 = new google.visualization.arrayToDataTable([
			['Id del paciente','C1 nº','C2 nº','C3 nº'],
    			<?php 
				$query = "SELECT paciente_id,r1,r2,r3 FROM (paciente INNER JOIN hecho ON paciente.id = hecho.cliente_id) INNER JOIN filtrado_colaborativo ON paciente.id = filtrado_colaborativo.paciente_id WHERE paciente.id = filtrado_colaborativo.paciente_id AND  hecho.hospital_id=4";
    				$exec = mysqli_query($con,$query);
    				while($row = mysqli_fetch_array($exec)){
						if($row['r1']<0){
							$r1="-";
						}else{
							$r1=$row['r1'];
						}
						if($row['r2']<0){
							$r2="-";
						}else{
							$r2=$row['r2'];
						}
						if($row['r3']<0){
							$r3="-";
						}else{
							$r3=$row['r3'];
						}
    					echo "['".$row['paciente_id']."','".$r1."','".$r2."','".$r3."'],";
    				}
    			?> 
    		 ]);

        var table = new google.visualization.Table(document.getElementById("table_hospital1"));
        table.draw(dataHospital1, {showRowNumber: false, width: '100%', height: '100%'});
		table = new google.visualization.Table(document.getElementById("table_hospital2"));
        table.draw(dataHospital2, {showRowNumber: false, width: '100%', height: '100%'});
		table = new google.visualization.Table(document.getElementById("table_hospital3"));
        table.draw(dataHospital3, {showRowNumber: false, width: '100%', height: '100%'});
		table = new google.visualization.Table(document.getElementById("table_hospital4"));
        table.draw(dataHospital4, {showRowNumber: false, width: '100%', height: '100%'});
      }
    </script>
  	
</head>
<body>
<div class="header">
		<h2>Recomendaciones de compuesto por hospital.</h2>
		<p>	Identificador del paciente | Números  de los compuestos recomendados.</p>
		<p>	En caso de que no se encuentre ninguna recomendacion, el numero del compuesto vendra dado por una barra ("-").</p>
	</div>
<div class="grid-container">
	<div class="middle">
		<span class="nombreHospital">Hospital 2</span>
		<div class="middle"  id="table_hospital2"></div>
	</div>
	<div class="middle1">
		<span class="nombreHospital">Hospital 3</span>
		<div class="middle1" id="table_hospital3"></div>
	</div>	
	<div class="right">
		<span class="nombreHospital">Hospital 4</span>
		<div class="right" id="table_hospital4"></div>
	</div>
	<div class="left">
		<span class="nombreHospital">Hospital 1</span>
		<div class="left" id="table_hospital1"></div>
	</div>
</div>
</body>
</html>