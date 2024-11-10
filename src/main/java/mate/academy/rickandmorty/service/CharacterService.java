package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CharacterService {

    private static final String EXTERNAL_API_URL = "https://rickandmortyapi.com/api/character";

    @Autowired
    private CharacterRepository characterRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void initializeDatabase() {
        if (characterRepository.count() == 0) {
            Character[] characters = restTemplate.getForObject(EXTERNAL_API_URL, Character[].class);
            if (characters != null) {
                for (Character character : characters) {
                    characterRepository.save(character);
                }
            }
        }
    }

    public Character getRandomCharacter() {
        long count = characterRepository.count();
        if (count == 0) {
            return null;
        }
        long randomId = new Random().nextInt((int) count) + 1;
        Optional<Character> character = characterRepository.findById(randomId);
        return character.orElse(null);
    }

    public List<Character> searchCharactersByName(String name) {
        return characterRepository.findByName(name);
    }
}
