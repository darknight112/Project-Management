package TrelloApp.TrelloApp.Controller;

import TrelloApp.TrelloApp.Models.Board;
import TrelloApp.TrelloApp.Models.Card;
import TrelloApp.TrelloApp.RequestObjects.GetBoardRequest;
import TrelloApp.TrelloApp.RequestObjects.GetCardRequest;
import TrelloApp.TrelloApp.ResponseObjects.GetCardResponse;
import TrelloApp.TrelloApp.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/boards/{boardId}/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<GetCardResponse> createCard(@PathVariable Long boardId, @RequestBody GetCardRequest newCard) {
        GetCardResponse response = cardService.createCard(boardId, newCard);
        if (response != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{cardId}")
    public GetCardResponse getCardById(@PathVariable Long boardId, @PathVariable Long cardId) {
        return cardService.getCardById(cardId);
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<GetCardResponse> updateCard(@PathVariable Long boardId,
                                                      @PathVariable Long cardId,
                                                      @RequestBody GetCardRequest updatedCard) {
        GetCardResponse response = cardService.updateCard(cardId, updatedCard);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<String> deleteCard (@PathVariable Long boardId, @PathVariable Long cardId) {
        cardService.deleteCard(cardId);
        String message = "Card with ID " + cardId + " has been deleted successfully from board " + boardId;
        return ResponseEntity.ok(message);
    }


}










//public class CardController {
//    @Autowired
//    private CardService cardService;
//
//    @PostMapping
//    public ResponseEntity<GetCardResponse> createCard(@PathVariable Long boardId, @RequestBody GetCardRequest newCard) {
//        GetCardResponse response = cardService.saveCard(newCard);
//        if (response != null) {
//            return ResponseEntity.status(HttpStatus.CREATED).body(response);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PostMapping //create board
//    public ResponseEntity<GetCardResponse> createCard (@PathVariable Long boardId, @RequestBody GetCardRequest cardRequest) {
//        saveCard(cardRequest);
//        if (response != null) {
//            return ResponseEntity.status(HttpStatus.CREATED).body(response);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping("/{cardId}")
//    public GetCardResponse getCardById(@PathVariable Long boardId, @PathVariable Long cardId) {
//        return cardService.getCardById(cardId);
//    }
//
//    @PutMapping("/{cardId}")
//    public ResponseEntity<GetCardResponse> updateCard(@PathVariable Long boardId,
//                                                      @PathVariable Long cardId,
//                                                      @RequestBody GetCardRequest updatedCard) {
//        GetCardResponse response = cardService.updateCard(cardId, updatedCard);
//        if (response != null) {
//            return ResponseEntity.ok(response);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{cardId}")
//    public ResponseEntity<Void> deleteCard(@PathVariable Long boardId, @PathVariable Long cardId) {
//        cardService.deleteCard(cardId);
//        return ResponseEntity.noContent().build();
//    }
//
//
//    public void saveCard(GetCardRequest cardRequest) {
//        Card card = new Card();
//        card.setTitle(cardRequest.getTitle());
//        card.setSection(card.getSection());
//        card.setDescription(card.getDescription());
//        card.setUpdatedDate(new Date());
//        card.setIsActive(true);
//        cardService.saveCard(card);
//    }
//}

