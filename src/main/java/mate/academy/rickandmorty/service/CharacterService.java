package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.ResponceCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterMapper characterMapper;

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

    public ResponceCharacterDto getRandomCharacter() {
        long totalNumOfCharacters = 826L;
        long randomId = new Random().nextLong(totalNumOfCharacters);
        Character character = characterRepository.findById(randomId)
                .orElseThrow(() -> new  IllegalArgumentException("User not found with id: " + randomId));
        return characterMapper.toDto(character);
    }

    public List<ResponceCharacterDto> searchCharactersByName(String name) {
        List<Character> characters = characterRepository.findByNameContaining(name);
        return characters.stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
