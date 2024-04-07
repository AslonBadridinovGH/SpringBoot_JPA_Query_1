package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.entity.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {


   //  A native query, that showing CarData available in each store by autoShopId
    @Query(value = "select * from  car join auto_shop_car_list  " +
                   "on car.id=auto_shop_car_list.car_list_id " +
                   "where auto_shop_id=:autoShopId", nativeQuery = true)
    List<Car> getCarsByAutoShopIdNative(Integer autoShopId);


    // native query, which displays Car data available in each store by gmId

    @Query(value = "select * from  car join auto_shop_car_list  " +
     "on car.id=auto_shop_car_list.car_list_id" +
     " join auto_shop  on auto_shop.id=auto_shop_car_list.auto_shop_id  where auto_shop.gm_id=:gmId", nativeQuery = true)

     List<Car>getCarsByGmIdNative(Integer gmId);




}
