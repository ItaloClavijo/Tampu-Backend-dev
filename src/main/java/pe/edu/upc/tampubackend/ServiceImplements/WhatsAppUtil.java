package pe.edu.upc.tampubackend.ServiceImplements;

import okhttp3.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WhatsAppUtil {

    private static final OkHttpClient client = new OkHttpClient();

    // Puedes mover estos a application.properties o .env
    private static final String API_KEY = "9617515";  // ← reemplaza
    private static final String BASE_URL = "https://api.callmebot.com/whatsapp.php";

    public static void enviarAlerta(String numeroTelefono, String mensaje) {
        String url = String.format(
                "%s?phone=%s&text=%s&apikey=%s",
                BASE_URL,
                numeroTelefono,
                URLEncoder.encode(mensaje, StandardCharsets.UTF_8),
                API_KEY
        );

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("❌ Error al enviar WhatsApp: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("🔗 URL generada: " + url);
                System.out.println("✅ Mensaje enviado: " + response.body().string());
            }
        });

    }

}
