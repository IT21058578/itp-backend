package com.web.backend.controllers.ServiceCreation;


import com.web.backend.model.ServiceCreation.*;
import com.web.backend.repositories.ServiceCreation.*;
import com.web.backend.services.ServiceCreation.CategorizedServicesSequenceGenerator;
import com.web.backend.services.ServiceCreation.CategorySequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CategoriesController {

    private final CategoryRepository CateRepo;
    private final HomeCleaningRepo homeRepo;
    private final ApartmentCleaningRepo apartmentRepo;
    private final LaundryServiceRepo laundryRepo;
    private final LearningCentersCleaningRepo learningRepo;
    private final OfficeCleaningRepo officeRepo;
    private final RestaurantCleaningRepo restaurantRepo;
    private final SportCenterCleaningRepo sportRepo;



    @Autowired
    public CategoriesController(CategoryRepository CateRepo, HomeCleaningRepo homeRepo, ApartmentCleaningRepo apartmentRepo, LaundryServiceRepo laundryRepo, LearningCentersCleaningRepo learningRepo, OfficeCleaningRepo officeRepo, RestaurantCleaningRepo restaurantRepo, SportCenterCleaningRepo sportRepo) {
        this.CateRepo = CateRepo;
        this.homeRepo = homeRepo;
        this.apartmentRepo = apartmentRepo;
        this.laundryRepo = laundryRepo;
        this.learningRepo = learningRepo;
        this.officeRepo = officeRepo;
        this.restaurantRepo = restaurantRepo;
        this.sportRepo = sportRepo;
    }


    @Autowired
    private CategorySequenceGenerator sq;
    @Autowired
    private CategorizedServicesSequenceGenerator serviceSq;

    //---------------Categories-----------------
    @PostMapping("/saveCategory")
    public String saveCate (@RequestBody CategoryModel cm){
        cm.setId(sq.getSequenceNumber(CategoryModel.SEQUENCE_NAME));
        CateRepo.save(cm);
        return "Added Category with Id : "+cm.getId();
    }

    @GetMapping("/findAllCategoriesNew")
    public List<CategoryModel> getAllCate(){
        return CateRepo.findAll();
    }

    @GetMapping("/findCategoryNew/{Id}")
    public Optional<CategoryModel> getCate(@PathVariable Long Id){
        return CateRepo.findById(Id);
    }

    @DeleteMapping("/deleteCategoryNew/{Id}")
    public String deleteCategoryNew(@PathVariable Long Id){
        CateRepo.deleteById(Id);
        return "Home Apartment Cleaning was deleted by id : "+ Id;
    }


    //------------------home cleaning--------------------
    @PostMapping("/Home")
    public String saveHc (@RequestBody HomeCleaningModel Hc){
        Hc.setId(serviceSq.getSequenceNumber(HomeCleaningModel.SEQUENCE_NAME));
        homeRepo.save(Hc);
        return "Added home cleaning service with Id : "+Hc.getId();
    }

    @GetMapping("/findAllHomeC")
    public List<HomeCleaningModel> getAllHc(){
        return homeRepo.findAll();
    }

    @GetMapping("/findHomeC/{Id}")
    public Optional<HomeCleaningModel> getHc(@PathVariable Long Id){
        return homeRepo.findById(Id);
    }

    @DeleteMapping("/deleteHomeC/{Id}")
    public String deleteHc(@PathVariable Long Id){
        homeRepo.deleteById(Id);
        return "Home cleaning service was deleted by id : "+ Id;
    }

    @PutMapping("/updateHomeC/{id}")
    public HomeCleaningModel updateHc(@RequestBody HomeCleaningModel Hc, @PathVariable Long id) {

        return homeRepo.findById(id)
                .map(updateHc -> {
                    updateHc.setName(Hc.getName());
                    updateHc.setCardDescription(Hc.getCardDescription());
                    updateHc.setDescription(Hc.getDescription());
                    updateHc.setImage(Hc.getImage());
                    return homeRepo.save(updateHc);
                })
                .orElseGet(() -> {
                    Hc.setId(id);
                    return homeRepo.save(Hc);
                });
    }

    //------------------apartment cleaning--------------------
    @PostMapping("/Apartment")
    public String saveApartmentC (@RequestBody ApartmentCleaningModel Ac){
        Ac.setId(serviceSq.getSequenceNumber(ApartmentCleaningModel.SEQUENCE_NAME));
        apartmentRepo.save(Ac);
        return "Added Apartment Cleaning service with Id : "+Ac.getId();
    }

    @GetMapping("/findAllApartmentC")
    public List<ApartmentCleaningModel> getAllApartmentC(){
        return apartmentRepo.findAll();
    }

    @GetMapping("/findApartmentC/{Id}")
    public Optional<ApartmentCleaningModel> getApartmentC(@PathVariable Long Id){
        return apartmentRepo.findById(Id);
    }

    @DeleteMapping("/deleteApartmentC/{Id}")
    public String deleteApartmentC(@PathVariable Long Id){
        apartmentRepo.deleteById(Id);
        return "Apartment Cleaning was deleted by id : "+ Id;
    }

    @PutMapping("/updateApartmentC/{id}")
    public ApartmentCleaningModel updateApartmentC(@RequestBody ApartmentCleaningModel Ac, @PathVariable Long id) {

        return apartmentRepo.findById(id)
                .map(updateAc -> {
                    updateAc.setName(Ac.getName());
                    updateAc.setCardDescription(Ac.getCardDescription());
                    updateAc.setDescription(Ac.getDescription());
                    updateAc.setImage(Ac.getImage());
                    return apartmentRepo.save(updateAc);
                })
                .orElseGet(() -> {
                    Ac.setId(id);
                    return apartmentRepo.save(Ac);
                });
    }
    //------------------laundry service--------------------
    @PostMapping("/Laundry")
    public String saveLaundryC (@RequestBody LaundryServiceModel Lc){
        Lc.setId(serviceSq.getSequenceNumber(LaundryServiceModel.SEQUENCE_NAME));
        laundryRepo.save(Lc);
        return "Added Laundry service with Id : "+Lc.getId();
    }

    @GetMapping("/findAllLaundryC")
    public List<LaundryServiceModel> getAllLaundryC(){
        return laundryRepo.findAll();
    }

    @GetMapping("/findLaundryC/{Id}")
    public Optional<LaundryServiceModel> getLaundryC(@PathVariable Long Id){
        return laundryRepo.findById(Id);
    }

    @DeleteMapping("/deleteLaundryC/{Id}")
    public String deleteLaundryC(@PathVariable Long Id){
        laundryRepo.deleteById(Id);
        return "Laundry service was deleted by id : "+ Id;
    }

    @PutMapping("/updateLandryC/{id}")
    public LaundryServiceModel updateLaundryC(@RequestBody LaundryServiceModel Lc, @PathVariable Long id) {

        return laundryRepo.findById(id)
                .map(updateLc -> {
                    updateLc.setName(Lc.getName());
                    //updateLc.setCardDescription(Lc.getCardDescription());
                    updateLc.setImage(Lc.getImage());
                    updateLc.setDescription(Lc.getDescription());
                    return laundryRepo.save(updateLc);
                })
                .orElseGet(() -> {
                    Lc.setId(id);
                    return laundryRepo.save(Lc);
                });
    }
    //------------------learning centers--------------------
    @PostMapping("/learningCenters")
    public String saveLearningCentersC (@RequestBody LearningCentersCleaningModel LCc){
        LCc.setId(serviceSq.getSequenceNumber(LearningCentersCleaningModel.SEQUENCE_NAME));
        learningRepo.save(LCc);
        return "Added Laundry service with Id : "+LCc.getId();
    }

    @GetMapping("/findAllLearningCentersC")
    public List<LearningCentersCleaningModel> getAllLearningCentersC(){
        return learningRepo.findAll();
    }

    @GetMapping("/findLearningCentersC/{Id}")
    public Optional<LearningCentersCleaningModel> getLearningCentersC(@PathVariable Long Id){
        return learningRepo.findById(Id);
    }

    @DeleteMapping("/deleteLearningCentersC/{Id}")
    public String deleteLearningCentersC(@PathVariable Long Id){
        learningRepo.deleteById(Id);
        return "learning Centers service was deleted by id : "+ Id;
    }

    @PutMapping("/updateLearningCentersC/{id}")
    public LearningCentersCleaningModel updateLearningCentersC(@RequestBody LearningCentersCleaningModel LCc,
                                                      @PathVariable Long id) {

        return learningRepo.findById(id)
                .map(updateLCc -> {
                    updateLCc.setName(LCc.getName());
                    updateLCc.setCardDescription(LCc.getCardDescription());
                    updateLCc.setImage(LCc.getImage());
                    updateLCc.setDescription(LCc.getDescription());
                    return learningRepo.save(updateLCc);
                })
                .orElseGet(() -> {
                    LCc.setId(id);
                    return learningRepo.save(LCc);
                });
    }
    //------------------office cleaning--------------------
    @PostMapping("/office")
    public String saveOfficeC (@RequestBody OfficeCleaningModel Oc){
        Oc.setId(serviceSq.getSequenceNumber(OfficeCleaningModel.SEQUENCE_NAME));
        officeRepo.save(Oc);
        return "Added Laundry service with Id : "+Oc.getId();
    }

    @GetMapping("/findAllOfficeC")
    public List<OfficeCleaningModel> getAllOfficeC(){
        return officeRepo.findAll();
    }

    @GetMapping("/findOfficeC/{Id}")
    public Optional<OfficeCleaningModel> getOfficeC(@PathVariable Long Id){
        return officeRepo.findById(Id);
    }

    @DeleteMapping("/deleteOfficeC/{Id}")
    public String deleteOfficeC(@PathVariable Long Id){
        officeRepo.deleteById(Id);
        return "learning Centers service was deleted by id : "+ Id;
    }

    @PutMapping("/updateOfficeC/{id}")
    public OfficeCleaningModel updateOfficeC(@RequestBody OfficeCleaningModel Oc,
                                                               @PathVariable Long id) {

        return officeRepo.findById(id)
                .map(updateOc -> {
                    updateOc.setName(Oc.getName());
                    updateOc.setCardDescription(Oc.getCardDescription());
                    updateOc.setImage(Oc.getImage());
                    updateOc.setDescription(Oc.getDescription());
                    return officeRepo.save(updateOc);
                })
                .orElseGet(() -> {
                    Oc.setId(id);
                    return officeRepo.save(Oc);
                });
    }
    //------------------restaurant cleaning--------------------
    @PostMapping("/restaurant")
    public String saveRestaurantC (@RequestBody RestaurantCleaningModel Rc){
        Rc.setId(serviceSq.getSequenceNumber(RestaurantCleaningModel.SEQUENCE_NAME));
        restaurantRepo.save(Rc);
        return "Added Laundry service with Id : "+Rc.getId();
    }

    @GetMapping("/findAllRestaurantC")
    public List<RestaurantCleaningModel> getAllRestaurantC(){
        return restaurantRepo.findAll();
    }

    @GetMapping("/findRestaurantC/{Id}")
    public Optional<RestaurantCleaningModel> getRestaurantC(@PathVariable Long Id){
        return restaurantRepo.findById(Id);
    }

    @DeleteMapping("/deleteRestaurantC/{Id}")
    public String deleteRestaurantC(@PathVariable Long Id){
        restaurantRepo.deleteById(Id);
        return "learning Centers service was deleted by id : "+ Id;
    }

    @PutMapping("/updateRestaurantC/{id}")
    public RestaurantCleaningModel updateRestaurantC(@RequestBody RestaurantCleaningModel Rc,
                                             @PathVariable Long id) {

        return restaurantRepo.findById(id)
                .map(updateRc -> {
                    updateRc.setName(Rc.getName());
                    updateRc.setCardDescription(Rc.getCardDescription());
                    updateRc.setImage(Rc.getImage());
                    updateRc.setDescription(Rc.getDescription());
                    return restaurantRepo.save(updateRc);
                })
                .orElseGet(() -> {
                    Rc.setId(id);
                    return restaurantRepo.save(Rc);
                });
    }
    //------------------Sport centers--------------------
    @PostMapping("/sport")
    public String saveSportC (@RequestBody SportCentersCleaningModel Sc){
        Sc.setId(serviceSq.getSequenceNumber(SportCentersCleaningModel.SEQUENCE_NAME));
        sportRepo.save(Sc);
        return "Added Laundry service with Id : "+Sc.getId();
    }

    @GetMapping("/findAllSportC")
    public List<SportCentersCleaningModel> getAllSportC(){
        return sportRepo.findAll();
    }

    @GetMapping("/findSportC/{Id}")
    public Optional<SportCentersCleaningModel> getSportC(@PathVariable Long Id){
        return sportRepo.findById(Id);
    }

    @DeleteMapping("/deleteSportC/{Id}")
    public String deleteSportC(@PathVariable Long Id){
        sportRepo.deleteById(Id);
        return "learning Centers service was deleted by id : "+ Id;
    }

    @PutMapping("/updateSportC/{id}")
    public SportCentersCleaningModel updateSportC(@RequestBody SportCentersCleaningModel Sc,
                                                     @PathVariable Long id) {

        return sportRepo.findById(id)
                .map(updateSc -> {
                    updateSc.setName(Sc.getName());
                    updateSc.setCardDescription(Sc.getCardDescription());
                    updateSc.setImage(Sc.getImage());
                    updateSc.setDescription(Sc.getDescription());
                    return sportRepo.save(updateSc);
                })
                .orElseGet(() -> {
                    Sc.setId(id);
                    return sportRepo.save(Sc);
                });
    }
}
