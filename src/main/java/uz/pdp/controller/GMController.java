package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Address;
import uz.pdp.entity.GM;
import uz.pdp.playload.GmDto;
import uz.pdp.repository.AddressRepository;
import uz.pdp.repository.GMRepository;

import java.util.List;

@RestController
public class GMController {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    GMRepository gmRepository;

    //READ
    @RequestMapping(value = "/gm", method = RequestMethod.GET)
    public List<GM> getGM() {
        List<GM> gmList = gmRepository.findAll();
        return gmList;
    }

    //Create
    @RequestMapping(value = "/gm", method = RequestMethod.POST)
    public String addGM(@RequestBody GmDto dto) {

         Address address = new Address();
         address.setCity(dto.getCity());
         address.setCountry(dto.getCountry());

         Address saveAddress = addressRepository.save(address);
         GM gm = new GM();
         gm.setCorpName(dto.getCorpName());
         gm.setDirectorName(dto.getDirectorName());
         gm.setAddress(saveAddress);
         gmRepository.save(gm);
        return "GM added";
    }



}
