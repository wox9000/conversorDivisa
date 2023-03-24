package conversorMonedas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //  input valores de monedas

        ArrayList<Moneda> moneda = new ArrayList<>();
        moneda.add(new Moneda("Peso Argentino", "ARS", 1));
        moneda.add(new Moneda("Dólar", "USD", 0.0026));
        moneda.add(new Moneda("Euro", "EUR", 0.0047));
        moneda.add(new Moneda("Libra Esterlina", "GBP", 0.0042));
        moneda.add(new Moneda("Yen Japonés", "JPY", 0.68));
        moneda.add(new Moneda("Won coreano", "KRW", 6.62));

        // input precios de gasolina

        ArrayList<PrecioGasolina> precio = new ArrayList<>();
        precio.add(new PrecioGasolina("Argentina", 0.969));
        precio.add(new PrecioGasolina("Uruguay", 1.827));
        precio.add(new PrecioGasolina("Chile", 1.619));
        precio.add(new PrecioGasolina("Bolivia", 0.542));
        precio.add(new PrecioGasolina("Brasil", 1.060));
        precio.add(new PrecioGasolina("Paraguay", 1.150));


        // inicio de aplicación gráfica

        JOptionPane.showMessageDialog(null, "Inicio de conversor de moneda");
        JOptionPane.showInternalMessageDialog(null, "Conversor de unidades - by wox9000");

        boolean bandera = true;

        while (bandera) {
            String opciones;
            try {
                opciones = (JOptionPane.showInputDialog(null, "¿Qué operación vas a realizar?", "Menú", JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Conversor de Moneda", "Conversor Gasolina en Viaje"}, "Selección")).toString();
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Ya te vas? Gracias igual! :)");
                break;
            }

            switch (opciones) {
                case "Conversor de Moneda" -> {
                    int divisaOrigen;
                    try {
                        divisaOrigen = Integer.parseInt(JOptionPane.showInputDialog("""
                                A qué moneda vas a convertir?
                                Ingresá un número del 1 al 5
                                Opciones:\s
                                1. Dolares, 2. Euro, 3. Libra Esterlina, 5. Yen Japonés, 5. Won coreano"""));

                        if (ValidarNumeroPais(divisaOrigen)) {

                            double cantidadAConvertir;

                            try {
                                cantidadAConvertir = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la cantidad a convertir"));
                            } catch (NumberFormatException | HeadlessException e) {
                                JOptionPane.showMessageDialog(null, "Error. Formato correcto: 0.00");
                                break;
                            } catch (NullPointerException e) {
                                JOptionPane.showMessageDialog(null, "Operación cancelada");
                                break;
                            }

                            double resultado = moneda.get(divisaOrigen).ConvierteValor(cantidadAConvertir);
                            String mensajeResultado = "Tus pesos equivalen a la cantidad de $" + resultado + " en moneda tipo " + moneda.get(divisaOrigen).getNombreDivisa();
                            JOptionPane.showMessageDialog(null, mensajeResultado);

                        } else {
                            JOptionPane.showMessageDialog(null, "Error. Sólo del 1 al 5. Volver Atrás");
                        }

                    } catch (NumberFormatException e) {
                        JOptionPane.showInternalMessageDialog(null, "Error formato de datos. Vuelve Atrás");
                    }
                }
                case "Conversor Gasolina en Viaje" -> {
                    JOptionPane.showMessageDialog(null, "Aviso: este conversor calcula cuántos LITROS de gasolina puedes comprar con tu presupuesto en pesos argentinos");
                    int seguirONo = 0;
                    while (seguirONo == 0) {

                        try {

                            //C  A  L  C  U  L  A  D  O  R  A en valor DÓLAR BLUE (20/03/2023) $386
                            // USUARIO INGRESA PRESUPUESTO EN MONEDA PESOS ARGENTINOS:
                            double presupuesto = Double.parseDouble(JOptionPane.showInputDialog("Ingresa tu presupuesto en Pesos Argentinos"));

                            int paisDestino = Integer.parseInt(JOptionPane.showInputDialog("Elige a qué país viajarás: 1 = Uruguay, 2 = Chile, 3 = Bolivia, 4 = Brasil, 5 = Paraguay"));

                            PrecioGasolina precioPaisDestino = new PrecioGasolina("paisDestino", presupuesto);
                            // convierte pesos a dólares
                            precioPaisDestino.ConvierteValor(presupuesto);
                            // Convierte el presupuesto en dólares a cantidad de LITROS //formula:  presupuesto / precio del litro = litros
                            double cantidadDeLitrosPaisDestino = (precioPaisDestino.calculaLitros(presupuesto)) / precio.get(paisDestino).getPrecioEnDolares();
                            JOptionPane.showMessageDialog(null, "Tu presupuesto de  " + presupuesto + " equivale a " + cantidadDeLitrosPaisDestino + " litros de gasolina en tu país destino: " + precio.get(paisDestino).getNombrePais());
                            // seguir o salir del ciclo
                            // 0 = seguir 1 = no // 2 = cancela
                            seguirONo = JOptionPane.showConfirmDialog(null, "Hacer otro cálculo?");

                        } catch (NumberFormatException | IndexOutOfBoundsException | HeadlessException |
                                 NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "Error de formato de número ingresado");
                            break;
                        }
                    }
                }
            }

            // 0 = seguir SI // 1 = no // 2 = cancelar
            int seguir = JOptionPane.showConfirmDialog(null, "Volver a Calcular?");
            boolean saludo = seguir == 0;

            if (saludo) {
            } else {
                JOptionPane.showMessageDialog(null, "GRACIAS POR UTILIZAR ESTE CONVERSOR!! FIN DEL PROGRAMA :)");
                bandera = false;
            }
        }
    }

    public static boolean ValidarNumeroPais(int validaOpcion) {
        try {
            return validaOpcion >= 0 && validaOpcion <= 5;
            }catch (NumberFormatException e){
            return false;
        }
    }
}