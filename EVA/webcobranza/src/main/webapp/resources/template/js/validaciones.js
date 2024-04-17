function soloDigitos(event) {
    var charCode = (event.which) ? event.which : event.keyCode;

    // Permitir solo dígitos (0-9)
    if (charCode < 48 || charCode > 57) {
        event.preventDefault();
        return false;
    }

    return true;
}

function soloLetras(event) {
    var inputValue = event.target.value;

    // Permitir solo letras (sin números ni caracteres especiales)
    if (!/^[a-zA-Z]+$/.test(inputValue)) {
        alert('Este campo solo acepta letras.');
        event.target.value = inputValue.replace(/[^a-zA-Z]/g, '');
        return false;
    }

    return true;
}

function addPrefix(input, prefix) {
    if (!input.value.startsWith(prefix) && validarInput(input, prefix)) {
        input.value = prefix + input.value;
    }
}

function updatePrefix(input, prefix) {
    if (!input.value.startsWith(prefix) && validarInput(input, prefix)) {
        input.value = prefix + input.value;
    }
}

function validarInput0(input, prefix) {
    // Eliminar el prefijo para realizar la validación
    var valorSinPrefijo = input.value.replace(prefix, '');

    // Validar que no sea solo ceros o letras
    if (/^0+$/.test(valorSinPrefijo) || isNaN(Number(valorSinPrefijo))) {
        input.setCustomValidity("Por favor ingrese una cantidad válida");
        return false;
    } else {
        input.setCustomValidity("");
        return true;
    }
}

    function validarInput(input, prefix) {
        // Obtener el valor actual del input
        var valorActual = input.value;

        // Eliminar el prefijo para realizar la validación
        var valorSinPrefijo = valorActual.replace(prefix, '');

        // Validar que no contenga letras
        if (/[a-zA-Z]/.test(valorSinPrefijo)) {
            input.setCustomValidity("No se permiten letras");
            return false;
        }

        // Validar que no sea solo ceros
        if (/^0+$/.test(valorSinPrefijo)) {
            input.setCustomValidity("Por favor ingrese una cantidad válida diferente de cero");
            return false;
        }

        // Validar que sea un número
        if (isNaN(Number(valorSinPrefijo))) {
            input.setCustomValidity("Por favor ingrese una cantidad válida");
            return false;
        }

        // Restaurar el prefijo al valor actual del input
        input.value = prefix + valorSinPrefijo;

        // Limpiar el mensaje de validación
        input.setCustomValidity("");

        return true;
    }

