function autocompletarCeros(input) {
    // Obtener el valor actual del campo de entrada
    var valor = input.value;

    // Eliminar cualquier car�cter no num�rico (por ejemplo, espacios en blanco)
    valor = valor.replace(/\D/g, '');

    // Completar con ceros a la izquierda para tener 5 d�gitos
    valor = ('00000' + valor).slice(-5);

    // Actualizar el valor del campo de entrada con el autollenado
    input.value = valor;
} 