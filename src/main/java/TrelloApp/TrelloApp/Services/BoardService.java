package TrelloApp.TrelloApp.Services;

import TrelloApp.TrelloApp.Models.Board;
import TrelloApp.TrelloApp.Repository.BoardRepository;
import TrelloApp.TrelloApp.ResponseObjects.GetBoardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public GetBoardResponse getBoardById(Long boardId) { //get by id
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            GetBoardResponse response = new GetBoardResponse();
            response.setTitle(board.getTitle());
            return response;
        }
        return null;
    }

//update method

    //delete method



}
