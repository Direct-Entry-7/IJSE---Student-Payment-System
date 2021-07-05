package service;

import model.Batch;
import model.Student;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BatchService {
    private static final List<Batch> batchDB = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    static {
//        Student student1 = new Student("931293828v","Rajitha","rajitha@gmail.com","0718284821",LocalDate.of(1995,02,10),"Male","address1");
//        Student student2 = new Student("951293822v","Pashan","pashan@yahoo.com","0778284111",LocalDate.of(1995,12,15),"Male","address2");
//        students.add(student1);
//        students.add(student2);
        Batch batch1 = new Batch("001", "3", LocalDate.of(2019, 05, 21), LocalDate.of(2019, 11, 20), "Batch 03", BigDecimal.valueOf(75000),null);
        Batch batch2 = new Batch("002", "4", LocalDate.of(2019, 05, 21), LocalDate.of(2019, 11, 20), "Batch 04",BigDecimal.valueOf(80000),null);
        Batch batch3 = new Batch("003", "5", LocalDate.of(2019, 05, 21), LocalDate.of(2019, 11, 20), "Batch 05",BigDecimal.valueOf(85000),null);
        Batch batch4 = new Batch("001", "6", LocalDate.of(2019, 05, 21), LocalDate.of(2019, 11, 20), "Batch 06",BigDecimal.valueOf(90000),null);
        Batch batch5 = new Batch("002", "7", LocalDate.of(2019, 05, 21), LocalDate.of(2019, 11, 20), "Batch 07",BigDecimal.valueOf(100000),null);
        batchDB.add(batch1);
        batchDB.add(batch2);
        batchDB.add(batch3);
        batchDB.add(batch4);
        batchDB.add(batch5);
    }

    public BatchService() {
    }

    public void saveBatch(Batch batch) throws DuplicateEntryException {
        if (exitsBatch(batch.getBatchNo())) {
            throw new DuplicateEntryException();
        }
        batchDB.add(batch);
    }

    private boolean exitsBatch(String batchNo) {
        for (Batch batch : batchDB) {

            if (batch.getBatchNo().equals(batchNo)) {
                return true;
            }
        }
        return false;
    }

    public void updateBatch(Batch batch) throws NotFoundException {
        Batch b = findBatch(batch.getBatchNo());
        int index = batchDB.indexOf(b);
        batchDB.set(index, batch);
    }

    public void deleteBatch(String batchNo) throws NotFoundException {
        Batch batch = findBatch(batchNo);
        batchDB.remove(batch);
    }

    public List<Batch> getAllBatches() {
        return batchDB;
    }

    public Batch findBatch(String batchNo) throws NotFoundException {
        for (Batch batch : batchDB) {

            if (batch.getBatchNo().equals(batchNo)) {
                return batch;
            }
        }
        throw new NotFoundException();

    }

//    public void saveStudent(Batch batch, Student student) {
//
//    }

}
