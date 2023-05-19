package com.pogorzhelskyy.rentme.service;

import com.pogorzhelskyy.rentme.entity.Housing;
import com.pogorzhelskyy.rentme.entity.Photo;
import com.pogorzhelskyy.rentme.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {
    private final PhotoRepo photoRepo;
@Autowired
    public PhotoService(PhotoRepo photoRepo) {
        this.photoRepo = photoRepo;
    }

    public List<Photo> getByHousing (Housing housing){
        return photoRepo.findByHousing(housing);
    }
    public void save (Photo photo){ photoRepo.save(photo);}

    public void delete (Photo photo) {photoRepo.delete(photo);}
}
