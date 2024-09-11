package model;

import com.google.gson.annotations.SerializedName;

public class CurrencyQuote {
	
	@SerializedName("name")
	private String name;

	public String getName() {
		return name;
	}

}
