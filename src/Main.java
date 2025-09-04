import accidentes.*;

import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static ArrayList<Marca> marcas = new ArrayList<>();
    static ArrayList<Carro> carros = new ArrayList<>();
    static ArrayList<Dueno> duenos = new ArrayList<>();
    static ArrayList<Incidente> incidentes = new ArrayList<>();
    static ArrayList<Comentario> comentarios = new ArrayList<>();

    static void imprimirMenu() {
        System.out.println("  1) Ingresar marca");
        System.out.println("  2) Ingresar carro");
        System.out.println("  3) Ingresar dueño");
        System.out.println("  4) Relacionar dueño con carro");
        System.out.println("  5) Registrar incidente a dueño");
        System.out.println(" Consultas");
        System.out.println("  6) Marca más vendida (conteo de carros)");
        System.out.println("  7) Marca con más incidentes");
        System.out.println("  8) País de origen más común (y cantidad)");
        System.out.println("  9) Mostrar incidentes de cada dueño");
        System.out.println("  0) Salir");
        System.out.print("Escoge la opción que desees: ");
    }

    public static void main(String[] args) {
        while (true) {
            imprimirMenu();
            String entrada = sc.nextLine().trim();
            int opcion;
            try { opcion = Integer.parseInt(entrada);
            }
            catch (Exception e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1: {
                    try {
                        System.out.print("Id (num): ");
                        long id = Long.parseLong(sc.nextLine());
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("País: ");
                        String pais = sc.nextLine();

                        Marca m = new Marca();
                        m.setId(id);
                        m.setNombre(nombre);
                        m.setPais(pais);

                        marcas.add(m);
                        System.out.println("Marca guardada.");
                    } catch (Exception e) {
                        System.out.println("Error creando marca.");
                    }
                    break;
                }

                case 2: {
                    try {
                        System.out.print("Placa: ");
                        String placa = sc.nextLine();
                        System.out.print("Modelo: ");
                        String modelo = sc.nextLine();
                        System.out.print("Año lanzamiento: ");
                        int anio = Integer.parseInt(sc.nextLine());
                        System.out.print("Nombre de la marca del carro: ");
                        String nombreMarca = sc.nextLine();

                        Marca marca = null;
                        for (Marca mTmp : marcas) {
                            if (mTmp.getNombre() != null && mTmp.getNombre().equalsIgnoreCase(nombreMarca)) {
                                marca = mTmp; break;
                            }
                        }
                        if (marca == null) {
                            System.out.println("No existe esa marca. Cree la marca primero.");
                            break;
                        }

                        Carro c = new Carro();
                        c.setPlaca(placa);
                        c.setModelo(modelo);
                        c.setAnioLanzamiento(anio);
                        c.setMarca(marca); // ya mantiene la relación con Marca

                        carros.add(c);
                        System.out.println("Carro guardado.");
                    } catch (Exception e) {
                        System.out.println("Error creando carro.");
                    }
                    break;
                }

                case 3: {
                    try {
                        System.out.print("Cédula: ");
                        long cedula = Long.parseLong(sc.nextLine());
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Apellido: ");
                        String apellido = sc.nextLine();
                        System.out.print("Teléfono: ");
                        String tel = sc.nextLine();

                        Dueno d = new Dueno();
                        d.setCedula(cedula);
                        d.setNombre(nombre);
                        d.setApellido(apellido);
                        d.setTelefono(tel);

                        duenos.add(d);
                        System.out.println("Dueño guardado.");
                    } catch (Exception e) {
                        System.out.println("Error creando dueño.");
                    }
                    break;
                }

                case 4: {
                    try {
                        System.out.print("Cédula del dueño: ");
                        long cedula = Long.parseLong(sc.nextLine());

                        Dueno d = null;
                        for (Dueno dx : duenos) {
                            if (dx.getCedula() == cedula) { d = dx; break;
                            }
                        }
                        if (d == null) {
                            System.out.println("No existe el dueño."); break;
                        }

                        System.out.print("Placa del carro: ");
                        String placa = sc.nextLine();

                        Carro c = null;
                        for (Carro cx : carros) {
                            if (cx.getPlaca() != null && cx.getPlaca().equalsIgnoreCase(placa)) {
                                c = cx; break;
                            }
                        }
                        if (c == null) {
                            System.out.println("No existe el carro."); break;
                        }

                        if (!d.getCarros().contains(c)) d.getCarros().add(c);
                        if (!c.getDuenos().contains(d)) c.getDuenos().add(d);

                        System.out.println("Se relacionó el dueño con el carro.");
                    }
                    catch (Exception e) {
                        System.out.println("No se pudo relacionar.");
                    }
                    break;
                }

                case 5: {
                    try {
                        System.out.print("Cédula del dueño: ");
                        long cedula = Long.parseLong(sc.nextLine());

                        Dueno d = null;
                        for (Dueno dx : duenos) {
                            if (dx.getCedula() == cedula) { d = dx; break; }
                        }
                        if (d == null) {
                            System.out.println("No existe el dueño.");
                            break;
                        }

                        System.out.print("Código incidente: ");
                        long codigo = Long.parseLong(sc.nextLine());
                        System.out.print("Tipo incidente: ");
                        String tipo = sc.nextLine();
                        System.out.print("Fecha (yyyy-MM-dd): ");
                        String fecha = sc.nextLine();
                        System.out.print("Teléfono de contacto: ");
                        String tel = sc.nextLine();

                        Date dt;
                        try {
                            dt = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
                        }
                        catch (Exception ex) {
                            System.out.println("Fecha inválida.");
                            break;
                        }

                        Incidente inc = new Incidente();
                        inc.setCodigo(codigo);
                        inc.setTipoIncidente(tipo);
                        inc.setFechaIncidente(dt);
                        inc.setTelefono(tel);
                        inc.setDueno(d);

                        d.agregarIncidente(inc);
                        incidentes.add(inc);

                        System.out.println("Incidente registrado.");
                    }
                    catch (Exception e) {
                        System.out.println("Error registrando incidente.");
                    }
                    break;
                }

                case 6: {
                    if (carros.isEmpty()) {
                        System.out.println("Sin carros.");
                        break;
                    }
                    Map<String, Integer> conteo = new HashMap<>();
                    for (Carro c : carros) {
                        Marca m = c.getMarca();
                        String nombre = (m != null && m.getNombre() != null) ? m.getNombre() : "Desconocida";
                        conteo.put(nombre, conteo.getOrDefault(nombre, 0) + 1);
                    }
                    String mejor = null; int max = -1;
                    for (Map.Entry<String,Integer> e : conteo.entrySet()) {
                        if (e.getValue() > max) {
                            max = e.getValue(); mejor = e.getKey();
                        }
                    }
                    System.out.println("Marca más vendida: " + mejor + " (" + max + " carros)");
                    break;
                }

                case 7: {
                    if (duenos.isEmpty()) { System.out.println("Sin dueños."); break; }
                    Map<String, Integer> conteo = new HashMap<>();
                    for (Dueno d : duenos) {
                        List<Incidente> listaInc = d.getIncidentes();
                        int cantInc = (listaInc == null) ? 0 : listaInc.size();
                        List<Carro> listaCarros = d.getCarros();
                        if (listaCarros == null) continue;
                        for (Carro car : listaCarros) {
                            Marca m = car.getMarca();
                            String nombre = (m != null && m.getNombre() != null) ? m.getNombre() : "Desconocida";
                            conteo.put(nombre, conteo.getOrDefault(nombre, 0) + cantInc);
                        }
                    }
                    if (conteo.isEmpty()) { System.out.println("No hay incidentes registrados."); break; }
                    String peor = null; int max = -1;
                    for (Map.Entry<String,Integer> e : conteo.entrySet()) {
                        if (e.getValue() > max) { max = e.getValue(); peor = e.getKey(); }
                    }
                    System.out.println("Marca con más incidentes: " + peor + " (" + max + " incidentes)");
                    break;
                }

                case 8: {
                    if (carros.isEmpty()) { System.out.println("Sin carros."); break; }
                    Map<String, Integer> conteo = new HashMap<>();
                    for (Carro c : carros) {
                        Marca m = c.getMarca();
                        String pais = (m != null && m.getPais() != null) ? m.getPais() : "Desconocido";
                        conteo.put(pais, conteo.getOrDefault(pais, 0) + 1);
                    }
                    String masComun = null; int max = -1;
                    for (Map.Entry<String,Integer> e : conteo.entrySet()) {
                        if (e.getValue() > max) { max = e.getValue(); masComun = e.getKey(); }
                    }
                    System.out.println("País de origen más común: " + masComun + " (" + max + " carros)");
                    break;
                }

                case 9: {
                    if (duenos.isEmpty()) { System.out.println("Sin dueños."); break; }
                    for (Dueno d : duenos) {
                        System.out.println("Dueño: " + d);
                        List<Incidente> lista = d.getIncidentes();
                        if (lista == null || lista.isEmpty()) {
                            System.out.println("(Sin incidentes)");
                        } else {
                            for (Incidente inc : lista) System.out.println(String.valueOf(inc));
                        }
                    }
                    break;
                }

                case 0: {
                    System.out.println("Saliendo");
                    return;
                }

                default: {
                    System.out.println("Opción no válida.");
                    break;
                }
            }
        }
    }
}
