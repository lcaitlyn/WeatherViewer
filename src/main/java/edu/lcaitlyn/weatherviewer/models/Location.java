package edu.lcaitlyn.weatherviewer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "locations", schema = "weatherviewer")
@Data
@NoArgsConstructor  @AllArgsConstructor
public class Location {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long userId;

    private BigDecimal latitude;

    private BigDecimal longitude;
}
