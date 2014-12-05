package com.epam.bookshop.shopping.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.epam.bookshop.authentication.service.AuthenticationService;
import com.epam.bookshop.authentication.view.controller.LoginFormController;
import com.epam.bookshop.home.view.model.HomepageModel;
import com.epam.bookshop.home.view.model.LanguageUrlMapping;
import com.epam.bookshop.home.view.support.LocalizationUrlBuilder;
import com.epam.bookshop.i18n.service.LocalizationService;
import com.epam.bookshop.shopping.view.model.ShoppingCart;
import com.epam.bookshop.shopping.view.model.ShowShoppingCartModel;
import com.epam.bookshop.stock.view.controller.AddBookFormController;

@Controller
@SessionAttributes(value = ShoppingCart.SHOPPING_CART_KEY)
public class ShowShoppingCartController {
    public static final String REQUEST_MAPPING = "/showShoppingCart.html";
    private final LocalizationService localizationService;
    private final AuthenticationService authenticationService;
    private final LocalizationUrlBuilder localizationUrlBuilder;

    @Autowired
    public ShowShoppingCartController(LocalizationService localizationService, AuthenticationService authenticationService,
            LocalizationUrlBuilder localizationUrlBuilder) {
        super();
        this.localizationService = localizationService;
        this.authenticationService = authenticationService;
        this.localizationUrlBuilder = localizationUrlBuilder;
    }

    @ModelAttribute("shoppingCartModel")
    public ShowShoppingCartModel createShoppingCartModel(@ModelAttribute(ShoppingCart.SHOPPING_CART_KEY) ShoppingCart shoppingCart,
            LocalizationService localizationService, AuthenticationService authenticationService, LocalizationUrlBuilder localizationUrlBuilder) {
        return new ShowShoppingCartModel(shoppingCart.getItems(), ClearShoppingCartController.REQUEST_MAPPING);
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

    @RequestMapping(value = REQUEST_MAPPING, method = RequestMethod.GET)
    public String addToCart() {
        return "shopping_cart";
    }
}
