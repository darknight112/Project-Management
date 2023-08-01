package TrelloApp.TrelloApp.Controller;

import TrelloApp.TrelloApp.Models.Board;
import TrelloApp.TrelloApp.RequestObjects.GetBoardRequest;
import TrelloApp.TrelloApp.ResponseObjects.GetBoardResponse;
import TrelloApp.TrelloApp.Services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class BoardController {
    @Autowired
    BoardService boardService;


    @PostMapping("api/board")
    public void createBoard (@RequestBody GetBoardRequest boardRequest) {
        saveBoard(boardRequest);
    }

    @GetMapping("api/board")
    public List<Board> getProduct () {
        return boardService.getBoard();
    }

    @GetMapping("api/board/{boardId}")
    public GetBoardResponse getBoardId (@PathVariable Long boardId) {
        return boardService.getBoardById(boardId);
    }
    public void saveBoard(GetBoardRequest boardRequest) {
        Board board = new Board();
        board.setTitle(boardRequest.getTitle());
        board.setCreatedDate(new Date());
        board.setIsActive(true);
        boardService.saveBoard(board);
    }
}
