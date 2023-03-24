package conversorMonedas;

public class PrecioGasolina implements Calcula {

    public PrecioGasolina(String nombrePais, double precioEnDolares) {
        this.nombrePais = nombrePais;
        this.precioEnDolares = precioEnDolares;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public double getPrecioEnDolares() {
        return precioEnDolares;
    }

    private String nombrePais;
    private double precioEnDolares;






    @Override
    public double ConvierteValor(Double presupuestoEnDolares) {

        return presupuestoEnDolares/386;

    }

    public double calculaLitros(double presupuestoALitros){
        return presupuestoALitros/386;
    }


}
