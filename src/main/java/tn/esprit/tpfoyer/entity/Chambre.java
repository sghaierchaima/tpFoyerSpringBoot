package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Chambre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idchambre;
    private Long numeroChambre;
    @Enumerated(EnumType.STRING)
    private TypeChambre tupeC;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Reservation> reservations;

    @ManyToOne
    private Bloc bloc;

    public Long getIdchambre() {
        return idchambre;
    }

    public Long getNumeroChambre() {
        return numeroChambre;
    }

    public TypeChambre getTupeC() {
        return tupeC;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public Bloc getBloc() {
        return bloc;
    }

    public void setIdchambre(Long idchambre) {
        this.idchambre = idchambre;
    }

    public void setNumeroChambre(Long numeroChambre) {
        this.numeroChambre = numeroChambre;
    }

    public void setTupeC(TypeChambre tupeC) {
        this.tupeC = tupeC;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void setBloc(Bloc bloc) {
        this.bloc = bloc;
    }
}
