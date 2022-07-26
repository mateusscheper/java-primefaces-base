package scheper.mateus.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class NovoRegistroDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 505864841350030607L;

    private String nome;

    private String email;

    private String senha;

}
