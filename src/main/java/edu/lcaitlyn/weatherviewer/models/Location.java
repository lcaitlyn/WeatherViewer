package edu.lcaitlyn.weatherviewer.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "locations", schema = "weatherviewer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Location {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String city;
    @NonNull
    private String country;
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @NonNull
    private User user;
    @NonNull
    private BigDecimal latitude;
    @NonNull
    private BigDecimal longitude;
}
