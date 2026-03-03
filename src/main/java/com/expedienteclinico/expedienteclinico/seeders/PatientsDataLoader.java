package com.expedienteclinico.expedienteclinico.seeders;

import com.expedienteclinico.expedienteclinico.models.patients.*;
import com.expedienteclinico.expedienteclinico.repositories.Patients.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Profile("dev")
public class PatientsDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(PatientsDataLoader.class);

    private final IPatientsRepository patientsRepository;
    private final IPatientsAppointmentsRepository appointmentsRepository;
    private final ITriageRepository triageRepository;
    private final IClinicHistoryRepository clinicHistoryRepository;

    public PatientsDataLoader(IPatientsRepository patientsRepository, IPatientsAppointmentsRepository appointmentsRepository, ITriageRepository triageRepository, IClinicHistoryRepository clinicHistoryRepository) {
        this.patientsRepository = patientsRepository;
        this.appointmentsRepository = appointmentsRepository;
        this.triageRepository = triageRepository;
        this.clinicHistoryRepository = clinicHistoryRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        // Al usar UUID, count() sigue funcionando igual
        if (patientsRepository.count() > 0) {
            log.info("Módulo clínico ya poblado. Se omite el seeding.");
            return;
        }

        log.info("Iniciando populado del Módulo Clínico con soporte UUID...");

        // No asignamos ID, Hibernate generará el UUID automáticamente
        PatientsModel juan = new PatientsModel();
        juan.setNombre("Juan");
        juan.setApellidos("Pérez Automático");
        juan.setCurp("PERJ900101HDFXXX99");
        juan.setFechaNacimiento(LocalDate.of(1990, 1, 1));
        juan.setGenero("M");
        juan.setTelefono("9988776655");
        juan.setEmail("juan.seeder@email.com");
        juan.setDireccion("Villas Jotoch, Cancún");
        juan.setTipoSangre("O+");

        // Al guardar, JPA llena el campo 'id' de la variable 'juan' con un UUID real
        juan = patientsRepository.save(juan);
        log.info("Paciente Juan creado con UUID: {}", juan.getId());

        LocalDateTime ahora = LocalDateTime.now();

        PatientsAppointmentsModel cita = new PatientsAppointmentsModel();
        cita.setPaciente(juan); // Pasamos el objeto completo, JPA extrae el UUID de la relación
        cita.setFechaHoraInicio(ahora.plusDays(1).withHour(10).withMinute(0));
        cita.setFechaHoraFin(ahora.plusDays(1).withHour(11).withMinute(0));
        cita.setEstado(StatusAppointment.PENDIENTE);
        cita.setConsultorio("Consultorio 101");
        appointmentsRepository.save(cita);
        log.info("Cita vinculada al UUID del paciente");

        // 3. TRIAJE DE JUAN
        TriageModel triaje = new TriageModel();
        triaje.setPaciente(juan);
        triaje.setFechaHora(ahora);
        triaje.setTemperatura(38.5);
        triaje.setPresionArterial("130/90");
        triaje.setFrecuenciaCardiaca(95);
        triaje.setSaturacionOxigeno(96);
        triaje.setNivel(UrgencyLevel.URGENCY);
        triageRepository.save(triaje);
        log.info("Triaje vinculado al UUID del paciente");

        ClinicHistoryModel historial = new ClinicHistoryModel();
        historial.setPaciente(juan);
        historial.setFechaRegistro(ahora);
        historial.setMotivoConsulta("Fiebre persistente y dolor de cabeza");
        historial.setPadecimientoActual("Dolor de cabeza punzante desde hace 48 horas");
        historial.setDiagnosticoPreliminar("Infección viral a descartar dengue");
        clinicHistoryRepository.save(historial);
        log.info("Historial vinculado al UUID del paciente");

        log.info("Seeding completado exitosamente con identificadores UUID!");
    }
}
