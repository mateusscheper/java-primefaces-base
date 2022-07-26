package scheper.mateus.service;

import scheper.mateus.business.LoginBusiness;
import scheper.mateus.dto.LoginDTO;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serial;
import java.io.Serializable;

public class LoginService implements Serializable {

    @Serial
    private static final long serialVersionUID = 3591898946654776670L;

    @Inject
    LoginBusiness loginBusiness;

    public LoginDTO login(LoginDTO loginDto) {
        return loginBusiness.login(loginDto);
    }

    public void logout() {
        loginBusiness.logout();
    }

}
