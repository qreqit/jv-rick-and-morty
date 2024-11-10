package mate.academy.rickandmorty.dto.external;

import lombok.Data;
import java.util.List;

@Data
public class CharacterDto {
    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private OriginDto origin;
    private LocationDto location;
    private String image;
    private List<String> episodes;
    private String url;
    private String created;

    @Data
    public static class OriginDto {
        private String name;
        private String url;
    }

    @Data
    public static class LocationDto {
        private String name;
        private String url;
    }
}
