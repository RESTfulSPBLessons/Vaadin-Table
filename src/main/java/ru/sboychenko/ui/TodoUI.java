package ru.sboychenko.ui;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.data.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sboychenko.dao.Person;
import ru.sboychenko.dao.ReadRSS;
import ru.sboychenko.dao.Todo;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by SBoichenko on 22.03.2017.
 */
@SpringUI
public class TodoUI extends UI {

    private VerticalLayout root;

    ReadRSS readRSS = new ReadRSS();

 /*   List<Person> people = Arrays.asList(
            new Person("Nic"),
            new Person("Nic1"));*/

    List<Person> people  = new ArrayList<>();



    Grid<Person> grid = new Grid<>();

    @Autowired
    private TodoListLayout todoLayout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        addHeader();
      //  addForm();
     //   addTodoList();
        addTable();
      //  addDeleteButton();
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

    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("80%");

        TextField task = new TextField();
        Button add = new Button("");
        add.addStyleName(ValoTheme.BUTTON_PRIMARY);
        add.setIcon(VaadinIcons.PLUS);

        formLayout.addComponentsAndExpand(task);
        formLayout.addComponents(add);

        add.addClickListener(click -> {
           todoLayout.add(new Todo(task.getValue()));
           task.clear();
           task.focus();
        });

        task.focus();
        add.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        root.addComponent(formLayout);
    }

    private void addTodoList() {
        todoLayout.setWidth("80%");
        root.addComponent(todoLayout);
    }
    private void addTable() {

        people.add(new Person());


        VerticalLayout tableLayout = new VerticalLayout();
        //tableLayout.setWidthUndefined();
        tableLayout.setWidth("98%");


    //    tableLayout.setMargin(true);


        grid.setItems(people);
        grid.setWidth("100%");
        grid.setHeight("500px");


        grid.addColumn(Person::getName).setCaption("Name");

        tableLayout.addComponent(grid);

//        tableLayout.setHeight("89%");

        root.addComponent(tableLayout);
    }


    private void addDeleteButton() {
        root.addComponent(new Button("Delete completed", click -> {
            if (todoLayout.countCompleted() == 0) {
                Notification.show("No completed task", Notification.Type.HUMANIZED_MESSAGE);
            }
            todoLayout.deleteCompleted();
        }));
    }
    private void updateButton() {


        Button updbtn = new Button("REFRESH");
        updbtn.addStyleName(ValoTheme.BUTTON_PRIMARY);
        updbtn.setWidth("40%");
        updbtn.setIcon(VaadinIcons.REFRESH);

     //   formLayout.addComponentsAndExpand(task);
      //  formLayout.addComponents(add);
        root.addComponent(updbtn);

        updbtn.addClickListener(click -> {

            /*List<Person> people = Arrays.asList(
                    new Person());*/

            //   people.addAll(readRSS.jaxbparse());
            for (Person tttt : readRSS.jaxbparse()) {

                people.add(tttt);

            }

            System.out.println("---------------------закончено 2222 -----------------------");
            //  readRSS.jaxbparse();
            //todoLayout.init();
            grid.setItems(people);

        });

      /*  root.addComponent(new Button("Update", click -> {



         //   people.addAll(readRSS.jaxbparse());
            for (Person tttt : readRSS.jaxbparse()) {

                people.add(tttt);

            }

            System.out.println("---------------------закончено 2222 -----------------------");
          //  readRSS.jaxbparse();
            //todoLayout.init();
            grid.setItems(people);

        }));
*/
    }
}
