package scheper.mateus.bean;

import org.omnifaces.cdi.ViewScoped;
import scheper.mateus.dto.LoginDTO;
import scheper.mateus.service.LoginService;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serial;
import java.io.Serializable;

@Named
@ViewScoped
public class LoginBean implements Serializable {

    
    @Serial
    private static final long serialVersionUID = 8606272551167403647L;

    @Inject
    private LoginService loginService;

    private LoginDTO loginDto = new LoginDTO();

    public void login() {
        loginDto = loginService.login(loginDto);
    }

    public void logout() {
        loginService.logout();
    }

    public LoginDTO getLoginDto() {
        return loginDto;
    }

    public void setLoginDto(LoginDTO loginDto) {
        this.loginDto = loginDto;
    }
}
