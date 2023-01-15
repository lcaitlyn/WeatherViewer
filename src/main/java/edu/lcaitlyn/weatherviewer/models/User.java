package edu.lcaitlyn.weatherviewer.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "weatherviewer")
@Data
@RequiredArgsConstructor
@AllArgsConstructor @NoArgsConstructor
public class User {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String email;
    @NonNull
    private String password;
}
