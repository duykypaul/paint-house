package com.bezkoder.spring.jpa.postgresql.controller;

import com.bezkoder.spring.jpa.postgresql.dto.TutorialDTO;
import com.bezkoder.spring.jpa.postgresql.model.Tutorial;
import com.bezkoder.spring.jpa.postgresql.repository.TutorialRepository;
import com.bezkoder.spring.jpa.postgresql.service.TutorialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class TutorialController {

    final TutorialRepository tutorialRepository;
    final TutorialService tutorialService;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        try {

//			if (title == null)
//				tutorials.addAll(tutorialRepository.findAll());
//			else
            List<Tutorial> tutorials = new ArrayList<>(tutorialRepository.findByTitleContaining(title));

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        return tutorialData
                .map(tutorial -> new ResponseEntity<>(tutorial, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        try {
            Tutorial _tutorial = tutorialRepository
                    .save(Tutorial.builder()
                            .title(tutorial.getTitle())
                            .published(false)
                            .description(tutorial.getDescription())
                            .build());
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
            Tutorial _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            tutorialRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            tutorialRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        try {
            List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/all")
    public ResponseEntity<List<?>> findAll() {
        try {
            List<?> tutorials = tutorialRepository.findAll1(2);
//			List<?> tutorials = tutorialRepository.findById1();

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/published/{size}/{page}")
    public ResponseEntity<Page<Tutorial>> findByPublished(@PathVariable Integer size, @PathVariable Integer page) {
        try {
            Pageable paging = PageRequest.of(page, size);
//			Page<Tutorial> tutorials = tutorialRepository.findByPublished(true, paging);
            Page<Tutorial> tutorials = tutorialRepository.customFindByPublished(true, paging);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/generic/{id}")
	@ResponseStatus(HttpStatus.OK)
    public TutorialDTO genericFindById(@PathVariable Long id) {
        try {
			return tutorialService.find(id);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
            return null;
        }
    }

    @GetMapping("/tutorials/generic/ex")
    @ResponseStatus(HttpStatus.OK)
    public TutorialDTO genericFindByEx() {
        try {
            Example<Tutorial> dExample = Example.of(Tutorial.builder().title("java").published(true).build());
            return tutorialService.findOneByExample(dExample);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @GetMapping("/tutorials/generic/exs")
    @ResponseStatus(HttpStatus.OK)
    public List<TutorialDTO> genericFindByExs() {
        try {
            Example<Tutorial> dExample = Example.of(Tutorial.builder().title("python").published(true).build());
            return tutorialService.findByExample(dExample);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

}
