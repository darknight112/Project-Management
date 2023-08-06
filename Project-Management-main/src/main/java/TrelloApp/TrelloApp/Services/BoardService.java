package TrelloApp.TrelloApp.Services;

import TrelloApp.TrelloApp.Models.Board;
import TrelloApp.TrelloApp.Models.Card;
import TrelloApp.TrelloApp.Repository.BoardRepository;
import TrelloApp.TrelloApp.Repository.CardRepository;
import TrelloApp.TrelloApp.RequestObjects.GetBoardRequest;
import TrelloApp.TrelloApp.RequestObjects.GetCardRequest;
import TrelloApp.TrelloApp.ResponseObjects.GetBoardResponse;
import TrelloApp.TrelloApp.ResponseObjects.GetCardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class BoardService {
    @Autowired
    BoardRepository boardRepository;


    public List<Board> getBoard(){
        return boardRepository.findAll();
    } //get all

    public void saveBoard(Board board){
        boardRepository.save(board);
    } //create board


    public GetBoardResponse updateBoard(Long boardId, GetBoardRequest updatedBoard) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            board.setTitle(updatedBoard.getTitle());
            board.setUpdatedDate(new Date());
            List<String> a = new ArrayList<>();
            a.add("To Do");
            a.add("In Progress");
            a.add("Done");
            board.setColumns(a);
            boardRepository.save(board);
            return getBoardById(boardId);
        }
        return null;
    }

    public GetBoardResponse getBoardById(Long boardId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            GetBoardResponse response = new GetBoardResponse();
            response.setId(board.getId());
            response.setTitle(board.getTitle());
            response.setColumns(board.getColumns());
            return response;
        }
        return null;
    }

    public List<Card> getAllCardsByBoardId(Long boardId) {
        return boardRepository.getOne(boardId).getCardList();
    }

    public void deleteBoardById(Long boardId) {
        boardRepository.deleteById(boardId);
    }







}
