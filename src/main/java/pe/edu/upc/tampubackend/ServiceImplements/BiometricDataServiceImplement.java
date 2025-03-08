package pe.edu.upc.tampubackend.ServiceImplements;


import org.springframework.stereotype.Service;
import pe.edu.upc.tampubackend.Entities.BiometricData;
import pe.edu.upc.tampubackend.Entities.Users;
import pe.edu.upc.tampubackend.Repositories.IBiometricDataRepository;
import pe.edu.upc.tampubackend.Repositories.IUserRepository;
import pe.edu.upc.tampubackend.Services.BiometricDataService;

import java.util.List;

@Service
public class BiometricDataServiceImplement implements BiometricDataService {

    private IBiometricDataRepository registroBiometricoRepository;
    private IUserRepository usuarioRepository;
    @Override
    public BiometricData guardarRegistro(BiometricData biodata) {

        Users usuario = usuarioRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        BiometricData biometricData = new BiometricData();

        biometricData.setUser(usuario);
        biometricData.setHeartRate(biodata.getHeartRate());
        biometricData.setBloodPressure(biodata.getBloodPressure());
        biometricData.setSpo2(biodata.getSpo2());
        biometricData.setDate(biodata.getDate());

        BiometricData guardado = registroBiometricoRepository.save(biometricData);

        // 🔥 Lógica para detectar ansiedad y enviar notificación
        if (esAtaqueAnsiedad(guardado)) {
            //notificacionService.enviarNotificacion(usuario);
        }

        return guardado;
    }

    //ALGORITMO TEMPORAL XD
    @Override
    public boolean esAtaqueAnsiedad(BiometricData registro) {
        return registro.getHeartRate() > 120 && registro.getSpo2() < 90;
    }

    @Override
    public List<BiometricData> obtenerRegistros(Long usuarioId) {
        return registroBiometricoRepository.findByUserIdOrderByDateDesc(usuarioId);
    }
}
