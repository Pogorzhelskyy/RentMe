package com.pogorzhelskyy.rentme.service;

import com.pogorzhelskyy.rentme.entity.Housing;
import com.pogorzhelskyy.rentme.entity.Review;
import com.pogorzhelskyy.rentme.repo.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepo reviewRepo;

    @Autowired
    public ReviewService(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    public List<Review> getByHousing(Housing housing) {
        return reviewRepo.findByHousing(housing);
    }

    public void save(Review review) {
        reviewRepo.save(review);
    }

    public void delete(Review review) {
        reviewRepo.delete(review);
    }
}
