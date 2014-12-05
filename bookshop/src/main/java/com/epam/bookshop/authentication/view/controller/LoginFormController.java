package com.epam.bookshop.authentication.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.bookshop.authentication.service.AuthenticationService;
import com.epam.bookshop.authentication.view.model.LoginRequest;
import com.epam.bookshop.home.view.model.HomepageModel;
import com.epam.bookshop.home.view.model.LanguageUrlMapping;
import com.epam.bookshop.home.view.support.LocalizationUrlBuilder;
import com.epam.bookshop.i18n.service.LocalizationService;
import com.epam.bookshop.stock.view.controller.AddBookFormController;

@Controller
public class LoginFormController {
    public static final String REQUEST_MAPPING = "/login.html";
    private final LocalizationService localizationService;
    private final AuthenticationService authenticationService;
    private final LocalizationUrlBuilder localizationUrlBuilder;

    @Autowired
    public LoginFormController(LocalizationService localizationService, AuthenticationService authenticationService,
            LocalizationUrlBuilder localizationUrlBuilder) {
        super();

        this.localizationService = localizationService;
        this.authenticationService = authenticationService;
        this.localizationUrlBuilder = localizationUrlBuilder;
    }

    @ModelAttribute("loginRequest")
    public LoginRequest createLoginRequest() {
        return new LoginRequest();
    }

    @ModelAttribute("homepageModel")
    public HomepageModel homepageModel() {
        HomepageModel result = new HomepageModel();
        result.setBookshopName("Bookshop");
        result.setLanguageSelectors(getLanguageSelectors());
        if (authenticationService.isUserAuthenticated()) {
            result.setLogoutUrl("/j_spring_security_logout");
            if (authenticationService.isUserAdmin()) {
                result.setAdminUrl(AddBookFormController.REQUEST_MAPPING);
            }
        } else {
            result.setLoginUrl(LoginFormController.REQUEST_MAPPING);
        }
        return result;
    }

    private List<LanguageUrlMapping> getLanguageSelectors() {
        return localizationUrlBuilder.buildAccessibleLanguageSelectors(localizationService.getAccessibleLanguages());
    }

    @RequestMapping(REQUEST_MAPPING)
    public String showLogin(@ModelAttribute("loginRequest") LoginRequest loginRequest, BindingResult bindingResult, HttpSession httpSession) {
        Throwable authException = (Throwable) httpSession.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        if (authException != null) {
            bindingResult.reject(authException.getMessage(), authException.getMessage());
            httpSession.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
        return "login_form";
    }
}
