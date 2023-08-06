package TrelloApp.TrelloApp.RequestObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetCardRequest {
    Long id;

    String title;

    String description;

    int section;
}
