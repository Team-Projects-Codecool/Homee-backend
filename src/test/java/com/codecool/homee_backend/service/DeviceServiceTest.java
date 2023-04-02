package com.codecool.homee_backend.service;


import com.codecool.homee_backend.controller.dto.device.DeviceDto;
import com.codecool.homee_backend.controller.dto.device.NewDeviceDto;
import com.codecool.homee_backend.controller.dto.device.UpdatedDeviceDto;
import com.codecool.homee_backend.entity.Device;
import com.codecool.homee_backend.mapper.DeviceMapper;
import com.codecool.homee_backend.repository.DeviceRepository;
import com.codecool.homee_backend.service.exception.DeviceNotFoundException;
import org.assertj.core.api.Assertions;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;
    @Mock
    private DeviceMapper deviceMapper;
    @InjectMocks
    private DeviceService deviceService;


    @Test
    void shouldReturnEmptyListWhenNoDevices() {
        // given:
        Mockito.when(deviceRepository.findAll()).thenReturn(List.of());
        // when:
        List<DeviceDto> actual = deviceService.getAllDevices();
        // then:
        Assertions.assertThat(actual).isEmpty();
    }


    @Test
    void shouldThrowExceptionWhenDeviceNotFound() {
        // given:
        UUID id = UUID.randomUUID();
        Mockito.when(deviceRepository.findById(id)).thenReturn(Optional.empty());
        // when:
        Throwable throwable = Assertions.catchThrowable(() -> deviceService.getDevice(id));
        // then:
        Assertions.assertThat(throwable)
                .isInstanceOf(DeviceNotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void shouldReturnDeviceDtoWhenAddingNewDevice() {
        // given:
        NewDeviceDto dto = Instancio.of(NewDeviceDto.class)
                .generate(Select.field("deviceType"), gen -> gen.text().pattern("AGD"))
                .create();
        Device device = Instancio.of(Device.class).create();
        DeviceDto deviceDto = Instancio.of(DeviceDto.class).create();
        Mockito.when(deviceMapper.mapDeviceDtoToEntity(dto)).thenReturn(device);
        Mockito.when(deviceRepository.save(device)).thenReturn(device);
        Mockito.when(deviceMapper.mapDeviceEntityToDto(device)).thenReturn(deviceDto);
        // when:
        DeviceDto actual = deviceService.addNewDevice(dto);
        // then:
        Assertions.assertThat(actual).isEqualTo(deviceDto);
    }

    @Test
    void shouldReturnDeviceDtoWhenUpdatingDevice() {
        // given:
        UpdatedDeviceDto updatedDeviceDto = Instancio.of(UpdatedDeviceDto.class)
                .generate(Select.field("deviceType"), gen -> gen.text().pattern("AGD"))
                .create();
        Device device = Instancio.of(Device.class).create();
        DeviceDto deviceDto = Instancio.of(DeviceDto.class).create();
        Mockito.when(deviceRepository.findById(updatedDeviceDto.id())).thenReturn(Optional.of(device));
        Mockito.when(deviceRepository.save(device)).thenReturn(device);
        Mockito.when(deviceMapper.mapDeviceEntityToDto(device)).thenReturn(deviceDto);
        // when:
        DeviceDto actual = deviceService.updateDevice(updatedDeviceDto);
        // then:
        Assertions.assertThat(actual).isEqualTo(deviceDto);
    }

    @Test
    void shouldReturnNumberOfUserDevices() {
        // given:
        UUID id = UUID.randomUUID();
        List<Device> devices = Instancio.ofList(Device.class).size(5).create();
        Mockito.when(deviceRepository.findAllByUserId(id)).thenReturn(devices);
        // when:
        Integer actual = deviceService.countUserDevices(id);
        // then:
        Assertions.assertThat(actual).isEqualTo(devices.size());
    }
}