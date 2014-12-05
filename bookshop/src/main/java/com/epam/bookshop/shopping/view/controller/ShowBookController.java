package com.epam.bookshop.shopping.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.bookshop.authentication.service.AuthenticationService;
import com.epam.bookshop.authentication.view.controller.LoginFormController;
import com.epam.bookshop.book.domain.Book;
import com.epam.bookshop.book.service.BookSearchService;
import com.epam.bookshop.home.view.model.HomepageModel;
import com.epam.bookshop.home.view.model.LanguageUrlMapping;
import com.epam.bookshop.home.view.support.LocalizationUrlBuilder;
import com.epam.bookshop.i18n.service.LocalizationService;
import com.epam.bookshop.shopping.view.model.AddShoppingCartBookItemRequest;
import com.epam.bookshop.shopping.view.model.BookDetailsModel;
import com.epam.bookshop.shopping.view.model.BookDetailsView;
import com.epam.bookshop.shopping.view.model.BookSummaryView;
import com.epam.bookshop.shopping.view.model.ShowBookRequest;
import com.epam.bookshop.shopping.view.transform.BookTransformer;
import com.epam.bookshop.stock.view.controller.AddBookFormController;

@Controller
public class ShowBookController {
    public static final String REQUEST_MAPPING = "/showBook.html";
    private final BookSearchService bookSearchService;
    private final BookTransformer bookTransformer;
    private final LocalizationService localizationService;
    private final AuthenticationService authenticationService;
    private final LocalizationUrlBuilder localizationUrlBuilder;

    @Autowired
    public ShowBookController(BookSearchService bookSearchService, BookTransformer bookTransformer, LocalizationService localizationService,
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

    @ModelAttribute("bookDetailsModel")
    public BookDetailsModel createBookDetailsModel(ShowBookRequest showBookRequest) {
        Book book = findBook(showBookRequest.getBookId());
        return initBookDetailsModel(book);
    }

    @ModelAttribute("addShoppingCartBookItemRequest")
    public AddShoppingCartBookItemRequest createAddShoppingCartBookItemRequest(ShowBookRequest showBookRequest) {
        AddShoppingCartBookItemRequest result = new AddShoppingCartBookItemRequest();
        result.setBookId(showBookRequest.getBookId());
        return result;
    }

    @RequestMapping(REQUEST_MAPPING)
    public String showBooks() {
        return "book_details";
    }

    private BookDetailsModel initBookDetailsModel(Book book) {
        BookSummaryView summary = bookTransformer.transformBookToSummary(book);
        BookDetailsView details = bookTransformer.transformBookToDetails(book);
        return new BookDetailsModel(summary, details, AddToShoppingCartController.REQUEST_MAPPING);
    }

    private Book findBook(Long bookId) {
        return bookSearchService.findBook(bookId);
    }

}
