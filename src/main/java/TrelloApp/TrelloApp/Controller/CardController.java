package TrelloApp.TrelloApp.Controller;

import TrelloApp.TrelloApp.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {
    @Autowired
    CardService cardService;

}
