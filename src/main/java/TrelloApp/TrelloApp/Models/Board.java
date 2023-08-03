package TrelloApp.TrelloApp.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@Data
@Entity
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JoinColumn(name = "board", referencedColumnName = "id")
    @OneToMany(cascade = CascadeType.ALL)
    List<Card> cardList;

    String title;

    List<String> columns;



}
