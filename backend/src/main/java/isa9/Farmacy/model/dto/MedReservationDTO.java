package isa9.Farmacy.model.dto;

import isa9.Farmacy.model.Medicine;
import isa9.Farmacy.model.Pharmacist;
import isa9.Farmacy.model.Pharmacy;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MedReservationDTO {
    private String id;
    private Date reservationDate;
    private Date lastDate;
    private boolean taken;

    private String medicine;
    private int quantity;

    private PharmacistDTO issued;
    private PharmacyDTO pharmacy;




}
