<?php
	$con = mysqli_connect('localhost:3310','root','','test');
?>
<!DOCTYPE html>
<html>
<head>
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
			['paciente_id','Recomendacion 1','Recomendacion 2','Recomendacion 3'],
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
			['paciente_id','Recomendacion 1','Recomendacion 2','Recomendacion 3'],
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
			['paciente_id','Recomendacion 1','Recomendacion 2','Recomendacion 3'],
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
			['paciente_id','Recomendacion 1','Recomendacion 2','Recomendacion 3'],
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
	<style>
		.grid-container {
			display: grid;
			grid-column-gap: 5px;
			grid-template-columns: auto auto auto	auto;
			background-color: #2196F3;
			padding: 10px;
		}

		.grid-item {
			background-color: rgba(255, 255, 255, 0.8);
			border: 1px solid rgba(0, 0, 0, 0.8);
			padding: 5px;
			font-size: 30px;
			text-align: center;
		}
	</style>
	<h3>Recomendaciones de compuesto por hospital</h3>
	<div class="grid-container">
		<div class="grid-item" id="table_hospital1"></div>
		<div class="grid-item" id="table_hospital2"></div>
		<div class="grid-item" id="table_hospital3"></div>		
		<div class="grid-item" id="table_hospital4"></div>	
	</div>
</body>
</html>