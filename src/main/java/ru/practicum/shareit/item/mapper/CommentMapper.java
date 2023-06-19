package ru.practicum.shareit.item.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.model.Comment;

import java.time.LocalDateTime;

@UtilityClass
public class CommentMapper {
    public CommentDto commentToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setAuthorName(comment.getAuthor().getName());
        commentDto.setText(comment.getText());
        commentDto.setCreated(comment.getCreated());
        return commentDto;
    }

    public Comment commentDtoToComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        comment.setCreated(LocalDateTime.now());
        return comment;
    }
}
