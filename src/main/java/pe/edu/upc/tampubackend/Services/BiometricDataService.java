package pe.edu.upc.tampubackend.Services;

import pe.edu.upc.tampubackend.Entities.BiometricData;

import java.util.List;

public interface BiometricDataService {

    public BiometricData guardarRegistro(BiometricData dto);

    public boolean esAtaqueAnsiedad(BiometricData registro);

    public List<BiometricData> obtenerRegistros(Long usuarioId);

}
