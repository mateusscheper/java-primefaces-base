package scheper.mateus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scheper.mateus.dto.NovoRegistroDTO;

import static scheper.mateus.utils.StringUtils.hashPasswordWithMD5;

@Entity
@Table(schema = "base", name = "usuario")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue
    @SequenceGenerator(name = "usuario_id_seq", sequenceName = "base.usuario_id_seq")
    private Long idUsuario;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private boolean ativo = true;

    public Usuario(NovoRegistroDTO novoRegistro) {
        this.nome = novoRegistro.getNome();
        this.email = novoRegistro.getEmail();
        this.senha = hashPasswordWithMD5(novoRegistro.getSenha());
    }
}
