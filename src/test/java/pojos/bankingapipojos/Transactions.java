package pojos.bankingapipojos;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Transactions {
    private Account account;
    private Double amount;
    private String date;
    private Integer transactionId;
    private String transactionName;

}
