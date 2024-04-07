package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.AutoShop;

import java.util.List;

public interface AutoShopRepository extends JpaRepository<AutoShop,Integer> {

     boolean existsByCarListId(Integer car_list_id);


    // AutoShops by gmId
    List<AutoShop>findAllByGmId(Integer gm_id);


}
