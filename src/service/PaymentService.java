package service;

import model.Payment;
import model.Student;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentService {

    private static final List<Payment> paymentDB = new ArrayList<>();

    static {
        Student student1 = new Student("9312938282v", "Rajitha", "rajitha@gmail.com", "0718284821", LocalDate.of(1995, 02, 10), "Male", "address1");
        Payment payment1 = new Payment("ref1", "001", "3", "9512938224v", "description1", "paymentMethod1", LocalDate.of(2021, 01, 01), null, BigDecimal.valueOf(10000), "note1");
        Payment payment2 = new Payment("ref2", "001", "3", "9512938224v", "description2", "paymentMethod2", LocalDate.of(2021, 07, 01), null, BigDecimal.valueOf(20000), "note2");
        paymentDB.add(payment1);
        paymentDB.add(payment2);

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

//    public List<Payment> getAllPayments() {
//        return paymentDB;
//    }

    public Payment findPayment(String refNo) throws NotFoundException {
        for (Payment payment : paymentDB) {

            if (payment.getRefNo().equals(refNo)) {
                return payment;
            }
        }
        throw new NotFoundException();

    }

    public  List<Payment> findPayments(String studentNIC,String courseCode) throws NotFoundException {
        List<Payment> filteredPayments = paymentDB.stream().filter(payment -> payment.getStudentNIC().equals(studentNIC)).filter(payment -> payment.getCourseCode().equals(courseCode)).collect(Collectors.toList());
        if(filteredPayments.size() > 0){
            return filteredPayments;
        }
        throw new NotFoundException();

    }

    public List<Payment> getAllPayments(String query) {
        List<Payment> result = new ArrayList<>();

        for (Payment payment : paymentDB) {
            if(payment.getRefNo().contains(query)
                    ||payment.getStudentNIC().contains(query)
                    ||payment.getCourseCode().contains(query)
                    ||payment.getBatchNo().contains(query)
                    ||payment.getDescription().contains(query)
                    ||payment.getPaymentMethod().contains(query)
                    ||payment.getAmount().toString().contains(query)
                    ||payment.getDate().toString().contains(query)
                    ||payment.getNote().contains(query)
            ){
                result.add(payment);
            }
        }
        return result;
    }
}
