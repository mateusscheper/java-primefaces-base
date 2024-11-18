package scheper.mateus.business;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.subject.Subject;
import scheper.mateus.dto.LoginDTO;
import scheper.mateus.security.UserAuthenticationToken;
import scheper.mateus.utils.MessageUtils;
import scheper.mateus.utils.UrlUtils;

import javax.annotation.PostConstruct;

public class LoginBusiness {

    private static final String URL_HOME = "url.home";

    Subject user;

    @PostConstruct
    public void init() {
        user = SecurityUtils.getSubject();
        if (user.isAuthenticated())
            UrlUtils.redirect(URL_HOME);
    }

    public LoginDTO login(LoginDTO loginDto) {
        user = SecurityUtils.getSubject();

        if (user.isAuthenticated()) {
            UrlUtils.redirect(URL_HOME);
            return loginDto;
        }

        String errorMsg = validateFields(loginDto);

        if (errorMsg != null) {
            MessageUtils.returnMessageOnFail(errorMsg);
            return loginDto;
        }

        UserAuthenticationToken token = new UserAuthenticationToken(loginDto);

        try {
            user.login(token);

            if (user.isAuthenticated())
                UrlUtils.redirect(URL_HOME);

            return loginDto;
        } catch (LockedAccountException e) {
            MessageUtils.returnMessageOnFail("Usuário desativado. Por favor, entre em contato com o administrador");
        } catch (IncorrectCredentialsException e) {
            MessageUtils.returnMessageOnFail("Usuário ou senha incorreta");
        } catch (Exception e) {
            MessageUtils.returnMessageOnFail("Ocorreu um erro ao efetuar o login. Por favor, entre em contato com o suporte");
        }

        return loginDto;
    }

    private String validateFields(LoginDTO loginDto) {
        String message = null;
        if (StringUtils.isBlank(loginDto.getEmail()) && StringUtils.isBlank(loginDto.getSenha()))
            message = "Por favor, preencha os campos";
        else if (StringUtils.isBlank(loginDto.getEmail()))
            message = "Usuário ou e-mail não informado";
        else if (StringUtils.isBlank(loginDto.getSenha()))
            message = "Senha não informada";

        return message;
    }

    public void logout() {
        user.logout();
    }
}
