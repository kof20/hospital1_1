package hospital.service.impl;

import hospital.model.Card;
import hospital.repo.CardRepo;
import hospital.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements GeneralService<Card> {
    @Autowired
    CardRepo cardRepo;

    @Override
    public void save(Card card) {
        cardRepo.save(card);
    }

    @Override
    public List<Card> findAll() {
        return cardRepo.findAll();
    }

    @Override
    public Card findById(Long id) {
        return cardRepo.findCardById(id);
    }

    @Override
    public void delete(Long id) {
        cardRepo.deleteById(id);
    }
}
