package org.RestFull.Modules.FileReaders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.RestFull.FilePaths.CreateBookingFilePaths;
import org.RestFull.Pojos.RequestPOJO.CreateBooking.CreateBookingRoot;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReadJsonFiles {

    private ReadJsonFiles() {}


    /*
    This method read JSON file in array input --> multiple payload to be stored in file and the return type will
    be the object 2D array, which can be passed to data provider
     */
    public static Object[][] provideBookingData() throws IOException {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<CreateBookingRoot>>() {}.getType();
        List<CreateBookingRoot> bookings = gson.fromJson(new FileReader(CreateBookingFilePaths.getValidCreateBookingPayload()), listType);
        Object[][] data = new Object[bookings.size()][1];
        for (int i = 0; i < bookings.size(); i++) {
            data[i][0] = bookings.get(i);
        }
        return data;
    }



}
