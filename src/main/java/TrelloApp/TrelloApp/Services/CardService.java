package TrelloApp.TrelloApp.Services;

import TrelloApp.TrelloApp.Repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CardService {
    @Autowired
    CardRepository cardRepository;

}
