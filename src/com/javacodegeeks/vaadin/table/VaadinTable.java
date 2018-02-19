package com.javacodegeeks.vaadin.table;

import java.util.Iterator;
import java.util.Random;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Title("My UI")
@Theme("valo")
public class VaadinTable extends UI implements Table.ColumnGenerator
{
  private static final long serialVersionUID = 1L;

  private Random rnd;
  private String[] races = {"Human", "Dwarf", "Elf", "Orc", "Halfling", "Durglor"};
  private String[] myClasses = {"Paladin", "Warrior", "Archer", "Wizard", "Cleric", "Thief"};
  private ClickListener clickListenerRemoveButton;
  private ClickListener clickListenerAddButton;
  private ValueChangeListener valueChangeListenrTable;
  private Table characters;
  private Button buttonRemove;
  private Button buttonAdd;
  private VerticalLayout verticalLayoutMain;
  private HorizontalLayout horizontalLayoutButtons;
  
  @Override
  protected void init(VaadinRequest request)
  {
    rnd = new Random();
    setUpEventHandling();
    setUpGui();
  }
  
  private void setUpGui()
  {
    buttonRemove = new Button("Remove", clickListenerRemoveButton);
    buttonAdd = new Button("Add", clickListenerAddButton);
    
    horizontalLayoutButtons = new HorizontalLayout();
    horizontalLayoutButtons.addComponent(buttonAdd);
    horizontalLayoutButtons.addComponent(buttonRemove);
    
    characters = new Table("Rising Swang Party");
    characters.addContainerProperty("Race", String.class, null);
    characters.addContainerProperty("Class", String.class, null);
    characters.addContainerProperty("Level", Integer.class, null);
    characters.addContainerProperty("Type", Label.class, null);
    characters.addValueChangeListener(valueChangeListenrTable);
    
    for(int i = 0; i < 5 ; i++)
    {
      generateCharacter();
    }
    
    characters.setPageLength(characters.size());
    characters.setSelectable(true);
    characters.setImmediate(true);
    characters.setFooterVisible(true);
    characters.setColumnFooter("Race", null);
    characters.setColumnFooter("Class", "Sum of party levels");
    
    verticalLayoutMain = new VerticalLayout();
    setContent(verticalLayoutMain);
    verticalLayoutMain.addComponent(horizontalLayoutButtons);
    verticalLayoutMain.addComponent(characters);

    characters.setColumnFooter("Level", String.valueOf(calcSum()));
  }
  
  private void setUpEventHandling()
  {
    clickListenerRemoveButton = new Button.ClickListener()
    {
      
      private static final long serialVersionUID = 1L;

      @Override
      public void buttonClick(ClickEvent event)
      {
        Object selected = characters.getValue();
        if(selected == null)
        {
          Notification.show("You must select an item");
        }
        else
        {
          Notification.show("Value : " + selected);
          characters.removeItem(selected);
          characters.setColumnFooter("Level", String.valueOf(calcSum()));
        }
      }
    };
    
    clickListenerAddButton = new Button.ClickListener()
    {
      private static final long serialVersionUID = 1L;

      @Override
      public void buttonClick(ClickEvent event)
      {
        generateCharacter();
        Notification.show("Added a row");
        characters.setColumnFooter("Level", String.valueOf(calcSum()));
      }
    };
    
    valueChangeListenrTable = new ValueChangeListener()
    {
      private static final long serialVersionUID = 1L;

      @Override
      public void valueChange(ValueChangeEvent event)
      {
        
        Notification.show("Selected item : " + characters.getValue());
      }
    };
    
  }
  
  private void generateCharacter()
  {
    Object newItemId = characters.addItem();
    Item row1 = characters.getItem(newItemId);
    row1.getItemProperty("Race").setValue(getRace());
    row1.getItemProperty("Class").setValue(getMyClass());
    row1.getItemProperty("Level").setValue(getLevel());
    row1.getItemProperty("Type").setValue(generateCell(characters, newItemId, "Level"));
  }
  
  private int calcSum()
  {
    int sum=0;
    
    for(Iterator<?> i = characters.getItemIds().iterator(); i.hasNext();)
    {
      int cID = (Integer) i.next();
      Item item = characters.getItem(cID);
      int level = (int) item.getItemProperty("Level").getValue();
      sum += level;
    }
    
    return sum;
  }
  
  
  private String getRace()
  {
    int iRace = rnd.nextInt(races.length);
    return races[iRace];
  }
  
  private String getMyClass()
  {
    int iClass = rnd.nextInt(myClasses.length);
    return myClasses[iClass];
  }
  
  private int getLevel()
  {
    return rnd.nextInt(19)+1;
  }

  @Override
  public Component generateCell(Table source, Object itemId, Object columnId)
  {
    Property prop = source.getItem(itemId).getItemProperty(columnId);
    if(prop.getType().equals(Integer.class) && prop != null)
    {
      int val = (int)prop.getValue();
      Label customLabel = new Label();
      if(val < 10)
      {
        customLabel.setValue("bad");
      }
      if(val >= 10 && val < 15)
      {
        customLabel.setValue("medium");
      }
      if(val >= 15 && val < 18)
      {
        customLabel.setValue("good");
      }
      if(val >= 18)
      {
        customLabel.setValue("best");
      }
      return customLabel;
    }
    return null;
  }

}
