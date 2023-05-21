package com.pogorzhelskyy.rentme.service;

import com.pogorzhelskyy.rentme.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DevInitializer {
    private final UserService userService;
    private final HousingService housingService;
    private final PhotoService photoService;
    private final BookingService bookingService;
@Autowired
    public DevInitializer(UserService userService, HousingService housingService, PhotoService photoService, BookingService bookingService) {
        this.userService = userService;
        this.housingService = housingService;
        this.photoService = photoService;
    this.bookingService = bookingService;
}

    @PostConstruct
    public void init()  {
        System.out.println("Initializing the system...");
    // default users
        User user = new User();
        user.setUsername("user");
        user.setPassword("111");
        user.setPhone("+38 050 222 22 22");
        user.setEmail("2222@gmail.com");
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setRoles(roles);
        userService.addUser(user);
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("333");
        Set<Role> roles2 = new HashSet<>();
        roles2.add(Role.ADMIN);
        admin.setRoles(roles2);
        userService.addUser(admin);
    //default housings
       Housing housing1 = new Housing();
        housing1.setDescription("The apartment is in a new house. New repair, has all the necessary household appliances. There are two metro stations, of different branches, nearby. You can walk to the zoo, circus, shopping centers, banks, markets. The apartment is located on the 24th floor, with a great view from the window.");
        housing1.setCity("Kyiv");
        housing1.setAddress("вулиця Лаврська, 4, Київ, Украина, 02000");
        housing1.setRooms(1);
        housing1.setPrice(40);
        housing1.setSquare(65);
       /* List<Photo> photos1 = new ArrayList<>();
        photos1.add(new Photo("https://a0.muscache.com/im/pictures/6f88f977-b8ec-465b-aa0c-8b6ede936083.jpg"));
        photos1.add(new Photo("https://a0.muscache.com/im/pictures/7142c6b0-1e2d-41d6-a0c8-3e1f50d1ef55.jpg"));
        photos1.add(new Photo("https://a0.muscache.com/im/pictures/0fcebb1a-e1fc-4d1e-b780-c3954cf9f219.jpg"));
        housing1.setPhotos(photos1);*/
        housingService.save(housing1);
        Photo photo = new Photo("https://a0.muscache.com/im/pictures/6f88f977-b8ec-465b-aa0c-8b6ede936083.jpg", housing1);
        photoService.save(photo);
        photo = new Photo("https://a0.muscache.com/im/pictures/7142c6b0-1e2d-41d6-a0c8-3e1f50d1ef55.jpg", housing1);
        photoService.save(photo);
        photo = new Photo("https://a0.muscache.com/im/pictures/0fcebb1a-e1fc-4d1e-b780-c3954cf9f219.jpg", housing1);
        photoService.save(photo);
       /* Booking booking = new Booking();
        bookingService.save(booking);*/
      //  housingService.deleteById(housing1.getId());
    }
}
