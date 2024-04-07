package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Car;
import uz.pdp.repository.CarRepository;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    CarRepository carRepository;

    //READ
    @RequestMapping(value = "/car", method = RequestMethod.GET)
    public List<Car> getCars(){
        List<Car> carList = carRepository.findAll();
        return carList;
    }


     @GetMapping(value = "/car/byAutoShopId/{autoShopId}")
     public List<Car>getCarsByGmId(@PathVariable Integer autoShopId)
     {
         List<Car> allByAuto_shopId = carRepository.getCarsByAutoShopIdNative(autoShopId);
         return allByAuto_shopId;
     }


    @GetMapping(value = "/car/byGmId/{gmId}")
    public List<Car>getAutoShopsByGmId(@PathVariable Integer gmId)
    {
        List<Car> allByGmId = carRepository.getCarsByGmIdNative(gmId);
        return allByGmId;
    }


  // Create
    @RequestMapping(value = "/car", method = RequestMethod.POST)
    public String addCar(@RequestBody Car car) {

        Car car1 =new Car();
        car1.setModel(car.getModel());
        car1.setPrice(car.getPrice());
        car1.setYear(car.getYear());
        carRepository.save(car1);
        return "Car added";
    }



}
