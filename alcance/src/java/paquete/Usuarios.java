package paquete;

import java.util.ArrayList;  // Importa la clase ArrayList de Java
import jakarta.servlet.http.HttpServletRequest;  // Importa la clase HttpServletRequest

public class Usuarios {
    // Utiliza la clase ArrayList de java.util
    ArrayList<String> visitantes = new ArrayList<>();
    String submit = null;
    String nombre = null;

    private void addUsuario(String nombre) {
        visitantes.add(nombre);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }

    public String[] getVisitantes() {
        String[] listaVisitantes = new String[visitantes.size()];
        visitantes.toArray(listaVisitantes);
        return listaVisitantes;
    }

    public void processRequest(HttpServletRequest request) {
        if (submit != null && submit.equals("agregar") && nombre != null && !nombre.isEmpty()) {
            addUsuario(nombre);
        }
        reset();
    }

    private void reset() {
        submit = null;
        nombre = null;
    }
}
