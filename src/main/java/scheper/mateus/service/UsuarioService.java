package scheper.mateus.service;

import scheper.mateus.business.UsuarioBusiness;
import scheper.mateus.dto.NovoRegistroDTO;
import scheper.mateus.exception.BusinessException;

import javax.inject.Inject;
import java.io.Serial;
import java.io.Serializable;

public class UsuarioService implements Serializable {

    
    @Serial
    private static final long serialVersionUID = -899026103031493772L;

    @Inject
    private UsuarioBusiness usuarioBusiness;

    public void registrarNovoUsuario(NovoRegistroDTO novoRegistro) throws BusinessException {
        usuarioBusiness.registrarNovoUsuario(novoRegistro);
    }
}
