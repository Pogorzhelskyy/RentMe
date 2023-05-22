package com.pogorzhelskyy.rentme.service;

import com.pogorzhelskyy.rentme.entity.DateFrame;
import com.pogorzhelskyy.rentme.entity.Housing;
import com.pogorzhelskyy.rentme.repo.HousingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HousingService {
    private final HousingRepo housingRepo;
  //  private final BookingService bookingService;
@Autowired
    public HousingService(HousingRepo housingRepo) {
        this.housingRepo = housingRepo;
 //   this.bookingService = bookingService;
}
    public List <Housing> getAll(){
    return housingRepo.findAll();
    }
    public Housing getById (Long id) {
        return housingRepo.getById(id);
    }
    public  List <Housing> getByCity (String city){return housingRepo.findHousingByCity(city);}
    public void save (Housing housing){
    housingRepo.save(housing);
    }
    public void deleteById (Long id){
    housingRepo.deleteById(id);}
 /*   public List<Housing> findAvailable(String city, Date from, Date until){
    List <Housing> housingsAvailable = new ArrayList<>();
    List <Housing> housingsByCity = getByCity(city);
    DateFrame dateFrame = new DateFrame(from, until);
        for (Housing h: housingsByCity) {
            if (bookingService.isAvailable(h, dateFrame)) housingsAvailable.add(h);
        }
    return housingsAvailable;
    }*/

}
