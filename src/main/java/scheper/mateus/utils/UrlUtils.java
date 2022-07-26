package scheper.mateus.utils;

import org.apache.http.client.utils.URIBuilder;

import javax.faces.context.FacesContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class UrlUtils {

    private UrlUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static final String PROJECT_ROOT_URL = "/" + UrlUtils.getProperty("project.name");

    public static String getUrl(String urlName) {
        return PROJECT_ROOT_URL + UrlUtils.getProperty(urlName);
    }

    public static void redirect(String urlName) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(getUrl(urlName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void redirect(String urlName, Map<String, String> param) {
        try {
            URIBuilder uriBuilder = new URIBuilder(getUrl(urlName));
            param.forEach(uriBuilder::addParameter);

            FacesContext.getCurrentInstance().getExternalContext().redirect(uriBuilder.build().toString());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static String getProperty(String property) {
        try {
            String configPropertiesFile = Objects.requireNonNull(UrlUtils.class.getClassLoader().getResource("config.properties")).getPath();

            Properties properties;
            try (FileInputStream fis = new FileInputStream(configPropertiesFile)) {
                properties = new Properties();
                properties.load(fis);
            }

            return properties.get(property).toString();
        } catch (IOException e) {
            e.getStackTrace();
        }
        return null;
    }
}