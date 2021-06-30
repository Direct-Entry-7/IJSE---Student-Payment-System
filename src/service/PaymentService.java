package service;

import model.Payment;
import model.Student;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    private static final List<Payment> paymentDB = new ArrayList<>();

    static {
        Student student1 = new Student("9312938282v", "Rajitha", "rajitha@gmail.com", "0718284821", LocalDate.of(1995, 02, 10), "Male", "address1");

        Payment payment1 = new Payment("ref1", "001", "description1", "paymentMethod1", LocalDate.of(2021, 07, 01), null, BigDecimal.valueOf(10000), "note1", student1);
        Payment payment2 = new Payment("ref2", "001", "description2", "paymentMethod2", LocalDate.of(2021, 07, 01), null, BigDecimal.valueOf(20000), "note1", student1);
        Payment payment3 = new Payment("ref3", "001", "description3", "paymentMethod3", LocalDate.of(2021, 07, 01), null, BigDecimal.valueOf(30000), "note1", student1);
        Payment payment4 = new Payment("ref4", "001", "description4", "paymentMethod1", LocalDate.of(2021, 07, 01), null, BigDecimal.valueOf(40000), "note1", student1);
        Payment payment5 = new Payment("ref5", "001", "description5", "paymentMethod2", LocalDate.of(2021, 07, 01), null, BigDecimal.valueOf(50000), "note1", student1);
        paymentDB.add(payment1);
        paymentDB.add(payment2);
        paymentDB.add(payment3);
        paymentDB.add(payment4);
        paymentDB.add(payment5);
    }

    public PaymentService() {
    }

    public void savePayment(Payment payment) throws DuplicateEntryException {
        if (exitsPayment(payment.getRefNo())) {
            throw new DuplicateEntryException();
        }
        paymentDB.add(payment);
    }

    private boolean exitsPayment(String refNo) {
        for (Payment payment : paymentDB) {
            if (payment.getRefNo().equals(refNo)) {
                return true;
            }
        }
        return false;
    }

    public void updatePayment(Payment payment) throws NotFoundException {
        Payment p = findPayment(payment.getRefNo());
        int index = paymentDB.indexOf(p);
        paymentDB.set(index, payment);
    }

    public void deletePayment(String refNo) throws NotFoundException {
        Payment payment = findPayment(refNo);
        paymentDB.remove(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentDB;
    }

    public Payment findPayment(String refNo) throws NotFoundException {
        for (Payment payment : paymentDB) {

            if (payment.getRefNo().equals(refNo)) {
                return payment;
            }
        }
        throw new NotFoundException();

    }
}
