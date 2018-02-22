package ru.sboychenko.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sboychenko.dao.Person;
import ru.sboychenko.dao.ReadRSS;

import java.util.ArrayList;
import java.util.List;

@SpringUI
public class TodoUI extends UI {

    ReadRSS readRSS = new ReadRSS();
    List<Person> people = new ArrayList<>();
    Grid<Person> grid = new Grid<>();
    private VerticalLayout root;

    @Autowired
    private TodoListLayout todoLayout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        addHeader();
        addTable();
        updateButton();
    }

    private void setupLayout() {
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(root);
    }

    private void addHeader() {
        Label header = new Label("XML NEWS");
        header.addStyleName(ValoTheme.LABEL_H1);
        root.addComponent(header);
    }

    private void addTable() {

        update();
        VerticalLayout tableLayout = new VerticalLayout();
        tableLayout.setWidth("98%");
        grid.setItems(people);
        grid.setWidth("100%");
        grid.setHeight("500px");
        grid.addColumn(Person::getName).setCaption("Name");
        tableLayout.addComponent(grid);
        root.addComponent(tableLayout);
    }


    private void update() {

        people.clear();
        for (Person tttt : readRSS.jaxbparse()) {
            people.add(tttt);
        }
    }

    private void updateButton() {

        Button updbtn = new Button("REFRESH");
        updbtn.addStyleName(ValoTheme.BUTTON_PRIMARY);
        updbtn.setWidth("40%");
        updbtn.setIcon(VaadinIcons.REFRESH);
        root.addComponent(updbtn);

        updbtn.addClickListener(click -> {
            update();
            grid.setItems(people);
        });
    }
}
