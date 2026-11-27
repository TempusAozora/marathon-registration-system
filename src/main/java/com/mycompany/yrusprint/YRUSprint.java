/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.yrusprint;

import com.mycompany.yrusprint.gui.BibPanel;
import com.mycompany.yrusprint.gui.EventDetails;
import com.mycompany.yrusprint.gui.EventPreviewTemplate;
import com.mycompany.yrusprint.gui.EventRegisterPanel;
import com.mycompany.yrusprint.gui.EventsPanel;
import com.mycompany.yrusprint.gui.RegistrationJFrame;
import com.mycompany.yrusprint.gui.RegistrationTemplate;
import com.mycompany.yrusprint.gui.SearchPanel;
import com.mycompany.yrusprint.helpers.EventManager;
import com.mycompany.yrusprint.helpers.SearchService;
import com.mycompany.yrusprint.models.RaceEvent;
import com.mycompany.yrusprint.models.Registration;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;

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

            Runnable backButtonFunction = () -> cardLayout.show(windowBody, "EVENT_PANEL"); 
            
            // Events
            EventDetails eventDetails = new EventDetails(backButtonFunction);
            EventsPanel eventsPanel = new EventsPanel();
            EventRegisterPanel registerPanel = new EventRegisterPanel();
            for (RaceEvent event : eventManager.getEvents()) {
                EventPreviewTemplate eventPreview = new EventPreviewTemplate(
                    event,
                    () -> {cardLayout.show(windowBody, "EVENT_DETAILS"); eventDetails.setEvent(event);},
                    () -> {cardLayout.show(windowBody, "REGISTER_PANEL"); System.out.println("CLICKED");}
                );
                eventsPanel.placeEventPreview(eventPreview);
            }
            
            eventsButton.addActionListener(e -> {
                cardLayout.show(windowBody, "EVENT_PANEL");
            });

            // Search
            SearchPanel searchPanel = new SearchPanel();
            BibPanel bibPanel = new BibPanel(backButtonFunction);
            searchButton.addActionListener(e -> cardLayout.show(windowBody, "SEARCH_PANEL"));
            searchPanel.getSearchButton().addActionListener((e) -> {
                JTextField SearchField = searchPanel.getSearchField();
                JPanel ResultsPanel = searchPanel.getResultsPanel();
                List<RegistrationTemplate> searchResults = searchPanel.getSearchResults();
                List<Registration> registrations = SearchService.findRegistrations(SearchField.getText());
                
                if (!searchResults.isEmpty()) {
                    for (RegistrationTemplate result : searchResults) {
                        ResultsPanel.remove(result);
                    }
                }
                
                if (registrations.isEmpty()) return;
                searchPanel.setSearchResults(new ArrayList<>());

                for (Registration r : registrations) {
                    RegistrationTemplate template = new RegistrationTemplate(r, () -> {
                        cardLayout.show(windowBody, "BIB_PREVIEW");
                        bibPanel.setRegistration(r);
                    });
                    searchResults.add(template);
                    ResultsPanel.add(template);
                }

                ResultsPanel.revalidate();
                ResultsPanel.repaint();
            });

            windowBody.add(eventsPanel, "EVENT_PANEL");
            windowBody.add(searchPanel, "SEARCH_PANEL");
            windowBody.add(bibPanel, "BIB_PREVIEW");
            windowBody.add(eventDetails, "EVENT_DETAILS");
            windowBody.add(registerPanel, "REGISTER_PANEL");

            mainWindow.setVisible(true);
        });
    }
}
