package cz.cvut.ear.clubevidence.service;

import cz.cvut.ear.clubevidence.dao.PaymentDao;
import cz.cvut.ear.clubevidence.model.Payment;
import cz.cvut.ear.clubevidence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentDao paymentDao;

    /**
     * Instantiates a new City service.
     *
     //* @param competitionDao the city dao
     */
    @Autowired
    public PaymentService(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    /**
     * Find all page.
     *
     // * @param pageable the pageable
     // * @param name     the name
     * @return the page
     */
    @Transactional(readOnly = true)
    public List<Payment> findAll() {
        return paymentDao.findAll();
    }

    /**
     * Find city.
     *
     * @param id the id
     * @return the city
     */
    @Transactional(readOnly = true)
    public Payment find(Integer id) {
        return paymentDao.find(id);
    }

    /**
     * Persist.
     *
     //   * @param city the city
     //create
     */
    @Transactional
    public void payForClubFirst(Payment payment) {
        paymentDao.persist(payment);
    }

    /**
     * Update.
     *
     //   * @param city the city
     */
    @Transactional
    public void update(Payment payment) {
        paymentDao.update(payment);
    }

    /**
     * Remove.
     *
     //    * @param city the city
     */
    @Transactional
    public void remove(Payment payment) {
        paymentDao.remove(payment);
    }

    @Transactional
    public List<Payment> getPaymentsByMember(User member) {
        List <Payment> list= new ArrayList<Payment>();
        if (paymentDao.find(member.getId()) != null){
            list.add(paymentDao.find(member.getId()));
        }
        return list;
    }

    @Transactional
    public double getPaymentSumByMember(User member) {
        List<Payment> payments = getPaymentsByMember(member);
        double totalSum = 0.0;
        for (Payment payment : payments) {
            totalSum += payment.getSum();
        }
        return totalSum;
    }

    @Transactional
    public void markPaymentAsNoted(Payment payment, User user) {
        payment.setNoted(user);
        paymentDao.update(payment);
    }
}
