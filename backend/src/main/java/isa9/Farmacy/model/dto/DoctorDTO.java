package isa9.Farmacy.model.dto;

import isa9.Farmacy.model.Address;
import isa9.Farmacy.model.UserRole;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor

public class DoctorDTO extends UserDTO {

    private double rating;

    public DoctorDTO(Long id, String name, String surname, Address address, String phoneNumber, UserRole role, String email) {
        super(id, name, surname, address, phoneNumber, role, email);
    }
}
