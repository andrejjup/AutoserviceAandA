package lv.autoservice.businesslogic.calendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class CalendarEditor {
    private static final String APPLICATION_NAME = "AutoService";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final String USER_ID = "photogallerybootcamp@gmail.com";

    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws Exception {
        InputStream in = CalendarEditor.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize(USER_ID);
    }

    public static boolean isPhotoServiceReserved(String userDateTime) throws Exception {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME).build();

        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list(USER_ID).setTimeMin(now).setOrderBy("startTime").setSingleEvents(true).execute();
        List<Event> items = events.getItems();

        String userDateTime1= userDateTime.replace(' ','T');

        if (!items.isEmpty()) {
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                System.out.printf("%s (%s)\n", event.getSummary(), start);
                System.out.println(String.valueOf(start) + "\n");
                String reservedDateTime = String.valueOf(start).substring(0, 16);
                if (reservedDateTime.equals(userDateTime1)) {
                    return false;
                }
            }
        }
        Event eventWrite = new Event().setSummary("This time is booked!");

        DateTime startDateTime = new DateTime(userDateTime1 + ":00+02:00");
        EventDateTime startWrite = new EventDateTime().setDateTime(startDateTime).setTimeZone("Europe/Riga");
        eventWrite.setStart(startWrite);

        char[] userDateTimeArray= new char[16];
        userDateTimeArray= userDateTime1.toCharArray();
        userDateTimeArray[12]++;
        String userDateTime2= String.valueOf(userDateTimeArray);

        DateTime endDateTime = new DateTime(userDateTime2 + ":00+02:00");
        EventDateTime end = new EventDateTime().setDateTime(endDateTime).setTimeZone("Europe/Riga");
        eventWrite.setEnd(end);
        service.events().insert(USER_ID, eventWrite).execute();
        return true;
    }
}

