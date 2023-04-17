package com.codecool.homee_backend.controller;

import com.codecool.homee_backend.controller.dto.space.NewSpaceDto;
import com.codecool.homee_backend.controller.dto.space.SpaceDto;
import com.codecool.homee_backend.repository.HomeeUserRepository;
import com.codecool.homee_backend.service.SpaceService;
import com.codecool.homee_backend.service.auth.JwtTokenService;
import com.codecool.homee_backend.service.exception.SpaceNotFoundException;
import org.hamcrest.Matchers;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.UUID;

import static com.codecool.homee_backend.config.auth.SpringSecurityConfig.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SpaceController.class)
class SpaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpaceService spaceService;

    @MockBean
    private JwtTokenService jwtTokenService;

    @MockBean
    private HomeeUserRepository userRepository;

    @WithMockUser(roles = USER)
    @Test
    void shouldReturnEmptyJson() throws Exception {
        // given:
        Mockito.when(spaceService.getAllSpaces()).thenReturn(List.of());


        // when:
        ResultActions response = mockMvc.perform(get("/api/v1/spaces"));

        // then:
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());

    }

    @WithMockUser(roles = USER)
    @Test
    void shouldReturnSpacesJson() throws Exception {
        // given:
        List<SpaceDto> dtos = Instancio.ofList(SpaceDto.class).size(2).create();

        Mockito.when(spaceService.getAllSpaces()).thenReturn(dtos);


        // when:
        ResultActions response = mockMvc.perform(get("/api/v1/spaces"));

        // then:
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value(dtos.get(0).name()))
                .andExpect(jsonPath("$[0].about").value(dtos.get(0).about()))
                .andExpect(jsonPath("$[1].name").value(dtos.get(1).name()))
                .andExpect(jsonPath("$[1].about").value(dtos.get(1).about()));


    }

    @WithMockUser(roles = USER)
    @Test
    void shouldReturn404WhenSpaceNotFound() throws Exception {
        // given:
        UUID id = UUID.randomUUID();
        Mockito.when(spaceService.getSpace(id)).thenThrow(new SpaceNotFoundException(id));

        // when:
        ResultActions response = mockMvc.perform(get("/api/v1/spaces/" + id));

        // then:
        response.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage", Matchers.containsString(id.toString())));
        ;

    }

    @WithMockUser(roles = USER)
    @Test
    void shouldReturnNewSpaceJson() throws Exception {
        // given:
        NewSpaceDto newSpaceDto = new NewSpaceDto("testName", "testAbout");

        SpaceDto spaceDto = new SpaceDto(UUID.randomUUID(), "testName", "testAbout");

        Mockito.when(spaceService.addNewSpace(newSpaceDto)).thenReturn(spaceDto);


        // when:
        ResultActions reponse = mockMvc.perform(post("/api/v1/spaces")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                         "name": "testName",
                          "about": "testAbout"
                        }
                                                """));
        // then:
        reponse.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(spaceDto.name()))
                .andExpect(jsonPath("$.about").value(spaceDto.about()));

    }
}