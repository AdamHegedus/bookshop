package com.epam.bookshop.stock.view.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.bookshop.authentication.service.AuthenticationService;
import com.epam.bookshop.authentication.view.controller.LoginFormController;
import com.epam.bookshop.book.domain.BookFormat;
import com.epam.bookshop.home.view.model.HomepageModel;
import com.epam.bookshop.home.view.model.LanguageUrlMapping;
import com.epam.bookshop.home.view.support.LocalizationUrlBuilder;
import com.epam.bookshop.i18n.service.LocalizationService;
import com.epam.bookshop.stock.view.model.AddBookFormModel;
import com.epam.bookshop.stock.view.model.AddBookRequest;

@Controller
public class AddBookFormController {
    public static final String REQUEST_MAPPING = "/addBookForm.html";
    private final LocalizationService localizationService;
    private final AuthenticationService authenticationService;
    private final LocalizationUrlBuilder localizationUrlBuilder;

    @Autowired
    public AddBookFormController(LocalizationService localizationService, AuthenticationService authenticationService,
            LocalizationUrlBuilder localizationUrlBuilder) {
        super();

        this.localizationService = localizationService;
        this.authenticationService = authenticationService;
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

    @ModelAttribute("addBookRequest")
    public AddBookRequest createListBooksModel(@ModelAttribute AddBookRequest addBookRequest) {
        return new AddBookRequest();
    }

    @ModelAttribute("addBookFormModel")
    public AddBookFormModel createListBooksModel() {
        return new AddBookFormModel(Arrays.asList(BookFormat.values()));
    }

    @RequestMapping(value = REQUEST_MAPPING, method = RequestMethod.GET)
    private String createBook() {
        return "add_book";
    }

}
