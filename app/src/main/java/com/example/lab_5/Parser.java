package com.example.lab_5;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Parser {
    public static ArrayList<String> getRates(InputStream stream) throws IOException, XmlPullParserException {
        ArrayList<String> rates = new ArrayList<>();
        String singleRate = null;

        try {
            XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myParser = xmlFactoryObject.newPullParser();
            myParser.setInput(stream, null);

            int event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT){
                String name = myParser.getName();
                switch (event){
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        if(name.equals("item")){
                            singleRate = myParser.getAttributeValue(null,"title" );
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        rates.add(singleRate);
                }
                event = myParser.next();
            }
        }
        catch (XmlPullParserException e){
            e.printStackTrace();
        }
        return rates;
    }
}
