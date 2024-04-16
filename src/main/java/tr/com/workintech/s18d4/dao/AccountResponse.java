package tr.com.workintech.s18d4.dao;

public record AccountResponse(long id, String accountName, double moneyAmount, CustomerResponse customerResponse) {
}
