package pojos.bankingapipojos;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Customer {
    private String accountOpenDate;
    private List<Account> account;
    private Boolean active;
    private String address;
    private String fullName;
    private Integer customerId;
    private Boolean isActive;




}
