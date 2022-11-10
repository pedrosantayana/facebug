package service;

public class TranslatorService {
//     private static String key = "ba6dc642de85489d9c2aa998dad58710";

//     // location, also known as region.
//    // required if you're using a multi-service or regional (not global) resource. It can be found in the Azure portal on the Keys and Endpoint page.
//     private static String location = "eastus";


//     // Instantiates the OkHttpClient.
//     OkHttpClient client = new OkHttpClient();

//     // This function performs a POST request.
//     public String Post() throws IOException {
//         MediaType mediaType = MediaType.parse("application/json");
//         RequestBody body = RequestBody.create(mediaType,
//                 "[{\"Text\": \"I would really like to drive your car around the block a few times!\"}]");
//         Request request = new Request.Builder()
//                 .url("https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=en&to=fr&to=zu")
//                 .post(body)
//                 .addHeader("Ocp-Apim-Subscription-Key", key)
//                 // location required if you're using a multi-service or regional (not global) resource. 
//                 .addHeader("Ocp-Apim-Subscription-Region", location) 
//                 .addHeader("Content-type", "application/json")
//                 .build();
//         Response response = client.newCall(request).execute();
//         return response.body().string();
//     }

//     // This function prettifies the json response.
//     public static String prettify(String json_text) {
//         JsonParser parser = new JsonParser();
//         JsonElement json = parser.parse(json_text);
//         Gson gson = new GsonBuilder().setPrettyPrinting().create();
//         return gson.toJson(json);
//     }

//     public static void main(String[] args) {
//         try {
//             TranslatorText translateRequest = new TranslatorText();
//             String response = translateRequest.Post();
//             System.out.println(prettify(response));
//         } catch (Exception e) {
//             System.out.println(e);
//         }
//     }
}