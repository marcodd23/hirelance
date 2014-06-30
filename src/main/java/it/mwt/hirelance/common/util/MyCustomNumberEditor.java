package it.mwt.hirelance.common.util;

import java.text.NumberFormat;

import org.springframework.beans.propertyeditors.CustomNumberEditor;

public class MyCustomNumberEditor extends CustomNumberEditor{

    public MyCustomNumberEditor(Class<? extends Number> numberClass, NumberFormat numberFormat, boolean allowEmpty) throws IllegalArgumentException {
        super(numberClass, numberFormat, allowEmpty);
    }

    public MyCustomNumberEditor(Class<? extends Number> numberClass, boolean allowEmpty) throws IllegalArgumentException {
        super(numberClass, allowEmpty);
    }

    @Override
    public String getAsText() {
        //return super.getAsText();
        return "Your desired text";
    }

    @Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub
		super.setValue(value);
	}

	@Override
    public void setAsText(String text) throws IllegalArgumentException {
        super.setAsText("set your desired text");
    }

}