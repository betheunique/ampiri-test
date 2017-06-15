import com.fasterxml.jackson.databind.ObjectMapper;
import com.glispa.ampiri.Application;
import com.glispa.ampiri.cache.AdDimensionCache;
import com.glispa.ampiri.config.PersistenceJPAConfig;
import com.glispa.ampiri.controller.AdvertisementController;
import com.glispa.ampiri.dao.AdDimensionRepository;
import com.glispa.ampiri.dao.AdPlaceRepository;
import com.glispa.ampiri.dao.AppRepository;
import com.glispa.ampiri.model.AdRequest;
import com.glispa.ampiri.service.AdPlaceService;
import com.glispa.ampiri.service.AdvDimensionService;
import com.glispa.ampiri.service.AppService;
import com.glispa.ampiri.service.ServeAdService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author abhishekrai
 * @since 10/05/2017
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)

//@SpringBootTest(classes = {AdvertisementController.class, ServeAdService.class, AdvDimensionService.class, AppService.class, AdPlaceService.class,
//        AdDimensionCache.class, AdDimensionRepository.class, AdPlaceRepository.class, AppRepository.class, PersistenceJPAConfig.class})
@AutoConfigureMockMvc
public class ApplicationTest {

//    @Autowired
//    private MockMvc mvc;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getAdDimension() throws Exception {
        AdRequest adRequest = new AdRequest();
        adRequest.setAddId("1f855d85-6b3b-4113-af6b-c87b1b39e185");
        adRequest.setHeight(1200);
        adRequest.setWidth(700);
        String json = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(adRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        mvc.perform(MockMvcRequestBuilders.post("/v1/serveAd").content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.url").value("http://cdn303.example.com/video/codec/mp4/1200x700.mp4"))
                .andExpect(jsonPath("$.type").value("video"));
    }
}
