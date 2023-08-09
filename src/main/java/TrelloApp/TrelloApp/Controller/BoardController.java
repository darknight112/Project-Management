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
@RequestMapping(path = "api/boards")
@CrossOrigin("*")
public class BoardController {
    @Autowired
    BoardService boardService;

    @PostMapping //create board
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

    @GetMapping//get board
    public List<Board> getBoards () {
        return boardService.getBoard();
    }

    @PutMapping(path = "/{boardId}") //update
    public ResponseEntity<GetBoardResponse> updateBoard(@PathVariable Long boardId, @RequestBody GetBoardRequest updatedBoard) {
        GetBoardResponse response = boardService.updateBoard(boardId, updatedBoard);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping(path = "{boardId}")//delete
    public ResponseEntity<String> deleteBoard (@PathVariable Long boardId) {
        boardService.deleteBoardById(boardId);
        String message = "Board with ID " + boardId + " has been deleted successfully.";
        return ResponseEntity.ok(message);
    }

    @GetMapping(path = "/{boardId}/cards") //get all cards by board id
        public ResponseEntity<List<Card>> getAllCardsByBoardId(@PathVariable Long boardId) {
            List<Card> cards = boardService.getAllCardsByBoardId(boardId);
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

