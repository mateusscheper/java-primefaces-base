package scheper.mateus.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.SimpleAccountRealm;
import scheper.mateus.dto.LoginDTO;
import scheper.mateus.repository.UsuarioRepository;
import scheper.mateus.utils.StringUtils;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static scheper.mateus.dto.LoginDTO.EMAIL;
import static scheper.mateus.dto.LoginDTO.SENHA;

public class ShiroRealm extends SimpleAccountRealm {

    @Inject
    private UsuarioRepository usuarioRepository;

    public ShiroRealm() {
        setName("loginDTO");
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UserAuthenticationToken authToken = (UserAuthenticationToken) token;
        LoginDTO loginDTO = usuarioRepository.login(authToken.getLoginDTO());

        validateLogin(authToken, loginDTO);

        Map<String, String> credentials = new HashMap<>();
        credentials.put(EMAIL, loginDTO.getEmail());
        credentials.put(SENHA, loginDTO.getSenha());

        // Set the hashed password so Shiro can compare loginDTO correctly
        authToken.getLoginDTO().setSenha(loginDTO.getSenha());

        return new SimpleAuthenticationInfo(loginDTO, credentials, ShiroRealm.class.getName());
    }

    private void validateLogin(UserAuthenticationToken authToken, LoginDTO loginDTO) {
        if (loginDTO.getId() == null || !StringUtils.validatePassword(loginDTO.getSenha(), authToken.getLoginDTO().getSenha()))
            throw new IncorrectCredentialsException();

        if (!loginDTO.isAtivo())
            throw new LockedAccountException();
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return UserAuthenticationToken.class.equals(token.getClass());
    }
}
