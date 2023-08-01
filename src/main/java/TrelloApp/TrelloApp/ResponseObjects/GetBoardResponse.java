package TrelloApp.TrelloApp.ResponseObjects;

import TrelloApp.TrelloApp.Models.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetBoardResponse {
    Long id;
    String title;
    List<Card> cardList;
}
