package TrelloApp.TrelloApp.Controller;

import TrelloApp.TrelloApp.Models.Board;
import TrelloApp.TrelloApp.Models.Card;
import TrelloApp.TrelloApp.RequestObjects.GetBoardRequest;
import TrelloApp.TrelloApp.ResponseObjects.GetBoardResponse;
import TrelloApp.TrelloApp.Services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class BoardController {
    @Autowired
    BoardService boardService;

//    @RequestMapping("api/boards")
    @RequestMapping(value = "api/boards", method = RequestMethod.POST) //create board
    public ResponseEntity<GetBoardResponse> createBoard (@RequestBody GetBoardRequest boardRequest) {
        Board savedBoard = saveBoard(boardRequest);
        GetBoardResponse response = new GetBoardResponse();
        response.setId(savedBoard.getId());
        response.setTitle(savedBoard.getTitle());
        List<String> c=new ArrayList<String>();
        c.add("To Do");
        c.add("In Progress");
        c.add("Done");
        response.setColumns(c);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "api/boards", method = RequestMethod.GET) //get board
    public List<Board> getProduct () {
        return boardService.getBoard();
    }

//    @RequestMapping(value = "api/boards/{boardId}", method = RequestMethod.GET) //find by id
//    public GetBoardResponse getBoardId (@PathVariable Long boardId) {
//        return boardService.getBoardById(boardId);
//    }

    @RequestMapping(value = "api/boards/{boardId}", method = RequestMethod.PUT) //update
    public ResponseEntity<GetBoardResponse> updateBoard(@PathVariable Long boardId, @RequestBody GetBoardRequest updatedBoard) {
        GetBoardResponse response = boardService.updateBoard(boardId, updatedBoard);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @RequestMapping(value = "api/boards/{boardId}", method = RequestMethod.DELETE)//delete
//    public void deleteBoard (@PathVariable Long boardId) {
//        boardService.deleteBoardById(boardId);
//    }

    @RequestMapping(value = "api/boards/{boardId}", method = RequestMethod.DELETE)//delete
    public ResponseEntity<String> deleteBoard (@PathVariable Long boardId) {
        boardService.deleteBoardById(boardId);
        String message = "Board with ID " + boardId + " has been deleted successfully.";
        return ResponseEntity.ok(message);
    }

    @GetMapping(value = "api/boards/{boardId}/cards")//Get all cards by board id
    public ResponseEntity<List<Card>> getAllCardsByBoardId(@PathVariable Long boardId) {
        List<Card> cards = boardService.getAllCardsByBoardId(boardId);

        cards.removeIf(card -> {
            card.setCreatedDate(null);
            card.setUpdatedDate(null);
            card.setIsActive(null);
            return true; // Always return true to remove the element
        });
        if (!cards.isEmpty()) {
            return ResponseEntity.ok(cards);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


        public Board saveBoard(GetBoardRequest boardRequest) {
        Board board = new Board();
        board.setTitle(boardRequest.getTitle());
        board.setCreatedDate(new Date());
        board.setIsActive(true);
        board.setUpdatedDate(new Date());
        List<String> a = new ArrayList<>();
        a.add("To Do");
        a.add("In Progress");
        a.add("Done");
        board.setColumns(a);
        boardService.saveBoard(board);
            return board;
        }
}

