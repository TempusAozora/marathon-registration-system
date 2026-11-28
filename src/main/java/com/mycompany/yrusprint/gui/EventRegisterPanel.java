/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.yrusprint.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.mycompany.yrusprint.helpers.AgeValidator;
import com.mycompany.yrusprint.helpers.BlankValidator;
import com.mycompany.yrusprint.helpers.ContactNumberValidator;
import com.mycompany.yrusprint.helpers.DataService;
import com.mycompany.yrusprint.helpers.EventManager;
import com.mycompany.yrusprint.helpers.Validator;
import com.mycompany.yrusprint.models.RaceCategory;
import com.mycompany.yrusprint.models.RaceEvent;
import com.mycompany.yrusprint.models.Registration;
import com.mycompany.yrusprint.models.ValidationResult;

/**
 *
 * @author Jangie
 */
public class EventRegisterPanel extends javax.swing.JPanel {
    private RaceEvent currentEvent;

    /**
     * Creates new form EventRegisterPanel
     */
    public EventRegisterPanel(Runnable backButtonFunction, Consumer<Registration> ProceedToBibPanel) {
        initComponents();

        backButton.addActionListener((e) -> backButtonFunction.run());  
         registerButton.addActionListener((e) -> {
            Validator blankValidator = new BlankValidator();
            Validator ageValidator = new AgeValidator();
            Validator contactNumberValidator = new ContactNumberValidator();

            blankValidator.setNext(ageValidator).setNext(contactNumberValidator);

            Map<String, String> entry = getFormValues(mainPanel);

            entry.put("eventId", String.valueOf(currentEvent.getId()));

            ValidationResult result = blankValidator.validate(entry);

            if (result.valid()) {

                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                String otherFormatBirthDate = LocalDate.parse(entry.get("birthDate"), inputFormatter).format(outputFormatter);

                Map<String, Object> name = new HashMap<>();
                name.put("lastName", entry.get("lastName"));
                name.put("firstName", entry.get("firstName"));
                name.put("middleName", entry.get("middleName"));
                
                Map<String, Object> runner = new HashMap<>();
                runner.put("name", name);
                runner.put("gender", entry.get("gender"));
                runner.put("contactNumber", entry.get("contactNumber"));
                runner.put("birthDate", otherFormatBirthDate);
                runner.put("singletSize", entry.get("singletSize"));

                Map<String, Object> entryFormat = new HashMap<>();

                entryFormat.put(
                    "bibId",
                    "E" + currentEvent.getId() + "-REG-" + generateRegistrationNumber(entry.get("raceCategory"))
                );

                entryFormat.put("runner", runner);
                entryFormat.put("date", LocalDate.now().format(outputFormatter));
                entryFormat.put("eventId", entry.get("eventId"));
                entryFormat.put("raceCategory", entry.get("raceCategory"));

                DataService.insert("data/registration_entries.json", entryFormat);
                EventManager.getInstance().fetchEvents();

                ProceedToBibPanel.accept(new Registration(entryFormat));
            } else {
                errorMessage.setText(result.message());
            }});
    }

    private String generateRegistrationNumber(String raceCategory) {
        int registrationCount = currentEvent.getRaceCategoryByName(raceCategory).getRegisteredSlots();

        return String.format("%03d", registrationCount+1);
    }

    private Map<String, String> getFormValues(JPanel panel) {
        Map<String, String> values = new HashMap<>();

        for (Component component : panel.getComponents()) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;

                values.put(textField.getName(), textField.getText());

            } else if (component instanceof JComboBox) {
                JComboBox<?> comboBox = (JComboBox<?>) component;

                Object selected = comboBox.getSelectedItem();

                values.put(
                    comboBox.getName(),
                    selected != null ? selected.toString() : ""
                );
            }

