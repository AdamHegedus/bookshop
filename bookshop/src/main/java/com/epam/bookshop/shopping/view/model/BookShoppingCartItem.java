package com.epam.bookshop.shopping.view.model;

public class BookShoppingCartItem {
	private BookSummaryView book;
	private Integer quantity;

	public BookShoppingCartItem(BookSummaryView book, Integer quantity) {
		super();
		this.book = book;
		this.quantity = quantity;
	}

	public BookSummaryView getBook() {
		return book;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Long getBookId() {
		return book.getBookId();
	}
}
