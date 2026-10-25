package main;

import content.TarjetaElemento;
import input.ValidadorForgiving;
import navigation.BreadcrumbNavigator;
import state.GestorSesionUI;

public class Main {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("UI PATTERNS & ETHICS SIMULATION");
        System.out.println("=================================================\n");

        runNavigationTest();
        runProgressiveDisclosureTest();
        runForgivingFormatTest();
        runCompletenessMeterTest();
        runLazyRegistrationAndToastTest();
        runZeroDarkPatternsTest();
    }

    // Test case 1: Breadcrumb and tabs navigation
    private static void runNavigationTest() {
        System.out.println("--- 1. PATRÓN DE NAVEGACIÓN (BREADCRUMBS + TABS) ---");

        BreadcrumbNavigator navigator = new BreadcrumbNavigator("Inicio");
        // User selects Catalog tab
        navigator.setTab("Catálogo");
        // User enters subcategory
        navigator.push("Servidor Principal");

        System.out.println("Ruta actual: " + navigator.getCurrentPath());
        System.out.println();
    }

    // Test case 2: Progressive disclosure accordion
    private static void runProgressiveDisclosureTest() {
        System.out.println("--- 2. PATRÓN DE CONTENIDO Y ESTRUCTURA (PROGRESSIVE DISCLOSURE) ---");

        TarjetaElemento serverCard = new TarjetaElemento(
            "Servidor Principal",
            "Activo",
            "IP: 192.168.1.10 | Puerto: 8080 | RAM: 4GB/16GB"
        );

        // Initially show only primary information
        serverCard.setVerAvanzado(false);
        System.out.println(serverCard.render());
        System.out.println();

        // Reveal technical details on demand
        System.out.println("[Usuario solicita ver detalles avanzados...]");
        serverCard.setVerAvanzado(true);
        System.out.println(serverCard.getAdvancedView());
        System.out.println();
    }

    // Test case 3: Forgiving format input handling
    private static void runForgivingFormatTest() {
        System.out.println("--- 3. PATRÓN DE ENTRADA DE DATOS (FORGIVING FORMAT) ---");

        ValidadorForgiving validator = new ValidadorForgiving();

        // Sample input with formatting symbols
        String rawInput1 = "+57 (300) 123-4567";
        String normalized1 = validator.normalizePhone(rawInput1);
        System.out.println("Entrada del usuario: \"" + rawInput1 + "\"");
        System.out.println("Teléfono normalizado sin error: \"" + normalized1 + "\"");
        System.out.println();

        // Workshop test case input
        String rawInput2 = "+57 (310) 999-8877";
        String normalized2 = validator.normalizePhone(rawInput2);
        System.out.println("Entrada del usuario (Caso 3): \"" + rawInput2 + "\"");
        System.out.println("Teléfono normalizado sin error: \"" + normalized2 + "\"");
        System.out.println();
    }

    // Test case 4: Dynamic completeness meter
    private static void runCompletenessMeterTest() {
        System.out.println("--- 4. PATRÓN DE FEEDBACK Y ESTADO (COMPLETENESS METER) ---");

        GestorSesionUI sessionManager = new GestorSesionUI();

        // Step 1: 1 of 3 fields completed
        String resource = "Laboratorio de Redes";
        System.out.println(sessionManager.getCompletenessStatus(resource, null, null));

        // Step 2: 2 of 3 fields completed
        String phone = "3001234567";
        System.out.println(sessionManager.getCompletenessStatus(resource, phone, null));

        // Step 3: user completes last field
        String email = "estudiante@udistrital.edu.co";
        System.out.println("Usuario ingresa correo: \"" + email + "\"");
        System.out.println(sessionManager.getCompletenessStatus(resource, phone, email));
        System.out.println();
    }

    // Test case 5: Lazy registration and toast notification
    private static void runLazyRegistrationAndToastTest() {
        System.out.println("--- 5. LAZY REGISTRATION + TOAST ---");

        GestorSesionUI sessionManager = new GestorSesionUI();

        // Unauthenticated user attempts to save reservation
        System.out.println("Usuario intenta guardar sin haber iniciado sesión...");
        String response = sessionManager.saveReservation(
            "Laboratorio de Redes",
            "3001234567",
            "estudiante@udistrital.edu.co"
        );
        System.out.println(response);
        System.out.println();

        // User finishes quick signup and draft commits automatically
        System.out.println("Usuario completa registro rápido -> Sesión activa = true");
        String commitResponse = sessionManager.authenticateAndCommit("estudianteUD");
        System.out.println(commitResponse);
        System.out.println();
    }

    // Verification of ethical design without dark patterns
    private static void runZeroDarkPatternsTest() {
        System.out.println("--- 6. ÉTICA EN UI (CERO DARK PATTERNS) ---");

        GestorSesionUI sessionManager = new GestorSesionUI();
        sessionManager.setUserActive(true);

        // Direct one-step cancellation with neutral messaging
        System.out.println("Usuario cancela la reserva de forma directa (Sin Roach Motel ni Confirmshaming):");
        System.out.println(sessionManager.cancelReservation());

        // Direct one-step logout
        System.out.println("Usuario cierra sesión en un solo paso:");
        System.out.println(sessionManager.logout());
        System.out.println();
    }
}
