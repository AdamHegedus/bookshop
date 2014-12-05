package com.epam.bookshop.shopping.view.model;

public class BookDetailsModel {
    private final BookSummaryView bookSummary;
    private final BookDetailsView bookDetails;
    private final String addToCartUrl;

    public BookDetailsModel(BookSummaryView bookSummary, BookDetailsView bookDetails, String addToCartUrl) {
        super();
        this.bookSummary = bookSummary;
        this.bookDetails = bookDetails;
        this.addToCartUrl = addToCartUrl;
    }

    public BookSummaryView getBookSummary() {
        return bookSummary;
    }

    public BookDetailsView getBookDetails() {
        return bookDetails;
    }

    public String getAddToCartUrl() {
        return addToCartUrl;
    }
}
