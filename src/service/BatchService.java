package service;

import model.Batch;
import model.Student;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BatchService {
    private static final List<Batch> batchDB = new ArrayList<>();

    static {
        Batch batch1 = new Batch("DEP", "3", LocalDate.of(2019, 05, 21), LocalDate.of(2019, 11, 20), "Batch 03");
        Batch batch2 = new Batch("DEP", "4", LocalDate.of(2019, 05, 21), LocalDate.of(2019, 11, 20), "Batch 04");
        Batch batch3 = new Batch("DEP", "5", LocalDate.of(2019, 05, 21), LocalDate.of(2019, 11, 20), "Batch 05");
        Batch batch4 = new Batch("DEP", "6", LocalDate.of(2019, 05, 21), LocalDate.of(2019, 11, 20), "Batch 06");
        Batch batch5 = new Batch("DEP", "7", LocalDate.of(2019, 05, 21), LocalDate.of(2019, 11, 20), "Batch 07");
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
}
