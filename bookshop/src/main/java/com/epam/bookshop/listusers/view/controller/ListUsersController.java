package com.epam.bookshop.listusers.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.bookshop.authentication.service.AuthenticationService;
import com.epam.bookshop.authentication.view.controller.LoginFormController;
import com.epam.bookshop.home.view.model.HomepageModel;
import com.epam.bookshop.home.view.model.LanguageUrlMapping;
import com.epam.bookshop.home.view.support.LocalizationUrlBuilder;
import com.epam.bookshop.i18n.service.LocalizationService;
import com.epam.bookshop.listusers.view.model.ListUsersModel;
import com.epam.bookshop.listusers.view.model.ListUsersRequest;
import com.epam.bookshop.listusers.view.model.UserSummaryView;
import com.epam.bookshop.listusers.view.transform.UserTransformer;
import com.epam.bookshop.stock.view.controller.AddBookFormController;
import com.epam.bookshop.user.domain.User;
import com.epam.bookshop.user.service.UserSearchService;

@Controller
public class ListUsersController {
    public static final String REQUEST_MAPPING = "/users.html";
    private final UserSearchService userSearchService;
    private final UserTransformer userTransformer;
    private final LocalizationService localizationService;
    private final AuthenticationService authenticationService;
    private final LocalizationUrlBuilder localizationUrlBuilder;

    @Autowired
    public ListUsersController(UserSearchService userSearchService, UserTransformer userTransformer, LocalizationService localizationService,
            AuthenticationService authenticationService, LocalizationUrlBuilder localizationUrlBuilder) {
        super();
        this.userSearchService = userSearchService;
        this.userTransformer = userTransformer;
        this.authenticationService = authenticationService;
        this.localizationService = localizationService;
        this.localizationUrlBuilder = localizationUrlBuilder;
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

    @ModelAttribute("listUsersModel")
    public ListUsersModel createListUsersModel(ListUsersRequest listUsersRequest) {
        List<User> users = listUsers(listUsersRequest);
        List<UserSummaryView> userViews = transformUsers(users);
        return initListUsersModel(userViews);
    }

    @RequestMapping(value = REQUEST_MAPPING, method = RequestMethod.GET)
    public String showUsers() {
        return "users";
    }

    private List<UserSummaryView> transformUsers(List<User> users) {
        return userTransformer.transformUsersToSummaries(users);
    }

    private ListUsersModel initListUsersModel(List<UserSummaryView> userViews) {
        return new ListUsersModel(userViews);
    }

    private List<User> listUsers(ListUsersRequest searchUsersRequest) {
        return userSearchService.listUsers(searchUsersRequest.getUsername());
    }

}
