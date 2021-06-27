import model.Batch;
import model.Course;
import service.BatchService;
import service.CourseService;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BatchServiceTest {
    public static void main(String[] args) throws DuplicateEntryException, NotFoundException {
        saveBatch();
        updateBatch();
        deleteBatch();
        getAllBatches();
    }

    private static void saveBatch() throws DuplicateEntryException, NotFoundException {
        BatchService batchService = new BatchService();
        Batch batch1 = new Batch("DEP", "10", LocalDate.of(2021, 01, 05), LocalDate.of(2021, 05, 15), "Batch 10", BigDecimal.valueOf(100000));
        batchService.saveBatch(batch1);
        assert batchService.findBatch("10") != null : "failed save test";
    }

    private static void updateBatch() throws NotFoundException {
        BatchService batchService = new BatchService();
        Batch batch1 = new Batch("Dep7", "3", LocalDate.of(2021, 01, 05), LocalDate.of(2021, 05, 15), "Batch 10",BigDecimal.valueOf(125000));
        batchService.updateBatch(batch1);
        assert batchService.findBatch("3") != null : "failed update test";
        assert batchService.findBatch("3").getCourseCode().equals("Dep7") : "failed update test";
    }

    private static void deleteBatch() throws NotFoundException {
        BatchService batchService = new BatchService();
        batchService.deleteBatch("3");
        assert batchService.findBatch("3") == null : "failed delete test";
    }

    private static void getAllBatches() {
        BatchService batchService = new BatchService();
        assert batchService.getAllBatches().size() == 1 : "failed findAllBatches test";
    }
}
