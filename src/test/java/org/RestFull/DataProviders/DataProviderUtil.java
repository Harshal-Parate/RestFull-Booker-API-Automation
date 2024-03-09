package org.RestFull.DataProviders;

public class DataProviderUtil {

    public static Object[][] dataProviderCombiner(Object[][] payloadData, Object[][] responseData) {
        int length = Math.min(payloadData.length, responseData.length);

        // Combine payloadData and responseData into a single array
        Object[][] combinedData = new Object[length][2];
        for (int i = 0; i < length; i++) {
            combinedData[i][0] = payloadData[i][0]; // Payload
            combinedData[i][1] = responseData[i][0]; // Expected response
        }
        return combinedData;
    }
}