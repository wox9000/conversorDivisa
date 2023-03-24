package conversorMonedas;

public class Moneda implements Calcula {


    public Moneda(String nombreDivisa, String codigo, double valorDivisa) {
        this.nombreDivisa = nombreDivisa;
        this.codigo = codigo;
        this.valorDivisa = valorDivisa;
    }

    private String nombreDivisa;
    private String codigo;
    private double valorDivisa;





    public String getNombreDivisa() {
        return nombreDivisa;
    }

    public double getValorDivisa() {
        return valorDivisa;
    }

    public void setNombreDivisa(String nombreDivisa) {
        this.nombreDivisa = nombreDivisa;
    }

    public void setValorDivisa(double valorDivisa) {
        this.valorDivisa = valorDivisa;
    }


    @Override
    public double ConvierteValor(Double valorAConvertir) {
       return valorAConvertir*valorDivisa;
       }
    }


