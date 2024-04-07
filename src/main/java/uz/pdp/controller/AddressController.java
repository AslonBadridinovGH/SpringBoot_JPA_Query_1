
package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Address;
import uz.pdp.playload.AddressDto;
import uz.pdp.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    AddressRepository addressRepository;


    @PostMapping
    public String addAddress(@RequestBody AddressDto dto){

        Address address=new Address();

      address.setCountry(dto.getCountry());
      address.setCity(dto.getCity());

        addressRepository.save(address);
        return "Address added";
    }

    @GetMapping
    public List<Address> getAddress(){
        List<Address> addresses = addressRepository.findAll();
        return addresses;
    }

    @PutMapping(value = "/{id}")
    public String editAddress(@PathVariable Integer id, @RequestBody AddressDto dto){

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address currentAddress = optionalAddress.get();

            currentAddress.setCity(dto.getCity());
            currentAddress.setCountry(dto.getCountry());

              addressRepository.save(currentAddress);
              return "Address saved";
            }
        return "Address not found";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteAddress(@PathVariable Integer id){
        addressRepository.deleteById(id);
        return "Address deleted";
    }

}

