package hospital.controller;

import hospital.model.Card;
import hospital.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {

    @Qualifier("cardServiceImpl")
    @Autowired
    GeneralService generalService;

    @RequestMapping(value = "/card/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> getCardById(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Card card = (Card) this.generalService.findById(id);

        if (card == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @RequestMapping(value = "/card", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> addCard(@RequestBody Card card) {
        HttpHeaders headers = new HttpHeaders();
        if (card == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.generalService.save(card);
        return new ResponseEntity<>(card, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/card/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> deleteCard(@PathVariable("id") Long id) {
        Card card = (Card) this.generalService.findById(id);

        if (card == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.generalService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/cards", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = this.generalService.findAll();

        if (cards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cards, HttpStatus.OK);
    }
}