package scheper.mateus.repository;

import scheper.mateus.dto.LoginDTO;

import javax.persistence.NoResultException;
import java.io.Serial;

public class UsuarioRepository extends BaseRepository {

    @Serial
    private static final long serialVersionUID = 2359630155678280083L;

    public LoginDTO login(LoginDTO loginDto) {
        try {
            return entityManager.createQuery("SELECT new scheper.mateus.dto.LoginDTO(u) " +
                            "FROM Usuario u " +
                            "WHERE u.email = :email", LoginDTO.class)
                    .setParameter("email", loginDto.getEmail())
                    .getSingleResult();
        } catch (NoResultException e) {
            return loginDto;
        }
    }
}
