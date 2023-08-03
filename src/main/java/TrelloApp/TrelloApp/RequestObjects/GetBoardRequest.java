package TrelloApp.TrelloApp.RequestObjects;

import TrelloApp.TrelloApp.Models.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetBoardRequest {
    Long id;
    String title;
    List<Card> cardList;



}
