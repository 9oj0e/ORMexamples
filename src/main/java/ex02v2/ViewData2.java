package ex02v2;

import ex02v2.model.Reply;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class ViewData2 {
    private int boardId;
    private String title;
    private String content;

    // Board 데이터만 넣는 생성자
    public ViewData2(int boardId, String title, String content) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
    }

    // 댓글들은 addReply로 추가하기
    private List<Reply> replies = new ArrayList<>();

    public void addReply(Reply reply){
        replies.add(reply);
    }

    public ViewData2(List<DBData2> dbData2List) {
        if (dbData2List.size() > 0) {
            this.boardId = dbData2List.get(0).getBoardId();
            this.title = dbData2List.get(0).getTitle();
            this.content = dbData2List.get(0).getContent();
        }
        /*
        dbData2List.stream().forEach(dbData2 -> {
            addReply(new Reply(dbData2.getReplyId(), dbData2.getComment()));
        });
        */
        replies = dbData2List.stream()
                .map(dbData2 -> new Reply(dbData2.getReplyId(), dbData2.getComment()))
                .toList();
    }
}