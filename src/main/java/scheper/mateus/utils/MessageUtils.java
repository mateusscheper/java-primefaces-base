package scheper.mateus.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageUtils {

    private MessageUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void returnMessageOnFail(String message) {
        returnMessageOnFail(message, false);
    }

    public static void returnMessageOnFail(String message, boolean isGlobal) {
        if (isGlobal)
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        addMessage(FacesMessage.SEVERITY_ERROR, message);
    }

    public static void returnMessageOnSuccess(String message) {
        returnMessageOnSuccess(message, false);
    }

    public static void returnMessageOnSuccess(String message, boolean isGlobal) {
        if (isGlobal)
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        addMessage(FacesMessage.SEVERITY_INFO, message);
    }

    private static void addMessage(FacesMessage.Severity severityError, String message) {
        FacesMessage facesMessage = new FacesMessage(severityError, "", message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
}