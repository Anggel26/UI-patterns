package state;

import java.util.ArrayList;
import java.util.List;

public class GestorSesionUI {
    // Current authentication status
    private boolean userActive;
    // Current authenticated user identifier
    private String username;
    // Temporary draft storage for lazy registration
    private ReservationDraft draftReservation;
    // Confirmed reservation data
    private ReservationDraft confirmedReservation;
    // Latest generated toast notification
    private String lastToast;

    // Inner class representing reservation data
    public static class ReservationDraft {
        public String resourceName;
        public String contactPhone;
        public String contactEmail;

        public ReservationDraft(String resourceName, String contactPhone, String contactEmail) {
            this.resourceName = resourceName;
            this.contactPhone = contactPhone;
            this.contactEmail = contactEmail;
        }
    }

    public GestorSesionUI() {
        this.userActive = false;
        this.username = null;
        this.draftReservation = null;
        this.confirmedReservation = null;
        this.lastToast = "";
    }

    // Calculate completion percentage based on 3 standard fields
    public int calculateProgress(String resource, String phone, String email) {
        int filled = countFilledFields(resource, phone, email);
        if (filled == 1) {
            return 33;
        } else if (filled == 2) {
            return 67;
        } else if (filled >= 3) {
            return 100;
        }
        return 0;
    }

    // Count how many required fields contain values
    private int countFilledFields(String resource, String phone, String email) {
        int count = 0;
        if (resource != null && !resource.trim().isEmpty()) {
            count++;
        }
        if (phone != null && !phone.trim().isEmpty()) {
            count++;
        }
        if (email != null && !email.trim().isEmpty()) {
            count++;
        }
        return count;
    }

    // Return detailed user feedback regarding form completeness
    public String getCompletenessStatus(String resource, String phone, String email) {
        int percentage = calculateProgress(resource, phone, email);
        int filled = countFilledFields(resource, phone, email);

        if (percentage == 100) {
            return "[Progreso: 100%] ¡Formulario completo!";
        }

        List<String> missing = new ArrayList<>();
        if (resource == null || resource.trim().isEmpty()) {
            missing.add("Nombre del recurso");
        }
        if (phone == null || phone.trim().isEmpty()) {
            missing.add("Teléfono de contacto");
        }
        if (email == null || email.trim().isEmpty()) {
            missing.add("Correo de contacto");
        }

        String missingStr = String.join(", ", missing);
        return "[Progreso: " + percentage + "%] Campos listos: " + filled + " de 3 (Falta: " + missingStr + ")";
    }

    // Attempt to save reservation applying lazy registration logic
    public String saveReservation(String resource, String phone, String email) {
        if (!userActive) {
            // Store progress in temporary draft so user does not lose input
            this.draftReservation = new ReservationDraft(resource, phone, email);
            return "[Lazy Registration] Avance guardado en borrador temporal. Por favor cree su cuenta para finalizar.";
        }

        // Commit reservation directly when user is authenticated
        this.confirmedReservation = new ReservationDraft(resource, phone, email);
        this.draftReservation = null;
        return emitToast("Reserva guardada exitosamente.");
    }

    // Authenticate user and automatically commit pending draft if one exists
    public String authenticateAndCommit(String user) {
        this.userActive = true;
        this.username = user;
        if (draftReservation != null) {
            this.confirmedReservation = this.draftReservation;
            this.draftReservation = null;
            return emitToast("Reserva guardada exitosamente.");
        }
        return emitToast("Sesión iniciada como " + user + ".");
    }

    // Cancel reservation directly in one step without dark patterns
    public String cancelReservation() {
        this.draftReservation = null;
        this.confirmedReservation = null;
        return "[Acción] Reserva cancelada correctamente.";
    }

    // Sign out in one step with neutral feedback
    public String logout() {
        this.userActive = false;
        this.username = null;
        return "[Acción] Sesión cerrada correctamente.";
    }

    // Emit a toast notification message
    public String emitToast(String message) {
        this.lastToast = "[Toast de Estado] " + message;
        return this.lastToast;
    }

    // Getters and setters
    public boolean isUserActive() {
        return userActive;
    }

    public void setUserActive(boolean userActive) {
        this.userActive = userActive;
    }

    public String getUsername() {
        return username;
    }

    public ReservationDraft getDraftReservation() {
        return draftReservation;
    }

    public ReservationDraft getConfirmedReservation() {
        return confirmedReservation;
    }

    public String getLastToast() {
        return lastToast;
    }
}
