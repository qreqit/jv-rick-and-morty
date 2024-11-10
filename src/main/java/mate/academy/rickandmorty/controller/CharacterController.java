package mate.academy.rickandmorty.controller;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/random")
    public Character getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/search")
    public List<Character> searchCharactersByName(@RequestParam String name) {
        return characterService.searchCharactersByName(name);
    }
}
