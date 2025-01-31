package isa9.Farmacy.service;

import isa9.Farmacy.model.*;
import isa9.Farmacy.model.dto.EPrescriptionDTO;
import isa9.Farmacy.model.dto.PharmacySearchDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PharmacyService extends GenericService<Pharmacy> {

    boolean pharmacyExists(Pharmacy p);
    int reduceQuantity(Pharmacy pharmacy, Medicine medicine, int resQuantity);

    Pharmacy findOneLocked(Long id);

    MedicineInPharmacy gedMedicineInPharmacy(Pharmacy pharmacy, Medicine medicine);

    List<Work> findDoctorsWork(Doctor doctor);

    Pharmacy updatePharmacyRating(Pharmacy pharmacy);

    List<Pharmacy> filterPharmacies(List<Pharmacy> pharmacies, PharmacySearchDTO pharmacySearchDTO);

    void checkMedicineInPharmacy(Pharmacy pharmacy, List<MedicineQuantity> medicines);

    void sendActionMail(MedPrice actionPromotion, Boolean delete);

    List<Pharmacy> getVisitedPharmacies(Patient patient);

    boolean subscribeToPharmacy(Pharmacy pharmacy, Long patientId);
    boolean unsubscribeToPharmacy(Pharmacy pharmacy, Long patientId);
    boolean isPatientSubscribed(Pharmacy pharmacy, Long patientId);

    List<Pharmacy> findEligibleForEPrescription(EPrescriptionDTO ePrescriptionDTO);
    boolean pharmacyHasEnoughMedicine(String code, int quantity, Pharmacy pharmacy); //has enough medicine for ePrescription
    Map<Long, Double> calculateTotalsInPharmacies(List<Pharmacy> pharmacies, EPrescriptionDTO ePrescriptionDTO);
    Double calculateTotalInPharmacy(Pharmacy pharmacy, EPrescriptionDTO ePrescriptionDTO);
}
