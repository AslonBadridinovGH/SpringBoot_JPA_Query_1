package uz.pdp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GM {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private  Integer id;

   private String  corpName;

   private String  directorName;

   @OneToOne
   private Address address;
}
