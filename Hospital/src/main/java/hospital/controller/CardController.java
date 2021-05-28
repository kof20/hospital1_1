package hospital.controller;

import hospital.model.Card;
import hospital.service.CardService;
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
    CardService cardService;

    @RequestMapping(value = "/card/{idPatient}/{idDoctor}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)  /*Прописываем путь 
    по которому вызывается метод и прописываем его работу */
    public ResponseEntity<Card> getCarByIdsPatientAndDoctor(@PathVariable("idPatient") Long idPatient,  //обьявляем метод
                                                            @PathVariable("idDoctor") Long idDoctor) {
        if (idPatient == null || idDoctor == null) {            // проверка существования значения
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Card> cards = this.cardService.findAll();     //находим все карточки
        Card card = new Card();    //создаем промежуточный объект
        for (Card stepCard:       // проходим по списку всех карт
             cards) {
            if ((stepCard.getPatient().getId().equals(idPatient))       //проверяем равны ли в каждой карточке айдишники пациента и доктора
                    && (stepCard.getDoctor().getId().equals(idDoctor))) {
                card = stepCard;       // присваеваем найденую карточку ранее объявленному объекту
            }
        }
        return new ResponseEntity<>(card, HttpStatus.OK);     // выводим объект
    }

    @RequestMapping(value = "/card/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> getCardById(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Card card = (Card) this.cardService.findById(id);

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
        this.cardService.save(card);
        return new ResponseEntity<>(card, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/card/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> deleteCard(@PathVariable("id") Long id) {
        Card card = (Card) this.cardService.findById(id);

        if (card == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.cardService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/cards", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = this.cardService.findAll();

        if (cards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cards, HttpStatus.OK);
    }
}
