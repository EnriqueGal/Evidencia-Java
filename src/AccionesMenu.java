import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AccionesMenu {

    public boolean GuardarCita(Citas CitaNueva) throws FileNotFoundException {
        ArrayList<Citas> ListCitas = CargarCitas();
        StringBuilder sb;
        try (PrintWriter writer = new PrintWriter(new File("Citas.csv"))) {
            for(Citas cita: ListCitas){
                sb = new StringBuilder();
                sb.append(cita.getIdCita());
                sb.append(',');
                sb.append(cita.getFecha());
                sb.append(',');
                sb.append(cita.getIdPaciente());
                sb.append(',');
                sb.append(cita.getIdDoctor());
                sb.append('\n');
                writer.write(sb.toString());
            }
            sb = new StringBuilder();
            sb.append(CitaNueva.getIdCita());
            sb.append(',');
            sb.append(CitaNueva.getFecha());
            sb.append(',');
            sb.append(CitaNueva.getIdPaciente());
            sb.append(',');
            sb.append(CitaNueva.getIdDoctor());
            sb.append('\n');
            writer.write(sb.toString());
            return true;
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }return false;
    }
    public boolean EliminarCita(Citas CitaEliminar){
        ArrayList<Citas> ListCitas = CargarCitas();
        try (PrintWriter writer = new PrintWriter(new File("Citas.csv"))) {
            ListCitas.stream().filter((cita) -> (cita.getIdCita() != CitaEliminar.getIdCita())).map((cita) -> {
                StringBuilder sb = new StringBuilder();
                sb.append(cita.getIdCita());
                sb.append(',');
                sb.append(cita.getFecha());
                sb.append(',');
                sb.append(cita.getIdPaciente());
                sb.append(',');
                sb.append(cita.getIdDoctor());
                return sb;
            }).map((sb) -> {
                sb.append('\n');
                return sb;
            }).forEachOrdered((sb) -> {
                writer.write(sb.toString());
            });

            return true;
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }return false;
    }
    public boolean ActualizarCita(Citas CitaActualizar){
        ArrayList<Citas> ListCitas = CargarCitas();
        boolean regresa= false;
        try (PrintWriter writer = new PrintWriter(new File("Citas.csv"))) {

            ListCitas.stream().map((cita) -> {
                StringBuilder sb = new StringBuilder();
                if(cita.getIdCita() == CitaActualizar.getIdCita()){
                    sb.append(CitaActualizar.getIdCita());
                    sb.append(',');
                    sb.append(CitaActualizar.getFecha());
                    sb.append(',');
                    sb.append(CitaActualizar.getIdPaciente());
                    sb.append(',');
                    sb.append(CitaActualizar.getIdDoctor());
                    sb.append('\n');
                }
                else {
                    sb.append(cita.getIdCita());
                    sb.append(',');
                    sb.append(cita.getFecha());
                    sb.append(',');
                    sb.append(cita.getIdPaciente());
                    sb.append(',');
                    sb.append(cita.getIdDoctor());
                    sb.append('\n');
                }
                return sb;
            }).forEachOrdered((sb) -> {
                writer.write(sb.toString());
            });

            return true;
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }return regresa;
    }
    public ArrayList<Citas> CargarCitas() {
        Path pathToFile = Paths.get("Citas.csv");
        ArrayList<Citas> ListCitas = new ArrayList<>();
        Citas Cita;
        File f = new File(pathToFile.toString());
        if(f.exists()) {
            try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
                String line = br.readLine();
                while (line != null) {
                    Cita = new Citas();
                    String[] attributes = line.split(",");
                    Cita.setIdCita(Integer.parseInt(attributes[0]));
                    Cita.setFecha(attributes[1]);
                    Cita.setIdPaciente(Integer.parseInt(attributes[3]));
                    Cita.setIdDoctor(Integer.parseInt(attributes[4]));
                    ListCitas.add(Cita);
                    line = br.readLine();
                }
            } catch (IOException ioe) {
            }
        }return ListCitas;
    }

    public boolean GuardarDoctores(Doctor DoctorNuevo) {
        ArrayList<Doctor> ListDoctores = CargarDoctores();
        StringBuilder sb;
        try (PrintWriter writer = new PrintWriter(new File("Doctores.csv"))) {
            for(Doctor doctor: ListDoctores){
                sb = new StringBuilder();
                sb.append(doctor.getIdDoctor());
                sb.append(',');
                sb.append(doctor.getNombre());
                sb.append(',');
                sb.append(doctor.getApellido());
                sb.append(',');
                writer.write(sb.toString());
            }
            sb = new StringBuilder();
            sb.append(DoctorNuevo.getIdDoctor());
            sb.append(',');
            sb.append(DoctorNuevo.getNombre());
            sb.append(',');
            sb.append(DoctorNuevo.getApellido());
            sb.append(',');
            writer.write(sb.toString());
            return true;
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }return false;
    }
    public void ActualizarDoctores(Doctor DoctorActualizar){
        try (PrintWriter writer = new PrintWriter(new File("Doctores.csv"))) {
            ArrayList<Doctor> ListDoctores = CargarDoctores();
            ListDoctores.stream().map((doctor) -> {
                StringBuilder sb = new StringBuilder();
                if(doctor.getIdDoctor()== DoctorActualizar.getIdDoctor()){
                    sb.append(DoctorActualizar.getIdDoctor());
                    sb.append(',');
                    sb.append(DoctorActualizar.getNombre());
                    sb.append(',');
                    sb.append(DoctorActualizar.getApellido());
                    sb.append(',');

                }
                else {
                    sb.append(doctor.getIdDoctor());
                    sb.append(',');
                    sb.append(doctor.getNombre());
                    sb.append(',');
                    sb.append(doctor.getApellido());
                    sb.append(',');

                }
                return sb;
            }).forEachOrdered((sb) -> {
                writer.write(sb.toString());
            });
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<Doctor> CargarDoctores() {
        Path pathToFile = Paths.get("Doctores.csv");
        ArrayList<Doctor> ListaDoctores = new ArrayList<>();
        Doctor Doctor;
        File f = new File(pathToFile.toString());

        if(f.exists()) {
            try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
                String line = br.readLine();
                while (line != null) {

                    Doctor = new Doctor();
                    String[] attributes = line.split(",");

                    Doctor.setIdDoctor(Integer.parseInt(attributes[0]));
                    Doctor.setNombre(attributes[1]);
                    Doctor.setApellido(attributes[2]);
                    ListaDoctores.add(Doctor);
                    line = br.readLine();
                }
            } catch (IOException ioe) {
            }
        }return ListaDoctores;
    }

    public boolean GuardarPaciente(Paciente PacienteNuevo) throws IOException {
        ArrayList<Paciente> ListPaciente = CargarPaciente();
        StringBuilder sb;
        try (PrintWriter writer = new PrintWriter(new File("Pacientes.csv"))) {
            for(Paciente paciente: ListPaciente){
                sb = new StringBuilder();
                sb.append(paciente.getIdPaciente());
                sb.append(',');
                sb.append(paciente.getNombre());
                sb.append(',');
                sb.append(paciente.getApellido());
                sb.append(',');
                writer.write(sb.toString());
            }
            sb= new StringBuilder();
            sb.append(PacienteNuevo.getIdPaciente());
            sb.append(',');
            sb.append(PacienteNuevo.getNombre());
            sb.append(',');
            sb.append(PacienteNuevo.getApellido());
            sb.append(',');
            writer.write(sb.toString());
            return true;
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }return false;
    }
    public void ActualizarPaciente(Paciente PacienteActualizar){
        try (PrintWriter writer = new PrintWriter(new File("Pacientes.csv"))) {
            ArrayList<Paciente> ListPaciente = CargarPaciente();
            ListPaciente.stream().map((paciente) -> {
                StringBuilder sb = new StringBuilder();
                if(paciente.getIdPaciente() == PacienteActualizar.getIdPaciente()){
                    sb.append(PacienteActualizar.getIdPaciente());
                    sb.append(',');
                    sb.append(PacienteActualizar.getNombre());
                    sb.append(',');
                    sb.append(PacienteActualizar.getApellido());
                    sb.append(',');
                } else {
                    sb.append(paciente.getIdPaciente());
                    sb.append(',');
                    sb.append(paciente.getNombre());
                    sb.append(',');
                    sb.append(paciente.getApellido());
                    sb.append(',');
                }
                return sb;
            }).forEachOrdered((sb) -> {
                writer.write(sb.toString());
            });
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<Paciente> CargarPaciente() {
        Path pathToFile = Paths.get("Pacientes.csv");
        ArrayList<Paciente> ListaPaciente = new ArrayList<>();
        Paciente Pacien;
        File f = new File(pathToFile.toString());
        if(f.exists()) {
            try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
                String line = br.readLine();
                while (line != null) {
                    Pacien = new Paciente();
                    String[] attributes = line.split(",");
                    Pacien.setIdPaciente(Integer.parseInt(attributes[0]));
                    Pacien.setNombre(attributes[1]);
                    Pacien.setApellido(attributes[2]);
                    ListaPaciente.add(Pacien);
                    line = br.readLine();
                }
            } catch (IOException ioe) {
            }
        }return ListaPaciente;
    }


}
