package PullFactory;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import poetry.Author;
import poetry.Poetry;
import poetry.Type;

/**
 * Created by zhangshijie on 2016/7/28.
 */
public class XpullFactory {



    public List<Poetry> getlist(InputStream in ) {
        List<Poetry> list = null;
        Poetry poetry = null;

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(in, "utf-8");

            int type = parser.getEventType();

            while (type != XmlPullParser.END_DOCUMENT) {

                switch (type) {

                    case XmlPullParser.START_DOCUMENT: {

                        list = new ArrayList<Poetry>();
                    }
                    break;
                    case XmlPullParser.START_TAG: {

                        String tagName = parser.getName();

                        if (tagName.equals("node")) {

                            poetry = new Poetry();

                        } else if (tagName.equals("title")) {
                            String s = parser.nextText();
                            poetry.setTitle(s);

                        } else if (tagName.equals("auth")) {
                            String s = parser.nextText();
                            poetry.setAuth(s);

                        } else if (tagName.equals("type")) {
                            String s = parser.nextText();
                            poetry.setType(s);

                        } else if (tagName.equals("content")) {
                            String s = parser.nextText();
                            poetry.setContent(s);

                        } else if (tagName.equals("desc")) {
                            String s = parser.nextText();
                            poetry.setDesc(s);
                        }

                    }
                    break;
                    case XmlPullParser.END_TAG: {
                        String parserName = parser.getName();
                        if (parserName.equals("node")) {
                            list.add(poetry);
                            poetry=null;
                        }
                    }
                    break;
                }

                type = parser.next();
            }


        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }


    public List<Author> getlist_auth(InputStream in ) {

        List<Author> list = null;
        Author author = null;

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(in, "utf-8");

            int type = parser.getEventType();

            while (type != XmlPullParser.END_DOCUMENT) {

                switch (type) {
                    case XmlPullParser.START_DOCUMENT: {
                        list = new ArrayList<Author>();
                    }
                    break;
                    case XmlPullParser.START_TAG: {

                        String tagName = parser.getName();
                        if (tagName.equals("node")) {
                            author = new Author();
                        } else if (tagName.equals("auth")) {
                            String s = parser.nextText();
                            author.setAuthorName(s);
                        }

                    }
                    break;

                    case XmlPullParser.END_TAG: {

                        String parserName = parser.getName();
                        if (parserName.equals("node")) {
                            list.add(author);
                            author=null;
                        }

                    }
                    break;
                }

                type = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  list;
    }

    public List<Type> getlist_type(InputStream in ) {
        List<Type> list = null;
        Type typename = null;

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(in, "utf-8");


            int type = parser.getEventType();

            while (type != XmlPullParser.END_DOCUMENT) {

                switch (type) {

                    case XmlPullParser.START_DOCUMENT: {

                        list = new ArrayList<Type>();

                    }
                    break;
                    case XmlPullParser.START_TAG: {

                        String tagName = parser.getName();

                        if (tagName.equals("node")) {

                            typename = new Type();

                        } else if (tagName.equals("type")) {

                            String s = parser.nextText();
                            typename.setTypeName(s);
                        }

                    }
                    break;

                    case XmlPullParser.END_TAG: {

                        String parserName = parser.getName();
                        if (parserName.equals("node")) {

                            list.add(typename);
                            typename=null;
                        }

                    }
                    break;

                }

                type = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  list;
    }




}
