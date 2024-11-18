package scheper.mateus.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import scheper.mateus.dto.LoginDTO;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

import static scheper.mateus.dto.LoginDTO.EMAIL;
import static scheper.mateus.dto.LoginDTO.SENHA;

public class UserAuthenticationToken implements AuthenticationToken {

    
    @Serial
    private static final long serialVersionUID = 1756334491268582481L;

    private final LoginDTO loginDTO;

    public UserAuthenticationToken() {
        this.loginDTO = (LoginDTO) SecurityUtils.getSubject().getPrincipal();
    }

    public UserAuthenticationToken(LoginDTO loginDTO) {
        this.loginDTO = loginDTO;
    }

    @Override
    public Object getPrincipal() {
        return this.loginDTO;
    }

    @Override
    public Object getCredentials() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put(EMAIL, loginDTO.getEmail());
        credentials.put(SENHA, loginDTO.getSenha());
        return credentials;
    }

    public LoginDTO getLoginDTO() {
        return loginDTO;
    }
}
