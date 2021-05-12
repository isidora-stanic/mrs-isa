package isa9.Farmacy.service.impl.base;

import isa9.Farmacy.model.Medicine;
import isa9.Farmacy.model.MedicineInPharmacy;
import isa9.Farmacy.model.Pharmacy;
import isa9.Farmacy.model.dto.PharmacySearchDTO;
import isa9.Farmacy.service.MedicineService;
import isa9.Farmacy.service.PharmacyService;
import isa9.Farmacy.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class PharmacyServiceBase implements PharmacyService {
    protected RatingService ratingService;

    @Autowired
    public void setRatingService(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @Override
    public boolean pharmacyExists(Pharmacy p) {
        for(Pharmacy ph : this.findAll()){
            if(ph.getName().equals(p.getName()) && ph.getAddress().equals(p.getAddress())) return true;
        };

        return false;
    }

    @Override
    public int reduceQuantity(Pharmacy pharmacy, Medicine medicine, int resQuantity) {
        MedicineInPharmacy mip = pharmacy.getMedicines().stream().filter(m -> m.getMedicine().equals(medicine)).findFirst().get();
        int quantity = mip.getInStock();
        if(quantity == 0) return 1;                 // nema ga na stanju
        if (quantity < resQuantity) return -1;      // manje ga je na stanju nego sto je poruceno

        quantity -= resQuantity;
        mip.setInStock(quantity);

        save(pharmacy);

        return 0;                                   // sve je okej
    }

    @Override
    public MedicineInPharmacy gedMedicineInPharmacy(Pharmacy pharmacy, Medicine medicine) {
        //TODO: check if null!!
        return pharmacy.getMedicines().stream().filter(x -> x.getMedicine().getId().equals(medicine.getId())).findFirst().get();
    }

    @Override
    public Pharmacy updatePharmacyRating(Pharmacy pharmacy) {
        double rating = ratingService.getPharmacyAverage(pharmacy);
        pharmacy.setRating(rating);
        return save(pharmacy);
    }

    @Override
    public List<Pharmacy> filterPharmacies(List<Pharmacy> pharmacies, PharmacySearchDTO pharmacySearchDTO) {
        if (pharmacySearchDTO == null) return pharmacies;
        final Map<String, Comparator<Pharmacy>> critMap = new HashMap<String, Comparator<Pharmacy>>();
        critMap.put("NAME_ASC", (o1,o2)->{return o1.getName().trim().compareToIgnoreCase(o2.getName().trim());});
        critMap.put("NAME_DES", (o2,o1)->{return o1.getName().trim().compareToIgnoreCase(o2.getName().trim());});
        critMap.put("ADDRESS_ASC", (o1,o2)->{return o1.getAddress().getStreet().trim().compareToIgnoreCase(o2.getAddress().getStreet().trim());});
        critMap.put("ADDRESS_DES", (o2,o1)->{return o1.getAddress().getStreet().trim().compareToIgnoreCase(o2.getAddress().getStreet().trim());});
        critMap.put("RATING_ASC", Comparator.comparingDouble(Pharmacy::getRating));
        critMap.put("RATING_DES", Comparator.comparingDouble(Pharmacy::getRating).reversed());
        Comparator<Pharmacy> comp = critMap.getOrDefault(pharmacySearchDTO.getSort().toUpperCase().trim(), critMap.values().iterator().next());

        return pharmacies.stream()
                .filter(p -> pharmacySearchDTO.getName().isEmpty() || p.getName().toLowerCase().contains(pharmacySearchDTO.getName().toLowerCase()))
                .filter(p -> pharmacySearchDTO.getAddressString().isEmpty() || (p.getAddress().getCity() + p.getAddress().getState() + p.getAddress().getStreet()).toLowerCase().contains(pharmacySearchDTO.getAddressString()))
                .filter(p -> p.getRating() >= pharmacySearchDTO.getMinRating() && p.getRating() <= pharmacySearchDTO.getMaxRating())
                .sorted(comp).collect(Collectors.toList());
    }
}