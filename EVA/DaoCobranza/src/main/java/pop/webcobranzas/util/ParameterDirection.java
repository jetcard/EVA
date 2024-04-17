/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.util;

/**
 *
 * @author Jyoverar
 */
public enum ParameterDirection {

    // Resumen:
    //     Se trata de un parámetro de entrada.
    // = 1
    Input ,
            //
            // Resumen:
            //     Se trata de un parámetro de salida.
            // = 2
            Output,
            //
            // Resumen:
            //     El parámetro puede ser de entrada o de salida.
            // = 3
            InputOutput,
            //
            // Resumen:
            //     El parámetro representa un valor devuelto de una operación como un procedimiento
            //     almacenado, una función integrada o una función definida por el usuario.
            // = 6
            ReturnValue, 
    
}
