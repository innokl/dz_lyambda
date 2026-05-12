package ru.mephi.vikingdemo.controller;

import org.springframework.stereotype.Component;
import ru.mephi.vikingdemo.gui.VikingDesktopFrame;
import ru.mephi.vikingdemo.model.Viking;
import ru.mephi.vikingdemo.service.VikingService;

import javax.swing.*;

@Component
public class VikingListener {

    private final VikingService service;
    private VikingDesktopFrame gui;

    public VikingListener(VikingService service) {
        this.service = service;
    }

    public void setGui(VikingDesktopFrame gui) {
        this.gui = gui;
    }


    public void onVikingCreated(Viking viking) {
        if (gui != null) {
            SwingUtilities.invokeLater(() -> {
                gui.addNewViking(viking);
            });
        }
    }


    public void onVikingDeleted(int id) {
        if (gui != null) {
            SwingUtilities.invokeLater(() -> {
                gui.removeViking(id);
            });
        }
    }

    public void onVikingUpdated(Viking viking) {
        if (gui != null) {
            SwingUtilities.invokeLater(() -> {
                gui.updateViking(viking);
            });
        }
    }
    public void testAdd() {
        if (gui != null) {
            gui.addNewViking(service.createRandomViking());
        }
    }
}