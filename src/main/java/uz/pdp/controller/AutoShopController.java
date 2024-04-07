package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Address;
import uz.pdp.entity.AutoShop;
import uz.pdp.entity.GM;
import uz.pdp.entity.Car;
import uz.pdp.playload.AutoShopDto;
import uz.pdp.repository.AddressRepository;
import uz.pdp.repository.AutoShopRepository;
import uz.pdp.repository.CarRepository;
import uz.pdp.repository.GMRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AutoShopController {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    GMRepository gmRepository;

    @Autowired
    AutoShopRepository autoShopRepository;

    @Autowired
    CarRepository carRepository;

    //READ
    @RequestMapping(value = "/autoShop", method = RequestMethod.GET)
    public List<AutoShop> getGM() {
        List<AutoShop> autoShops = autoShopRepository.findAll();
        return autoShops;
    }

    @GetMapping(value = "/autoShop/byGmId/{gmId}")
    public List<AutoShop>getAutoShopsByGmId(@PathVariable Integer gmId)
    {
        List<AutoShop> allByGmId = autoShopRepository.findAllByGmId(gmId);
          return allByGmId;
    }


    //Create
    @RequestMapping(value = "/autoShop", method = RequestMethod.POST)
    public String addGM(@RequestBody AutoShopDto dto) {


        Optional<GM> optionalGM = gmRepository.findById(dto.getGmId());
        if (!optionalGM.isPresent()) {
            return "GM not found";
        } else {
            AutoShop autoShop = new AutoShop();
            autoShop.setGm(optionalGM.get());
            autoShop.setName(dto.getName());

             Address address = new Address();
            address.setCity(dto.getCity());
            address.setCountry(dto.getCountry());
            Address saveAddress = addressRepository.save(address);
            autoShop.setAddress(saveAddress);


            List<Car>carList=new ArrayList<>();

            for (Integer carId : dto.getCarIds()) {
                boolean exists = autoShopRepository.existsByCarListId(carId);
                if (exists)
                return carId+" CarId already used";
                Optional<Car> byId = carRepository.findById(carId);
                if (byId.isPresent()) {
                    carList.add(byId.get());
                }else {
                    return carId+" CarId not found";
                }            }
            autoShop.setCarList(carList);
            autoShopRepository.save(autoShop);
            return "autoShop added";
        }
}
}
