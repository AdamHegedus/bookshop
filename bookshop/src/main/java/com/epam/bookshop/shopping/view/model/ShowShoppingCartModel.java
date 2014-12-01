package com.epam.bookshop.shopping.view.model;

import java.util.List;

public class ShowShoppingCartModel {
	private List<BookShoppingCartItem> cartItems;
	private String clearShoppingCartUrl;

	public ShowShoppingCartModel(List<BookShoppingCartItem> cartItems, String clearShoppingCartUrl) {
		super();
		this.cartItems = cartItems;
		this.clearShoppingCartUrl = clearShoppingCartUrl;
	}

	public List<BookShoppingCartItem> getCartItems() {
		return cartItems;
	}

	public String getClearShoppingCartUrl() {
		return clearShoppingCartUrl;
	}

}
