package paymentgateway.usermanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import paymentgateway.usermanager.appUser.AppUser;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Marchand extends AppUser implements Serializable {

    @Column(nullable = false,updatable = false)
    private String marchandCode;
}
