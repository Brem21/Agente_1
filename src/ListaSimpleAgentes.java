public class ListaSimpleAgentes {
    private Nodo cabeza;

    // Método para agregar un nuevo agente
    public void agregar(Agente agente) {
        Nodo nuevo = new Nodo(agente);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    // Método para modificar un agente por ID
    public boolean modificar(String id, String nuevaMision, int nuevaPeligrosidad, double nuevoPago) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.agente.id.equals(id)) {
                actual.agente.mision = nuevaMision;
                actual.agente.peligrosidad = nuevaPeligrosidad;
                actual.agente.pagoMensual = nuevoPago;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    // Método para eliminar un agente por ID
    public boolean eliminar(String id) {
        if (cabeza == null) return false;
        if (cabeza.agente.id.equals(id)) {
            cabeza = cabeza.siguiente;
            return true;
        }

        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.agente.id.equals(id)) {
                actual.siguiente = actual.siguiente.siguiente;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    // Método para listar todos los agentes
    public String listar() {
        Nodo actual = cabeza;
        StringBuilder sb = new StringBuilder();
        while (actual != null) {
            Agente a = actual.agente;
            sb.append("ID: ").append(a.id)
                    .append(", Nombre: ").append(a.nombre)
                    .append(", Misión: ").append(a.mision)
                    .append(", Peligrosidad: ").append(a.peligrosidad)
                    .append(", Pago mensual: $").append(a.pagoMensual)
                    .append(", Aporte fondo: $").append(a.calcularAporte())
                    .append(", Impuesto mensual: $").append(String.format("%.2f", a.calcularImpuesto() / 12))
                    .append(", Pago neto: $").append(String.format("%.2f", a.pagoNeto()))
                    .append("\n\n");
            actual = actual.siguiente;
        }
        return sb.toString();
    }

    public Nodo buscarNodo(String id) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.agente.id.equals(id)) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }

}
