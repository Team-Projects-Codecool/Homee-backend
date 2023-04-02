package randomizers;

import org.jeasy.random.api.Randomizer;

public class DeviceTypeRandomizer implements Randomizer<String> {
    private String deviceType;

    public DeviceTypeRandomizer(String deviceType) {
        this.deviceType = deviceType;
    }

    @Override
    public String getRandomValue() {
        return deviceType;
    }
}
