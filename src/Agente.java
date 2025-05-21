public class Agente {
    String id;
    String nombre;
    String mision;
    int peligrosidad;
    double pagoMensual;

    public Agente(String id, String nombre, String mision, int peligrosidad, double pagoMensual) {
        this.id = id;
        this.nombre = nombre;
        this.mision = mision;
        this.peligrosidad = peligrosidad;
        this.pagoMensual = pagoMensual;
    }

    public double calcularAporte() {
        return pagoMensual * 0.08;
    }

    public double calcularImpuesto() {
        double anual = pagoMensual * 12;
        if (anual <= 5000) return 0;
        else if (anual <= 10000) return (anual - 5000) * 0.10;
        else if (anual <= 20000) return (5000 * 0.10) + (anual - 10000) * 0.20;
        else return (5000 * 0.10) + (10000 * 0.20) + (anual - 20000) * 0.30;
    }

    public double pagoNeto() {
        return pagoMensual - calcularAporte() - (calcularImpuesto() / 12);
    }

    public void mostrarInforme() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Pago mensual: $" + pagoMensual);
        System.out.println("Aporte al fondo: $" + calcularAporte());
        System.out.println("Impuesto mensual: $" + (calcularImpuesto() / 12));
        System.out.println("Pago neto: $" + pagoNeto());
        System.out.println("---------------");
    }
}
