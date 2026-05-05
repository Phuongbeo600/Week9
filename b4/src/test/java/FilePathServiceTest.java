import org.example.FilePathService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Test
public void testGetPath() {
    FilePathService service = new FilePathService();
    String result = service.getProjectFolderPath("data", "logs");

    // REFACTOR: Kết quả mong đợi cũng phải linh hoạt theo hệ điều hành
    String expected = "data" + java.io.File.separator + "logs";

    assertEquals(expected, result);
}