package TrelloApp.TrelloApp.ResponseObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetCardResponse {
    Long id;

    String title;

    String description;

    int section;
}
