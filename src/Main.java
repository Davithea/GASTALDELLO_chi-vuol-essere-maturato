public class Main {
    public static void main(String[] args) {
        ApiClient apiClient = new ApiClient();
        String response = apiClient.fetchQuestions(5, "multiple", "easy");
        System.out.println(response);
    }
}
