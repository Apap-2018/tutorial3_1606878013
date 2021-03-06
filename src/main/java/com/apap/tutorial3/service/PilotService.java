package com.apap.tutorial3.service;

import com.apap.tutorial3.model.PilotModel;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface PilotService {
    void addPilot(PilotModel pilot);
    List<PilotModel> getPilotList();
    PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
    boolean deletePilot(String pilotId);
}
