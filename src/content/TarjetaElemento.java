package content;

public class TarjetaElemento {
    // Primary title of the element
    private String title;
    // Current operational state
    private String status;
    // Technical parameters hidden by default
    private String technicalDetails;
    // Flag indicating whether advanced technical details are visible
    private boolean showAdvanced;

    public TarjetaElemento(String title, String status, String technicalDetails) {
        this.title = title;
        this.status = status;
        this.technicalDetails = technicalDetails;
        // Keep advanced details hidden initially
        this.showAdvanced = false;
    }

    // Toggle advanced details visibility
    public void toggleAdvanced() {
        this.showAdvanced = !this.showAdvanced;
    }

    // Set advanced visibility directly
    public void setShowAdvanced(boolean showAdvanced) {
        this.showAdvanced = showAdvanced;
    }

    // Alias setter matching workshop naming
    public void setVerAvanzado(boolean verAvanzado) {
        this.showAdvanced = verAvanzado;
    }

    // Check if advanced view is active
    public boolean isShowAdvanced() {
        return this.showAdvanced;
    }

    // Alias getter matching workshop naming
    public boolean isVerAvanzado() {
        return this.showAdvanced;
    }

    // Return the summary view with only essential data
    public String getSummaryView() {
        return "[Vista Resumen] " + title + " · Estado: " + status;
    }

    // Return the secondary technical details
    public String getAdvancedView() {
        return "[Acordeón Desplegado] " + technicalDetails;
    }

    // Render component view depending on disclosure state
    public String render() {
        if (showAdvanced) {
            return getSummaryView() + System.lineSeparator() + getAdvancedView();
        }
        return getSummaryView();
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTechnicalDetails() {
        return technicalDetails;
    }

    public void setTechnicalDetails(String technicalDetails) {
        this.technicalDetails = technicalDetails;
    }
}
