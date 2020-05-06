<?php
    $con = mysqli_connect('localhost:3310','root','','test');
?>

<!DOCTYPE HTML>
<html>
<head>
<style>
.header{
	 background:#deece7;
	 border-style: double;
	 text-align:center;
	 font-family:arial;
}
.item1,.item5,.item2,.item3,.item7{
	min-width:300px;
	width: 900px;
	min-height:200px;
	height: 500px;
}

.grid-containerDonut{
	min-width:300px;
	width: 900px;
	min-height:200px;
	height: 500px;
}
.item4,.item6{
	min-width:300px;
	width: 445px;
	min-height:200px;
	height: 300px;
}

.grid-container {
  display: grid;
  
  height:100%;
  min-height:850px;
  width: 100%;
  min-width: 750px;
  margin: 0 auto; 
	grid-template-rows:  auto auto auto;
  grid-template-columns: 50% 50%;
  grid-gap: 5px;
  
}
.grid-containerDonut {
  display: grid;
  background: #7fbca9; 
	grid-template-rows:  auto;
  grid-template-columns: 50% 50%;
  grid-gap: 5px;
  
}

.grid-container div {
  color: white;
  font-size: 20px;
  padding: 5px;
}
</style>

    <meta charset="utf-8">
    <title>Dashboard</title>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['bar']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([

                ['Identificador del hospital', 'Nº fallecidos'],

                <?php
                $query = "SELECT hecho.hospital_id, count(hecho.fallecido) 
                            FROM test.hecho WHERE ((hecho.fallecido)=\"Si\")
                            group by hecho.hospital_id;";
                $exec = mysqli_query($con,$query);
                while($row = mysqli_fetch_array($exec)){
                    echo "['".$row['hospital_id']."',".$row['count(hecho.fallecido)']."],";
                }
                ?>
            ]);

            var options = {
                title: 'Número total de fallecidos por cada hospital',
				legend: { position: 'none'},				
                bars: 'horizontal'		
            };

            var chart = new google.charts.Bar(document.getElementById('barchart_material'));

            chart.draw(data, google.charts.Bar.convertOptions(options));
        }
    </script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawVisualization);

        function drawVisualization() {
            // Some raw data (not necessarily accurate)
            var data = google.visualization.arrayToDataTable([
                ['Identificador hospital', 'EPOC','Alcoholismo', 'Cancer', 'Cardiopatia',
                'Colesterol', 'Hepatitis', 'Hipertension','Reuma', 'Tabaquismo'],
                <?php
                $query = "SELECT hospital_id, sum(paciente.epoc), sum(paciente.alcoholismo), sum(paciente.cancer), sum(paciente.cardiopatia), sum(paciente.colesterol),
                            sum(paciente.hepatitis), sum(paciente.hipertension), sum(paciente.reuma), sum(paciente.tabaquismo)
                            FROM test.paciente INNER JOIN test.hecho ON paciente.id = hecho.cliente_id
                            WHERE (((paciente.alcoholismo)=1) OR ((paciente.cancer)>0) OR ((paciente.cardiopatia)=1) OR ((paciente.colesterol)=1) OR ((paciente.hepatitis)=1) OR ((paciente.hipertension)=1) OR ((paciente.reuma)=1) OR ((paciente.tabaquismo)=1) OR ((paciente.epoc)=1))
                            GROUP BY hecho.hospital_id";
                $exec = mysqli_query($con,$query);
                while($row = mysqli_fetch_array($exec)){
                    echo "[".$row['hospital_id'].",".$row['sum(paciente.epoc)'].",".$row['sum(paciente.alcoholismo)'].", ".$row['sum(paciente.cancer)'].", ".$row['sum(paciente.cardiopatia)'].",
                    ".$row['sum(paciente.colesterol)'].", ".$row['sum(paciente.hepatitis)'].", ".$row['sum(paciente.hipertension)'].", ".$row['sum(paciente.reuma)'].",
                    ".$row['sum(paciente.tabaquismo)']."],";
                }
                ?>
            ]);

            var options = {
                title : 'Total de personas ingresadas por hospital agrupadas por patologías',
                vAxis: {title: 'Total de personas'},
                hAxis: {title: 'Hospital'},

                seriesType: 'bars',
                series: {10: {type: 'line'}},
				width: '100%', height: '100%'};

            var chart = new google.visualization.ComboChart(document.getElementById('barras_hospitales'));
            chart.draw(data, options);
        }
    </script>
    <script type="text/javascript">
        google.charts.load("current", {packages:["corechart"]});
        google.charts.setOnLoadCallback(drawChart);
        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Identificador hospital', 'Nº ingresados UCI'],
                <?php
                $query = "SELECT hecho.hospital_id, count(hecho.uci) 
                            FROM test.hecho INNER JOIN test.tiempo ON tiempo.id = hecho.fecha_ingreso_id 
							WHERE hecho.uci=\"Si\"	AND tiempo.anio='2020'						
                            group by hecho.hospital_id";
                $exec = mysqli_query($con,$query);
                while($row = mysqli_fetch_array($exec)){
                    echo "['".$row['hospital_id']."',".$row['count(hecho.uci)']."],";
                }
                ?>
            ]);

            var options = {
                title: 'Número de ingresados en UCI en el año 2020 por cada hospital',
                pieHole: 0.4
            };

            var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
            chart.draw(data, options);
        }
    </script>
	<script type="text/javascript">
        google.charts.load("current", {packages:["corechart"]});
        google.charts.setOnLoadCallback(drawChart);
        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Identificador hospital', 'Nº ingresados UCI'],
                <?php
                $query = "SELECT hecho.hospital_id, count(hecho.uci) 
                            FROM test.hecho INNER JOIN test.tiempo ON tiempo.id = hecho.fecha_ingreso_id 
							WHERE hecho.uci=\"Si\"	AND tiempo.anio='2019'						
                            group by hecho.hospital_id";
                $exec = mysqli_query($con,$query);
                while($row = mysqli_fetch_array($exec)){
                    echo "['".$row['hospital_id']."',".$row['count(hecho.uci)']."],";
                }
                ?>
            ]);

            var options = {
                title: 'Número de ingresados en UCI en el año 2019 por cada hospital',
                pieHole: 0.4
            };

            var chart = new google.visualization.PieChart(document.getElementById('donutchart2019'));
            chart.draw(data, options);
        }
    </script>

    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                [ 'Mes', 'Hospital 1', 'Hospital 2', 'Hospital 3', 'Hospital 4'],
                <?php				
				for ($i = 1; $i <= 12; $i++) {					
					$r1=0;
					$r2=0;
					$r3=0;
					$r4=0;
					$query1 = "SELECT tiempo.mes as mes, count(hecho.hospital_id)
                        FROM test.tiempo INNER JOIN test.hecho ON tiempo.id = hecho.fecha_ingreso_id  
						WHERE hecho.hospital_id=1 AND tiempo.mes=".$i." AND tiempo.anio=2020
                        group by mes";		
					$exec1 = mysqli_query($con,$query1);
					while($row = mysqli_fetch_array($exec1)){
						if($row[0]==$i){							
							$r1=$row['count(hecho.hospital_id)'];
						}
					}
					$query2 = "SELECT tiempo.mes as mes, count(hecho.hospital_id)
                        FROM test.tiempo INNER JOIN test.hecho ON tiempo.id = hecho.fecha_ingreso_id  
						WHERE hecho.hospital_id=2  AND tiempo.mes=".$i." AND tiempo.anio=2020
                        group by mes";
					$exec2 = mysqli_query($con,$query2);
					while($row = mysqli_fetch_array($exec2)){
						if($row[0]==$i){
							$r2=$row['count(hecho.hospital_id)'];
						}
					}
					$query3 = "SELECT tiempo.mes as mes, count(hecho.hospital_id)
                        FROM test.tiempo INNER JOIN test.hecho ON tiempo.id = hecho.fecha_ingreso_id  
						WHERE hecho.hospital_id=3  AND tiempo.mes=".$i." AND tiempo.anio=2020
                        group by mes";
					$exec3 = mysqli_query($con,$query3);
					while($row = mysqli_fetch_array($exec3)){
						if($row[0]==$i){
							$r3=$row['count(hecho.hospital_id)'];
						}
					}
					$query4 = "SELECT tiempo.mes as mes, count(hecho.hospital_id)
                        FROM test.tiempo INNER JOIN test.hecho ON tiempo.id = hecho.fecha_ingreso_id  
						WHERE hecho.hospital_id=4  AND tiempo.mes=".$i." AND tiempo.anio=2020
                        group by mes";
					$exec4 = mysqli_query($con,$query4);
					while($row = mysqli_fetch_array($exec4)){
						if($row[0]==$i){
							$r4=$row['count(hecho.hospital_id)'];
						}
					}

					echo "[".$i.",".$r1.",".$r2.",".$r3.",".$r4."],";					
				}                
				?>				
            ]);
            var options = {
                title: 'Número de pacientes ingresados por mes en el año 2020',
                curveType: 'function',
                legend: { position: 'top' }
            };

            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

            chart.draw(data, options);
        }
    </script>
	<script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                [ 'Mes', 'Hospital 1', 'Hospital 2', 'Hospital 3', 'Hospital 4'],
                <?php                
				for ($i = 1; $i <= 12; $i++) {					
					$r1=0;
					$r2=0;
					$r3=0;
					$r4=0;
					$query1 = "SELECT tiempo.mes, count(hecho.hospital_id)
                        FROM test.tiempo INNER JOIN test.hecho ON tiempo.id = hecho.fecha_ingreso_id  
						WHERE hecho.hospital_id=1  AND tiempo.mes=".$i." AND tiempo.anio=2019
                        group by mes";		
					$exec1 = mysqli_query($con,$query1);
					while($row = mysqli_fetch_array($exec1)){
						if($row['mes']==$i){							
							$r1=$row['count(hecho.hospital_id)'];
						}
					}	
					$query2 = "SELECT tiempo.mes, count(hecho.hospital_id)
                        FROM test.tiempo INNER JOIN test.hecho ON tiempo.id = hecho.fecha_ingreso_id  
						WHERE hecho.hospital_id=2  AND tiempo.mes=".$i." AND tiempo.anio=2019
                        group by mes";
					$exec2 = mysqli_query($con,$query2);
					while($row = mysqli_fetch_array($exec2)){
						if($row['mes']==$i){
							$r2=$row['count(hecho.hospital_id)'];
						}
					}
					$query3 = "SELECT tiempo.mes, count(hecho.hospital_id)
                        FROM test.tiempo INNER JOIN test.hecho ON tiempo.id = hecho.fecha_ingreso_id  
						WHERE hecho.hospital_id=3 AND tiempo.mes=".$i." AND tiempo.anio=2019
                        group by mes";
					$exec3 = mysqli_query($con,$query3);
					while($row = mysqli_fetch_array($exec3)){
						if($row['mes']==$i){
							$r3=$row['count(hecho.hospital_id)'];
						}
					}
					$query4 = "SELECT tiempo.mes, count(hecho.hospital_id)
                        FROM test.tiempo INNER JOIN test.hecho ON tiempo.id = hecho.fecha_ingreso_id  
						WHERE hecho.hospital_id=4 AND tiempo.mes=".$i." AND tiempo.anio=2019
                        group by mes";
					$exec4 = mysqli_query($con,$query4);
					while($row = mysqli_fetch_array($exec4)){
						if($row['mes']==$i){
							$r4=$row['count(hecho.hospital_id)'];
						}
					}

					echo "[".$i.",".$r1.",".$r2.",".$r3.",".$r4."],";					
				}                
				?>				
            ]);
            var options = {
                title: 'Número de pacientes ingresados por mes en el año 2019',
                curveType: 'function',
                legend: { position: 'top' }
            };

            var chart = new google.visualization.LineChart(document.getElementById('curve_chart2019'));

            chart.draw(data, options);
        }
    </script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawVisualization);

        function drawVisualization() {
            // Some raw data (not necessarily accurate)
            var data = google.visualization.arrayToDataTable([
                [ 'Edad', 'Mujeres', 'Hombres'],
                <?php
                $query1 = "SELECT '0-19' ,count(CASE WHEN paciente.sexo = 1 THEN hecho.cliente_id END),count(CASE WHEN paciente.sexo = 0 THEN hecho.cliente_id END)
                            FROM test.paciente INNER JOIN test.hecho ON paciente.id = hecho.cliente_id
                            WHERE paciente.edad>0 AND paciente.edad<20";
                $exec1 = mysqli_query($con,$query1);
                $query2 = "SELECT '20-39' ,count(CASE WHEN paciente.sexo = 1 THEN hecho.cliente_id END),count(CASE WHEN paciente.sexo = 0 THEN hecho.cliente_id END)
                            FROM test.paciente INNER JOIN test.hecho ON paciente.id = hecho.cliente_id
                            WHERE paciente.edad>=20 AND paciente.edad<40";
                $exec2 = mysqli_query($con,$query2);
                $query3 = "SELECT '40-59' ,count(CASE WHEN paciente.sexo = 1 THEN hecho.cliente_id END),count(CASE WHEN paciente.sexo = 0 THEN hecho.cliente_id END)
                            FROM test.paciente INNER JOIN test.hecho ON paciente.id = hecho.cliente_id
                            WHERE paciente.edad>=40 AND paciente.edad<60";
                $exec3 = mysqli_query($con,$query3);
                $query4 = "SELECT '60-79' ,count(CASE WHEN paciente.sexo = 1 THEN hecho.cliente_id END),count(CASE WHEN paciente.sexo = 0 THEN hecho.cliente_id END)
                            FROM test.paciente INNER JOIN test.hecho ON paciente.id = hecho.cliente_id
                            WHERE paciente.edad>=60 AND paciente.edad<80";
                $exec4 = mysqli_query($con,$query4);
                $query5 = "SELECT '80-100' ,count(CASE WHEN paciente.sexo = 1 THEN hecho.cliente_id END),count(CASE WHEN paciente.sexo = 0 THEN hecho.cliente_id END)
                            FROM test.paciente INNER JOIN test.hecho ON paciente.id = hecho.cliente_id
                            WHERE paciente.edad>=80 AND paciente.edad<100";
                $exec5 = mysqli_query($con,$query5);
                while($row = mysqli_fetch_array($exec1)){
                    echo "['".$row['0-19']."',".$row['count(CASE WHEN paciente.sexo = 1 THEN hecho.cliente_id END)'].",".$row['count(CASE WHEN paciente.sexo = 0 THEN hecho.cliente_id END)']."],";
                }
                while($row = mysqli_fetch_array($exec2)){
                    echo "['".$row['20-39']."',".$row['count(CASE WHEN paciente.sexo = 1 THEN hecho.cliente_id END)'].",".$row['count(CASE WHEN paciente.sexo = 0 THEN hecho.cliente_id END)']."],";
                }
                while($row = mysqli_fetch_array($exec3)){
                    echo "['".$row['40-59']."',".$row['count(CASE WHEN paciente.sexo = 1 THEN hecho.cliente_id END)'].",".$row['count(CASE WHEN paciente.sexo = 0 THEN hecho.cliente_id END)']."],";
                }
                while($row = mysqli_fetch_array($exec4)){
                    echo "['".$row['60-79']."',".$row['count(CASE WHEN paciente.sexo = 1 THEN hecho.cliente_id END)'].",".$row['count(CASE WHEN paciente.sexo = 0 THEN hecho.cliente_id END)']."],";
                }
                while($row = mysqli_fetch_array($exec5)){
                    echo "['".$row['80-100']."',".$row['count(CASE WHEN paciente.sexo = 1 THEN hecho.cliente_id END)'].",".$row['count(CASE WHEN paciente.sexo = 0 THEN hecho.cliente_id END)']."],";
                }
                ?>
            ]);

            var options = {
                title : 'Total de pacientes ingresados por rangos de edad según su género',
                vAxis: {title: 'Pacientes'},
                hAxis: {title: 'Rango de edad'},
                seriesType: 'bars'
				
            };

            var chart = new google.visualization.ComboChart(document.getElementById('barras_genero'));
            chart.draw(data, options);
        }
    </script>

</head>
<body style="background: #7fbca9;">
<div class="header">
		<h2>Panel informativo</h2>
</div>
<section class="grid-container">   
	<div class="item7" id="curve_chart2019"></div>
	<div class="item2" id="curve_chart"></div>	
	<div class="item5" id="barras_hospitales"></div>	
	<div class="item3" id="barras_genero"></div>
	<div class="grid-containerDonut">
		<div class="item4" id="donutchart"></div>	
		<div class="item6" id="donutchart2019"></div>
	</div>
	<div class="item1" id="barchart_material"></div>	
</section>
</body>
</html>