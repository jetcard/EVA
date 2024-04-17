$(function () {
          
                var lineData = {
                    labels: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio"],
                    datasets: [
                        {
                            label: "Fondo Popular",
                            fillColor: "rgba(220,220,220,0.5)",
                            strokeColor: "rgba(220,220,220,1)",
                            pointColor: "rgba(220,220,220,1)",
                            pointStrokeColor: "#fff",
                            pointHighlightFill: "#fff",
                            pointHighlightStroke: "rgba(220,220,220,1)",
                            data: [65, 59, 40, 51, 36, 25, 40]
                        },
                        {
                            label: "Fondo MYPE",
                            fillColor: "rgba(26,179,148,0.5)",
                            strokeColor: "rgba(26,179,148,0.7)",
                            pointColor: "rgba(26,179,148,1)",
                            pointStrokeColor: "#fff",
                            pointHighlightFill: "#fff",
                            pointHighlightStroke: "rgba(26,179,148,1)",
                            data: [48, 48, 60, 39, 56, 37, 30]
                        },
						{
                            label: "Example 03",
                            fillColor: "rgba(151,187,205,0.2)",
                            strokeColor: "rgba(151,187,205,1)",
                            pointColor: "rgba(151,187,205,1)",
                            pointStrokeColor: "#fff",
                            pointHighlightFill: "#fff",
                            pointHighlightStroke: "rgba(151,187,205,1)",
                            data: [10, 20, 10, 50, 30, 50, 10]
                        },
						{
                            label: "Example 04",
                            fillColor: "rgba(99,58,33,0.5)",
                            strokeColor: "rgba(99,88,33,1)",
                            pointColor: "rgba(99,88,33,1)",
                            pointStrokeColor: "#fff",
                            pointHighlightFill: "#fff",
                            pointHighlightStroke: "rgba(99,88,33,1)",
                            data: [60, 20, 45, 59, 29, 55, 50]
                        }
                    ]
                };

                var lineOptions = {
                    scaleShowGridLines: true,
                    scaleGridLineColor: "rgba(0,0,0,.05)",
                    scaleGridLineWidth: 1,
                    bezierCurve: true,
                    bezierCurveTension: 0.4,
                    pointDot: true,
                    pointDotRadius: 4,
                    pointDotStrokeWidth: 1,
                    pointHitDetectionRadius: 20,
                    datasetStroke: true,
                    datasetStrokeWidth: 2,
                    datasetFill: true,
                    responsive: true,
                };


                //var ctx = document.getElementById("lineChart").getContext("2d");
                var ctx = $("#lineChart").get(0).getContext("2d");
                var myNewChart = new Chart(ctx).Line(lineData, lineOptions);

            });
      