package komo.fraczek.electronicsservice.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ToString
public class CommentsPayload {

    @Setter
    private List<String> comments;

}
