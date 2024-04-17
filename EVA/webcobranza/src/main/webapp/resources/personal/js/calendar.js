/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    $('#data_5 .input-daterange').datepicker({
        timePicker: true,
        timePickerIncrement: 30,
        startDate: '01/01/2008',
        endDate: '01/01/2025',
        locale: {
            format: 'DD/MM/YYYY h:mm A'
        }
    });
    $('#data_1 .input-group.date').datepicker({
        timePicker: true,
        timePickerIncrement: 30,
        startDate: '01/01/2008',
        endDate: '01/01/2025',
        locale: {
            format: 'DD/MM/YYYY h:mm A'
        }
    });
});

