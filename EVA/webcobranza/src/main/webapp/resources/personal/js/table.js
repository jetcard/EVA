/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $('.dataTables-example').DataTable({
        "bFilter": false,
        "bInfo": false,
        "paging": true,
        "ordering": false,
        "info": false,
        dom: 'Bfrtip',
        buttons: [
            {extend: 'copy'},
            {extend: 'csv'},
            {extend: 'excel', title: 'ExampleFile'},
            {extend: 'pdf', title: 'ExampleFile'}
        ]
    });

    $('.dataTables-example2').DataTable({
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            {extend: 'copy'},
            {extend: 'csv'},
            {extend: 'excel', title: 'ExampleFile'},
            {extend: 'pdf', title: 'ExampleFile'},
            {extend: 'print',
                customize: function (win) {
                    $(win.document.body).addClass('white-bg');
                    $(win.document.body).css('font-size', '10px');

                    $(win.document.body).find('table')
                            .addClass('compact')
                            .css('font-size', 'inherit');
                }
            }
        ]
    });

    $.extend(true, $.fn.dataTable.defaults, {
        "searching": false
    });

    $('.dataTables-clien').DataTable({
        select: true,
        responsive: true,
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            {extend: 'copy'},
            {extend: 'excel', title: 'ListaClienteDeudor'}
        ]
    });

    $('.dataTables-cliente-deudor').DataTable({
        "info": false,
        select: true,
        responsive: false,
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            {extend: 'copy'},
            {extend: 'excel', title: 'ListaClienteDeudor'}
        ]
    });
    
    $('.dataTables-requerimientos').DataTable({
        "info": false,
        select: true,
        responsive: false,
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            {extend: 'copy'},
            {extend: 'excel', title: 'ListaRequerimientos'}
        ]
    });    

   $('.dataTables-activo-judicial').DataTable({
        "info": false,
        select: true,
        responsive: false,
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            {extend: 'copy'},
            {extend: 'excel', title: 'ListaActivoJudicial'}
        ]
    });
    
    $('.dataTables-alertas-prepj').DataTable({
        "info": false,
        select: true,
        responsive: false,
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            {extend: 'copy'},
            {extend: 'excel', title: 'ListaAlertasPrePJ'}
        ],
        colReorder: true,
        /*fixedColumns: {
            leftColumns: 1 // Ajusta según la cantidad de columnas fijas que desees
        }     */   
    });
    /*
    // Añade el filtro de columna en la primera columna (ajusta según la columna deseada)
    $('.dataTables-generacion-demanda thead th:first-child').each(function () {
        var title = $(this).text();
        $(this).html('<input type="text" placeholder="Filtrar ' + title + '" />');
    });

    // Aplica el filtro al escribir en el cuadro de texto
    $('input', $('.dataTables-generacion-demanda thead th:first-child')).on('keyup change', function () {
        table.column(0).search(this.value).draw();
    });    
    
    // Añade los filtros de columna
    /*$('.dataTables-generacion-demanda thead th').each(function () {
        var title = $(this).text();
        $(this).html('<input type="text" placeholder="Filtrar ' + title + '" />');
    });*/

    // Aplica los filtros al escribir en los cuadros de texto
    /*table.columns().every(function () {
        var that = this;

        $('input', this.header()).on('keyup change', function () {
            if (that.search() !== this.value) {
                that.search(this.value).draw();
            }
        });
    }); */   
    
    $('.dataTables-generacion-demanda').DataTable({
        "order": [[0, 'desc']],         
        "info": false,
        "searching": true, // Habilita la función de búsqueda
        "language": {
            "search": "Buscar:", 
            "lengthMenu": "Mostrar _MENU_ registros por página",
            "paginate": {
                "previous": "Anterior", // Cambia el texto del botón "Previous"
                "next": "Siguiente"     // Cambia el texto del botón "Next"
            }//"show": "Ver:", "entries": "entradas"// Cambia el texto del cuadro de búsqueda a "Buscar"
            // Otras propiedades de idioma que puedes personalizar según tu necesidad
            // "lengthMenu": "Mostrar _MENU_ registros por página",
            // "info": "Mostrando _START_ a _END_ de _TOTAL_ registros",
            // ...
        },        
        select: true,
        responsive: false,        
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            {extend: 'copy', text: 'Copiar'},
            {extend: 'excel', title: 'ListaHistoricoDemanda', text: 'Exportar Excel'}
        ]
    });
    
    $('.dataTables-estadoctaa').DataTable({
        "info": false,
        select: true,
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            {extend: 'copy'},
            {extend: 'excel', title: 'ListaClienteDeudor'},
            {extend: 'print',
                customize: function (win) {
                    $(win.document.body).addClass('white-bg');
                    $(win.document.body).css('font-size', '10px');

                    $(win.document.body).find('table')
                            .addClass('compact')
                            .css('font-size', 'inherit');
                }
            }
        ]
    });

    $('.dataTables-estadocta-excel').DataTable({
        "info": false,
        select: true,
        responsive: true,
        "paging": false,
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            {extend: 'copy'},
            {extend: 'excel', title: 'Estado de Cuenta'},
            {extend: 'print',
                customize: function (win) {
                    $(win.document.body).addClass('white-bg');
                    $(win.document.body).css('font-size', '10px');

                    $(win.document.body).find('table')
                            .addClass('compact')
                            .css('font-size', 'inherit');
                }
            }
        ]
    });


    $('.dataTables-llamdas-clien').DataTable({
        select: true,
        responsive: true,
        columnDefs: [
            {targets: [1],
                visible: false,
                searchable: false
            },
            {targets: [2],
                visible: false,
                searchable: false
            },
            {targets: [3],
                visible: false,
                searchable: false
            }],
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            {extend: 'copy'},
            {extend: 'excel', title: 'ListaClienteDeudor'},
            {extend: 'print',
                customize: function (win) {
                    $(win.document.body).addClass('white-bg');
                    $(win.document.body).css('font-size', '10px');

                    $(win.document.body).find('table')
                            .addClass('compact')
                            .css('font-size', 'inherit');
                }
            }
        ]
    });

    $('.dataTables-seg').DataTable({
        select: true,
        responsive: true,
        columnDefs: [
            {targets: [8],
                visible: false,
                searchable: false
            }],
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            {extend: 'copy'},
            {extend: 'excel', title: 'ListaSeguimiento'},
            {extend: 'print',
                customize: function (win) {
                    $(win.document.body).addClass('white-bg');
                    $(win.document.body).css('font-size', '10px');

                    $(win.document.body).find('table')
                            .addClass('compact')
                            .css('font-size', 'inherit');
                }
            }
        ]
    });

    $('.dataTables-rec-diario').DataTable({
        select: true,
        responsive: true,
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            {extend: 'copy'},
            {extend: 'excel', title: 'ListaRecorridoDiario'},
            {extend: 'print',
                customize: function (win) {
                    $(win.document.body).addClass('white-bg');
                    $(win.document.body).css('font-size', '10px');

                    $(win.document.body).find('table')
                            .addClass('compact')
                            .css('font-size', 'inherit');
                }
            }
        ]
    });

    $('.dataTables-comprom-clien').DataTable({
        select: true,
        responsive: true,
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            {extend: 'copy'},
            {extend: 'excel', title: 'ListaClienteDeudor'}
        ]
    });
    
    $('.dataTables-otros-sgto').DataTable({
        select: true,
        responsive: true,
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            {extend: 'copy'},
            {extend: 'excel', title: 'SeguimientoOtrosProc'}
        ],
        order: [[0, 'desc']]
    });

});

//$(function () {
//    $('.summernote').summernote({focus: true});
//});

var save = function () {
    var makrup = $('.click2edit').summernote('code');
    $('.click2edit').summernote('destroy');
};