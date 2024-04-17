package Useful;

public enum TipoTransaccion {

    SELECCION("SELECCION"), INSERCION("INSERCION"), ACTUALIZACION("ACTUALIZACION"), ELIMINACION("ELIMINACION");
    private String attr;

    TipoTransaccion(String attr) {
        this.attr = attr;
    }
}
