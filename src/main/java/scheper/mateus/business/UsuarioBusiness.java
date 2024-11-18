package scheper.mateus.business;

import scheper.mateus.dto.NovoRegistroDTO;
import scheper.mateus.entity.Usuario;
import scheper.mateus.exception.BusinessException;
import scheper.mateus.repository.UsuarioRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;
import static scheper.mateus.utils.StringUtils.validateEmail;

@Transactional
public class UsuarioBusiness {

    @Inject
    private UsuarioRepository usuarioRepository;


    public void registrarNovoUsuario(NovoRegistroDTO novoRegistro) throws BusinessException {
        validarRegistrarNovoUsuario(novoRegistro);

        Usuario usuario = new Usuario(novoRegistro);
        usuarioRepository.persist(usuario);
    }

    private void validarRegistrarNovoUsuario(NovoRegistroDTO novoRegistro) throws BusinessException {
        List<String> erros = new ArrayList<>();
        if (isBlank(novoRegistro.getNome()))
            erros.add("Nome não preenchido.");

        if (isBlank(novoRegistro.getEmail()))
            erros.add("E-mail não preenchido.");
        else if (!validateEmail(novoRegistro.getEmail()))
            erros.add("E-mail inválido.");

        if (isBlank(novoRegistro.getSenha()))
            erros.add("Senha não preenchida.");
        else if (novoRegistro.getSenha().length() < 6)
            erros.add("A senha deve ter no mínimo 6 caracteres.");

        if (!erros.isEmpty())
            throw new BusinessException(erros);
    }
}