            if (component instanceof JPanel) {
                values.putAll(getFormValues((JPanel) component));
            }
        }

        return values;
    }

    public void setEvent(RaceEvent event) {
        eventName.setText(event.getName());
        currentEvent = event;
        raceCategory.setModel(new DefaultComboBoxModel<>());
        List<RaceCategory> raceCategories = event.getRaceCategories();
        for (RaceCategory r : raceCategories) {
            raceCategory.addItem(r.getCategoryName());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        eventName = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        registerButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        firstName = new javax.swing.JTextField();
        gender = new javax.swing.JComboBox<>();
        contactNumber = new javax.swing.JTextField();
        birthDate = new javax.swing.JTextField();
        singletSize = new javax.swing.JComboBox<>();
        raceCategory = new javax.swing.JComboBox<>();
        middleName = new javax.swing.JTextField();
        lastName = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        errorMessage = new javax.swing.JLabel();

        setName("Form"); // NOI18N

        mainPanel.setBackground(new java.awt.Color(18, 35, 52));
        mainPanel.setName("mainPanel"); // NOI18N

        eventName.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        eventName.setForeground(new java.awt.Color(139, 206, 251));
        eventName.setText("Event Registration");
        eventName.setName("eventName"); // NOI18N

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setName("jSeparator2"); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Run. Challenge. Be part of the Marathon.");
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(242, 242, 242));
        jLabel11.setText("Name: ");
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(242, 242, 242));
        jLabel13.setText("Gender: ");
        jLabel13.setName("jLabel13"); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(242, 242, 242));
        jLabel14.setText("Contact Number:");
        jLabel14.setName("jLabel14"); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(139, 206, 251));
        jLabel16.setText("RUNNER INFORMATION");
        jLabel16.setName("jLabel16"); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(242, 242, 242));
        jLabel17.setText("Birth Date:");
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(242, 242, 242));
        jLabel18.setText("Singlet Size:");
        jLabel18.setName("jLabel18"); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(139, 206, 251));
        jLabel19.setText("RACE CATEGORY");
        jLabel19.setName("jLabel19"); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(242, 242, 242));
        jLabel21.setText("Select Category: ");
        jLabel21.setName("jLabel21"); // NOI18N

        registerButton.setBackground(new java.awt.Color(139, 206, 251));
        registerButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        registerButton.setForeground(new java.awt.Color(18, 35, 52));
        registerButton.setText("REGISTER");
        registerButton.setName("registerButton"); // NOI18N
        registerButton.addActionListener(this::registerButtonActionPerformed);

        backButton.setBackground(new java.awt.Color(139, 206, 251));
        backButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        backButton.setForeground(new java.awt.Color(18, 35, 52));
        backButton.setText("BACK");
        backButton.setName("backButton"); // NOI18N
        backButton.addActionListener(this::backButtonActionPerformed);

        firstName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        firstName.setForeground(new java.awt.Color(102, 102, 102));
        firstName.setText("First name");
        firstName.setName("firstName"); // NOI18N
        firstName.addActionListener(this::firstNameActionPerformed);

        gender.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        gender.setForeground(new java.awt.Color(102, 102, 102));
        gender.setMaximumRowCount(4);
        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "His", "Her", " " }));
        gender.setName("gender"); // NOI18N

        contactNumber.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        contactNumber.setForeground(new java.awt.Color(102, 102, 102));
        contactNumber.setText("Enter your contact number");
        contactNumber.setName("contactNumber"); // NOI18N

        birthDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        birthDate.setForeground(new java.awt.Color(102, 102, 102));
        birthDate.setText("MM/DD/YYYY");
        birthDate.setName("birthDate"); // NOI18N

        singletSize.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        singletSize.setForeground(new java.awt.Color(102, 102, 102));
        singletSize.setMaximumRowCount(5);
        singletSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "XS", "S", "M", "L", "XL", "2XL" }));
        singletSize.setName("singletSize"); // NOI18N

        raceCategory.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        raceCategory.setForeground(new java.awt.Color(102, 102, 102));
        raceCategory.setMaximumRowCount(4);
        raceCategory.setName("raceCategory"); // NOI18N
        raceCategory.addActionListener(this::raceCategoryActionPerformed);

        middleName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        middleName.setForeground(new java.awt.Color(102, 102, 102));
        middleName.setText("Middle name");
        middleName.setName("middleName"); // NOI18N

        lastName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lastName.setForeground(new java.awt.Color(102, 102, 102));
        lastName.setText("Last name");
        lastName.setName("lastName"); // NOI18N
        lastName.addActionListener(this::lastNameActionPerformed);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(242, 242, 242));
        jLabel12.setText("*");
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(242, 242, 242));
        jLabel15.setText("*");
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(242, 242, 242));
        jLabel20.setText("*");
        jLabel20.setName("jLabel20"); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(242, 242, 242));
        jLabel22.setText("*");
        jLabel22.setName("jLabel22"); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(242, 242, 242));
        jLabel23.setText("*");
        jLabel23.setName("jLabel23"); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(242, 242, 242));
        jLabel24.setText("*");
        jLabel24.setName("jLabel24"); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(242, 242, 242));
        jLabel25.setText("*");
        jLabel25.setName("jLabel25"); // NOI18N

        errorMessage.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errorMessage.setForeground(new java.awt.Color(255, 153, 153));
        errorMessage.setText(" ");
        errorMessage.setName("errorMessage"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel13)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel18))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(gender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(contactNumber)
                                            .addComponent(singletSize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(birthDate)
                                            .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(middleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lastName, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jLabel16)
                                    .addComponent(errorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addGap(18, 18, 18)
                                        .addComponent(raceCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 53, Short.MAX_VALUE))))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jLabel10))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(eventName)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(eventName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(errorMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(firstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(middleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel20)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(contactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel23)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(birthDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)))
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(singletSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(28, 28, 28)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(raceCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(registerButton))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registerButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backButtonActionPerformed

    private void firstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameActionPerformed

    private void lastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameActionPerformed

    private void raceCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_raceCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_raceCategoryActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JTextField birthDate;
    private javax.swing.JTextField contactNumber;
    private javax.swing.JLabel errorMessage;
    private javax.swing.JLabel eventName;
    private javax.swing.JTextField firstName;
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField lastName;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField middleName;
    private javax.swing.JComboBox<String> raceCategory;
    private javax.swing.JButton registerButton;
    private javax.swing.JComboBox<String> singletSize;
    // End of variables declaration//GEN-END:variables
}
