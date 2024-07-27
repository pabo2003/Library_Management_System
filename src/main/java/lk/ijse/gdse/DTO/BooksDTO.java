package lk.ijse.gdse.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class BooksDTO {
    int book_id;
    String title;
    String edition;
    String language;
    int available_copies;
    String shelf_location;
    int category_id;
}
