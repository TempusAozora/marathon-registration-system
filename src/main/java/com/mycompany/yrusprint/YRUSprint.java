/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.yrusprint;

import com.mycompany.yrusprint.gui.EventsPanel;
import com.mycompany.yrusprint.gui.RegistrationJFrame;
import com.mycompany.yrusprint.gui.SearchPanel;
import com.mycompany.yrusprint.helpers.EventManager;
import com.mycompany.yrusprint.models.RaceEvent;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.*;

/**
 *
 * @author tempus
 */
public class YRUSprint {

    public static void main(String[] args) {
        EventManager eventManager = EventManager.getInstance();
        eventManager.fetchEvents();

        SwingUtilities.invokeLater(() -> {

            RegistrationJFrame mainWindow = new RegistrationJFrame();
            JButton eventsButton = mainWindow.getEventsButton();
            JButton searchButton = mainWindow.getSearchButton();
            CardLayout cardLayout = mainWindow.getCardLayout();
            JPanel windowBody = mainWindow.getBody();

            EventsPanel eventsPanel = new EventsPanel();
            for (RaceEvent event : eventManager.getEvents()) {
                eventsPanel.addEvent(event);
            }
            SearchPanel searchPanel = new SearchPanel();
            
            eventsButton.addActionListener(e -> {
                System.out.println("CLICKED");
                cardLayout.show(windowBody, "EVENT_PANEL");
            });
            searchButton.addActionListener(e -> cardLayout.show(windowBody, "SEARCH_PANEL"));
            
            windowBody.add(eventsPanel, "EVENT_PANEL");
            windowBody.add(searchPanel, "SEARCH_PANEL");

            mainWindow.setVisible(true);
        });
        

        
    }
}
