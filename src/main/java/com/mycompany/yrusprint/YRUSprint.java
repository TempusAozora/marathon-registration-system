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
import com.mycompany.yrusprint.helpers.CardLayoutManager;
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
            JPanel windowBody = mainWindow.getBody();
            CardLayoutManager cardLayoutManager = new CardLayoutManager(windowBody, mainWindow.getCardLayout());
            
            // Events
            EventsPanel eventsPanel = new EventsPanel();
            BibPanel bibPanel = new BibPanel(() -> cardLayoutManager.back());
            EventRegisterPanel registerPanel = new EventRegisterPanel(
                () -> cardLayoutManager.back(),
                (r) -> {
                    cardLayoutManager.reset();
                    bibPanel.setRegistration(r);
                    cardLayoutManager.show(bibPanel);
                }
            );
            EventDetails eventDetails = new EventDetails(() -> cardLayoutManager.back(), (event) -> {cardLayoutManager.show(registerPanel); registerPanel.setEvent(event);});
            for (RaceEvent event : eventManager.getEvents()) {
                EventPreviewTemplate eventPreview = new EventPreviewTemplate(
                    event,
                    () -> {cardLayoutManager.show(eventDetails); eventDetails.setEvent(event); },
                    () -> {cardLayoutManager.show(registerPanel); registerPanel.setEvent(event);}
                );
                eventsPanel.placeEventPreview(eventPreview);
            }
            
            eventsButton.addActionListener(e -> cardLayoutManager.show(eventsPanel));

            // Search
            SearchPanel searchPanel = new SearchPanel();
            searchButton.addActionListener(e -> cardLayoutManager.show(searchPanel));
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
                        cardLayoutManager.show(bibPanel);
                        bibPanel.setRegistration(r);
                    });
                    searchResults.add(template);
                    ResultsPanel.add(template);
                }

                ResultsPanel.revalidate();
                ResultsPanel.repaint();
            });

            cardLayoutManager.add(eventsPanel, "EVENT_PANEL");
            cardLayoutManager.add(searchPanel, "SEARCH_PANEL");
            cardLayoutManager.add(bibPanel, "BIB_PREVIEW");
            cardLayoutManager.add(eventDetails, "EVENT_DETAILS");
            cardLayoutManager.add(registerPanel, "REGISTER_PANEL");

            mainWindow.setVisible(true);
        });
    }
}
