package TrelloApp.TrelloApp.Services;

import TrelloApp.TrelloApp.Models.Board;
import TrelloApp.TrelloApp.Models.Card;
import TrelloApp.TrelloApp.Repository.BoardRepository;
import TrelloApp.TrelloApp.Repository.CardRepository;
import TrelloApp.TrelloApp.RequestObjects.GetCardRequest;
import TrelloApp.TrelloApp.ResponseObjects.GetBoardResponse;
import TrelloApp.TrelloApp.ResponseObjects.GetCardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private BoardRepository boardRepository;

    public GetCardResponse createCard(Long boardId, GetCardRequest newCard) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            Card card = new Card();
            card.setTitle(newCard.getTitle());
            card.setDescription(newCard.getDescription());
            card.setSection(newCard.getSection());
            card.setCreatedDate(new Date());
            card.setIsActive(true);
            board.getCardList().add(card);
            cardRepository.save(card);
            return getCardById(card.getId());
        } else {
            System.out.println("Board with ID " + boardId + " not found");
        }
        return null;
    }

    public GetCardResponse getCardById(Long cardId) {
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            GetCardResponse cardResponse = new GetCardResponse();
            cardResponse.setId(card.getId());
            cardResponse.setTitle(card.getTitle());
            cardResponse.setDescription(card.getDescription());
            cardResponse.setSection(card.getSection());
            return cardResponse;
        } else {
            System.out.println("Card with ID " + cardId + " not found");
        }
        return null;
    }

    public GetCardResponse updateCard(Long cardId, GetCardRequest updatedCard) {
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            card.setTitle(updatedCard.getTitle());
            card.setDescription(updatedCard.getDescription());
            card.setSection(updatedCard.getSection());
            card.setUpdatedDate(new Date());
            cardRepository.save(card);
            return getCardById(cardId);
        } else {
            System.out.println("Card with ID " + cardId + " not found");
        }
        return null;
    }

    public void deleteCard(Long cardId) {
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if (optionalCard.isPresent()) {
            cardRepository.deleteById(cardId);
        } else {
            System.out.println("Card with ID " + cardId + " not found");
        }
    }



}
