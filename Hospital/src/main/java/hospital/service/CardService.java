package hospital.service;

import hospital.model.Card;

public interface CardService extends GeneralService<Card> {
    Card getCardByPatintAndDoctor(Long idPatient, Long idDoctor);
}
