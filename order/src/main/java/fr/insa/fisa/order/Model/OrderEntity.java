package fr.insa.fisa.order.Model;

import com.ctc.wstx.shaded.msv_core.datatype.xsd.datetime.IDateTimeValueType;
import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "\"order\"")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idClient;

    private int idProduit;
    private String name;
    private String description;
    private int quantite;
    private float montant;

    private ZonedDateTime date;
    private String status;
    private String total;
}
