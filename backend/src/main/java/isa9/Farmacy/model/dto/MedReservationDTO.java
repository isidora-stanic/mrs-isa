package isa9.Farmacy.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import isa9.Farmacy.model.Medicine;
import isa9.Farmacy.model.MedicineInPharmacy;
import isa9.Farmacy.model.Pharmacist;
import isa9.Farmacy.model.Pharmacy;

import java.time.LocalDate;
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
    private Long id;
    private String code;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate reservationDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate lastDate;
    private int status; // 0 = pending; 1 = taken; 2 = canceled; 3 = expired;

    private MedInPharmaDTO medicineInPharmacy;
    private int quantity;

    private PharmacistDTO issued;
}
