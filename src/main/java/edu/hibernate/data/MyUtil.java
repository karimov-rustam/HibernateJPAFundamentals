package edu.hibernate.data;

import edu.hibernate.data.entities.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class MyUtil {

    static Account createNewAccount(String owner, String name) {
        Account account = new Account();
        account.setCloseDate(new Date());
        account.setOpenDate(new Date());
        account.setCreatedBy("Created by " + owner);
        account.setInitialBalance(new BigDecimal("50.00"));
        account.setName(name);
        account.setCurrentBalance(new BigDecimal("100.00"));
        account.setLastUpdatedBy("Updated by " + owner);
        account.setLastUpdatedDate(new Date());
        account.setCreatedDate(new Date());
        return account;
    }

    static Transaction createNewBeltPurchase(Account account) {
        Transaction beltPurchase = new Transaction();
        beltPurchase.setAccount(account);
        beltPurchase.setTitle("Dress Belt");
        beltPurchase.setAmount(new BigDecimal("50.00"));
        beltPurchase.setClosingBalance(new BigDecimal("0.00"));
        beltPurchase.setCreatedBy("Kevin Bowersox");
        beltPurchase.setCreatedDate(new Date());
        beltPurchase.setInitialBalance(new BigDecimal("0.00"));
        beltPurchase.setLastUpdatedBy("Kevin Bowersox");
        beltPurchase.setLastUpdatedDate(new Date());
        beltPurchase.setNotes("New Dress Belt");
        beltPurchase.setTransactionType("Debit");
        return beltPurchase;
    }

    static Transaction createNewShoePurchase(Account account) {
        Transaction shoePurchase = new Transaction();
        shoePurchase.setAccount(account);
        shoePurchase.setTitle("Work Shoes");
        shoePurchase.setAmount(new BigDecimal("100.00"));
        shoePurchase.setClosingBalance(new BigDecimal("0.00"));
        shoePurchase.setCreatedBy("Kevin Bowersox");
        shoePurchase.setCreatedDate(new Date());
        shoePurchase.setInitialBalance(new BigDecimal("0.00"));
        shoePurchase.setLastUpdatedBy("Kevin Bowersox");
        shoePurchase.setLastUpdatedDate(new Date());
        shoePurchase.setNotes("Nice Pair of Shoes");
        shoePurchase.setTransactionType("Debit");
        return shoePurchase;
    }

    public static User createUser() {
        User user = new User();
        Address address = new Address();
        user.setAge(34);
        user.setAddress(Arrays.asList(new Address[]{createAddress()}));
        user.setBirthDate(new Date());
        user.setCreatedBy("Rustam");
        user.setCreatedDate(new Date());
        user.setCredential(createCredential(user));
        user.setEmailAddress("test@test.com");
        user.setFirstName("Rustam");
        user.setLastName("Karimov");
        user.setLastUpdateby("Rustam");
        user.setLastUpdateDate(new Date());
        return user;
    }

    private static Credential createCredential(User user) {
        Credential credential = new Credential();
        credential.setUser(user);
        credential.setUsername("test_username");
        credential.setPassword("test_password");
        return credential;
    }

    private static Address createAddress() {
        Address address = new Address();
        address.setAddressLine1("Address Line 1");
        address.setAddressLine2("Address Line 2");
        address.setCity("Samara");
        address.setState("SA");
        address.setZipCode("12345");
        address.setAddressType("Primary");
        return address;
    }
}
