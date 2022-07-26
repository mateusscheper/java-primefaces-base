package scheper.mateus.dto;

import lombok.Getter;
import lombok.Setter;
import scheper.mateus.entity.Usuario;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class LoginDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 505864841350030607L;

    public static final String EMAIL = "email";

    public static final String SENHA = "senha";

    private Long id;

    private String email;

    private String senha;

    private boolean ativo;

    public LoginDTO() {
    }

    public LoginDTO(Usuario usuario) {
        this.id = usuario.getIdUsuario();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.ativo = usuario.isAtivo();
    }
}
