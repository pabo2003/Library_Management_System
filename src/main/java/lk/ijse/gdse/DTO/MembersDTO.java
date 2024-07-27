package lk.ijse.gdse.DTO;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class MembersDTO {
    int member_id;
    String name;
    String email;
    String phone_number;
    String address;
    Date date_of_birth;
    Date join_date;
    String membership_type;
}
