package com.codecool.homee_backend.mapper;


import com.codecool.homee_backend.controller.dto.device.DeviceDto;
import com.codecool.homee_backend.controller.dto.device.NewDeviceDto;
import com.codecool.homee_backend.entity.Device;
import com.codecool.homee_backend.entity.type.DeviceType;
import org.instancio.Instancio;
import org.instancio.Select;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeviceMapperTest {

    private final DeviceMapper deviceMapper = new DeviceMapper();
    private final EasyRandomParameters parameters = new EasyRandomParameters();
    private final EasyRandom easyRandom = new EasyRandom(parameters);


    @Test
    void shouldMapDeviceEntityToDto() {
        //given:
        Device device = easyRandom.nextObject(Device.class);
        //when:
        DeviceDto actual = deviceMapper.mapDeviceEntityToDto(device);
        //then:
        DeviceDto expected = new DeviceDto(
                device.getId(),
                device.getName(),
                device.getModel(),
                device.getDeviceType().toString(),
                device.getSpot(),
                device.getWarrantyStart(),
                device.getWarrantyEnd(),
                device.getPurchaseDate(),
                device.getPurchasePrice(),
                device.getCreatedAt(),
                device.getUpdatedAt(),
                device.getAbout(),
                device.getImageName());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldMapDeviceDtoToEntity() {
        //given:
        NewDeviceDto dto = Instancio.of(NewDeviceDto.class)
                .generate(Select.field("deviceType"), gen -> gen.text().pattern("AGD"))
                .create();
        //when:
        Device actual = deviceMapper.mapDeviceDtoToEntity(dto);
        //then:
        Device expected = new Device(
                dto.name(),
                dto.model(),
                DeviceType.valueOf(dto.deviceType()),
                dto.spot(),
                dto.warrantyStart(),
                dto.warrantyEnd(),
                dto.purchaseDate(),
                dto.purchasePrice(),
                dto.about()
        );
        assertDeviceEntityIsCorrect(actual, expected);
    }

    private static void assertDeviceEntityIsCorrect(Device actual, Device expected) {
        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getDeviceType()).isEqualTo(expected.getDeviceType());
        assertThat(actual.getModel()).isEqualTo(expected.getModel());
        assertThat(actual.getSpot()).isEqualTo(expected.getSpot());
        assertThat(actual.getWarrantyStart()).isEqualTo(expected.getWarrantyStart());
        assertThat(actual.getWarrantyEnd()).isEqualTo(expected.getWarrantyEnd());
        assertThat(actual.getPurchaseDate()).isEqualTo(expected.getPurchaseDate());
        assertThat(actual.getPurchasePrice()).isEqualTo(expected.getPurchasePrice());
        assertThat(actual.getAbout()).isEqualTo(expected.getAbout());
    }
}