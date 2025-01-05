package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Bloc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBloc;
    private String nomBloc;
    private String capaciteBloc;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bloc")
    private Set<Chambre> chambres;

    @ManyToOne
    private  Foyer foyers;

    public Long getIdBloc() {
        return idBloc;
    }

    public String getNomBloc() {
        return nomBloc;
    }

    public String getCapaciteBloc() {
        return capaciteBloc;
    }

    public Set<Chambre> getChambres() {
        return chambres;
    }

    public Foyer getFoyers() {
        return foyers;
    }

    public void setIdBloc(Long idBloc) {
        this.idBloc = idBloc;
    }

    public void setNomBloc(String nomBloc) {
        this.nomBloc = nomBloc;
    }

    public void setCapaciteBloc(String capaciteBloc) {
        this.capaciteBloc = capaciteBloc;
    }

    public void setChambres(Set<Chambre> chambres) {
        this.chambres = chambres;
    }

    public void setFoyers(Foyer foyers) {
        this.foyers = foyers;
    }
}
