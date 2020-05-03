//SELECT '10-15' ,count(CASE WHEN paciente.sexo = 1 THEN hecho.cliente_id END),count(CASE WHEN paciente.sexo = 0 THEN hecho.cliente_id END)
                        FROM test.paciente INNER JOIN test.hecho ON paciente.id = hecho.cliente_id
						WHERE paciente.edad>30 AND paciente.edad<60;
<?php
    $con = mysqli_connect('localhost:3310','root','','test');
?>

<!DOCTYPE HTML>
<html>
<head>
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
                title: 'Número de fallecidos por cada hospital',                
                bars: 'horizontal' // Required for Material Bar Charts.
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
                ['hospital_id', 'Alcoholismo', 'sum(paciente.cancer)', 'sum(paciente.cardiopatia)',
                'sum(paciente.colesterol)', 'sum(paciente.hepatitis)', 'sum(paciente.hipertension)',
                'sum(paciente.reuma)', 'sum(paciente.tabaquismo)'],
                <?php
                $query = "SELECT hospital_id, sum(paciente.alcoholismo), sum(paciente.cancer), sum(paciente.cardiopatia), sum(paciente.colesterol),
                            sum(paciente.hepatitis), sum(paciente.hipertension), sum(paciente.reuma), sum(paciente.tabaquismo)
                            FROM test.paciente INNER JOIN test.hecho ON paciente.id = hecho.cliente_id
                            WHERE (((paciente.alcoholismo)=1) OR ((paciente.cancer)>0) OR ((paciente.cardiopatia)=1) OR ((paciente.colesterol)=1) OR ((paciente.hepatitis)=1) OR ((paciente.hipertension)=1) OR ((paciente.reuma)=1) OR ((paciente.tabaquismo)=1) OR ((paciente.epoc)=1))
                            group by hecho.hospital_id";
                $exec = mysqli_query($con,$query);
                while($row = mysqli_fetch_array($exec)){
                    echo "[".$row['hospital_id'].",".$row['sum(paciente.alcoholismo)'].", ".$row['sum(paciente.cancer)'].", ".$row['sum(paciente.cardiopatia)'].",
                    ".$row['sum(paciente.colesterol)'].", ".$row['sum(paciente.hepatitis)'].", ".$row['sum(paciente.hipertension)'].", ".$row['sum(paciente.reuma)'].",
                    ".$row['sum(paciente.tabaquismo)']."],";
                }
                ?>
            ]);

            var options = {
                title : 'Total de personas por cada hospital agrupadas por patologías',
                vAxis: {title: 'Total de personas'},
                hAxis: {title: 'Hospital'},
				seriesType: 'bars'};

            var chart = new google.visualization.ComboChart(document.getElementById('barras_hospitales'));
            chart.draw(data, options);
        }
    </script>

    <script type="text/javascript">
        google.charts.load("current", {packages:["corechart"]});
        google.charts.setOnLoadCallback(drawChart);
        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['hospital_id', 'count(hecho.uci)'],
                <?php
                $query = "SELECT hecho.hospital_id, count(hecho.uci) 
                            FROM test.hecho WHERE hecho.uci=\"Si\" 
                            group by hecho.hospital_id";
                $exec = mysqli_query($con,$query);
                while($row = mysqli_fetch_array($exec)){
                    echo "['".$row['hospital_id']."',".$row['count(hecho.uci)']."],";
                }
                ?>
            ]);

            var options = {
                title: 'Pacientes ingresados actualmente en la UCI según los distintos hospitales',
                is3D: true,
            };

            var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
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
                $query1 = "SELECT tiempo.mes, count(hecho.hospital_id)
                        FROM test.tiempo INNER JOIN test.hecho ON tiempo.id = hecho.fecha_ingreso_id  
						WHERE hecho.hospital_id=1 AND tiempo.anio='2020'
                        group by mes";		
                $exec1 = mysqli_query($con,$query1);
				$query2 = "SELECT tiempo.mes, count(hecho.hospital_id)
                        FROM test.tiempo INNER JOIN test.hecho ON tiempo.id = hecho.fecha_ingreso_id  
						WHERE hecho.hospital_id=2 AND tiempo.anio='2020'
                        group by mes";
                $exec2 = mysqli_query($con,$query2);
				$query3 = "SELECT tiempo.mes, count(hecho.hospital_id)
                        FROM test.tiempo INNER JOIN test.hecho ON tiempo.id = hecho.fecha_ingreso_id  
						WHERE hecho.hospital_id=3 AND tiempo.anio='2020'
                        group by mes";
                $exec3 = mysqli_query($con,$query3);
				$query4 = "SELECT tiempo.mes, count(hecho.hospital_id)
                        FROM test.tiempo INNER JOIN test.hecho ON tiempo.id = hecho.fecha_ingreso_id  
						WHERE hecho.hospital_id=4 AND tiempo.anio='2020'
                        group by mes";
                $exec4 = mysqli_query($con,$query4);
				
				for ($i = 1; $i <= 12; $i++) {					
					$r1=0;
					$r2=0;
					$r3=0;
					$r4=0;
					while($row = mysqli_fetch_array($exec1)){
						if($row['mes']==$i){							
							$r1=$row['count(hecho.hospital_id)'];
						}
					}					
					while($row = mysqli_fetch_array($exec2)){
						if($row['mes']==$i){
							$r2=$row['count(hecho.hospital_id)'];
						}
					}
					while($row = mysqli_fetch_array($exec3)){
						if($row['mes']==$i){
							$r3=$row['count(hecho.hospital_id)'];
						}
					}
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
                title: 'Número de pacientes ingresados por mes en el año 2020',
                curveType: 'function',
                legend: { position: 'top' }
            };

            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

            chart.draw(data, options);
        }
    </script>

    <script type="text/javascript">
        google.charts.load('current', {'packages':['table']});
        google.charts.setOnLoadCallback(drawTable);

        function drawTable() {
            var data = google.visualization.arrayToDataTable([
                ['edad', 'sexo', 'uci'],
                <?php
                $query = "SELECT paciente.edad, paciente.sexo, hecho.uci
                            FROM test.paciente INNER JOIN test.hecho ON paciente.id = hecho.cliente_id
                            where hospital_id='1' and fallecido='No' and sexo='0'
                            group by edad";
                $exec = mysqli_query($con,$query);
                while($row = mysqli_fetch_array($exec)){
                    echo "[".$row['edad'].", ".$row['sexo'].",'".$row['uci']."'],";
                }
                ?>
            ]);

            var table = new google.visualization.Table(document.getElementById('table_div'));

            table.draw(data, {showRowNumber: true, width: '100%', height: '100%'});
        }
    </script>


</head>
<body>
    <h2>Panel informativo</h2>
    <tr></tr>
    <table>
        <tr>
            <td><div id="barchart_material" style="width:300px; height: 290px;"></div></td>
            <td><div id="curve_chart" style="width: 800px; height: 290px"></div></td>
            <td><div id="piechart_3d" style="width: 380px; height: 290px;"></div></td>
        </tr>
    </table>
    <table>
        <tr>
            <td><div id="barras_hospitales" style="width: 840px; height: 420px;"></div></td>
            <td><div id="table_div" style="width: 300px; height: 380px;"></div></td>
        </tr>
    </table>



    <!--
    <div id="barchart_material" style="width: 900px; height: 500px;"></div>
    <div id="barras_hospitales" style="width: 900px; height: 500px;"></div>
    <div id="piechart_3d" style="width: 900px; height: 500px;"></div>
    <div id="curve_chart" style="width: 900px; height: 500px"></div>
    <div id="table_div"></div>
    !-->
</body>
</html>