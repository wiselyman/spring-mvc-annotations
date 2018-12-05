package top.wisely.springmvcannotations;


import java.beans.PropertyEditorSupport;

public class DogPropertiesEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] array = text.split("-");
        Long id = new Long(array[0]);
        String name = array[1];
        setValue(new Dog(id , name));
    }

}
