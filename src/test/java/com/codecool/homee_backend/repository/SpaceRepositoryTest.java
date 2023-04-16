package com.codecool.homee_backend.repository;

import com.codecool.homee_backend.entity.Space;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
class SpaceRepositoryTest {

    @Autowired
    private SpaceRepository spaceRepository;

    @Test
    void shouldReturnAllSpacesFromDb() {
        // when:
        List<Space> actual = spaceRepository.findAll();

        // then:
        Assertions.assertThat(actual.size()).isEqualTo(1);
    }

    @Test
    void shouldReturnSpaceWithDevices() {
        // given:
        UUID spaceId = UUID.fromString("55d40137-4bfc-4701-ba3a-44f2b7e600cf");

        // when:
        Optional<Space> actual = spaceRepository.findById(spaceId);

        // then:
        Assertions.assertThat(actual).isPresent();
        Assertions.assertThat(actual.get().getAbout()).isEqualTo("testAbout");
        Assertions.assertThat(actual.get().getDevices().size()).isEqualTo(1);
        Assertions.assertThat(actual.get().getDevices().get(0).getAbout()).isEqualTo("testAbout");
        Assertions.assertThat(actual.get().getDevices().get(0).getSpace().getId()).isEqualTo(spaceId);
    }

    @Test
    void shouldReturnSpaceWithoutDevices() {
        // given:
        UUID spaceId = UUID.fromString("e741dad0-ae02-4dec-b895-c356f9795d99");

        // when:
        Optional<Space> actual = spaceRepository.findById(spaceId);

        // then:
        Assertions.assertThat(actual).isPresent();
        Assertions.assertThat(actual.get().getAbout()).isEqualTo("testAboutTwo");
        Assertions.assertThat(actual.get().getName()).isEqualTo("testNameTwo");
        Assertions.assertThat(actual.get().getDevices()).isEmpty();
    }
}