package io.github.funkynoodles.projectcirkt;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Louis on 9/16/2015.
 */
public class HandleXML {

    /**
     *String variables setup
     */
    private String subjectID;
    private String subjectName;
    private String courseID;
    private String courseName;
    private String description;

    public String getSubjectID() {
        return subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDescription() {
        return description;
    }

    public String getSectionNumber() {
        return sectionNumber;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getSectionNotes() {
        return sectionNotes;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public String getMeetingStart() {
        return meetingStart;
    }

    public String getMeetingEnd() {
        return meetingEnd;
    }

    public String getDaysOfTheWeek() {
        return daysOfTheWeek;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public ArrayList<String> getInstructors() {
        return instructors;
    }

    public String getUrlString() {
        return urlString;
    }

    private String sectionNumber;
    private String statusCode;
    private String sectionNotes;
    private String startDate;
    private String endDate;
    private String meetingType;
    private String meetingStart;
    private String meetingEnd;
    private String daysOfTheWeek;
    private String roomNumber;
    private String buildingName;
    private ArrayList<String> instructors = new ArrayList<String>();

    private XmlPullParserFactory xmlFactoryObject;

    private String urlString;

    public volatile boolean parsingComplete = true;

    public HandleXML(String url){
        urlString = url;
    }

    public void parseXMLAndStoreIt(XmlPullParser myParser){
        int event;
        String text = null;

        try{
            event = myParser.getEventType();
            while(event != XmlPullParser.END_DOCUMENT){
                String name = myParser.getName();

                switch(event){
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(name.equals("subject")){
                            subjectID = myParser.getAttributeValue("http://rest.cis.illinois.edu","id");
                            subjectName = text;
                        }else if(name.equals("course")){
                            courseID = myParser.getAttributeValue("http://rest.cis.illinois.edu", "id");
                            courseName = text;
                        }else if(name.equals("sectionNumber")){
                            sectionNumber = text;
                        }else if(name.equals("statusCode")){
                            statusCode = text;
                        }else if(name.equals("sectionNotes")){
                            sectionNotes = text;
                        }else if(name.equals("startDate")){
                            startDate = text;
                        }else if(name.equals("endDate")){
                            endDate = text;
                        }else if(name.equals("type")){
                            meetingType = text;
                        }else if(name.equals("start")){
                            meetingStart = text;
                        }else if(name.equals("end")){
                            meetingEnd = text;
                        }else if(name.equals("daysOfTheWeek")){
                            daysOfTheWeek = text;
                        }else if(name.equals("roomNumber")){
                            roomNumber = text;
                        }else if(name.equals("buildingName")){
                            buildingName = text;
                        }else if(name.equals("instructor")){
                            instructors.add(text);
                        }
                        break;
                }
                event = myParser.next();
            }
            parsingComplete = false;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("There were errors retrieving the information");
        }
    }

    public void fetchXML(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream stream = conn.getInputStream();
                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myParser = xmlFactoryObject.newPullParser();

                    myParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myParser.setInput(stream, null);

                    parseXMLAndStoreIt(myParser);
                    stream.close();
                }catch(Exception e){
                    e.printStackTrace();
                    System.out.println("There were errors connecting to XML files");
                }
            }
        });
        thread.start();
    }
}
