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
public class Members {
        int memberId;
    String name;
    String email;
    String phoneNumber;
    String address;
    Date dateOfBirth;
    Date joinDate;
    String membershipType;
}

/*
member_id INT AUTO_INCREMENT PRIMARY KEY,
        ->     name VARCHAR(100) NOT NULL,
    ->     email VARCHAR(255) UNIQUE NOT NULL,
    ->     phone_number VARCHAR(15),
    ->     address VARCHAR(255),
    ->     date_of_birth DATE,
    ->     join_date DATE NOT NULL,
    ->     membership_type VARCHAR(50)
*/
