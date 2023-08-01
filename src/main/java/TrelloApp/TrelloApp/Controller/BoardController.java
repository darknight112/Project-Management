package TrelloApp.TrelloApp.Controller;

import TrelloApp.TrelloApp.Models.Board;
import TrelloApp.TrelloApp.RequestObjects.GetBoardRequest;
import TrelloApp.TrelloApp.ResponseObjects.GetBoardResponse;
import TrelloApp.TrelloApp.Services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class BoardController {
    @Autowired
    BoardService boardService;


    @PostMapping("api/board") //create board
    public void createBoard (@RequestBody GetBoardRequest boardRequest) {
        saveBoard(boardRequest);
    }

    @GetMapping("api/board") //get board
    public List<Board> getProduct () {
        return boardService.getBoard();
    }

    @GetMapping("api/board/{boardId}") //find by id
    public GetBoardResponse getBoardId (@PathVariable Long boardId) {
        return boardService.getBoardById(boardId);
    }

    @PutMapping("api/board/{boardId}") //update
    public ResponseEntity<GetBoardResponse> updateBoard(@PathVariable Long boardId, @RequestBody GetBoardRequest updatedBoard) {
        GetBoardResponse response = boardService.updateBoard(boardId, updatedBoard);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("api/board/delete/{boardId}")//delete
    public void deleteBoard (@PathVariable Long boardId) {
        boardService.deleteBoardById(boardId);
    }


    public void saveBoard(GetBoardRequest boardRequest) {
        Board board = new Board();
        board.setTitle(boardRequest.getTitle());
        board.setCreatedDate(new Date());
        board.setIsActive(true);
        boardService.saveBoard(board);
    }
}
