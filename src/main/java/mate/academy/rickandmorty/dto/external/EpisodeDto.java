package mate.academy.rickandmorty.dto.external;

import lombok.Data;

import java.util.List;

@Data
public class EpisodeDto {
    private int id;
    private String name;
    private String airDate;
    private String episode;
    private List<String> characters;
    private String url;
    private String created;
}
