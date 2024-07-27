package lk.ijse.gdse.DTO;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class BorrowingTransactionsDTO {
    int transaction_id;
    int book_id;
    int member_id;
    Date borrow_date;
    Date due_date;
    Date return_date;
}
