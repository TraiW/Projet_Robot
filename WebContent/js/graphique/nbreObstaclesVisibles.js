$(document).ready(function () {
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });

    Highcharts.chart('nbreObstaclesVisibles', {
        chart: {
            type: 'spline',
            animation: Highcharts.svg, // don't animate in old IE
            marginRight: 10,
            events: {
                load: function () {

                    // set up the updating of the chart each second
                   var series = this.series[0];
                   /* setInterval(function () {
                        var x = (new Date()).getTime(), // current time
                            y = Math.random();
                        series.addPoint([x, y], true, true);
                    }, 1000);*/
                    
                    setInterval(function(){
                		$.get("rest/cmd/measure",function(data) {
                			console.log("Graph Mesure Obstacle Visible"); 
                			var x = (new Date()).getTime(); // current time
                			var y;
                			y = data.mesuresGraphes[1].value;
                			console.log("y : ",y);
                            series.addPoint([x, y], true, true);
                		});
                		console.log('FIN MesuresGraph Obstacle Visible');
                	},5000);
                }
            }
        },
        title: {
            text: 'Nombre d obstacles visibles par le robot'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: 'Nombre d obstacles visibles'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                    Highcharts.numberFormat(this.y, 2);
            }
        },
        legend: {
            enabled: false
        },
        exporting: {
            enabled: false
        },
        series: [{
            name: 'Nombre d obstacles visibles par le robot',
            data: (function () {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime(),
                    i;

                for (i = -19; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 1000,
                        y: 0,
                    });
                }
                return data;
            }())
        }]
    });
});
