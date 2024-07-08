package sirma;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class FileParserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testParseToMap() throws Exception {
        Resource fileResource = new ClassPathResource("employee.cvs");

        assertNotNull(fileResource);

        MockMultipartFile file = new MockMultipartFile(
                "employee.cvs", fileResource.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                fileResource.getInputStream());

        assertNotNull(file);

        mockMvc.perform(multipart("/employeeMap")
                        .file("file", file.getBytes())
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    public void testParseToList() throws Exception {
        Resource fileResource = new ClassPathResource("employee.cvs");

        assertNotNull(fileResource);

        MockMultipartFile file = new MockMultipartFile(
                "employee.cvs", fileResource.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                fileResource.getInputStream());

        assertNotNull(file);

        mockMvc.perform(multipart("/employeeList")
                        .file("file", file.getBytes())
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}