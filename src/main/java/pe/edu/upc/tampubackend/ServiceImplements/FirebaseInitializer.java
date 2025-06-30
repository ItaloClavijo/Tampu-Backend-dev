package pe.edu.upc.tampubackend.ServiceImplements;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseInitializer {
    public static void initialize() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) { // Solo inicializa si no hay apps
            String credentialsPath = System.getenv("FIREBASE_CREDENTIALS_PATH");
            FileInputStream serviceAccount = new FileInputStream(credentialsPath);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(com.google.auth.oauth2.GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);
            System.out.println("FirebaseApp initialized!");
        }
    }
}