package group4.LastBuildWeek;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EndpointTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetClientiEndpoint() {
        String url = "http://localhost:" + port + "/clienti";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("");

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        // Assicurati che la risposta sia quella che ti aspetti
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("{\"content\":[],\"pageable\":{\"pageNumber\":0,\"pageSize\":5,\"sort\":{\"empty\":false,\"sorted\":true,\"unsorted\":false},\"offset\":0,\"paged\":true,\"unpaged\":false},\"totalPages\":0,\"totalElements\":0,\"last\":true,\"size\":5,\"number\":0,\"sort\":{\"empty\":false,\"sorted\":true,\"unsorted\":false},\"first\":true,\"numberOfElements\":0,\"empty\":true}", responseEntity.getBody());
    }

//    @Test
//    @WithMockUser(roles = "USER")
//    void testAdminEndpointWithUserRole() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/clienti").with(csrf()))
//                .andExpect(MockMvcResultMatchers.status().isForbidden());
//    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testAdminEndpointWithAdminRole() throws Exception {

        String url = "http://localhost:" + port + "/clienti";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("");

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/clienti").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
