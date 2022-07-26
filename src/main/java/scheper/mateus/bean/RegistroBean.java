package scheper.mateus.bean;

import lombok.Getter;
import org.omnifaces.cdi.ViewScoped;
import scheper.mateus.dto.LoginDTO;
import scheper.mateus.dto.NovoRegistroDTO;
import scheper.mateus.exception.BusinessException;
import scheper.mateus.service.LoginService;
import scheper.mateus.service.UsuarioService;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serial;
import java.io.Serializable;

import static scheper.mateus.utils.MessageUtils.returnMessageOnFail;
import static scheper.mateus.utils.MessageUtils.returnMessageOnSuccess;

@Named
@ViewScoped
public class RegistroBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 8606272551167403647L;

    @Inject
    private UsuarioService usuarioService;

    @Getter
    private NovoRegistroDTO novoRegistro = new NovoRegistroDTO();

    public void registrarNovoUsuario() {
        try {
            usuarioService.registrarNovoUsuario(novoRegistro);
            returnMessageOnSuccess("Usuário registrado com sucesso!");
        } catch (BusinessException e) {
            returnMessageOnFail(e.getMessage());
        }
    }
}
