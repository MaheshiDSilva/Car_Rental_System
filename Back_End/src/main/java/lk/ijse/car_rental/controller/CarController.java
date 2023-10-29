package lk.ijse.car_rental.controller;

import lk.ijse.car_rental.dto.CarDTO;
import lk.ijse.car_rental.dto.CarPhotoDTO;
import lk.ijse.car_rental.embedded.FreeMileage;
import lk.ijse.car_rental.embedded.PriceRate;
import lk.ijse.car_rental.service.CarService;
import lk.ijse.car_rental.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
@CrossOrigin
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping
    public ResponseUtil saveCar(@ModelAttribute CarPhotoDTO carPhotoDTO, @ModelAttribute PriceRate price, @ModelAttribute FreeMileage freeMileage, @ModelAttribute CarDTO carDTO) {
        carDTO.setCarPhoto(carPhotoDTO);
        carDTO.setPriceRate(price);
        carDTO.setFreeMileage(freeMileage);
        carService.saveCar(carDTO);
        return new ResponseUtil("OK", "Successfully Saved..!", "");
    }

    @GetMapping
    public ResponseUtil getAll() {
        return new ResponseUtil("OK", "Successfully Loaded..!", carService.getAllCars());
    }

    @PutMapping
    public ResponseUtil updateCar(@ModelAttribute CarPhotoDTO carPhotoDTO, @ModelAttribute PriceRate price, @ModelAttribute FreeMileage freeMileage, @ModelAttribute CarDTO carDTO) {
        carDTO.setCarPhoto(carPhotoDTO);
        carDTO.setPriceRate(price);
        carDTO.setFreeMileage(freeMileage);

        carService.updateCar(carDTO);

        return new ResponseUtil("OK", "Successfully Updated..!", "");
    }

    @DeleteMapping
    public ResponseUtil deleteCar(@RequestParam String regNum) {

        carService.deleteCar(regNum);
        return new ResponseUtil("OK", "Successfully Deleted..!", "");

    }

    @GetMapping(path = "/filterByRegNum")
    public ResponseUtil filterByRegNum(@RequestParam String text, @RequestParam String search, @RequestParam String fuel) {

        return new ResponseUtil("OK", "Successfully Deleted..!", carService.filterCarsByRegNum(text, search, fuel));

    }

    @GetMapping(path = "/brand")
    public ResponseUtil countCarsByBrand() {

        return new ResponseUtil("OK", "Successfully Loaded..!", carService.countCarsByBrand());

    }

    @GetMapping(path = "/countMaintain")
    public ResponseUtil countMaintainingCars() {

        return new ResponseUtil("OK", "Successfully Loaded..!", carService.countMaintainingCars());

    }

    @PostMapping(path = "/image")
    public ResponseUtil saveImages(@ModelAttribute CarPhotoDTO carPhotoDTO) {

        carService.saveCarImages(carPhotoDTO);
        return new ResponseUtil("OK", "Successfully Saved..!", "");

    }

    @PutMapping(params = {"regNum"})
    public ResponseUtil addToMaintains(@RequestParam String regNum) {

        carService.addToMaintains(regNum);
        return new ResponseUtil("OK", "Successfully Saved..!", "");

    }

    @GetMapping(params = {"regNum"})
    public ResponseUtil getCar(@RequestParam String regNum) {

        return new ResponseUtil("OK", "Successfully Loaded..!", carService.getCar(regNum));

    }

    @GetMapping(path = "/available")
    public ResponseUtil countAvailableCars() {

        return new ResponseUtil("OK", "Successfully Loaded..!", carService.countAvailableCars());

    }

    @GetMapping(path = "/reserved")
    public ResponseUtil countReservedCars() {

        return new ResponseUtil("OK", "Successfully Loaded..!", carService.countReservedCars());

    }

}
