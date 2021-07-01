package com.inss.http.controller;

import com.google.gson.Gson;
import com.inss.domain.Inss;
import com.inss.exception.HttpExceptionHandler;
import com.inss.http.request.InssRequest;
import com.inss.service.SaveService;
import com.inss.service.DeleteService;
import com.inss.service.RetrieveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InssControllerTest {

    private final String URL = "/api/inss";
    private InssRequest request, nullable;
    private Inss inss;

    @Mock
    private SaveService saveService;

    @Mock
    private RetrieveService retrieveService;

    @Mock
    private DeleteService deleteService;

    @InjectMocks
    private InssController controller;

    private MockMvc mvc;

    @BeforeEach
    public void init() {
        mvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(HttpExceptionHandler.class).build();

        request =  InssRequest.builder()
                .year("2020")
                .until(new BigDecimal("1100"))
                .percent(new BigDecimal("7.5"))
                .fromSecond(new BigDecimal("1100.1"))
                .untilSecond(new BigDecimal("2203.48"))
                .percentSecond(new BigDecimal("9"))
                .fromThird(new BigDecimal("2203.49"))
                .untilThird(new BigDecimal("3305.22"))
                .percentThird(new BigDecimal("12"))
                .fromFourth(new BigDecimal("3305.23"))
                .untilFourth(new BigDecimal("6433.57"))
                .percentFourth(new BigDecimal("14"))
                .isCurrent(Boolean.TRUE)
                .build();

        inss = Inss.builder()
                .id(UUID.randomUUID())
                .year("2020")
                .until(new BigDecimal("1100"))
                .percent(new BigDecimal("7.5"))
                .fromSecond(new BigDecimal("1100.1"))
                .untilSecond(new BigDecimal("2203.48"))
                .percentSecond(new BigDecimal("9"))
                .fromThird(new BigDecimal("2203.49"))
                .untilThird(new BigDecimal("3305.22"))
                .percentThird(new BigDecimal("12"))
                .fromFourth(new BigDecimal("3305.23"))
                .untilFourth(new BigDecimal("6433.57"))
                .percentFourth(new BigDecimal("14"))
                .isCurrent(Boolean.TRUE)
                .build();

        nullable = InssRequest.builder().build();
    }

    @Test
    void it_should_create_successfully() throws Exception {

        Inss inss = InssRequest.from(request);

        when(saveService.execute(any())).thenReturn(inss);

        this.mvc.perform(MockMvcRequestBuilders.post(URL)
                .content(new Gson().toJson(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void it_should_throw_bad_request_exception_when_is_nullable() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.post(URL)
                .content(new Gson().toJson(nullable))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
    }

    @Test
    void it_should_throw_bad_request_exception_when_malformed_json() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.post(URL)
                .content("{")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
    }

    @Test
    void it_should_retrieve_successfully() throws Exception {

        when(retrieveService.execute(any())).thenReturn(Optional.ofNullable(inss));

        this.mvc.perform(MockMvcRequestBuilders.get(URL+"/"+inss.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void it_should_throw_not_found_when_retrieve() throws Exception {
        when(retrieveService.execute(any())).thenReturn(Optional.empty());

        this.mvc.perform(MockMvcRequestBuilders.get(URL+"/"+inss.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void it_should_delete_successfully() throws Exception {
        when(retrieveService.execute(any())).thenReturn(Optional.ofNullable(inss));

        doNothing().when(deleteService).execute(any());

        this.mvc.perform(MockMvcRequestBuilders.delete(URL+"/"+inss.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void it_should_throw_not_found_when_deleted() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.delete(URL+"/"+inss.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void it_should_update_successfully() throws Exception {

        when(retrieveService.execute(any())).thenReturn(Optional.ofNullable(inss));
        when(saveService.execute(any())).thenReturn(inss);

        this.mvc.perform(MockMvcRequestBuilders.put(URL+"/"+inss.getId())
                .content(new Gson().toJson(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void it_should_throw_not_found_when_updated() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.put(URL+"/"+inss.getId())
                .content(new Gson().toJson(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

}