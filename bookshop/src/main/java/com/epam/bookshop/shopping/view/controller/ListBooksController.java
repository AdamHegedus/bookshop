package com.epam.bookshop.shopping.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.bookshop.authentication.service.AuthenticationService;
import com.epam.bookshop.authentication.view.controller.LoginFormController;
import com.epam.bookshop.book.domain.Book;
import com.epam.bookshop.book.service.BookSearchService;
import com.epam.bookshop.home.view.model.HomepageModel;
import com.epam.bookshop.home.view.model.LanguageUrlMapping;
import com.epam.bookshop.home.view.support.LocalizationUrlBuilder;
import com.epam.bookshop.i18n.service.LocalizationService;
import com.epam.bookshop.shopping.view.model.BookSummaryView;
import com.epam.bookshop.shopping.view.model.ListBooksModel;
import com.epam.bookshop.shopping.view.model.ListBooksRequest;
import com.epam.bookshop.shopping.view.transform.BookTransformer;
import com.epam.bookshop.stock.view.controller.AddBookFormController;

@Controller
public class ListBooksController {
    public static final String REQUEST_MAPPING = "/shopping.html";
    private final BookSearchService bookSearchService;
    private final BookTransformer bookTransformer;
    private final LocalizationService localizationService;
    private final AuthenticationService authenticationService;
    private final LocalizationUrlBuilder localizationUrlBuilder;

    @Autowired
    public ListBooksController(BookSearchService bookSearchService, BookTransformer bookTransformer, LocalizationService localizationService,
            AuthenticationService authenticationService, LocalizationUrlBuilder localizationUrlBuilder) {
        super();
        this.bookSearchService = bookSearchService;
        this.bookTransformer = bookTransformer;
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

    @ModelAttribute("listBooksModel")
    public ListBooksModel createListBooksModel(ListBooksRequest listBooksRequest) {
        List<Book> books = listBooks(listBooksRequest);
        List<BookSummaryView> bookViews = transformBooks(books);
        return initListBooksModel(bookViews);
    }

    @RequestMapping(value = REQUEST_MAPPING, method = RequestMethod.GET)
    public String showBooks() {
        return "shopping";
    }

    private List<BookSummaryView> transformBooks(List<Book> books) {
        return bookTransformer.transformBooksToSummaries(books);
    }

    private ListBooksModel initListBooksModel(List<BookSummaryView> bookViews) {
        return new ListBooksModel(bookViews);
    }

    private List<Book> listBooks(ListBooksRequest searchBooksRequest) {
        return bookSearchService.listBooks(searchBooksRequest.getTitle());
    }

}
