package lk.ijse.gdse.Entity;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class BorrowingTransactions {
    int transaction_id;
    int book_id;
    int member_id;
    Date borrow_date;
    Date due_date;
    Date return_date;
}
